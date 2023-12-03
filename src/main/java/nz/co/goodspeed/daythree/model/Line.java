package nz.co.goodspeed.daythree.model;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.MatchResult;
import java.util.regex.Pattern;

public class Line {

    Line previous;
    Line next;

    LineItem[] myData;

    String input;
    public Line(String input, Line previous) {
        this.input = input;
        setPrevious(previous);
        char[] data = input.toCharArray();
        myData = new LineItem[data.length];
        for(int i = 0; i < data.length; i++) {
            myData[i] = new LineItem(data[i]);
        }
    }

    public Line getPrevious() {
        return previous;
    }

    public void setPrevious(Line previous) {
        this.previous = previous;
    }

    public Line getNext() {
        return next;
    }

    public void setNext(Line next) {
        this.next = next;
    }

    public LineItem[] getMyData() {
        return myData;
    }

    private List<Integer> findSpecialIndexes(Line input) {
        if(input == null)
            return new ArrayList<>();
        List<Integer> toReturn = new ArrayList<>();
        String regex = "[^(\\d|\\.)]+";
        Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
        return pattern.matcher(input.getInput()).results().mapToInt(MatchResult::start).boxed().toList();
    }

    public List<Integer> findAllIntegersWithAdjacentSymbol() {
        List<Integer> foundThese = new ArrayList<>();
        List<Integer> priorSpecialIndexes = findSpecialIndexes(this.getPrevious());
        List<Integer> nextIndexes = findSpecialIndexes(this.getNext());
        List<Integer> currentIndexes = findSpecialIndexes(this);
        String regex = "\\d+";
        Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
        pattern.matcher(this.input).results()
                .forEach(
                        i -> {
                            if(isValidResult(i.start(), i.end(), priorSpecialIndexes, currentIndexes, nextIndexes))
                                foundThese.add(Integer.parseInt(i.group()));
                        }
                );

        return foundThese;

    }

    private boolean isValidResult(int start, int end, List<Integer> prior, List<Integer> current, List<Integer> next) {
        int adjacentStart = start - 1;
        int adjacentEnd = end + 1;

        return prior.stream().anyMatch(
                i -> i >= adjacentStart && i < adjacentEnd
        ) || next.stream().anyMatch(
                i -> i >= adjacentStart && i < adjacentEnd
        ) || current.stream().anyMatch(
                i -> i == adjacentStart || i == (adjacentEnd - 1)
        );
    }

    public String getInput() {
        return this.input;
    }
}
