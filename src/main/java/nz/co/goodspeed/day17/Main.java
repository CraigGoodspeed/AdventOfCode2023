package nz.co.goodspeed.day17;

import nz.co.goodspeed.AppStartup;
import nz.co.goodspeed.day16.Model.Coordinates;
import nz.co.goodspeed.day17.mode.Map;
import nz.co.goodspeed.day17.mode.Node;

import java.io.IOException;

public class Main extends AppStartup {

    Map map;
    String rawData;

    Node topLeft;
    public Main(String filePath) throws IOException {
        super(filePath);
        rawData = super.readAll();
        map = new Map(rawData);
        topLeft = new Node(
                new Coordinates(0,0),
                map
        );
    }
    @Override
    public void runForEachLine(String line) {

    }

    public static void main(String[] args) throws IOException {
        Main mne = new Main("/home/craig/dev/AdventOfCode2023/input/day17/test.txt");
        Node topRight = null;
        Node tmp = mne.topLeft;

        while(tmp != null){
            if(tmp.getHorizontal() != null)
                tmp = tmp.getHorizontal();
            else {
                topRight = tmp;
                break;
            }
        }
        Node bottomLeft = null;
        while(topRight != null) {
            if(topRight.getVertical() != null)
                topRight = topRight.getVertical();
            else {
                bottomLeft = topRight;
                break;
            }
        }

        System.out.println(bottomLeft);

    }
}
