package nz.co.goodspeed.dayfour.model;

import java.util.*;

public class Game {

    List<Card> theBoard;

    public Game(List<Card> theBoard) {
        this.theBoard = theBoard;
    }

    public int calculatePoints() {
        List<Card> winningsTracker = new ArrayList<>();
        for(int i = 0; i < theBoard.size(); i++) {
            int matches = theBoard.get(i).countMatches();
            int index = theBoard.get(i).getIndex();
            for(int x = 0; x < matches; x++) {
                winningsTracker.add(theBoard.get(index + x));
            }
        }
        List<Card> copyWinnings = new ArrayList<>();
        for(int count = 0; count < winningsTracker.size(); count++) {
            Card copy = winningsTracker.get(count);
            int index = copy.getIndex();
            int matches = copy.countMatches();
            for(int i = 0; i < matches; i++) {
                winningsTracker.add(theBoard.get((index+i)));
            }
        }



        return winningsTracker.size() + theBoard.size();
    }
}
