package nz.co.goodspeed.dayeight;

import nz.co.goodspeed.AppStartup;
import nz.co.goodspeed.dayeight.model.Direction;
import nz.co.goodspeed.dayeight.model.DirectionSteps;
import nz.co.goodspeed.dayeight.model.Node;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main extends AppStartup {

    DirectionSteps directionSteps;
    Map<String, Node> steps;

    List<String> keyNames = new ArrayList<>();
    List<String> keyDestinations = new ArrayList<>();

    public Main(String path) throws FileNotFoundException {
        super(path);
        this.steps = new HashMap<>();
        super.start();

        for (String key: keyNames) {
            steps.put(key, new Node(key));
        }

        for(int i = 0; i < keyNames.size(); i++) {
            String encodedText = keyDestinations.get(i);
            encodedText = encodedText.replace(" ", "");
            encodedText = encodedText.replace("(","");
            encodedText = encodedText.replace(")","");
            String[] leftAndRight = encodedText.split(",");
            Node left = steps.get(leftAndRight[0]);
            Node right = steps.get(leftAndRight[1]);
            steps.get(keyNames.get(i))
                    .setStepRight(right);
            steps.get(keyNames.get(i))
                    .setStepLeft(left);


        }

    }

    private void resolveChildren() {
        for (String key : steps.keySet()) {
            resolveChildren(steps.get(key));
        }
    }

    private void resolveChildren(Node node) {
        if(node.getStepLeft() == null) {
            node.setStepLeft(steps.get(node.getText()));
            resolveChildren(node.getStepLeft());
        }
        if(node.getStepRight() == null) {
            node.setStepRight(steps.get(node.getText()));
            resolveChildren(node.getStepRight());
        }
    }

    public static void main(
            String[] args
    ) throws FileNotFoundException {
        Main tst = new Main("/home/craig/dev/AdventOfCode2023/input/dayeight/actual.txt");
        List<Node> state = getStartingPoints(tst.steps);
        List<Direction> directionSteps = tst.directionSteps.getInput();

        long total = 1;
        for(Node node : state) {
            Node tmp = node;
            int outerCount = 0;
            while(!tmp.isEndPoint()) {
                for (Direction item : directionSteps) {
                    tmp = tmp.step(item);
                    if (tmp.isEndPoint()) {
                        break;
                    }
                }
                outerCount++;
            }
            node.setIterationCount(outerCount);
            total *= outerCount;
        }
        System.out.println(total * directionSteps.size());

    }

    private static List<Node> getStartingPoints(Map<String, Node> steps) {
        return steps.keySet().stream().filter(i -> i.endsWith("A"))
                .map(i -> steps.get(i))
                .toList();
    }

    private static boolean isEndPoint(List<Node> data) {
        return data.stream().allMatch(Node::isEndPoint);
    }



    @Override
    public void runForEachLine(String line) {
        if(line.isEmpty()) {
            return;
        }
        if(this.directionSteps == null)
            this.directionSteps = new DirectionSteps(line);
        else {
            String[] data = line.split("=");
            String nodeText = data[0].replace(" ","");
            keyNames.add(nodeText);
            keyDestinations.add(data[1]);
        }
    }
}
