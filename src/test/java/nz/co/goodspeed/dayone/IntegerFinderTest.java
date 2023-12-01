package nz.co.goodspeed.dayone;

import static org.junit.jupiter.api.Assertions.assertEquals;

class IntegerFinderTest {

    @org.junit.jupiter.api.Test
    void isInt() {
        String value  = "12";
        IntegerFinder finderTest = new IntegerFinder(value);
        assertEquals("1", finderTest.getLeft());
        assertEquals("2", finderTest.getRight());
        assertEquals(12, finderTest.getValue());
    }




    @org.junit.jupiter.api.Test
    void isInt2() {
        String value  = "azsasd1231212asdasd";
        IntegerFinder finderTest = new IntegerFinder(value);
        assertEquals("1", finderTest.getLeft());
        assertEquals("2", finderTest.getRight());
        assertEquals(12, finderTest.getValue());
    }
}