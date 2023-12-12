package nz.co.goodspeed.dayeleven.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Universe {
    List<List<Point>> universe;

    List<String> lines;

    public Universe(String input) {
        lines = new ArrayList<>(Arrays.asList(input.split("\n")));
        List<Integer> addNewLines = new ArrayList<>();
        String emptyRow= null;
        for(int i = 0; i < lines.size(); i++) {
            if(lines.get(i).replace(".", "").isEmpty()) {
                emptyRow = lines.get(i).replace(".", "^");
                addNewLines.add(i);
            }
        }


        List<Integer> addNewColumns = new ArrayList<>();
        int columns = lines.get(0).length();
        for (int y = 0; y < columns; y++) {
            boolean allDots = true;
            for(int row = 0; row < lines.size() && allDots; row++){
                allDots = lines.get(row).charAt(y) == '.';
            }
            if(allDots)
                addNewColumns.add(y);
        }
        int addMe = 0;
        for(Integer counter : addNewLines) {
            if(emptyRow != null) {
                lines.add(counter + addMe, emptyRow);
                addMe++;
            }
        }
        addMe = 0;
        for(Integer counter : addNewColumns) {
            for(int row = 0; row < lines.size(); row++) {
                String newVal = lines.get(row);
                newVal = newVal.substring(0,(counter + addMe)) + "^" + newVal.substring((counter + addMe));
                lines.set(row, newVal);
            }
            addMe++;
        }


        universe = new ArrayList<>();

        int galaxyCount = 1;
        for(int x = 0; x < lines.size(); x++) {
            String row = lines.get(x);
            char[] steps = row.toCharArray();
            universe.add(new ArrayList<>());
            for(int y = 0; y < steps.length; y++) {
                if(steps[y] == '#') {
                    universe.get(x).add(new Point(x, y, steps[y], galaxyCount));
                    galaxyCount++;
                } else {
                    universe.get(x).add(new Point(x, y, steps[y], 0));
                }
            }
        }

    }

    public Long calculateDistance(Point start, Point destination, long universeSize) {
        int startX, startY;
        int endX,endY;
        int stepsX = 0, stepsY = 0;
        int giantLeapX = 0;
        int giantLeapY = 0;
        if(start.x > destination.x) {
            startX = destination.x;
            endX = start.x;
        }
        else {
            startX = start.x;
            endX = destination.x;
        }

        if(start.y > destination.y) {
            startY = destination.y;
            endY = start.y;
        } else {
            startY = start.y;
            endY = destination.y;
        }

        while((startX + stepsX + giantLeapX) < endX) {
            if(universe.get(startX + stepsX + giantLeapX).get(startY).getItem() == '^')
                giantLeapX++;
            else stepsX++;
        }

        while((startY + stepsY + giantLeapY) < endY) {

            if(universe.get(endX).get(startY + stepsY + giantLeapY).getItem() == '^')
                giantLeapY++;
            else
                stepsY ++;
        }
        if(universeSize > 0) {
            return (stepsX - giantLeapX) + (giantLeapX * universeSize) + (stepsY - giantLeapY) + (giantLeapY * universeSize);
        } else {
            return (long)(stepsX + stepsY + giantLeapY + giantLeapX);
        }

    }

    public List<List<Point>> getUniverse() {
        return universe;
    }
}
