package nz.co.goodspeed.daytwo.model;

public class Item {
    Integer value;
    Color type;

    public Item(String toParse) {
        String[] numberColor = toParse.split("\\s");
        value = Integer.valueOf(numberColor[0]);
        type = Color.valueOf(numberColor[1].toUpperCase());
    }

    public Integer getValue() {
        return value;
    }

    public Color getType() {
        return type;
    }
}
