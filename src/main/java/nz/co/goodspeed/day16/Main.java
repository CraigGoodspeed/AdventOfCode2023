package nz.co.goodspeed.day16;

import nz.co.goodspeed.AppStartup;
import nz.co.goodspeed.day16.Model.Coordinates;
import nz.co.goodspeed.day16.Model.Direction;
import nz.co.goodspeed.day16.Model.Map;
import nz.co.goodspeed.day16.bo.RayOfLight;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Main extends AppStartup {

    public Main(String filePath) {
        super(filePath);

    }
    @Override
    public void runForEachLine(String line) {

    }

    public static void main(String[] args) throws IOException {
        Main mne = new Main("/home/craig/dev/AdventOfCode2023/input/day16/input.txt");
        String[] allLines = mne.readAll().split("\n");
        Map map = new Map(allLines);

        //identify possible starting points.

        Set<Coordinates> data = mne.getStartingPoints(map);
        List<List<Coordinates>> itemsToChoose = new ArrayList<>();
        for(Coordinates start: data) {
            RayOfLight me = new RayOfLight(
                    start,
                    null
            );
            me.travel(map, start.getDirection());
            itemsToChoose.add(me.getHistory().stream().toList());
        }

        List<Long> count =
                itemsToChoose.stream().map(
                        i -> i.stream().map(coordinates -> new Coordinates(coordinates.getX(), coordinates.getY(), Direction.LEFT_TO_RIGHT)).distinct().count()
                ).toList();
        System.out.println(count.stream().max(Long::compare).get());
    }

    public Set<Coordinates> getStartingPoints(Map map) {
        Set<Coordinates> tmp = new HashSet<>();

        for(int i = 0; i < map.getMap()[0].length; i++ ) {
            tmp.add(
                    new Coordinates(
                            0,i,Direction.TOP_TO_BOTTOM
                    )
            );

            tmp.add(
                    new Coordinates(
                            map.getMap().length - 1,
                            i,
                            Direction.BOTTOM_TO_TOP
                    )
            );
        }

        for(int i = 0; i < map.getMap().length; i++ ) {
            tmp.add(
                    new Coordinates(
                            i,0,Direction.LEFT_TO_RIGHT
                    )
            );

            tmp.add(
                    new Coordinates(
                            i,
                            map.getMap()[i].length - 1,
                            Direction.RIGHT_TO_LEFT
                    )
            );
        }

        return tmp;

    }
}
