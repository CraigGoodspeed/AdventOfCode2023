package nz.co.goodspeed.day16.Model;

import java.util.Objects;

public class Coordinates {
    int x,y;
    Direction direction;
    public Coordinates(int x, int y, Direction direction) {
        this.x = x;
        this.y = y;
        this.direction = direction;
    }


    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Direction getDirection() {
        return direction;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Coordinates that = (Coordinates) o;
        return x == that.x && y == that.y && direction == that.direction;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y, direction);
    }

    public Coordinates moveBy(int x, int y, Direction direction) {
        return new Coordinates((getX() + x), (getY() + y), direction);
    }
}
