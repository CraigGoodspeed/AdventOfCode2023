package nz.co.goodspeed.dayeleven.model;

public class Point {

    int x,y;

    char item;

    int galaxyLabel;

    public Point(int x, int y, char item, int galaxyLabel) {
        this.x = x;
        this.y = y;
        this.item = item;
        this.galaxyLabel = galaxyLabel;
    }

    public int calculateDistance(Point destination) {
        return Math.abs(destination.x - this.x) + Math.abs(destination.y - this.y);
    }

    public char getItem() {
        return item;
    }

    public int getGalaxyLabel() {
        return galaxyLabel;
    }
}
