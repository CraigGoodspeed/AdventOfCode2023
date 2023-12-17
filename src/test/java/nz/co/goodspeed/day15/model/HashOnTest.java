package nz.co.goodspeed.day15.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HashOnTest {

    @Test
    public void calculateHashCheck() {
        HashOn hashOn = new HashOn("HASH");
        assertEquals(52, hashOn.calculateHash());
    }

    @Test
    public void calculateIndexCheck() {
        String[] itemsToCheck = new String[]
                {
                        "rn",
                        "qp",
                        "cm",
                        "pc",
                        "ot",
                        "ab"
                };
        for(String item : itemsToCheck) {
            HashOn hashOn = new HashOn(item);
            System.out.println(hashOn.calculateHash());
        }
    }

}