package nz.co.goodspeed.dayeleven;

import nz.co.goodspeed.AppStartup;
import nz.co.goodspeed.dayeleven.model.Point;
import nz.co.goodspeed.dayeleven.model.Universe;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main extends AppStartup {


    Main(String filePath) throws FileNotFoundException {
        super((filePath));
        start();
    }

    public static void main(String[] args) throws IOException {
        Main main = new Main("/home/craig/dev/AdventOfCode2023/input/dayeleven/input.txt");
        String data = main.readAll();

        Universe universe = new Universe(data);
        System.out.println(universe);

        List<Point> galaxyItem = new ArrayList<>();

        for(List<Point> loopHere : universe.getUniverse()) {
            galaxyItem.addAll(
                    loopHere.stream().filter(
                            i -> i.getItem() == '#'
                    ).toList()
            );
        }

        List<Long> loop = new ArrayList<>();
        while (!galaxyItem.isEmpty()) {
            Point outer = galaxyItem.get(galaxyItem.size() - 1);
            for(int y = 0; y < galaxyItem.size(); y++) {
                Point inner = galaxyItem.get(y);
                if(inner.equals(outer)) continue;
                loop.add(universe.calculateDistance(inner, outer,1000000L));
            }
            galaxyItem.remove(outer);
        }


        System.out.println(loop.stream().reduce(0L, Long::sum));
    }

    @Override
    public void runForEachLine(String line) {

    }
}
