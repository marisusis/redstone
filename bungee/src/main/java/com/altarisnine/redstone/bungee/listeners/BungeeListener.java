package com.altarisnine.redstone.bungee.listeners;

import com.altarisnine.redstone.api.util.Rank;
import com.altarisnine.redstone.bungee.user.BungeeUser;
import com.altarisnine.redstone.common.RedstoneCore;
import com.altarisnine.redstone.common.listeners.CoreListener;
import com.altarisnine.redstone.common.user.User;
import com.google.common.collect.ImmutableList;
import net.md_5.bungee.api.ServerPing;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.connection.Server;
import net.md_5.bungee.api.event.*;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

import java.util.List;
import java.util.stream.Collectors;

public class BungeeListener extends CoreListener implements Listener {

    private final List<String> DENY_PERMS = ImmutableList.<String>builder()
            .add("bungeecord.command.reload")
            .add("bungeecord.command.end")
            .add("bungeecord.command.send").build();

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
    public void onPermissionCheck(PermissionCheckEvent event) {
        if (DENY_PERMS.contains(event.getPermission())) {
            event.setHasPermission(false);
        } else {
            event.setHasPermission(true);
        }
        core.getLogger().error("Permission check for " + event.getPermission());
    }

                                  @EventHandler
    public void onMOTD(ProxyPingEvent event) {
        // Get the ServerPing response
        ServerPing ping = event.getResponse();

        // TODO move MOTD to api?
        // TODO do image stuff better



        List<ServerPing.PlayerInfo> info = core.getPlayerManager().getActivePlayers().values().stream().filter(p -> p.getRank().hasClearanceOf(Rank.TRAINEE)).map(p -> {
            return new ServerPing.PlayerInfo(p.getDisplayName().replace("&", "§"), "");
        }).collect(Collectors.toList());

        info.add(0, new ServerPing.PlayerInfo("§6Staff Online:", ""));

        ping.setPlayers(new ServerPing.Players(100, core.getOnlineUsers().size(), info.toArray(new ServerPing.PlayerInfo[info.size()])));

//        ServerPing.Protocol protocol = new ServerPing.Protocol("The Adventure Version", ping.getVersion().getProtocol() - 1);
//        ping.setVersion(protocol);
        ping.setDescriptionComponent(new TextComponent(TextComponent.fromLegacyText("§a§lAltaris9 §7- §6Factions §fopening soon!")));


    }
}
