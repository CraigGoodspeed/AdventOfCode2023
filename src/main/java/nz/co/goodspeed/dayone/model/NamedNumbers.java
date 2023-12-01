package nz.co.goodspeed.dayone.model;

public enum NamedNumbers {

    ONE("1"),
    TWO("2"),
    THREE("3"),
    FOUR("4"),
    FIVE("5"),
    SIX("6"),
    SEVEN("7"),
    EIGHT("8"),
    NINE("9");

    final String val;

    NamedNumbers(String number) {
        this.val = number;
    }

    public String getValue() {
        return val;
    }
}
