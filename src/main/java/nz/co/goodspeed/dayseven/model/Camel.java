package nz.co.goodspeed.dayseven.model;

public class Camel {
    Hand hand;
    int value;

    int order = 0;

    public Camel(String hand, int value) {
        this.hand = new Hand(hand);
        this.value = value;
    }

    public HandType getHandType() {
        return this.hand.getHandType();
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public Hand getHand() {
        return hand;
    }

    public int getValue() {
        return value;
    }
}
