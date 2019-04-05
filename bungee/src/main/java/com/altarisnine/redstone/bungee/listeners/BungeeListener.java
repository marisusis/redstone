package com.altarisnine.redstone.bungee.listeners;

import com.altarisnine.redstone.bungee.user.BungeeUser;
import com.altarisnine.redstone.common.RedstoneCore;
import com.altarisnine.redstone.common.listeners.CoreListener;
import com.altarisnine.redstone.common.user.User;
import net.md_5.bungee.api.ServerPing;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.event.ChatEvent;
import net.md_5.bungee.api.event.PlayerDisconnectEvent;
import net.md_5.bungee.api.event.PostLoginEvent;
import net.md_5.bungee.api.event.ProxyPingEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

public class BungeeListener extends CoreListener implements Listener {
    public BungeeListener(RedstoneCore instance) {
        super(instance);
    }

    // TODO apply setDisplayName to custom display name for consistency in commands such as /glist
    @EventHandler
    public void onPlayerJoin(PostLoginEvent event) {
        super.onPlayerJoin(new BungeeUser(event.getPlayer()));
    }

    @EventHandler
    public void onPlayerLeave(PlayerDisconnectEvent event) {
        super.onPlayerLeave(new BungeeUser(event.getPlayer()));
    }

    @EventHandler
    public void onPlayerChat(ChatEvent event) {
        if (event.getSender() instanceof ProxiedPlayer) {
            if (!event.getMessage().startsWith("/")) {
                User user = new BungeeUser((ProxiedPlayer) event.getSender());
                boolean cancelled = super.onPlayerChat(user, event.getMessage());
                event.setCancelled(cancelled);
            }
        }
    }

    @EventHandler
    public void onMOTD(ProxyPingEvent event) {
        // Get the ServerPing response
        ServerPing ping = event.getResponse();

        // TODO move MOTD to api?
        // TODO do image stuff better

        ping.setPlayers(new ServerPing.Players(10, 4, new ServerPing.PlayerInfo[]{
                new ServerPing.PlayerInfo("§6Staff Online:", ""),
                new ServerPing.PlayerInfo("  §c[ADMIN] Ryde__", ""),
                new ServerPing.PlayerInfo("  §c[ADMIN] Kashall", "")
        }));

        ServerPing.Protocol protocol = new ServerPing.Protocol("The Adventure Version", ping.getVersion().getProtocol() - 1);
        ping.setVersion(protocol);
        ping.setDescriptionComponent(new TextComponent(TextComponent.fromLegacyText("§6Altaris9 §7- §6Coming soon...")));


    }
}
