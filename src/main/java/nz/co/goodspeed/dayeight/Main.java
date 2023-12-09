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

    public Main(String path) throws FileNotFoundException {
        super(path);
        this.steps = new HashMap<>();
        super.start();
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
        long count = 0;
        long currentMillis = System.currentTimeMillis();
        List<Direction> directionSteps = tst.directionSteps.getInput();
        while(!isEndPoint(state)) {
            for(Direction item: directionSteps) {
                state = new ArrayList<>(
                        state.parallelStream()
                                .map(m ->
                                        tst.steps.get(
                                                m.step(item.getIndex()).getText()
                                        )
                                ).toList()
                );
                count++;
                if(isEndPoint(state)) {
                    break;
                }
                if(count % 500000 == 0) {
                    System.out.println(
                            String.format(
                                    "took %s to process 500000 currently at %s",
                                    System.currentTimeMillis() - currentMillis,
                                    count
                            )
                    );
                    currentMillis = System.currentTimeMillis();
                }
            }

            //System.out.println(String.format("one loop %s", (System.currentTimeMillis() - start) ));
        }

        System.out.println(count);

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
            steps.put(nodeText, new Node(nodeText, data[1], steps));
        }
    }
}
