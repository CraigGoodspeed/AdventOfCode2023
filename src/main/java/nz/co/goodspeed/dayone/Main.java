package nz.co.goodspeed.dayone;

import java.io.IOException;
import java.io.File;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        int result = 0;
        File file = new File("/home/craig/dev/AdventOfCode2023/input/dayone/dayone.txt");
        Scanner scanner = new Scanner(file);
        while(scanner.hasNextLine()) {
            String line = scanner.nextLine();
            IntegerFinder finder = new IntegerFinder(line);
            result += finder.getValue();
        }
        System.out.println(result);
    }
}