package nz.co.goodspeed.dayfive;

import nz.co.goodspeed.dayfive.model.Calculator;
import nz.co.goodspeed.dayfive.model.Range;
import nz.co.goodspeed.dayfive.model.StartingRanges;
import nz.co.goodspeed.dayfive.model.Types;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class Main1Test {

    static List<Long> endIndex = new ArrayList<>();
    @Test
    void checkLoadUp() throws IOException, InterruptedException {
        Main1 mn1 = new Main1("/home/craig/dev/AdventOfCode2023/input/dayFive/input.txt");
        Calculator calculator = new Calculator(mn1.readAll());
        assertFalse(calculator.getRanges().isEmpty());

        StartingRanges ranges = new StartingRanges(calculator.getStartingPoints());
        List<Long> extremes = calculator.getExtremes(ranges.getMyItems());
        /*HashSet<Long> startIndexes = new HashSet<>();

        for(StartingRanges.StartAndEnd range : ) {
            for (long i = range.getStart(); i < range.getEnd(); i++) {
                startIndexes.add(i);
            }

        }*/


        System.out.println(Collections.min(extremes));
    }


    @Test
    void checkLoadUp2() throws IOException, InterruptedException {
        Main1 mn1 = new Main1("/home/craig/dev/AdventOfCode2023/input/dayFive/input.txt");
        Calculator calculator = new Calculator(mn1.readAll());
        assertFalse(calculator.getRanges().isEmpty());

        StartingRanges ranges = new StartingRanges(calculator.getStartingPoints());

        //List<Long> extremes = calculator.getExtremes(ranges.getMyItems());
        /*HashSet<Long> startIndexes = new HashSet<>();

        for(StartingRanges.StartAndEnd range : ) {
            for (long i = range.getStart(); i < range.getEnd(); i++) {
                startIndexes.add(i);
            }

        }*/


        System.out.println(calculator.reverseOrder(ranges.getMyItems()));
    }



}