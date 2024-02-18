package nz.co.goodspeed.day17.mode;

import nz.co.goodspeed.day16.Model.Coordinates;
import nz.co.goodspeed.day16.Model.Direction;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class Node {
    private static Set<Node> ALL_ITEMS = new HashSet<>();
    Node left;
    Node straight;
    Node right;
    Coordinates coordinates;
    int myHeat;

    Node parent;

    Map map;

    public Node(Coordinates coordinates, Map map, Node parent) {
        this.map = map;
        this.coordinates = coordinates;
        myHeat = map.getHeatLostOnThisBlock()[this.coordinates.getX()][this.coordinates.getY()];
        this.parent = parent;
        if(!alreadyAdded()) {
            ALL_ITEMS.add(this);
            Coordinates[] leftStraightRight = calculateMoveBy(coordinates.getDirection());
            if (isOnMap(leftStraightRight[0]))
                this.left = new Node(leftStraightRight[0], map, this);
            if (isOnMap(leftStraightRight[1]))
                this.straight = new Node(leftStraightRight[1], map, this);
            if (isOnMap(leftStraightRight[2]))
                this.right = new Node(leftStraightRight[2], map, this);
        }
    }

    private boolean alreadyAdded() {
        return ALL_ITEMS.contains(this);
    }

    public Coordinates[] calculateMoveBy(Direction direction) {
        Coordinates[] toReturn = new Coordinates[3];
        if(direction == Direction.LEFT_TO_RIGHT) {
            toReturn[0] = new Coordinates(-1,0,Direction.BOTTOM_TO_TOP);
            toReturn[1] = new Coordinates(0,1,Direction.LEFT_TO_RIGHT);
            toReturn[2] = new Coordinates(1,0, Direction.TOP_TO_BOTTOM);
        } else if (direction == Direction.BOTTOM_TO_TOP) {
            toReturn[0] = new Coordinates(0,-1,Direction.RIGHT_TO_LEFT);
            toReturn[1] = new Coordinates(-1,0,Direction.BOTTOM_TO_TOP);
            toReturn[2] = new Coordinates(0,1, Direction.LEFT_TO_RIGHT);
        } else if (direction == Direction.TOP_TO_BOTTOM) {
            toReturn[0] = new Coordinates(0,1,Direction.LEFT_TO_RIGHT);
            toReturn[1] = new Coordinates(1,0,Direction.TOP_TO_BOTTOM);
            toReturn[2] = new Coordinates(0,-1, Direction.RIGHT_TO_LEFT);
        } else if (direction == Direction.RIGHT_TO_LEFT) {
            toReturn[0] = new Coordinates(1,0,Direction.TOP_TO_BOTTOM);
            toReturn[1] = new Coordinates(0,-1,Direction.RIGHT_TO_LEFT);
            toReturn[2] = new Coordinates(-1, 0, Direction.BOTTOM_TO_TOP);
        }
        for(int i = 0; i < toReturn.length; i++) {
            toReturn[i] = this.coordinates.moveBy(toReturn[i]);
        }
        return toReturn;
    }

    public boolean isOnMap(Coordinates point) {
        return point.getX() >= 0 && point.getX() < map.getHeatLostOnThisBlock().length
                &&
             point.getY() >= 0 && point.getY() < map.getHeatLostOnThisBlock()[0].length;
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

    public Node getLeft() {
        return left;
    }

    public Node getStraight() {
        return straight;
    }

    public Node getRight() {
        return right;
    }

    public Node getParent() {
        return parent;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Node node = (Node) o;
        return myHeat == node.myHeat && coordinates.getX() == node.coordinates.getX() && coordinates.getY() == node.coordinates.getY();
    }

    @Override
    public int hashCode() {
        return Objects.hash(coordinates.getX(), coordinates.getY(), myHeat);
    }
}
