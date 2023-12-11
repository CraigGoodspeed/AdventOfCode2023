package nz.co.goodspeed.dayeleven.model;

public class Point {

    int x,y;

    char item;

    public Point(int x, int y, char item) {
        this.x = x;
        this.y = y;
        this.item = item;
    }

    public int calculateDistance(Point destination) {
        return Math.abs(destination.x - this.x) + Math.abs(destination.y - this.y);
    }

    public char getItem() {
        return item;
    }
}
