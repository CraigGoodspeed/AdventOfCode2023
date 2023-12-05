package nz.co.goodspeed.dayfour;

import nz.co.goodspeed.AppStartup;
import nz.co.goodspeed.dayfour.model.Card;
import nz.co.goodspeed.dayfour.model.Game;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class Main extends AppStartup {

    static List<Card> cards;
    public Main(String filePath) {
        super(filePath);
    }

    public static void main(String[] args) throws FileNotFoundException {
        cards = new ArrayList<>();
        Main mn = new Main("/home/craig/dev/AdventOfCode2023/input/dayfour/input.txt");
        mn.start();
        int result = 0;
        for(Card c : cards) {
            result += c.calculatePoints();
        }
        System.out.println(result);

        Game game = new Game(cards);
        System.out.println(game.calculatePoints());


    }

    @Override
    public void runForEachLine(String line) {
        String[] data = line.split("\\|");

        String left = data[0].split("\\:")[1];
        String right = data[1];
        cards.add(
                new Card(cards.size()+1, left, right)
        );
    }
}
