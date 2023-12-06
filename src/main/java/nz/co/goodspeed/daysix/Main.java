package nz.co.goodspeed.daysix;

import nz.co.goodspeed.AppStartup;
import nz.co.goodspeed.daysix.model.Boat;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main extends AppStartup {
    List<Boat> boats;
    public Main(String path) throws IOException {
        super(path);
        String data = readAll();
        boats = new ArrayList<>();
        String[] timeAndDistance = data.split("\\n");
        long time = Long.parseLong(timeAndDistance[0].split(":")[1].replace(" ", ""));
        long distance = Long.parseLong(timeAndDistance[1].split(":")[1].replace(" ", ""));
        boats.add(new Boat(distance, time));
    }

    public static void main(String[] args) throws IOException {
        Main main = new Main("/home/craig/dev/AdventOfCode2023/input/daySix/input.txt");
        long val = main.boats.stream().mapToInt(Boat::countTheWins).reduce(1, (a,b) -> a * b);
        System.out.println(val);
    }



    @Override
    public void runForEachLine(String line) {

    }
}
