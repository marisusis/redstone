package com.altarisnine.networkcore.bukkit.listeners;

import com.altarisnine.networkcore.api.entity.Entity;
import com.altarisnine.networkcore.api.entity.living.player.Player;
import com.altarisnine.networkcore.api.event.Cancellable;
import com.altarisnine.networkcore.api.event.Event;
import com.altarisnine.networkcore.api.event.player.PlayerMoveEvent;
import com.altarisnine.networkcore.api.inventory.InventoryView;
import com.altarisnine.networkcore.api.text.Text;
import com.altarisnine.networkcore.bukkit.Converter;
import com.altarisnine.networkcore.bukkit.inventory.BukkitInventoryView;
import com.altarisnine.networkcore.bukkit.user.BukkitUser;
import com.altarisnine.networkcore.bukkit.world.block.BukkitBlock;
import com.altarisnine.networkcore.common.NetworkCore;
import com.altarisnine.networkcore.common.listeners.CoreListener;
import org.apache.commons.lang3.ArrayUtils;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.server.TabCompleteEvent;

import java.util.List;
import java.util.regex.Pattern;

public class BukkitListener extends CoreListener implements Listener {

    private static final Pattern WHITESPACE_PATTERN = Pattern.compile(" ( *)");

    public BukkitListener(NetworkCore instance) {
        super(instance);
    }

    @EventHandler
    public void onPlayerChat(AsyncPlayerChatEvent event) {
        boolean cancelled = super.onPlayerChat(new BukkitUser(event.getPlayer()), event.getMessage());
        event.setCancelled(cancelled);
    }

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        Player player = core.getPlayerManager().getPlayer(event.getPlayer().getUniqueId());

        if (event.getAction() == Action.LEFT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_AIR) {
            Cancellable c = (Cancellable) super.onPlayerInteract(player, Converter.action(event.getAction()), null);
            event.setCancelled(c.isCancelled());
        } else {
            Cancellable c = (Cancellable) super.onPlayerInteract(player, Converter.action(event.getAction()), new BukkitBlock(event.getClickedBlock()));
            event.setCancelled(c.isCancelled());
        }
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        event.setJoinMessage(Converter.parse(Text.of(String.format("&7%s &3joined the game.", event.getPlayer().getDisplayName()))).toLegacyText());
        super.onPlayerJoin(new BukkitUser(event.getPlayer()));
    }

    @EventHandler
    public void onPlayerLeave(PlayerQuitEvent event) {
        event.setQuitMessage(Converter.parse(Text.of(String.format("&7%s &3left the game.", event.getPlayer().getDisplayName()))).toLegacyText());
        super.onPlayerLeave(new BukkitUser(event.getPlayer()));
    }

    @EventHandler
    public void onPlayerMove(org.bukkit.event.player.PlayerMoveEvent event) {
        PlayerMoveEvent e = (PlayerMoveEvent) super.onPlayerMove(core.getPlayerManager().getPlayer(event.getPlayer().getUniqueId()), Converter.location(event.getTo()));

        // Sets the location the player should be moved to
        event.setTo(Converter.location(e.getTo()));
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        if (event.getWhoClicked() instanceof org.bukkit.entity.Player) {
            org.bukkit.entity.Player player = (org.bukkit.entity.Player) event.getWhoClicked();

            InventoryView view = new BukkitInventoryView(core, event.getView());

            Event e = super.onInventoryClick(core.getPlayerManager().getPlayer(player.getUniqueId()), view, event.getSlot(), event.getRawSlot());
            Cancellable c = (Cancellable) e;
            event.setCancelled(c.isCancelled());
        }
    }

    @EventHandler
    public void onEntityDamage(EntityDamageEvent event) {
        org.bukkit.entity.Entity entity = event.getEntity();
        Entity e = Converter.entity(entity);

        super.onEntityDamage(e, event.getDamage(), Converter.cause(event.getCause()));
    }

    @EventHandler
    public void onInventoryClose(InventoryCloseEvent event) {
        Player player = core.getPlayerManager().getPlayer(event.getPlayer().getUniqueId());
        super.onInventoryClose(player, new BukkitInventoryView(core, event.getView()));
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onTabComplete(TabCompleteEvent event) {
        // Get current input
        String buffer = event.getBuffer();

        CommandSender sender = event.getSender();

        // Make sure it's a command and has text after the /
        if (!(sender instanceof ConsoleCommandSender)) {
            buffer = buffer.substring(1);
        }

        // Split into parts
        final String[] parts = buffer.replaceAll(" ( *)", " ").split(" ");

        List<String> completions;

        if (buffer.endsWith(" ")) {
            completions = core.getCommandManager().getCompletions(ArrayUtils.add(parts, " "), (sender instanceof CommandSender));
        } else {
            completions = core.getCommandManager().getCompletions(parts, (sender instanceof CommandSender));
        }

        event.setCompletions(completions);
    }
}
