package nz.co.goodspeed.dayfour.model;

import java.util.Arrays;
import java.util.List;

public class Card {

    int index;
    List<Integer> winningNumbers;

    List<Integer> myNumbers;

    public Card(Integer index, String winningNumbers, String myNumbers) {
        this.index = index;
        this.winningNumbers = Arrays.stream(winningNumbers.split(" "))
                .filter(i->!i.isEmpty())
                .mapToInt(Integer::parseInt).boxed().toList();
        this.myNumbers = Arrays
                .stream(myNumbers.split(" "))
                .filter(i -> !i.isEmpty())
                .mapToInt(Integer::parseInt).boxed().toList();
    }

    public List<Integer> getWinningNumbers() {
        return winningNumbers;
    }

    public List<Integer> getMyNumbers() {
        return myNumbers;
    }

    public int countMatches() {
        int count = 0;
        for(int i = 0; i < getMyNumbers().size(); i++) {
            if(getWinningNumbers().contains(getMyNumbers().get(i)))
                count++;
        }

        return count;
    }

    public int calculatePoints() {
        int shiftCount = countMatches();
        if(shiftCount == 0)
            return 0;
        int toReturn = 1;
        for(int i = 1; i < shiftCount; i++) {
            toReturn = toReturn << 1;
        }
        return toReturn;
    }

    public int getIndex() {
        return index;
    }
}
