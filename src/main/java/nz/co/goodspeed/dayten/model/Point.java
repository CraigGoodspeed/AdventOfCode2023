package nz.co.goodspeed.dayten.model;

import java.util.ArrayList;
import java.util.List;

public class Point {
    public ConnectionPoint connectionPoint;
    Point source;
    Point destination;
    char[][] map;

    PointHelper point;
    public Point(char[][] map, int x, int y) {
        this.map = map;
        connectionPoint = ConnectionPoint.parseSymbol(map[x][y]);
        point = new PointHelper(x,y);
    }

    public Point travel(Point source) {
        this.source = source;
        PointHelper helper = this.connectionPoint.getDestinationPoint(this.source.point, this.point);
        this.destination = new Point(this.map, helper.getX(), helper.getY());
        if(this.destination.connectionPoint.equals(ConnectionPoint.GROUND))
            return null;
        return this.destination;
    }

    public List<Point> travelFromStart() {
        //identify places we can go
        int startX = this.point.getX();
        int startY = this.point.getY();
        List<Point> toReturn = new ArrayList<>();

        int[] xChange = new int[]{1,-1};
        int[] yChange = new int[]{1,-1};

        for(int x = 0; x < xChange.length; x++) {
            int newPossibleLocation = startX + xChange[x];
            if(newPossibleLocation < 0 || newPossibleLocation > map.length) {
                continue;
            }
            Point item = new Point(map, newPossibleLocation, startY);
            if(!item.connectionPoint.equals(ConnectionPoint.GROUND))
                toReturn.add(new Point(map, newPossibleLocation, startY));
        }

        for(int y = 0; y < yChange.length; y++) {
            int newPossibleLocation = startY + yChange[y];
            if(newPossibleLocation < 0 || newPossibleLocation > map[startX].length) {
                continue;
            }
            Point item = new Point(map, startX, newPossibleLocation);
            if(!item.connectionPoint.equals(ConnectionPoint.GROUND))
                toReturn.add(new Point(map, startX, newPossibleLocation));
        }

        return toReturn;
    }

    public PointHelper getPoint() {
        return point;
    }

    public boolean comparePoints(PointHelper point1, PointHelper point2) {
        return point1.getX() == point2.getX() && point1.getY() == point2.getY();
    }

}
