package nz.co.goodspeed.dayeleven;

import nz.co.goodspeed.dayeleven.model.Point;
import nz.co.goodspeed.dayeleven.model.Universe;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        Universe universe = new Universe( "....#........\n" +
                ".........#...\n" +
                "#............\n" +
                ".............\n" +
                ".............\n" +
                "........#....\n" +
                ".#...........\n" +
                "............#\n" +
                ".............\n" +
                ".............\n" +
                ".........#...\n" +
                "#....#.......");
        System.out.println(universe);

        List<Point> galaxyItem = new ArrayList<>();

        for(List<Point> loopHere : universe.getUniverse()) {
            galaxyItem.addAll(
                    loopHere.stream().filter(
                            i -> i.getItem() == '#'
                    ).toList()
            );
        }

        List<Integer> loop = new ArrayList<>();
        for(Point outer : galaxyItem) {
            for(Point inner : galaxyItem) {
                if(inner.equals(outer)) continue;
                loop.add(outer.calculateDistance(inner));
            }
        }

        System.out.println(loop.stream().reduce(0, Integer::sum));
    }
}
