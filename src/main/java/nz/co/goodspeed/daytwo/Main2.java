package nz.co.goodspeed.daytwo;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Main2 {

    public static void main(String[] args) throws IOException {
        int result = 0;
        File file = new File("/home/craig/dev/AdventOfCode2023/input/daytwo/daytwo.txt");
        Scanner scanner = new Scanner(file);
        while(scanner.hasNextLine()) {
            String line = scanner.nextLine();
            Game currentGame = new Game(line);
            result += currentGame.maxRed() * currentGame.maxBlue() * currentGame.maxGreen();
        }
        System.out.println(result);
    }
}
