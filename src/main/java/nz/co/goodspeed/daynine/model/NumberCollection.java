package nz.co.goodspeed.daynine.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class NumberCollection {

    List<Integer> parentNumbers;
    List<Integer> numbers;
    NumberCollection next;

    NumberCollection prev;
    boolean iAmAllZeros = false;
    NumberCollection lastItem;

    public NumberCollection(String numbers) {
        this(Arrays.stream(numbers.split("\\s")).mapToInt(Integer::parseInt).boxed().toList());
    }

    public NumberCollection(NumberCollection parent) {
        this(parent.numbers);
        this.prev = parent;
    }

    public NumberCollection(List<Integer> numbers) {
        this.parentNumbers = numbers;
        if(!numbers.stream().allMatch(i -> i == 0)) {
            List<Integer> differences = new ArrayList<>();
            for(int i = 1; i < numbers.size(); i++) {
                differences.add(numbers.get(i) - numbers.get(i - 1));
            }
            this.numbers = differences;
            iAmAllZeros = this.numbers.stream().allMatch(i -> i == 0);
            if(!iAmAllZeros) {
                next = new NumberCollection(this);
            }
            else {
                this.lastItem = this;
            }
        }
    }

    public NumberCollection getNext() {
        return next;
    }

    public Integer getMaxItem() {
        if(this.numbers != null)
            return this.numbers.get(this.numbers.size() - 1);
        return null;
    }

    public boolean isiAmAllZeros() {
        return iAmAllZeros;
    }

    public NumberCollection getLastItem() {
        return lastItem;
    }

    public NumberCollection getPrev() {
        return prev;
    }

    public Integer getParentMaxNumber() {
        return this.parentNumbers.get(this.parentNumbers.size() - 1);
    }

    public Integer getParentMinValue() {
        return this.parentNumbers.get(0);
    }

    public Integer calculateParentNextMin(Integer priorMin) {
        Integer parentMin = this.getParentMinValue();
        return parentMin - priorMin;
    }
}
