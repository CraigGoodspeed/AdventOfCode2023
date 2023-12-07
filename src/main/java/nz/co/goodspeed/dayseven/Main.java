package nz.co.goodspeed.dayseven;

import nz.co.goodspeed.AppStartup;
import nz.co.goodspeed.dayseven.model.Camel;
import nz.co.goodspeed.dayseven.model.Hand;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Main extends AppStartup {

    List<Camel> items;

    public Main(String filePath) throws FileNotFoundException {
        super(filePath);
        items = new ArrayList<>();
    }
    @Override
    public void runForEachLine(String line) {
        String[] data = line.split("\\s");
        items.add(new Camel(data[0], Integer.parseInt(data[1])));
    }

    public static void main(String[] args) throws FileNotFoundException {
        Main main = new Main("/home/craig/dev/AdventOfCode2023/input/DaySeven/input.txt");
        main.start();
        getGroups(main.items);
        int val = main.items.stream().mapToInt(i -> i.getOrder() * i.getValue()).sum();
        System.out.println(val);
    }


    public static void getGroups(List<Camel> items) {
        int rank = 1;
        for(int i = 1; i <= 7; i++) {
            int finalI = i;
            List<Camel> tmp = items.stream().filter(
                    c -> c.getHandType().getIndex() == finalI
            ).collect(Collectors.toList());
            rank = seperateGroups(tmp, rank);
        }

    }

    public static int seperateGroups(List<Camel> seperateMe, int rank) {

        seperateMe.sort(new Hand.CamelSorter());

        for (Camel c : seperateMe) {
            c.setOrder(rank);
            rank++;
        }
        return rank;
    }

}
