package nz.co.goodspeed.dayseven.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Hand {
    List<CardTypes> cards;

    HandType handType;

    public Hand(String hand) {
        cards = new ArrayList<>();
        for (char card: hand.toCharArray()) {
            cards.add(CardTypes.parse(String.valueOf(card)));
        }
        this.handType = calculateHandType();
    }

    public int valueOf(int index) {
        return cards.get(index).getValue();
    }

    public HandType calculateHandType() {

        List<Integer> indexHelper = new ArrayList<>();
        int count = -1;
        for(CardTypes type : CardTypes.values()) {
            count = (int)cards.stream().filter(i -> i == type)
                    .count();
            if(count > 0) {
                indexHelper.add(count);
            }
        }

        indexHelper.sort(Collections.reverseOrder());
        handType = HandType.HighCard;
        for(int i : indexHelper) {
            if( i == 5) {
                handType = HandType.FiveOfAKind;
            }
            else if(i == 4) {
                handType = HandType.FourOfAKind;
            }
            else if(i == 3 && handType == HandType.Pair) {
                handType = HandType.FullHouse;
            }
            else if (i == 3) {
                handType = HandType.ThreeOfAKind;
            }
            else if(i == 2 && handType == HandType.ThreeOfAKind) {
                handType = HandType.FullHouse;
            }
            else if(i == 2 && handType == HandType.Pair) {
                handType = HandType.TwoPair;
            }
            else if(i == 2) {
                handType = HandType.Pair;
            }
        }
        return handType;
    }

    public HandType getHandType() {
        return handType;
    }

    public static class CamelSorter implements Comparator<Camel> {
        public int compare(Camel c1, Camel c2) {
            for(int i = 0; i < 5; i ++) {
                CardTypes card1 = c1.getHand().cards.get(i);
                CardTypes card2 = c2.getHand().cards.get(i);
                if(card1 != card2) {
                    return card1.compareTo(card2);
                }
            }
            return 0;
        }
    }
}
