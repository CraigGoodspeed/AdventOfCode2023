package nz.co.goodspeed.day17.mode;

import nz.co.goodspeed.day16.Model.Coordinates;

public class Node {
    Node left;
    Node straight;
    Node right;
    Coordinates coordinates;
    int myHeat;

    Map map;

    public Node(Coordinates coordinates, Map map) {
        this.map = map;
        this.coordinates = coordinates;
        myHeat = map.getHeatLostOnThisBlock()[this.coordinates.getX()][this.coordinates.getY()];



        Coordinates horizontalMove = this.coordinates.moveBy(0,1);
        Coordinates verticalMove = this.coordinates.moveBy(1,0);
        if(isOnMap(horizontalMove))
            this.horizontal = new Node(horizontalMove, map);
        if(isOnMap(verticalMove))
            this.vertical = new Node(verticalMove, map);
    }

    public boolean isOnMap(Coordinates point) {
        return point.getX() >= 0 && point.getX() < map.getHeatLostOnThisBlock().length
                &&
             point.getY() >= 0 && point.getY() < map.getHeatLostOnThisBlock()[0].length;
    }

    public Node getHorizontal() {
        return horizontal;
    }

    public Node getVertical() {
        return vertical;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public int getMyHeat() {
        return myHeat;
    }

    public Map getMap() {
        return map;
    }
}
