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
        int jackCount = (int)cards.stream().filter(i -> i == CardTypes.J).count();
        for(CardTypes type : CardTypes.values()) {
            if(cards.contains(type) && type != CardTypes.J) {
                count = (int) cards.stream().filter(i -> i == type)
                        .count();
                if (count > 0) {
                    indexHelper.add(count);

                }
            }
        }

        if(indexHelper.isEmpty() && jackCount == 5) {
            // 5 J's
            indexHelper.add(0);
        }

        indexHelper.sort(Collections.reverseOrder());
        indexHelper.set(0,indexHelper.get(0) + jackCount);
        handType = performIf(indexHelper);
        return handType;
    }

    private HandType performIf(List<Integer> vals) {
        handType = HandType.HighCard;
        for(int i : vals) {
            if( i == 5) {
                handType = HandType.FiveOfAKind;
                break;
            }
            else if(i == 4) {
                handType = HandType.FourOfAKind;
                break;
            }
            else if(i == 3 && handType == HandType.Pair) {
                handType = HandType.FullHouse;
                break;
            }
            else if (i == 3) {
                handType = HandType.ThreeOfAKind;
            }
            else if(i == 2 && handType == HandType.ThreeOfAKind) {
                handType = HandType.FullHouse;
                break;
            }
            else if(i == 2 && handType == HandType.Pair) {
                handType = HandType.TwoPair;
                break;
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
