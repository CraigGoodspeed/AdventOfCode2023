package nz.co.goodspeed;

import nz.co.goodspeed.dayone.IntegerFinder;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
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

    public String readAll() throws IOException {
        File file = new File(filepath);
        return new String(Files.readAllBytes(Paths.get(filepath)));
    }

    public abstract void runForEachLine(String line);
}
