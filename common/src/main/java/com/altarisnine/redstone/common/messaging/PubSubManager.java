package com.altarisnine.redstone.common.messaging;

import com.altarisnine.redstone.common.RedstoneCore;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPubSub;

public class PubSubManager {
    private RedstoneCore plugin;

    private final Jedis publishJedis;

    public PubSubManager(RedstoneCore instance) {
        this.plugin = instance;
        PubSubListenerThread thread = new PubSubListenerThread();
        this.publishJedis = new Jedis("localhost");
        thread.start();
    }

    public Long publish(String channel, String message) {
        return publishJedis.publish(channel, message);
    }

    private class PubSubListenerThread extends Thread {
        private final Jedis jedis;

        public PubSubListenerThread() {
            super("Redis Listener Thread");
            this.jedis = new Jedis("localhost");
        }

        @Override
        public void run() {
            jedis.subscribe(new PubSubListener(), "player");
        }
    }

    private class PubSubListener extends JedisPubSub {
        @Override
        public void onMessage(String channel, String message) {
            if (channel.equals("player")) {
                final String[] commands = message.split(" ");

                switch (commands[0]) {
                    case "ban":
                    case "unban":
                    case "kick":
                    case "mute":
                    case "unmute":
                        plugin.getLogger().success(String.format("Action %s on player %s.", commands[0], commands[1]));
                        break;
                    default:
                        plugin.getLogger().error("Invalid message received from channel player!");

                }
            }
        }
    }

}
