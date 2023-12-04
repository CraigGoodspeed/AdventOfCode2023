package nz.co.goodspeed.daytwo;


import nz.co.goodspeed.AppStartup;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Main extends AppStartup {

    static int result;
    public Main(String filePath) {
        super(filePath);
    }

    public static void main(String[] args) throws IOException {
        Main main = new Main("/Users/goodspeedc/dev/AdventOfCode2023/input/daytwo/daytwo.txt");
        main.start();
        System.out.println(result);
    }

    public static boolean isValidGame(Game game) {
        return game.maxRed() <= 12 && game.maxGreen() <= 13 && game.maxBlue() <= 14;
    }


    @Override
    public void runForEachLine(String line) {
        Game currentGame = new Game(line);
        if(isValidGame(currentGame)) {
            result += currentGame.getGameIndex();
        }
    }
}
