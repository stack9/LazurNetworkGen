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

    public static void buildGenerator(Location origin) {
        buildFilledSquare(origin, 10, Material.BLACK_WOOL);
        for (int i = 1; i <= 14; i++) {
            buildSquare(origin.clone().add(1, i, 1), 8, Material.RED_STAINED_GLASS);
        }
        buildFilledSquare(origin.clone().add(0, 15, 0), 10, Material.BLACK_WOOL);
    }
}
