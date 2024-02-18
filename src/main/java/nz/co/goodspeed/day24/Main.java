package nz.co.goodspeed.day24;

import nz.co.goodspeed.AppStartup;
import nz.co.goodspeed.day24.model.Hailstone;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class Main extends AppStartup {

    List<Hailstone> allItems;
    public Main(String path) throws FileNotFoundException {
        super(path);
        allItems = new ArrayList<>();
        super.start();
    }

    @Override
    public void runForEachLine(String line) {
        String[] positionsAndVelocities = line.split("@");



    }
}
