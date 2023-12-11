package nz.co.goodspeed.dayten;

import nz.co.goodspeed.AppStartup;
import nz.co.goodspeed.dayten.model.EnclosedBy;
import nz.co.goodspeed.dayten.model.Point;
import nz.co.goodspeed.dayten.model.PointHelper;

import java.io.IOException;
import java.util.Arrays;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.OptionalInt;
import java.util.stream.Collectors;

public class Main extends AppStartup {

    char[][] map;
    int[][] steps;

    Point start;


    public Main(String inputFile) throws IOException {
        super(inputFile);
        String allData = super.readAll();
        String[] lines = allData.split("\n");
        map = new char[lines.length][lines[0].length()];
        steps = new int[map.length][map[0].length];
        int startX = 0;
        int startY = 0;
        int count = 0;
        for(String line : lines) {
            map[count] = line.toCharArray();
            if(line.contains("S")) {
                startX = count;
                startY = line.indexOf("S");
            }
            count++;
        }
        start = new Point(map, startX, startY);
    }

    public static void main(String[] args) throws IOException {
        Main tmp = new Main("/home/craig/dev/AdventOfCode2023/input/dayten/test.txt");
        List<Point> startHere = tmp.start.travelFromStart();
        PointHelper startPoint = tmp.start.getPoint();
        Point source = tmp.start;
        tmp.steps[startPoint.getX()][startPoint.getY()] = 0;
        for(Point point: startHere) {
            int count = 1;
            tmp.steps[point.getPoint().getX()][point.getPoint().getY()] = count;
            while(!point.comparePoints(point.getPoint(), startPoint)) {
                Point tmpPoint = point.travel(source);
                source = point;
                point = tmpPoint;
                count++;
                if(point.comparePoints(point.getPoint(), startPoint))
                    break;
                if(tmp.steps[point.getPoint().getX()][point.getPoint().getY()] > count ||
                        tmp.steps[point.getPoint().getX()][point.getPoint().getY()] == 0) {
                    tmp.steps[point.getPoint().getX()][point.getPoint().getY()] = count;
                }

            }
        }
        OptionalInt optInt = Arrays.stream(tmp.steps).flatMapToInt(Arrays::stream).max();

        EnclosedBy enclosedBy = new EnclosedBy(tmp.steps, tmp.map);
        for(int x = 0; x < tmp.map.length; x++) {
            for(int y = 0; y < tmp.map[x].length; y++) {
                enclosedBy.add(new Point(tmp.map, x, y));
            }
        }

        System.out.println(enclosedBy.getItems());

        List<List<Point>> withItems = enclosedBy.getItems().stream().filter(i -> i.size() > 0).toList();
        int size = 0;

        for(List<Point> listedItem: withItems) {
           size += listedItem.size();
        }

        System.out.println(size);



        System.out.println(
            optInt.getAsInt()
        );

    }
    @Override
    public void runForEachLine(String line) {

    }
}
