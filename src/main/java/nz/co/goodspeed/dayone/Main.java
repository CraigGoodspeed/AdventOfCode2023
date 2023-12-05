package nz.co.goodspeed.dayone;

import nz.co.goodspeed.AppStartup;

import java.io.IOException;
import java.io.File;
import java.util.Scanner;

public class Main extends AppStartup {

    public Main(String filePath) {
        super(filePath);
    }
    static int result = 0;
    public static void main(String[] args) throws IOException {
        Main main = new Main("/home/craig/dev/AdventOfCode2023/input/dayone/dayone.txt");
        main.start();
        System.out.println(result);
    }

    @Override
    public void runForEachLine(String line) {
        IntegerFinder finder = new IntegerFinder(line);
        result += finder.getValue();
    }
}