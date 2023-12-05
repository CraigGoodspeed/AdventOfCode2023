package nz.co.goodspeed.dayfour;

import nz.co.goodspeed.dayfour.model.Card;
import nz.co.goodspeed.dayfour.model.Game;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MainTest {

    @Test
    void main() throws FileNotFoundException {

        Main mn = new Main("/home/craig/dev/AdventOfCode2023/input/dayfour/test.txt");
        mn.cards = new ArrayList<>();
        mn.start();
        int result = 0;
        Game game = new Game(mn.cards);

        System.out.println(game.calculatePoints());
    }
}