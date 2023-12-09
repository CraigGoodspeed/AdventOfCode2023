package nz.co.goodspeed.dayeight.model;

import java.util.ArrayList;
import java.util.List;

public class DirectionSteps {
    List<Direction> input;

    public DirectionSteps(String input) {
        this.input = new ArrayList<>();

        for(char item : input.toCharArray()) {
            this.input.add(
                    Direction.LEFT.checkText(item)
                    ? Direction.LEFT : Direction.RIGHT
            );
        }
    }

    public List<Direction> getInput() {
        return input;
    }
}
