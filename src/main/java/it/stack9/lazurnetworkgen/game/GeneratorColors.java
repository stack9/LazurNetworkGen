package it.stack9.lazurnetworkgen.game;

public enum GeneratorColors {

    WHITE,
    ORANGE,
    MAGENTA,
    LIGHT_BLUE,
    YELLOW,
    LIME,
    PINK,
    GRAY,
    LIGHT_GRAY,
    CYAN,
    PURPLE,
    BLUE,
    BROWN,
    GREEN,
    RED,
    BLACK;

    public String getColorCode() {
        switch(this.ordinal()) {
            case 0:
                return "§f";
            case 1:
                return "§6";
            case 2:
            case 10:
                return "§5";
            case 3:
                return "§b";
            case 4:
                return "§e";
            case 5:
                return "§a";
            case 6:
                return "§d";
            case 7:
                return "§8";
            case 8:
                return "§7";
            case 9:
                return "§3";
            case 11:
                return "§9";
            case 12:
            case 15:
                return "§0";
            case 13:
                return "§2";
            case 14:
                return "§c";
            default:
                return null;
        }
    }

    public static GeneratorColors fromValue(int value) {
        switch (value) {
            case 0:
                return GeneratorColors.WHITE;
            case 1:
                return GeneratorColors.ORANGE;
            case 2:
                return GeneratorColors.MAGENTA;
            case 3:
                return GeneratorColors.LIGHT_BLUE;
            case 4:
                return GeneratorColors.YELLOW;
            case 5:
                return GeneratorColors.LIME;
            case 6:
                return GeneratorColors.PINK;
            case 7:
                return GeneratorColors.GRAY;
            case 8:
                return GeneratorColors.LIGHT_GRAY;
            case 9:
                return GeneratorColors.CYAN;
            case 10:
                return GeneratorColors.PURPLE;
            case 11:
                return GeneratorColors.BLUE;
            case 12:
                return GeneratorColors.BROWN;
            case 13:
                return GeneratorColors.GREEN;
            case 14:
                return GeneratorColors.RED;
            case 15:
                return GeneratorColors.BLACK;
            default:
                return null;
        }
    }
}
