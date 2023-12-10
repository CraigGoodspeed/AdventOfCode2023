package nz.co.goodspeed.daynine;

import nz.co.goodspeed.AppStartup;
import nz.co.goodspeed.daynine.model.NumberCollection;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class Main extends AppStartup {

    List<NumberCollection> numbers;
    public Main(String filePath) throws FileNotFoundException {
        super(filePath);
        numbers = new ArrayList<>();
        start();
    }
    @Override
    public void runForEachLine(String line) {
        numbers.add(new NumberCollection(line));
    }

    public static void main(String[] args) throws FileNotFoundException {
        Main mne = new Main("/home/craig/dev/AdventOfCode2023/input/daynine/actual.txt");

        int runningTotal = 0;
        for(NumberCollection collection : mne.numbers) {
            NumberCollection startHere = collection;
            while(!startHere.isiAmAllZeros()) {
                startHere = startHere.getNext();
            }

            do {
                runningTotal = runningTotal + startHere.getParentMaxNumber();
                startHere = startHere.getPrev();
            } while(startHere != null);
        }


        System.out.println(runningTotal);
    }
}
