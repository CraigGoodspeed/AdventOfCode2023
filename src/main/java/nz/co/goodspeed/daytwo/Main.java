package nz.co.goodspeed.daytwo;


import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException {
        int result = 0;
        File file = new File("/home/craig/dev/AdventOfCode2023/input/daytwo/daytwo.txt");
        Scanner scanner = new Scanner(file);
        while(scanner.hasNextLine()) {
            String line = scanner.nextLine();
            Game currentGame = new Game(line);
            if(isValidGame(currentGame)) {
                result += currentGame.getGameIndex();
            }
        }
        System.out.println(result);
    }

    public static boolean isValidGame(Game game) {
        return game.maxRed() <= 12 && game.maxGreen() <= 13 && game.maxBlue() <= 14;
    }


}
