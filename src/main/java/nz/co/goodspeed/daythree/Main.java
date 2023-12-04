package nz.co.goodspeed.daythree;

import nz.co.goodspeed.AppStartup;
import nz.co.goodspeed.daythree.model.Line;
import nz.co.goodspeed.daytwo.Game;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main extends AppStartup {

    static List<Line> items = new ArrayList<>();
    static Line previous = null;

    public Main(String filePath) {
        super(filePath);
    }
    public static void main(String[] args) throws IOException {
        Main main = new Main("/Users/goodspeedc/dev/AdventOfCode2023/input/daythree/three.txt");
        main.start();

        int result = 0;
        for(Line item: items) {
            int addMe = item.findAllIntegersWithAdjacentSymbol().stream().reduce(0, Integer::sum);
            result += addMe;
        }

        System.out.println(result);
    }

    @Override
    public void runForEachLine(String line) {
        items.add(new Line(line, previous));
        previous = items.get(items.size() -1);

        for(int i = 0; i < items.size() - 1; i++) {
            items.get(i).setNext(items.get(i + 1));
        }
    }
}
