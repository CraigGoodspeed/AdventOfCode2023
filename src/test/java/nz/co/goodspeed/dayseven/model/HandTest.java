package nz.co.goodspeed.dayseven.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HandTest {

    @Test
    void calculateHandType1() {
        Hand hand = new Hand("22222");
        assertEquals(hand.getHandType(), HandType.FiveOfAKind);
    }
    @Test
    void calculateHandType2() {
        Hand hand = new Hand("2222A");
        assertEquals(hand.getHandType(), HandType.FourOfAKind);
    }
    @Test
    void calculateHandType3() {
        Hand hand = new Hand("222AA");
        assertEquals(hand.getHandType(), HandType.FullHouse);
    }
    @Test
    void calculateHandType4() {
        Hand hand = new Hand("AA222");
        assertEquals(hand.getHandType(), HandType.FullHouse);
    }
    @Test
    void calculateHandType5() {
        Hand hand = new Hand("22KTJ");
        assertEquals(hand.getHandType(), HandType.Pair);
    }
    @Test
    void calculateHandType6() {
        Hand hand = new Hand("22TKK");
        assertEquals(hand.getHandType(), HandType.TwoPair);
    }

    @Test
    void calculateHandType8() {
        Hand hand = new Hand("2KKKK");
        assertEquals(hand.getHandType(), HandType.FourOfAKind);
    }


    @Test
    void calculateHandType7() {
        Hand hand = new Hand("23456");
        assertEquals(hand.getHandType(), HandType.HighCard);
    }

}