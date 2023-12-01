package nz.co.goodspeed.dayone.model;

import java.util.regex.MatchResult;

public class Number {

    String value;
    int index;
    public Number(MatchResult result) {
        value = parseGroup(result.group(1));
        index = result.start();
    }

    private String parseGroup(String toParse) {
        if(toParse.length() == 1)
            return toParse;
        return NamedNumbers.valueOf(toParse.toUpperCase()).getValue();
    }

    public String getValue() {
        return value;
    }

    public int getIndex() {
        return index;
    }
}
