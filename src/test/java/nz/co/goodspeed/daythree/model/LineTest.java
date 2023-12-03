package nz.co.goodspeed.daythree.model;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

class LineTest {

    @Test
    void checkAgainstSample() {
        String[] data = new String[] {
                "467..114..",
                "...*......",
                "..35..633.",
                "......#...",
                "617*......",
                ".....+.58.",
                "..592.....",
                "......755.",
                "...$.*....",
                ".664.598.."
        };

        List<Line> items = new ArrayList<>();
        Line previous = null;
        for(String line: data) {
            items.add(new Line(line, previous));
            previous = items.get(items.size() -1);
        }
        for(int i = 0; i < items.size() - 1; i++) {
            items.get(i).setNext(items.get(i + 1));
        }

        int result = 0;

        for(Line line: items ) {
            result += line.findAllIntegersWithAdjacentSymbol().stream().reduce(0, Integer::sum);
        }

        System.out.println(result);

    }

    @Test
    public void checkLinePostCharacter() {
        Line line = new Line("*617......", null);
        List<Integer> data = line.findAllIntegersWithAdjacentSymbol();
        System.out.println(data);
    }


    @Test
    public void checkLinePostCharacter2() {
        Line line = new Line(".......................*617......", null);
        List<Integer> data = line.findAllIntegersWithAdjacentSymbol();
        System.out.println(data);
    }

    @Test
    public void checkLinePostCharacter3() {
        Line line = new Line(".222...................617*999......", null);
        List<Integer> data = line.findAllIntegersWithAdjacentSymbol();
        System.out.println(data);
    }

}