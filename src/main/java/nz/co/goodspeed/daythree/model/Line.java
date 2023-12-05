package nz.co.goodspeed.daythree.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
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
        String regex = "\\*";
        Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
        return pattern.matcher(input.getInput()).results().mapToInt(MatchResult::start).boxed().toList();
    }

    public List<Integer> findAllIntegersAdjacentToStar() {
        List<Integer> checkHere = findSpecialIndexes(this);
        if(checkHere.isEmpty())
            return new ArrayList<>();

        String regex = "\\d+";
        Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
        List<MatchResult> currentRow = pattern.matcher(getInput()).results().toList();
        List<MatchResult> previousRow = new ArrayList<>();
        List<MatchResult> nextRow = new ArrayList<>();
        if(getPrevious() != null)
            previousRow = pattern.matcher(getPrevious().getInput()).results().toList();
        if(getNext() != null)
            nextRow = pattern.matcher(getNext().getInput()).results().toList();

        List<Integer> toReturn = new ArrayList<>();

        for(Integer starIndex : checkHere) {
            /*
            int adjacentStart = start - 1;
            int adjacentEnd = end + 1;
            i -> i == adjacentStart || i == (adjacentEnd - 1)
             */
            List<MatchResult> current =
                    currentRow.stream().filter(i -> (starIndex + 1) == i.start() || starIndex == i.end()).toList();
            List<MatchResult> next =
                    nextRow.stream().filter(i -> (starIndex + 1) >= i.start() && starIndex <= i.end()).toList();
            List<MatchResult> previous =
                    previousRow.stream().filter(i -> (starIndex + 1) >= i.start() && starIndex <= i.end()).toList();

            List<MatchResult> combined = new ArrayList(current);
            combined.addAll(next);
            combined.addAll(previous);

            if(combined.size() == 2) {
                toReturn.add(
                        Integer.parseInt(combined.get(0).group())
                        *
                        Integer.parseInt(combined.get(1).group())
                );
            }
        }
        return toReturn;
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
                            if(isValidResultPart2(i.start(), i.end(), priorSpecialIndexes, currentIndexes, nextIndexes))
                                foundThese.add(Integer.parseInt(i.group()));
                        }
                );

        return foundThese;

    }

    private boolean isValidResult(int start, int end, List<Integer> prior, List<Integer> current, List<Integer> next) {
        int adjacentStart = start - 1;
        int adjacentEnd = end + 1;

        return prior.stream().anyMatch(
                i ->
                        i >= adjacentStart && i < adjacentEnd
        ) || next.stream().anyMatch(
                i -> i >= adjacentStart && i < adjacentEnd
        ) || current.stream().anyMatch(
                i -> i == adjacentStart || i == (adjacentEnd - 1)
        );
    }


    private boolean isValidResultPart2(int start, int end, List<Integer> prior, List<Integer> current, List<Integer> next) {
        int adjacentStart = start - 1;
        int adjacentEnd = end + 1;

        boolean priorMatch = prior.stream().anyMatch(
                i ->
                        (i >= adjacentStart && i < adjacentEnd));
        boolean nextMatch = next.stream().anyMatch(
                i -> i >= adjacentStart && i < adjacentEnd
        );

        boolean currentMatch = current.stream().anyMatch(
                i -> i == adjacentStart || i == (adjacentEnd - 1)
        );

        //need two positives
        return ((priorMatch ? 1 : 0) + (nextMatch ? 1 : 0) + (currentMatch ? 1 : 0)) >= 2;


    }

    public String getInput() {
        return this.input;
    }
}
