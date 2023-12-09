package nz.co.goodspeed.dayeight.model;

public enum Direction {
    LEFT('L', 0),
    RIGHT('R',1);

    final char text;
    final int index;

    Direction(char text, int index) {
        this.text = text;
        this.index = index;
    }

    public char getText() {
        return text;
    }

    public int getIndex() {
        return index;
    }

    public boolean checkText(char check) {
        return this.getText() == check;
    }
}
