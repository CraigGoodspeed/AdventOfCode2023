package nz.co.goodspeed.daytwo;

import nz.co.goodspeed.daytwo.model.Color;
import nz.co.goodspeed.daytwo.model.Item;

import java.util.List;
import java.util.regex.MatchResult;
import java.util.regex.Pattern;

public class GameItem {
    static final String CUBE_REGEX = "(\\d+\\s(red|green|blue))";
    List<Item> data;


    public GameItem(String parseMe) {
        Pattern pattern = Pattern.compile(CUBE_REGEX, Pattern.CASE_INSENSITIVE);
        data = pattern.matcher(parseMe).results().map(GameItem::parsePatternMatcher
        ).toList();

    }

    private static Item parsePatternMatcher(MatchResult result) {
        Item value = null;
        int count = result.groupCount();
        for(int i = 0; i <= count; i++) {
            if(!result.group(i).isEmpty()) {
                value = new Item(result.group(i));
                break;
            }
        }
        return value;
    }

    public List<Item> getData() {
        return data;
    }

    public Integer countBlue() {
        return countColor(Color.BLUE);
    }

    public Integer countGreen() {
        return countColor(Color.GREEN);
    }

    public Integer countRed() {
        return countColor(Color.RED);
    }

    private Integer countColor(Color val) {
        return data.stream().filter(i -> i.getType().equals(val)).mapToInt(Item::getValue).max().orElse(0);
    }

    public Integer getRed() {
        return getColor(Color.RED);
    }
    public Integer getGreen() {
        return getColor(Color.GREEN);
    }

    public Integer getBlue() {
        return getColor(Color.BLUE);
    }

    public Integer getColor(Color val) {
        return data.stream().filter(i -> i.getType().equals(val)).mapToInt(Item::getValue).findFirst().orElse(0);
    }
}
