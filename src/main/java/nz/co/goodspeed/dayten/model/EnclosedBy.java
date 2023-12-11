package nz.co.goodspeed.dayten.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class EnclosedBy {

    List<List<Point>> items;


    char[][] map;
    int[][] counter;

    public EnclosedBy(int[][] counter, char[][] map) {
        this.map = map;
        this.counter = counter;
        items = new ArrayList<>();
    }

    public void add(Point addMe) {
        boolean added = false;
        if(isValid(addMe)) {
            for (List<Point> addHere : this.items) {
                if (isInRange(addHere, addMe)) {
                    addHere.add(addMe);
                    added = true;
                    break;
                }
            }
            if(!added) {
                if(
                        canAdd(addMe)
                )
                    this.items.add(new ArrayList<>(List.of(addMe)));
            }
        }
        else if(isNotUsed(addMe) && isEdge(addMe)) {
            for(int counter = 0; counter < this.items.size(); counter++) {
                if (isInRange(this.items.get(counter), addMe)) {
                    this.items.get(counter).clear();//remove all items from this list, if one is not valid they are all invalid
                }
            }
        }
    }

    public boolean canAdd(Point pt) {
        int x = pt.point.getX();
        int y = pt.point.getY();
        return
                this.counter[x][y] == 0
                        &&
                        (
                                this.counter[x - 1][y] > 0 //north
                                        ||
                                        this.counter[x][y - 1] > 0 //east
                                        ||
                                        this.counter[x + 1][y] > 0 //south
                                        ||
                                        this.counter[x][y+ 1] > 0 //west
                        );
    }

    public boolean isInRange(List<Point> pt, Point toAdd) {
        boolean toReturn = false;
        int cnt = 0;
        while(cnt < pt.size() && !toReturn) {
            toReturn =
                    (
                            pt.get(cnt).getPoint().getY() == toAdd.point.getY()
                            &&
                            Math.abs(toAdd.getPoint().getX() - pt.get(cnt).point.getX()) == 1
                    )
                    ||
                    (
                            pt.get(cnt).getPoint().getX() == toAdd.point.getX()
                                    &&
                                    Math.abs(toAdd.getPoint().getY() - pt.get(cnt).point.getY()) == 1
                    );
            cnt++;
        }
        return toReturn;
    }

    public boolean isNotUsed(Point pt) {
        return this.counter[pt.point.x][pt.point.y] == 0;
    }

    public boolean isEdge(Point pt) {
        int x = pt.point.getX();
        int y = pt.point.getY();
        return x == 0 || x == map.length - 1 || y == 0 || y == map[x].length - 1;
    }



    public boolean isValid(Point checkMe) {
        int x = checkMe.point.getX();
        int y = checkMe.point.getY();

        return
                !ConnectionPoint.parseSymbol(map[x][y]).equals(ConnectionPoint.START)
                &&
                this.counter[x][y] == 0
                &&
                !isEdge(checkMe);
    }

    public List<List<Point>> getItems() {
        return items;
    }
}
