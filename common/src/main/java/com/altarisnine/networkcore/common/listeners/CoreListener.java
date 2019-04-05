package com.altarisnine.networkcore.common.listeners;

import com.altarisnine.networkcore.api.Core;
import com.altarisnine.networkcore.api.block.Block;
import com.altarisnine.networkcore.api.entity.Entity;
import com.altarisnine.networkcore.api.entity.living.player.Player;
import com.altarisnine.networkcore.api.event.Event;
import com.altarisnine.networkcore.api.event.block.Action;
import com.altarisnine.networkcore.api.event.entity.DamageCause;
import com.altarisnine.networkcore.api.event.entity.EntityDamageEvent;
import com.altarisnine.networkcore.api.event.inventory.InventoryCloseEvent;
import com.altarisnine.networkcore.api.event.inventory.interact.InventoryClickEvent;
import com.altarisnine.networkcore.api.event.player.PlayerInteractEvent;
import com.altarisnine.networkcore.api.event.player.PlayerJoinEvent;
import com.altarisnine.networkcore.api.event.player.PlayerMoveEvent;
import com.altarisnine.networkcore.api.guard.Guard;
import com.altarisnine.networkcore.api.guard.region.ResizableRegion;
import com.altarisnine.networkcore.api.guard.session.Session;
import com.altarisnine.networkcore.api.guard.spatial.selection.Selector;
import com.altarisnine.networkcore.api.guard.spatial.vector.VectorI3;
import com.altarisnine.networkcore.api.gui.GUI;
import com.altarisnine.networkcore.api.inventory.InventoryView;
import com.altarisnine.networkcore.api.text.Text;
import com.altarisnine.networkcore.api.world.Location;
import com.altarisnine.networkcore.api.world.World;
import com.altarisnine.networkcore.common.NetworkCore;
import com.altarisnine.networkcore.common.gui.GUIManager;
import com.altarisnine.networkcore.common.user.User;
import com.altarisnine.networkcore.common.util.ServerType;

import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

public class CoreListener {
    protected NetworkCore core;

    public CoreListener(NetworkCore instance) {
        this.core = instance;
    }

    public final void onPlayerJoin(User player) {
        core.getNetworkScheduler().runAsync(() -> {
            final UUID uuid = player.getUniqueId();
            core.getLogger().info("Player with UUID:" + uuid.toString() + " joined.");

            // Load player
            core.getPlayerManager().loadPlayer(uuid);

            // Create player guard session
            if (core.getType().equals(ServerType.INSTANCE)) core.getSessionManager().createSession(core.getPlayerManager().getPlayer(uuid));

            // Add player to online players
            core.addOnlinePlayer(player.getName(), player.getUniqueId());

            // Send player join event to other handlers asynchronously
            core.getEventManager().callEvent(new PlayerJoinEvent(Core.getApi().getPlayer(uuid)));

            if (core.getType().equals(ServerType.INSTANCE)) {
                Player plr = core.getPlayerManager().getPlayer(player.getUniqueId());
                plr.setListName(Text.of(plr.getDisplayName()));
            }
        });
    }

    // TODO player leave event
    public final void onPlayerLeave(User player) {
        final String name = player.getName();
        final UUID uuid = player.getUniqueId();

        core.getNetworkScheduler().getEventsExecutor().execute(() -> {
            core.getLogger().info("Player with UUID:" + uuid.toString() + " left.");


            if (core.getType().equals(ServerType.INSTANCE)) {
                // Player left, unload guard session
                core.getSessionManager().unloadSession(uuid);
            } else if (core.getType().equals(ServerType.PROXY)) {
                // Update last seen time for player
                core.getPlayerManager().getOfflinePlayer(name).updateLastActive();
            }

            // TODO player leave event for external handlers?

            // Remove player from online players
            // TODO may need to bake name and uuid to prevent null pointer
            core.removeOnlinePlayer(name);

            // Unload player from player manager
            core.getPlayerManager().unloadPlayer(uuid);


        });

    }

    public final Event onPlayerInteract(final Player who, final Action action, final Block blockClicked) {
        PlayerInteractEvent event = new PlayerInteractEvent(who, action, blockClicked);

        // Handle building
        Session session = core.getSessionManager().getSession(who.getUniqueId());

        World world = who.getLocation().getWorld();

        // MUST redo how wand works, make it more like Grief Prevention
        // right click once to choose first point, again to choose second
        // same thing with resizing
        // have visualizations for regions

        // When wand is used on a block other than air
        if (who.getItemInHand().equals(Guard.RESIZE_WAND_ITEM) && blockClicked != null) {
            event.setCancelled(true);
            if (action.equals(Action.LEFT_CLICK_BLOCK)) {

                // Get the clicked block's location
                Location loc = blockClicked.getLocation();

                if (!session.isResizing()) {
                    // Get regions at that point
                    Set<ResizableRegion> matchedRegions = core.getGuard().getApplicableRegions(loc.getWorld(), loc.toVector()).stream()
                            .filter(r -> r instanceof ResizableRegion).map(r -> (ResizableRegion) r).collect(Collectors.toSet());

                    Optional<ResizableRegion> region = Optional.empty();

                    for (ResizableRegion match : matchedRegions) {
                        if (match.isCornerPoint(loc.toBlockVector().toBlockVector2())) {
                            region = Optional.of(match);
                            break;
                        }
                    }

                    if (!region.isPresent()) {
                        who.sendMessage(Text.of("&cThat is not the corner of a resizable region!"));
                        return event;
                    }

                    // Check if the session can resize
                    if (!region.get().canResize(session)) {
                        who.sendMessage(Text.of("&cYou are not permitted to resize this region!"));
                        return event;
                    }

                    session.setLastResizePoint(loc.toBlockVector());
                    session.setResizing(true);
                } else {
                    if (session.getLastResizePoint() != null && session.getLastResizeRegion() != null) {
                        session.getLastResizeRegion().attemptResize(session.getLastResizePoint().toBlockVector2(), loc.toBlockVector().toBlockVector2());
                        session.setResizing(false);
                        session.setLastResizePoint(null);
                        session.setLastResizeRegion(null);
                    }
                }


            }
        }




        // Handle wand usage
        if (who.getItemInHand().equals(Guard.SELECT_WAND_ITEM) && blockClicked != null) {
            // Get players block vector location
            VectorI3 playerVector = who.getLocation().toBlockVector();

            // Get the player's selector
            Selector selector = who.getSelector();

            synchronized (selector) {

                // Get worlds to compare
                World blockWorld = blockClicked.getLocation().getWorld();

                if (selector.getWorld() == null) selector.setWorld(blockWorld);

                World selectedWorld = selector.getWorld();

                // Check if the selection's world has changed
                if (!blockWorld.equals(selectedWorld)) {
                    // Different world, clear current selection
                    selector.clear();
                    selector.setWorld(blockWorld);
                }

                VectorI3 vector = blockClicked.getLocation().toBlockVector();

                // Check the type of action
                if (action.equals(Action.LEFT_CLICK_BLOCK)) {
                    // Select the primary point
                    selector.selectPrimary(vector);

                    // Send feedback message
                    who.sendMessage(Text.of("&6&lPoint A &6set to (%s, %s, %s)", vector.getX(), vector.getY(), vector.getZ()));
                } else if (action.equals(Action.RIGHT_CLICK_BLOCK)) {
                    // Select the secondary point
                    selector.selectSecondary(vector);

                    // Send feedback message
                    who.sendMessage(Text.of("&6&lPoint B &6set to (%s, %s, %s)", vector.getX(), vector.getY(), vector.getZ()));
                }

            }
             event.setCancelled(true);
        }

        if (action.equals(Action.LEFT_CLICK_BLOCK) || action.equals(Action.RIGHT_CLICK_BLOCK)) {
            if (!session.canBuildAt(blockClicked, who.getItemInHand())) {
                who.sendMessage(Text.of("&4You are not allowed to build here!"));

                // Cancel building event
                event.setCancelled(true);
            }
        }

        core.getEventManager().callEvent(event);
        return event;
    }

    public Event onPlayerMove(final Player who, final Location to) {
        PlayerMoveEvent event =  new PlayerMoveEvent(who, to);
        Session session = Core.getApi().getSession(who.getUniqueId());
        // TODO use queue for async operation

        Location loc = session.handleMoveTo(to);
        if (loc != null) {
            // Change destination location
            event.setTo(loc);
            event.getPlayer().sendMessage(Text.of("&4You are not allowed to move there!"));
        }

        core.getEventManager().callEvent(event);
        return event;
    }
    
    // TODO switch away from boolean as a way of telling implementations to cancel event?
    // TODO switch common handler to new structure
    protected boolean onPlayerChat(User sender, String message) {
        Player player = core.getPlayerManager().getPlayer(sender.getUniqueId());

        // Make sure the entity isn't being handled by the proxy server
        if (core.getType() != ServerType.PROXY) {
            // Check if the player is muted
            if (player.isMuted()) {
                sender.sendMessage("&4You are muted!");
                return true;
            }

            // Log the chat message
            core.getLogger().info("&3[&aCHAT&3] " + player.getDisplayName() + "&7>> &f" + message);

            // Set the display name to be shown in the tablist // PERFORMANCE does this need to happen this often?
            player.setListName(Text.of(player.getDisplayName()));

            // Send all online players the chat message
            // TODO player ignore system
            core.getOnlineUsers().forEach(user -> Core.getApi().getPlayer(user.getUniqueId()).sendMessage(Text.of(player.getDisplayName() + " &7>> &f" + message)));

            return true;
        } else {
            return false;
        }

    }

    public Event onInventoryClick(final Player who, final InventoryView view, final int slot, final int rawSlot) {
        InventoryClickEvent event = new InventoryClickEvent(view, slot);

        // For debugging purposes
        // TEMP who.sendMessage(Text.of("&cSlot: " + slot + " &bRawSlot: " + rawSlot + " &eViewSize: " + view.slots() + " &aTopSize: " + view.getTopInventory().getSize() + " &6BottomSize: " + view.getBottomInventory().getSize()));

        // Get GUI manager
        final GUIManager guiManager = core.getGuiManager();

        // Check if the player has a GUI open
        if (guiManager.hasOpenGUI(who)) {
            // Cancel event, the GUI is open
            event.setCancelled(true);

            // Get the open GUI
            GUI gui = guiManager.getOpenGUI(who);

            // Check if clicked item slot is in the GUI
            if (gui.inGUI(rawSlot)) {
                // Check if there is an item in the slot
                if (gui.isFilledSlot(slot)) {
                    // TODO should gui handle this, or event method here
                    if (gui.isActiveSlot(slot)) gui.fireAction(who, slot);
                }
            }
        } else {
            // Only dispatch event if it doesn't conflict with the GUI system
            core.getEventManager().callEvent(event);
        }

        return event;
    }

    public Event onEntityDamage(final Entity who, final double damage, final DamageCause cause) {
        Event event = new EntityDamageEvent(who, damage, cause);
        core.getEventManager().callEvent(event);
        return event;
    }

    public Event onInventoryClose(final Player who, final InventoryView view) {
        InventoryCloseEvent event = new InventoryCloseEvent(view);

        // Get the GUI manager
        final GUIManager guiManager = core.getGuiManager();

        // Check if player has a GUI open
        if (guiManager.hasOpenGUI(who)) {
            // Remove from open gui list
            guiManager.closeGUI(who);
        } else {
            // Only dispatch event if it doesn't conflict with the GUI system
            core.getEventManager().callEvent(event);
        }

        return event;
    }
}
