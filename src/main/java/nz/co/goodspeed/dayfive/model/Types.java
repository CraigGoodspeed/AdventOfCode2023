package nz.co.goodspeed.dayfive.model;

public enum Types {
    SEED(0),
    SOIL(1),
    FERTILIZER(2),
    WATER(3),
    LIGHT(4),
    TEMPERATURE(5),
    HUMIDITY(6),
    LOCATION(7);

    private final int index;

    Types(int index) {
        this.index = index;
    }

    public int getIndex() {
        return index;
    }

    public Types getNext() {
        switch (this) {
            case SEED -> {
                return SOIL;
            }
            case SOIL -> {
                return FERTILIZER;
            }
            case FERTILIZER -> {
                return WATER;
            }
            case WATER -> {
                return LIGHT;
            }
            case LIGHT -> {
                return TEMPERATURE;
            }
            case TEMPERATURE -> {
                return HUMIDITY;
            }
            case HUMIDITY -> {
                return LOCATION;
            }
            default-> {
                return null;
            }
        }
    }
}
