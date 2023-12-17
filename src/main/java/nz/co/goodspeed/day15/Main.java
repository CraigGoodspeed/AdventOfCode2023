package nz.co.goodspeed.day15;

import nz.co.goodspeed.AppStartup;
import nz.co.goodspeed.day15.model.HashOn;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main extends AppStartup {

    List<HashOn> hashThese;
    public Main(String filePath) throws IOException {
        super(filePath);
        hashThese = new ArrayList<>();
        String data = super.readAll();
        data = data.replace("\n", "");
        String[] toHash = data.split(",");
        for(int i = 0; i < toHash.length; i++) {
            hashThese.add(new HashOn(toHash[i]));
        }
    }
    @Override
    public void runForEachLine(String line) {

    }

    public static void main(String[] args) throws IOException {
        Main mne = new Main("/home/craig/dev/AdventOfCode2023/input/day15/input.txt");

        System.out.println(mne.hashThese.stream().map(
                HashOn::calculateHash
        ).mapToInt(Integer::intValue).sum());
    }
}
