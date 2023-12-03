package nz.co.goodspeed.daythree;

import nz.co.goodspeed.daythree.model.Line;
import nz.co.goodspeed.daytwo.Game;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        File file = new File("/home/craig/dev/AdventOfCode2023/input/daythree/three.txt");
        Scanner scanner = new Scanner(file);
        List<Line> items = new ArrayList<>();
        Line previous = null;
        while(scanner.hasNextLine()) {
            String line = scanner.nextLine();
            items.add(new Line(line, previous));
            previous = items.get(items.size() -1);
        }

        for(int i = 0; i < items.size() - 1; i++) {
            items.get(i).setNext(items.get(i + 1));
        }

        int result = 0;
        for(Line item: items) {
            result += item.findAllIntegersWithAdjacentSymbol().stream().reduce(0, Integer::sum);
        }
        System.out.println(result);
    }
}
