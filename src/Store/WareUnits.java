package Store;

import com.sun.source.tree.LiteralTree;

public enum WareUnits {
    PIECE("pcs."),
    MILLILITER("ml."),
    LITER("l."),
    GRAM("g."),
    KILOGRAM("kg."),
    UNDEFINED_UNIT("undefined");

    private String readableFormat;
    private WareUnits(String readableFormat)
    {
        this.readableFormat = readableFormat;
    }

    public static WareUnits fromString(String str)
    {
        switch (str.toLowerCase())
        {
            case "stk.":
            case "psc.":
                return PIECE;
            case "milliliter":
            case "ml.":
                return MILLILITER;
            case "liter":
            case "l.":
                return LITER;
            case "gram":
            case "g.":
                return GRAM;
            case "kilo":
            case "kg.":
            case "kilogram":
                return KILOGRAM;
            default:
                return UNDEFINED_UNIT;
        }
    }

    @Override
    public String toString()
    {
        return readableFormat;
    }
}
