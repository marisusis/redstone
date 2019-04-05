package com.altarisnine.networkcore.bukkit;

import com.altarisnine.networkcore.api.Core;
import com.altarisnine.networkcore.api.block.Material;
import com.altarisnine.networkcore.api.entity.Entity;
import com.altarisnine.networkcore.api.event.block.Action;
import com.altarisnine.networkcore.api.event.entity.DamageCause;
import com.altarisnine.networkcore.api.inventory.DroppedItem;
import com.altarisnine.networkcore.api.inventory.Item;
import com.altarisnine.networkcore.api.text.Text;
import com.altarisnine.networkcore.api.text.format.TextColor;
import com.altarisnine.networkcore.api.text.format.TextFormat;
import com.altarisnine.networkcore.api.text.format.TextStyle;
import com.altarisnine.networkcore.api.util.Vector;
import com.altarisnine.networkcore.api.world.Location;
import com.altarisnine.networkcore.bukkit.entity.BukkitEntity;
import com.altarisnine.networkcore.bukkit.entity.living.*;
import com.altarisnine.networkcore.bukkit.entity.living.ambient.BukkitBat;
import com.altarisnine.networkcore.bukkit.entity.living.creature.*;
import com.altarisnine.networkcore.bukkit.entity.living.creature.animal.*;
import com.altarisnine.networkcore.bukkit.entity.living.creature.golem.BukkitIronGolem;
import com.altarisnine.networkcore.bukkit.entity.living.creature.golem.BukkitShulker;
import com.altarisnine.networkcore.bukkit.entity.living.creature.golem.BukkitSnowman;
import com.altarisnine.networkcore.bukkit.entity.living.creature.monster.*;
import com.altarisnine.networkcore.bukkit.entity.living.creature.npc.BukkitVillager;
import com.altarisnine.networkcore.bukkit.entity.living.slime.BukkitMagmaCube;
import com.altarisnine.networkcore.bukkit.entity.living.water.BukkitSquid;
import com.altarisnine.networkcore.bukkit.inventory.BukkitDroppedItem;
import com.altarisnine.networkcore.bukkit.world.BukkitWorld;
import com.google.common.collect.BiMap;
import com.google.common.collect.ImmutableBiMap;
import io.sentry.Sentry;
import io.sentry.event.EventBuilder;
import io.sentry.event.interfaces.ExceptionInterface;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.entity.*;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public final class Converter {

    public static final BiMap<Class<? extends org.bukkit.entity.Entity>, Class<? extends Entity>> entityMap = new ImmutableBiMap.Builder<Class<? extends org.bukkit.entity.Entity>, Class<? extends Entity>>()
            .put(org.bukkit.entity.Entity.class, BukkitEntity.class)
            .put(Bat.class, BukkitBat.class)
            .put(Chicken.class, BukkitChicken.class)
            .put(Cow.class, BukkitCow.class)
            .put(MushroomCow.class, BukkitMushroomCow.class)
            .put(Ocelot.class, BukkitOcelot.class)
            .put(Parrot.class, BukkitParrot.class)
            .put(Pig.class, BukkitPig.class)
            .put(Rabbit.class, BukkitRabbit.class)
            .put(Sheep.class, BukkitSheep.class)
            .put(Wolf.class, BukkitWolf.class)
            .put(IronGolem.class, BukkitIronGolem.class)
            .put(Shulker.class, BukkitShulker.class)
            .put(Snowman.class, BukkitSnowman.class)
            .put(Blaze.class, BukkitBlaze.class)
            .put(CaveSpider.class, BukkitCaveSpider.class)
            .put(Creeper.class, BukkitCreeper.class)
            .put(ElderGuardian.class, BukkitElderGuardian.class)
            .put(Enderman.class, BukkitEnderman.class)
            .put(Endermite.class, BukkitEndermite.class)
            .put(Evoker.class, BukkitEvoker.class)
            .put(Giant.class, BukkitGiant.class)
            .put(Guardian.class, BukkitGuardian.class)
            .put(Husk.class, BukkitHusk.class)
            .put(Illager.class, BukkitIllager.class)
            .put(Illusioner.class, BukkitIllusioner.class)
            .put(PigZombie.class, BukkitPigZombie.class)
            .put(Silverfish.class, BukkitSilverfish.class)
            .put(Skeleton.class, BukkitSkeleton.class)
            .put(Spellcaster.class, BukkitSpellcaster.class)
            .put(Spider.class, BukkitSpider.class)
            .put(Stray.class, BukkitStray.class)
            .put(Vex.class, BukkitVex.class)
            .put(Vindicator.class, BukkitVindicator.class)
            .put(Witch.class, BukkitWitch.class)
            .put(Wither.class, BukkitWither.class)
            .put(WitherSkeleton.class, BukkitWitherSkeleton.class)
            .put(Zombie.class, BukkitZombie.class)
            .put(Villager.class, BukkitVillager.class)
            .put(Ageable.class, BukkitAgeable.class)
            .put(Animals.class, BukkitAnimal.class)
            .put(Golem.class, BukkitGolem.class)
            .put(Monster.class, BukkitMonster.class)
            .put(NPC.class, BukkitNPC.class)
            .put(MagmaCube.class, BukkitMagmaCube.class)
            .put(Squid.class, BukkitSquid.class)
            .put(Ambient.class, BukkitAmbient.class)
            .put(Creature.class, BukkitCreature.class)
            .put(LivingEntity.class, BukkitLivingEntity.class)
            .put(Slime.class, BukkitSlime.class)
            .put(WaterMob.class, BukkitWaterMob.class)
            .put(org.bukkit.entity.Item.class, DroppedItem.class).build();

    public static org.bukkit.Location location(Location location) {
        return new org.bukkit.Location(Bukkit.getWorld(location.getWorld().getName()), location.getX(),
                location.getY(),
                location.getZ(),
                location.getYaw(),
                location.getPitch());
    }

    public static Location location(org.bukkit.Location location) {
        return new Location(new BukkitWorld(location.getWorld()), location.getX(),
                location.getY(),
                location.getZ(),
                location.getYaw(),
                location.getPitch());
    }

    public static Material blockType(org.bukkit.Material material) {
        return new Material(material.getKey().getNamespace(), material.getKey().getKey());
    }

    public static org.bukkit.Material blockType(Material material) {
        return org.bukkit.Material.matchMaterial(material.getFullName());
    }

    public static Action action(org.bukkit.event.block.Action action) {
        return Action.valueOf(action.toString());
    }

    public static ItemStack item(Item item) {
        ItemStack stack = new ItemStack(blockType(item.getMaterial()), item.getAmount());
        ItemMeta meta = stack.getItemMeta();
        meta.setDisplayName(parse(item.getName()).toLegacyText());
        List<String> lore = new ArrayList<>();

        for (Text text : item.getLore()) {
            lore.add(parse(text).toLegacyText());
        }

        meta.setLore(lore);

        stack.setItemMeta(meta);

        return stack;
    }

    public static Item item(ItemStack itemStack) {
        Material material = blockType(itemStack.getType());
        Item item = new Item(material, itemStack.getAmount());

        Collection<Text> lore = new ArrayList<>();

        if (itemStack.getItemMeta() != null) {
            if (itemStack.getItemMeta().getLore() != null) {
                for (String string : itemStack.getItemMeta().getLore()) {
                    lore.add(Text.of(string));
                }
            }
            if (itemStack.getItemMeta().getDisplayName() != null) {
                item.setName(Text.of(itemStack.getItemMeta().getDisplayName().replace("§","&")));
            }
        }

        return item;
    }

    public static <E extends Entity> E getEntity(org.bukkit.entity.Entity entity) {
        if (entity instanceof Player) {
            return (E) Core.getApi().getPlayer(entity.getUniqueId());
        }

        try {
             Constructor<?> c = entityMap.get(entity.getClass()).getConstructors()[0];
             Core.getApi().getLogger().info(c.toGenericString());
                    return (E) c.newInstance(entity);
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
            Sentry.capture(new EventBuilder()
                    .withMessage("error converting entity type " + entity.getType().toString())
                    .withExtra("entityClass", entity.getClass().toGenericString())
                    .withSentryInterface(new ExceptionInterface(e)));
            return null;
        } catch (NullPointerException e) {
            Sentry.capture(new EventBuilder()
                    .withMessage("no entity map entry for entity type " + entity.getType().toString())
                    .withExtra("entityClass", entity.getClass().toGenericString())
                    .withSentryInterface(new ExceptionInterface(e)));
            return null;
        }
    }

    public static Class<? extends org.bukkit.entity.Entity> getEntityClass(Class<? extends Entity> clazz) {
        return entityMap.inverse().get(clazz);
    }

    // PERFORMANCE Use map for classes rather than a chain of instanceofs
    public static Entity entity(org.bukkit.entity.Entity entity) {
// TEMP Core.getApi().getLogger().info(entity.getClass().getTypeName());
        if (entity instanceof LivingEntity) {
            if (entity instanceof Creature) {
                if (entity instanceof Ageable) {
                    if (entity instanceof Animals) {
                        if (entity instanceof Chicken) return new BukkitChicken((Chicken) entity);
                        else if (entity instanceof Cow) {
                            if (entity instanceof MushroomCow) return new BukkitMushroomCow((MushroomCow) entity);
                            return new BukkitCow((Cow) entity);
                        } else if (entity instanceof Ocelot) return new BukkitOcelot((Ocelot) entity);
                        else if (entity instanceof Parrot) return new BukkitParrot((Parrot) entity);
                        else if (entity instanceof Pig) return new BukkitPig((Pig) entity);
                        else if (entity instanceof Rabbit) return new BukkitRabbit((Rabbit) entity);
                        else if (entity instanceof Sheep) return new BukkitSheep((Sheep) entity);
                        else if (entity instanceof Wolf) return new BukkitWolf((Wolf) entity);
                        return new BukkitAnimal((Animals) entity);
                    } else if (entity instanceof Villager) return new BukkitVillager((Villager) entity);
                    return new BukkitAgeable((Ageable) entity);
                } else if (entity instanceof Monster) {
                    if (entity instanceof Blaze) return new BukkitBlaze((Blaze) entity);
                    else if (entity instanceof Creeper) return new BukkitCreeper((Creeper) entity);
                    else if (entity instanceof Enderman) return new BukkitEnderman((Enderman) entity);
                    else if (entity instanceof Endermite) return new BukkitEndermite((Endermite) entity);
                    else if (entity instanceof Giant) return new BukkitGiant((Giant) entity);
                    else if (entity instanceof Guardian) {
                        if (entity instanceof ElderGuardian) return new BukkitElderGuardian((ElderGuardian) entity);
                        return new BukkitGuardian((Guardian) entity);
                    } else if (entity instanceof Illager) {
                        if (entity instanceof Spellcaster) {
                            if (entity instanceof Evoker) return new BukkitEvoker((Evoker) entity);
                            else if (entity instanceof Illusioner) return new BukkitIllusioner((Illusioner) entity);
                            return new BukkitSpellcaster((Spellcaster) entity);
                        } else if (entity instanceof Vindicator) return new BukkitVindicator((Vindicator) entity);
                        return new BukkitIllager((Illager) entity);
                    } else if (entity instanceof Silverfish) return new BukkitSilverfish((Silverfish) entity);
                    else if (entity instanceof Skeleton) {
                        if (entity instanceof Stray) return new BukkitStray((Stray) entity);
                        else if (entity instanceof WitherSkeleton)
                            return new BukkitWitherSkeleton((WitherSkeleton) entity);
                        return new BukkitSkeleton((Skeleton) entity);
                    } else if (entity instanceof Spider) {
                        if (entity instanceof CaveSpider) return new BukkitCaveSpider((CaveSpider) entity);
                        return new BukkitSpider((Spider) entity);
                    } else if (entity instanceof Vex) return new BukkitVex((Vex) entity);
                    else if (entity instanceof Witch) return new BukkitWitch((Witch) entity);
                    else if (entity instanceof Wither) return new BukkitWither((Wither) entity);
                    else if (entity instanceof Zombie) {
                        if (entity instanceof Husk) return new BukkitHusk((Husk) entity);
                        else if (entity instanceof PigZombie) return new BukkitPigZombie((PigZombie) entity);
                        else if (entity instanceof ZombieVillager)
                            return new BukkitZombieVillager((ZombieVillager) entity);
                        return new BukkitZombie((Zombie) entity);
                    }
                    return new BukkitMonster((Monster) entity);
                } else if (entity instanceof NPC) {
                    if (entity instanceof Villager) return new BukkitVillager((Villager) entity);
                    return new BukkitNPC((NPC) entity);
                }
                return new BukkitCreature((Creature) entity);
            } else if (entity instanceof Player) {
                return Core.getApi().getPlayer(((Player) entity).getUniqueId());
            } else if (entity instanceof Slime) {
                if (entity instanceof MagmaCube) return new BukkitMagmaCube((MagmaCube) entity);
                return new BukkitSlime((Slime) entity);
            } else if (entity instanceof WaterMob) {
                if (entity instanceof Squid) return new BukkitSquid((Squid) entity);
                return new BukkitWaterMob((WaterMob) entity);
            }
            return new BukkitLivingEntity((LivingEntity) entity);
        } else if (entity instanceof org.bukkit.entity.Item) {
            return new BukkitDroppedItem((org.bukkit.entity.Item) entity);
        }
        return new BukkitEntity(entity);
    }

    public static Item[] items(ItemStack[] items) {
        ItemStack[] i = items;
        List<Item> result = new ArrayList<>(items.length);

        for (ItemStack item : i) {
            result.add(Converter.item(item));
        }

        return result.toArray(new Item[items.length]);
    }

    public static ItemStack[] items(Item[] items) {
        Item[] i = items;
        List<ItemStack> result = new ArrayList<>(items.length);

        for (Item item : i) {
            result.add(Converter.item(item));
        }

        return result.toArray(new ItemStack[items.length]);
    }

    public static DamageCause cause(EntityDamageEvent.DamageCause cause) {
        return DamageCause.valueOf(cause.toString().toUpperCase());
    }



    public static ChatColor color(TextColor color) {
        return ChatColor.valueOf(color.getColor().toString());
    }

    public static TextComponent parse(Text text) {
        TextComponent message = new TextComponent(text.getContent());

        // Get the text format
        TextFormat format = text.getFormat();

        // Set the color of the text component
        message.setColor(color(text.getFormat().getColor()));

        // Set the text style
        TextStyle style = format.getStyle();
        message.setBold(style.isBold());
        message.setItalic(style.isItalic());
        message.setUnderlined(style.isUnderline());
        message.setStrikethrough(style.isStrikethrough());
        message.setObfuscated(style.isMagic());

        if (text.getChildren() != null) {
            for (Text child : text.getChildren()) {
                message.addExtra(parse(child));
            }
        }

        //TEMP message.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder("hello").color(ChatColor.LIGHT_PURPLE).create()));

        return message;
    }

    public static Vector vector(org.bukkit.util.Vector vector) {
        return new Vector(vector.getX(), vector.getY(), vector.getZ());
    }

    public static org.bukkit.util.Vector vector(Vector vector) {
        return new org.bukkit.util.Vector(vector.getX(), vector.getY(), vector.getZ());
    }

}
