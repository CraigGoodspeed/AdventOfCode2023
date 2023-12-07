package nz.co.goodspeed.dayseven.model;

import java.util.List;

public enum CardTypes {
    _2(1),
    _3(2),
    _4(3),
    _5(4),
    _6(5),
    _7(6),
    _8(7),
    _9(8),
    T(9),
    J(10),
    Q(11),
    K(12),
    A(13);

    final int value;
    CardTypes(int index) {
        this.value = index;
    }

    public int getValue() {
        return value;
    }

    public String getStringValue() {
        return this.name().replace("_","");
    }

    private static final List<String> specialChars = List.of("A","K","Q","J","T");
    public static CardTypes parse(String val) {

        if(specialChars.contains(val)) {
            return CardTypes.valueOf(val);
        }

        return CardTypes.valueOf(String.format("_%s",val));
    }

}
