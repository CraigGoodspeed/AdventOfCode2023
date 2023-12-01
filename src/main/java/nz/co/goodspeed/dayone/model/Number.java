package nz.co.goodspeed.dayone.model;

import java.util.regex.MatchResult;

public class Number {

    String value;
    int index;
    public Number(MatchResult result) {
        int count = result.groupCount();
        for(int i = 0; i <= count; i++) {
            if(!result.group(i).isEmpty()) {
                value = parseGroup(result.group(i));
                break;
            }
        }
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
