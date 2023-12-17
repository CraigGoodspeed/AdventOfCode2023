package nz.co.goodspeed.day15.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HashOnTest {

    @Test
    public void calculateHashCheck() {
        HashOn hashOn = new HashOn("HASH");
        assertEquals(52, hashOn.calculateHash());
    }

}