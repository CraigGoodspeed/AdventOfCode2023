package nz.co.goodspeed.day16.Model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Map {

    public PointOnMap[][] map;

    public Map(String[] toConvert) {
        map = new PointOnMap[toConvert.length][toConvert[0].length()];
        for(int x= 0; x < map.length; x++) {
            for(int y = 0; y < map[x].length;y++) {
                map[x][y] = PointOnMap.parseCharacter(toConvert[x].charAt(y));
            }
        }
    }

    public int bottomOfMap() {
        return map.length;
    }

    public boolean isAtTheBottomOrOff(int x) {
        return x >= bottomOfMap();
    }

    public int rightOfMap() {
        return map[0].length;
    }

    public boolean isAtTheRightOrOff(int y) {
        return y >= rightOfMap();
    }

    public PointOnMap getPointOnMap(Coordinates location) {
        return this.map[location.getX()][location.getY()];
    }

    public boolean isOnMap(Coordinates location) {
        return
                location.getX() < this.map.length && location.getX() >= 0
                && location.getY() < this.map[0].length && location.getY() >= 0;
    }

    public PointOnMap[][] getMap() {
        return map;
    }
}
