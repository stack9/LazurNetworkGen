package it.stack9.lazurnetworkgen.util;

import org.bukkit.Location;
import org.bukkit.Material;

public class Builder {

    public static void buildFilledSquare(Location origin, int l, Material material) {
        for (int i = 0; i < l; i++) {
            for (int j = 0; j < l; j++) {
                origin.clone().add(i, 0, j).getBlock().setType(material);
            }
        }
    }

    public static void buildSquare(Location origin, int l, Material material) {
        for (int i = 0; i < l; i++) {
            for (int j = 0; j < l; j += (l - 1)) {
                origin.clone().add(i, 0, j).getBlock().setType(material);
                origin.clone().add(j, 0, i).getBlock().setType(material);
            }
        }
    }

    public static boolean isSpaceFree(Location origin, int l, int height) {
        Location loc;
        for (int k = 0; k < height; k++) {
            for (int i = 0; i < l; i++) {
                for (int j = 0; j < l; j++) {
                    loc = origin.clone().add(i, k, j);
                    if (
                            loc.getBlockX() != origin.getBlockX() &&
                            loc.getBlockY() != origin.getBlockY() &&
                            loc.getBlockZ() != origin.getBlockZ() &&
                            loc.getBlock().getType() != Material.AIR
                    ) {
                        return false;
                    }
                }
            }
        }
        return true;
    }
}
