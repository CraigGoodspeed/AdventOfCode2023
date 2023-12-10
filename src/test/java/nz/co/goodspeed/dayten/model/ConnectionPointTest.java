package nz.co.goodspeed.dayten.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ConnectionPointTest {

    char[][] map = new char[][]{
            {'7','-','F','7','-'},
            {'.','F','J','|','7'},
            {'S','J','L','L','7'},
            {'|','F','-','-','J'},
            {'L','J','.','L','J'}
    };

    @Test
    void getDestinationPoint() {
        Point northToEast = new Point(map,4,0);
        Point source = new Point(map,3,0);
        Point expectedDestination = new Point(map,4,1);

        Point actual = northToEast.travel(source);

        assertTrue(actual.comparePoints(actual.point, expectedDestination.point));

        actual = actual.travel(northToEast);

        expectedDestination = new Point(map,3,1);

        assertTrue(actual.comparePoints(actual.point, expectedDestination.point));

    }
}