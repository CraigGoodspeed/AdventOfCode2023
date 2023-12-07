package nz.co.goodspeed.dayseven.model;

public enum HandType {

    FiveOfAKind(7),
    FourOfAKind(6),
    FullHouse(5),
    ThreeOfAKind(4),
    TwoPair(3),
    Pair(2),
    HighCard(1);

    final int index;

    HandType(int index) {
        this.index = index;
    }

    public int getIndex() {
        return index;
    }


}
