package nz.co.goodspeed.daythree.model;

public class LineItem {

    char me;
    public LineItem(char me) {
        this.me = me;
    }

    public boolean isInt(){
        return (int)me >= 48 && (int)me <= 57;
    }

    public boolean isPeriod() {
        return this.me == '.';
    }
}
