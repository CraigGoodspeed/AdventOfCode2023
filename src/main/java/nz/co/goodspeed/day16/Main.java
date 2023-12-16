package nz.co.goodspeed.day16;

import nz.co.goodspeed.AppStartup;
import nz.co.goodspeed.day16.Model.Coordinates;
import nz.co.goodspeed.day16.Model.Direction;
import nz.co.goodspeed.day16.Model.Map;
import nz.co.goodspeed.day16.bo.RayOfLight;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main extends AppStartup {

    public Main(String filePath) {
        super(filePath);

    }
    @Override
    public void runForEachLine(String line) {

    }

    public static void main(String[] args) throws IOException {
        Main mne = new Main("/home/craig/dev/AdventOfCode2023/input/day16/test.txt");
        String[] allLines = mne.readAll().split("\n");
        Map map = new Map(allLines);
        RayOfLight rayOfLight = new RayOfLight(
                new Coordinates(0,0, Direction.LEFT_TO_RIGHT), null
        );

        rayOfLight.travel(map, Direction.LEFT_TO_RIGHT);

        System.out.println(rayOfLight.getHistory());

        System.out.println(rayOfLight.getHistory().stream().map(i -> new Coordinates(i.getX(), i.getY(), Direction.LEFT_TO_RIGHT)).distinct().count());
    }
}
