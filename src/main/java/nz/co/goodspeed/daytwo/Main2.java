package nz.co.goodspeed.daytwo;

import nz.co.goodspeed.AppStartup;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Main2 extends AppStartup {

    static int result = 0;
    public Main2(String filepath) {
        super(filepath);
    }

    public static void main(String[] args) throws IOException {
        Main2 main = new Main2("/Users/goodspeedc/dev/AdventOfCode2023/input/daytwo/daytwo.txt");
        main.start();
        System.out.println(result);
    }

    @Override
    public void runForEachLine(String line) {
        Game currentGame = new Game(line);
        result += currentGame.maxRed() * currentGame.maxBlue() * currentGame.maxGreen();
    }
}
