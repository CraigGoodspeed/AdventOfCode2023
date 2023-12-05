package nz.co.goodspeed.daythree.model;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
        assertEquals(result, 4361);

    }

    @Test
    public void checkLinePostCharacter() {
        Line line = new Line("*617......", null);
        List<Integer> data = line.findAllIntegersWithAdjacentSymbol();
        assertEquals(data.get(0), 617);
    }

    @Test
    public void checkLinePostCharacter4() {
        Line line = new Line("617*......", null);
        List<Integer> data = line.findAllIntegersWithAdjacentSymbol();
        assertEquals(data.get(0),617);
    }

    @Test
    public void checkLinePostCharacter5() {
        Line line = new Line(".....*55", null);
        List<Integer> data = line.findAllIntegersWithAdjacentSymbol();
        assertEquals(data.get(0), 55);
    }


    @Test
    public void checkLinePostCharacter2() {
        Line line = new Line("......444..............617)", null);
        List<Integer> data = line.findAllIntegersWithAdjacentSymbol();
        System.out.println(data);
        assertEquals(data.size(), 0);
    }

    @Test
    public void checkLinePostCharacter3() {
        Line line = new Line("....*124.15..675.", null);
        List<Integer> data = line.findAllIntegersWithAdjacentSymbol();
        System.out.println(data);
        assertEquals(data.get(0), 124);
    }

    @Test
    public void checkTwoLinesAtEnd() {
        String[] data = new String[] {
                "....509.....=...........890...................&........9................./..847.154..568............@...102................280...*..........",
                ".....*..950.67.............-......161.......530....=...................=............*...../..............@.......................426........",
                "614...............................*.............59.591.....259*.......342...427..........29../.........-....................946............."
        };

        int[] byHand = new int[]{509,890,568,102,
        67,161,530,426,
        591,259,342,29};

        List<Line> items = new ArrayList<>();
        Line previous = null;
        for(String dat : data) {
            items.add(new Line(dat, previous));
            previous = items.get(items.size() -1);
        }

        for(int i = 0; i < items.size() - 1; i++) {
            items.get(i).setNext(items.get(i + 1));
        }

        int result = 0;
        for(Line item: items) {
            int addMe = item.findAllIntegersWithAdjacentSymbol().stream().reduce(0, Integer::sum);
            result += addMe;
        }

        assertEquals(Arrays.stream(byHand).sum(), result);
    }


    @Test
    void partTwo() {
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
        for(String dat : data) {
            items.add(new Line(dat, previous));
            previous = items.get(items.size() -1);
        }

        for(int i = 0; i < items.size() - 1; i++) {
            items.get(i).setNext(items.get(i + 1));
        }

        int result = 0;
        for(Line item: items) {
            int addMe = item.findAllIntegersAdjacentToStar().stream().reduce(0, Integer::sum);
            result += addMe;
        }

        System.out.println(result);
    }

}