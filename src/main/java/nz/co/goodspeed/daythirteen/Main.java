package nz.co.goodspeed.daythirteen;

import nz.co.goodspeed.AppStartup;
import nz.co.goodspeed.daythirteen.model.Index;
import nz.co.goodspeed.daythirteen.model.Line;
import nz.co.goodspeed.daythirteen.model.Pattern;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Main extends AppStartup {


    List<Pattern> data;

    Pattern currentItem;


    int index = 0;
    public Main(String filePath) throws FileNotFoundException {
        super(filePath);
        data = new ArrayList<>();
        currentItem = new Pattern();
        start();
        populateVertical();
        data.add(currentItem);

    }

    private void populateVertical() {
        int length = currentItem.getHorizontal().get(0).getValue().length();
        for(int i = 0; i < length; i++) {
            int finalI = i;
            String val = currentItem.getHorizontal().stream()
                    .map(line -> line.getValue().charAt(finalI))
                    .map(String::valueOf)
                    .collect(Collectors.joining());
            currentItem.getVertical().add(new Line(val));
        }
    }

    @Override
    public void runForEachLine(String line) {
        if(line.isEmpty()) {
            data.add(currentItem);
            populateVertical();
            currentItem = new Pattern();
        } else {
            currentItem.getHorizontal().add(new Line(line));
        }
    }

    public Index calculateIndex(List<Line> data) {
        //find the most central part that has equal items on either side, until one counter falls off.
        for(int i = data.size() - 1; i >= 1; i--) {
            if(data.get(i).getValue().equals(data.get(i - 1).getValue())) {
                if(moveOutAndAllEqual(data,i, i -1))
                    return new Index(i, -1);
            }
        }
        return null;
    }

    boolean moveOutAndAllEqual(List<Line> data, int left, int right) {
        boolean toReturn = true;
        while(data.size() > left && right >= 0 && toReturn) {
            toReturn = toReturn && data.get(left).getValue().equals(data.get(right).getValue());
            left++;
            right--;
        }
        return toReturn;
    }

    public int findInverse(int left, int max) {
        return max - left;
    }

    public int calculateSum(Index calculateMe) {
        if(calculateMe != null) {
            return calculateMe.getIndex();
        }
        return 0;
    }

    public static void main(String[] args) throws FileNotFoundException {
        Main mne = new Main("/home/craig/dev/AdventOfCode2023/input/daythirteen/input.txt");
        int sumVertical = 0;
        int sumHorizontal = 0;
        for(Pattern p : mne.data) {
            Index vertical = mne.calculateIndex(p.getVertical());
            Index horizontal = mne.calculateIndex(p.getHorizontal());
            if(horizontal != null) {
                sumHorizontal +=  mne.calculateSum(horizontal);
            }
            if(vertical != null) {
                sumVertical += mne.calculateSum(vertical);
            }

        }

        System.out.println(sumVertical + sumHorizontal * 100);

    }


}
