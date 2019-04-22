package com.altarisnine.redstone.common.messaging;

import com.altarisnine.redstone.api.Redstone;
import com.altarisnine.redstone.api.entity.living.player.Player;
import com.altarisnine.redstone.common.RedstoneCore;
import com.altarisnine.redstone.common.util.ServerType;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPubSub;

import java.util.UUID;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class PubSubManager {
    private volatile RedstoneCore plugin;

    private final Jedis publishJedis;

    private final BlockingQueue<Message> messageQueue;

    public PubSubManager(RedstoneCore instance) {
        this.plugin = instance;
        PubSubListenerThread thread = new PubSubListenerThread();
        this.publishJedis = new Jedis("localhost");

        // create blocking queue
        this.messageQueue = new LinkedBlockingQueue<>();

        plugin.getNetworkScheduler().runAsyncTaskTimer(() -> {
            Message message = messageQueue.poll();

            if (message != null) {
                plugin.getNetworkScheduler().runSync(() -> {
                    handleMessage(message);
                });
            }
        }, 1);

        thread.start();
    }

    public void handleMessage(Message message) {
        if (plugin.getType().equals(ServerType.PROXY)) {
            try {
                String[] args = message.getContent().split(" ");
                if (message.getChannel().equalsIgnoreCase("control")) {
                    switch (args[0]) {
                        case "send":
                            String playerUUID = args[1];
                            String serverName = args[2];

                            Player player = plugin.getPlayerManager().getPlayer(UUID.fromString(playerUUID));
                            player.sendToServer(serverName.toLowerCase());
                            break;
                        default:
                            throw new UnsupportedOperationException("Bad message");
                    }
                }
            } catch (ArrayIndexOutOfBoundsException | UnsupportedOperationException e) {
                plugin.getLogger().error("Bad message sent through redis: <" + message.getChannel() + "> " + message);
            }
        } else {
            plugin.getLogger().info("Message: " + message);
        }

    }

    public Long publish(String channel, String message) {
        return publishJedis.publish(channel, message);
    }
    public Long publish(String channel, String... args) {
        StringBuilder builder = new StringBuilder();

        for (String arg : args) {
            builder.append(arg);
            builder.append(" ");
        }

        return publishJedis.publish(channel, builder.toString());
    }

    private class PubSubListenerThread extends Thread {
        private final Jedis jedis;

        public PubSubListenerThread() {
            super("Redis Listener Thread");
            this.jedis = new Jedis("localhost");
        }

        @Override
        public void run() {
            jedis.subscribe(new PubSubListener(this), "control");
        }

        public void onMessage(String channel, String content) {
            Redstone.getApi().getLogger().debug(String.format("%s: %s", channel, content));

            // Create message object
            Message message = new Message(channel, content);

            messageQueue.add(message);
        }
    }

    private class PubSubListener extends JedisPubSub {
        final PubSubListenerThread thread;

        public PubSubListener(PubSubListenerThread thread) {
            this.thread = thread;
        }

        @Override
        public void onMessage(String channel, String content) {
            thread.onMessage(channel, content);
        }
    }
}
