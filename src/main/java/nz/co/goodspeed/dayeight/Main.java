package nz.co.goodspeed.dayeight;

import nz.co.goodspeed.AppStartup;
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
        int count = 0;
        while(!isEndPoint(state)) {
            for(int i = 0;
                i < tst.directionSteps.getInput().size()
                &&
                !isEndPoint(state)
                    ;
                i++) {
                int finalI = i;


                state = new ArrayList<>(
                state.parallelStream()
                        .map(m ->
                                tst.steps.get(
                                        m.step(tst.directionSteps.getInput().get(finalI).getIndex()).getText()
                                )
                        ).toList()
                );

                count++;
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
