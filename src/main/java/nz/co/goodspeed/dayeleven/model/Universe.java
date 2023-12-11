package nz.co.goodspeed.dayeleven.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Universe {
    List<List<Point>> universe;

    public Universe(String input) {
        List<String> lines = new ArrayList<>(Arrays.asList(input.split("\n")));
        List<Integer> addNewLines = new ArrayList<>();
        String emptyRow= null;
        for(int i = 0; i < lines.size(); i++) {
            if(lines.get(i).replace(".", "").isEmpty()) {
                emptyRow = lines.get(i);
                addNewLines.add(i);
            }
        }
        int addMe = 0;
        for(Integer counter : addNewLines) {
            if(emptyRow != null) {
                lines.add(counter + addMe, emptyRow);
                addMe++;
            }
        }


        universe = new ArrayList<>();

        for(int x = 0; x < lines.size(); x++) {
            String row = lines.get(x);
            char[] steps = row.toCharArray();
            universe.add(new ArrayList<>());
            for(int y = 0; y < steps.length; y++) {
                universe.get(x).add(new Point(x,y,steps[y]));
            }
        }

    }

    public List<List<Point>> getUniverse() {
        return universe;
    }
}
