package it.stack9.lazurnetworkgen.game;

@SuppressWarnings("DeprecatedIsStillUsed")
public enum GeneratorColors {

    /**
     * Price: 50€
     * Profit: 5€
     */
    WHITE (0, 'f', "Bianco", 5),

    @Deprecated
    ORANGE (1, '6', "Arancio", 0),

    @Deprecated
    MAGENTA (2, '5', "Magenta", 0),

    /**
     * Price: 2 000€
     * Profit: 350€
     */
    LIGHT_BLUE (3, 'b', "Azzurro", 350),

    /**
     * Price: 5 000€
     * Profit: 500€
     */
    YELLOW (4, 'e', "Giallo", 500),

    /**
     * Price: 300€
     * Profit: 30€
     */
    LIME (5, 'a', "Verde", 30),

    /**
     * Price: 500€
     * Profit: 50€
     */
    PINK (6, 'd', "Rosa", 50),

    @Deprecated
    GRAY (7, '8', "Grigio", 0),

    /**
     * Price: 150
     * Profit: 5
     */
    LIGHT_GRAY (8, '7', "Grigio", 15),

    @Deprecated
    CYAN (9, '3', "Ciano", 0),

    /**
     * Price: 1 000€
     * Profit: 150€
     */
    PURPLE (10, '5', "Viola", 150),

    @Deprecated
    BLUE (11, '9', "Blue", 0),

    @Deprecated
    BROWN (12, '0', "Marrone", 0),

    @Deprecated
    GREEN (13, '2', "Verde", 0),

    /**
     * Price: 10 000€
     * Profit: 1 000€
     */
    RED (14, 'c', "Rosso", 1000),

    @Deprecated
    BLACK (15, '0', "Nero", 0);

    private final int id;
    private final String label;
    private final char code;
    private final int value;

    GeneratorColors(int id, char code, String label, int value) {
        this.id = id;
        this.code = code;
        this.label = label;
        this.value = value;
    }

    public int getId() {
        return id;
    }

    public String getCode() {
        return "§" + code;
    }

    public String getLabel() {
        return label;
    }

    public int getValue() {
        return value;
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
