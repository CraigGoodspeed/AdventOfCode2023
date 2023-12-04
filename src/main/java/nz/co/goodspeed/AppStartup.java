package nz.co.goodspeed;

import nz.co.goodspeed.dayone.IntegerFinder;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public abstract class AppStartup {

    String filepath;
    protected AppStartup(String filePath) {
        this.filepath = filePath;
    }

    public void start() throws FileNotFoundException {
        File file = new File(filepath);
        Scanner scanner = new Scanner(file);
        while(scanner.hasNextLine()) {
            runForEachLine(scanner.nextLine());
        }
    }

    public abstract void runForEachLine(String line);
}
