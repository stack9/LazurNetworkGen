package it.stack9.lazurnetworkgen.game;

import it.stack9.lazurnetworkgen.LazurNetworkGen;
import it.stack9.lazurnetworkgen.event.GeneratorFullEvent;
import it.stack9.lazurnetworkgen.util.Builder;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.scheduler.BukkitScheduler;

import java.util.UUID;

public class Generator {

    private static final int GENERATION_DELAY = 60;
    public static int LAST_ID = 0;

    private final int id;
    private final Location origin;
    private final UUID ownerId;
    private final String color;
    private int level;
    private int taskId;

    public Generator(int id, Location origin, UUID ownerId, String color, int level) {
        this.id = id;
        this.origin = origin;
        this.ownerId = ownerId;
        this.color = color;
        this.level = level;
    }

    public void update() {
        // Draw structure frame
        Builder.buildFilledSquare(origin, 10, Material.BLACK_WOOL);
        for (int i = 1; i <= 14; i++) {
            Material material = Material.AIR;

            if (i <= level) {
                material = Material.getMaterial(color + "_WOOL");
            }
            Builder.buildFilledSquare(origin.clone().add(2, i, 2), 6, material);
            // Frame
            Builder.buildSquare(origin.clone().add(1, i, 1), 8, Material.getMaterial(color + "_STAINED_GLASS"));
        }
        Builder.buildFilledSquare(origin.clone().add(0, 15, 0), 10, Material.BLACK_WOOL);
    }

    public void levelUp() {
        // FIXME: this code is temporary
        if (level == 13) {
            Bukkit.getServer().getScheduler().cancelTask(taskId);
        }
        this.level++;
        this.update();
        if (level == 14) {
            Bukkit.getPluginManager().callEvent(new GeneratorFullEvent(this));
        }
    }

    public void start(LazurNetworkGen plugin) {
        BukkitScheduler scheduler = Bukkit.getServer().getScheduler();
        this.taskId = scheduler.scheduleSyncRepeatingTask(plugin, () -> plugin.getGenerator(id).levelUp(), GENERATION_DELAY, GENERATION_DELAY);
    }

    public void delete() {
        for (int i = 0; i <= 16; i++) {
            Builder.buildFilledSquare(origin.clone().add(0, i, 0), 10, Material.AIR);
        }
        Bukkit.getServer().getScheduler().cancelTask(taskId);
    }

    public void setTaskId(int taskId) {
        this.taskId = taskId;
    }

    public int getTaskId() {
        return taskId;
    }

    public int getId() {
        return id;
    }

    public Location getOrigin() {
        return origin;
    }

    public UUID getOwnerId() {
        return ownerId;
    }

    public int getLevel() {
        return level;
    }

    public String getColor() {
        return color;
    }

    public static ItemStack createItem(String color) {
        Material itemMaterial = Material.getMaterial(color.toUpperCase() + "_WOOL");
        if (itemMaterial == null) {
            return null;
        }
        ItemStack item = new ItemStack(itemMaterial);
        ItemMeta meta = item.getItemMeta();
        if (meta != null) {
            GeneratorColors genColor = GeneratorColors.valueOf(color.toUpperCase());
            meta.getPersistentDataContainer().set(LazurNetworkGen.GENERATORS_KEY, PersistentDataType.INTEGER, genColor.ordinal());
            meta.setDisplayName("§r" + genColor.getColorCode() + "Generatore");
            // Set enchanted
            item.addUnsafeEnchantment(Enchantment.LURE, 1);
            meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
            item.setItemMeta(meta);
        }
        return item;
    }

    public static Generator create(LazurNetworkGen plugin, UUID ownerId, Location origin, String color) {
        final int genId = LAST_ID++;
        final Generator gen = new Generator(genId, origin, ownerId, color, 0);
        gen.update();
        gen.start(plugin);
        return gen;
    }
}
