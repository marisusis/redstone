package com.altarisnine.redstone.api.inventory;

import com.altarisnine.redstone.api.block.Material;
import com.altarisnine.redstone.api.text.Text;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Item {

    @Getter @Setter private Material material;
    @Getter @Setter private int amount;

    @Getter @Setter private Text name;
    @Getter @Setter private List<Text> lore;

    public Item(Material material) {
        this(material, 1);
    }

    public Item(Material material, int amount) {
        this.material = material;
        this.amount = amount;
        this.name = Text.of("Item Name Goes Here");
        this.lore = new ArrayList<>();
    }


    public void addLore(Text text) {
        lore.add(text);
    }

    public boolean equalsWithoutCount(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Item item = (Item) o;
        return amount == item.amount &&
                Objects.equals(material, item.material) &&
                Objects.equals(name, item.name);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Item item = (Item) o;
        return amount == item.amount &&
                Objects.equals(material, item.material) &&
                Objects.equals(name, item.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(material, amount, name);
    }

    @Override
    public String toString() {
        return "Item{" +
                "material=" + material +
                ", amount=" + amount +
                ", name=" + name.toString() +
                ", lore=" + lore +
                '}';
    }
}
