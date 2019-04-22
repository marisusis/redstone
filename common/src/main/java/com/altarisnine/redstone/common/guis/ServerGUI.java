package com.altarisnine.redstone.common.guis;

import com.altarisnine.redstone.api.block.Material;
import com.altarisnine.redstone.api.gui.GUI;
import com.altarisnine.redstone.api.inventory.Item;
import com.altarisnine.redstone.api.text.Text;

import java.util.Collections;

public class ServerGUI extends GUI {

    private static final Item FACTIONS = new Item(new Material("minecraft", "iron_sword"), 1);
    private static final Item ARCADE = new Item(new Material("minecraft", "slime_ball"), 1);
    private static final Item CREATIVE = new Item(new Material("minecraft", "golden_shovel"), 1);
    static {
        FACTIONS.setName(Text.of("&aFactions &2(Online)"));
        FACTIONS.setLore(Collections.singletonList(Text.of("&7&oClick to join")));

        ARCADE.setName(Text.of("&cArcade &4(Offline)"));
        ARCADE.setLore(Collections.singletonList(Text.of("&7&oUnable to join")));

        CREATIVE.setName(Text.of("&cCreative &4(Offline)"));
        CREATIVE.setLore(Collections.singletonList(Text.of("&7&oUnable to join")));
    }
    /**
     * Instantiates a new GUI.
     *
     */
    public ServerGUI() {
        super(4);

        this.title = Text.of("&9Server Selector");

        setItem(11, FACTIONS);
        setAction(11, (clicker, clicked) -> {
            clicker.sendToServer("factions");
        });

        setItem(13, ARCADE);
        setAction(13, (clicker, clicked) -> {
            clicker.sendMessage(Text.of("&cServer offline"));
            clicker.closeInventory();
        });

        setItem(15, CREATIVE);
        setAction(15, (clicker, clicked) -> {
            clicker.sendMessage(Text.of("&cServer offline"));
            clicker.closeInventory();
        });
    }


}
