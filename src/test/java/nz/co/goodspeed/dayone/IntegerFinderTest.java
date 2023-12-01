package nz.co.goodspeed.dayone;

import org.junit.jupiter.api.Test;

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

    @Test
    void overlappingRegex() {

        IntegerFinder finder = new IntegerFinder("twone");
        assertEquals("2", finder.getLeft());
        assertEquals("1", finder.getRight());
    }


    @Test
    void debugger() {
        String[] values = new String[] {
                "twone",
                "two1nine",
                "eightwothree",
                "abcone2threexyz",
                "xtwone3four",
                "4nineeightseven2",
                "zoneight234",
                "7pqrstsixteen",
                "7one1tnqxfvhmjvjzfive",
                "xsdcktrone29",
                "foureightmppchbgz8lqbzqbjztwo7cksqxns",
                "962",
                "9dlvndqbddghpxc",

        };

        for (String val: values
             ) {
            IntegerFinder finder = new IntegerFinder(val);
            System.out.println(finder.value);
        }
    }
}