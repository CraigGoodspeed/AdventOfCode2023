package nz.co.goodspeed.day15;

import nz.co.goodspeed.AppStartup;
import nz.co.goodspeed.day15.model.HashOn;

import java.io.IOException;
import java.util.*;

public class Main extends AppStartup {

    LinkedHashMap<String, HashOn>[] hashThese = new LinkedHashMap[256];
    public Main(String filePath) throws IOException {
        super(filePath);
        String data = super.readAll();
        data = data.replace("\n", "");
        String[] toHash = data.split(",");
        for(int i = 0; i < toHash.length; i++) {
            HashOn hashMe = new HashOn(toHash[i]);
            if(hashThese[hashMe.getBlockNumber()] == null)
                hashThese[hashMe.getBlockNumber()] = new LinkedHashMap<>();
            if(hashMe.isAddMe()) {
                hashThese[hashMe.getBlockNumber()].put(hashMe.getLettersToHash(), hashMe);
            } else {
                if (hashThese[hashMe.getBlockNumber()] != null)
                    hashThese[hashMe.getBlockNumber()].remove(hashMe.getLettersToHash());
            }
        }


    }
    @Override
    public void runForEachLine(String line) {

    }

    public static void main(String[] args) throws IOException {
        Main mne = new Main("/home/craig/dev/AdventOfCode2023/input/day15/input.txt");

        int value = 0;
        for(int boxNumber = 0; boxNumber < mne.hashThese.length; boxNumber++) {
            if(mne.hashThese[boxNumber] == null)
                continue;
            List<String> keys = new ArrayList<>(mne.hashThese[boxNumber].keySet());
            for(int lenseIndex = 0; lenseIndex < keys.size(); lenseIndex++) {
                value += (boxNumber + 1) * (lenseIndex + 1) * mne.hashThese[boxNumber].get(keys.get(lenseIndex)).getLense();
            }

        }

        System.out.println(mne);
        System.out.println(value);
    }
}
