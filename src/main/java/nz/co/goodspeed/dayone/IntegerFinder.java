package nz.co.goodspeed.dayone;

import nz.co.goodspeed.dayone.model.Number;

import java.util.Comparator;
import java.util.List;
import java.util.regex.Pattern;

public class IntegerFinder {
    private static final String REGEX = "(?=(\\d|one|two|three|four|five|six|seven|eight|nine)){1}";
    String left;
    String right;
    Integer value;

    public IntegerFinder(String toParse) {
        Pattern pattern = Pattern.compile(REGEX, Pattern.CASE_INSENSITIVE);
        List<Number> data = pattern.matcher(toParse).results().map(
            Number::new
        ).toList();
        left = data.stream().min(Comparator.comparing(Number::getIndex)).orElseThrow().getValue();
        right = data.stream().max(Comparator.comparing(Number::getIndex)).orElseThrow().getValue();
        value = Integer.valueOf(String.format("%s%s", left,right));
    }

    public String getLeft() {
        return left;
    }

    public String getRight() {
        return right;
    }

    public Integer getValue() {
        return value;
    }
}
