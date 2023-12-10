package nz.co.goodspeed.dayeight.model;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class Node {
    String text;

    Node stepLeft;

    Node stepRight;

    Node[] childArray;

    boolean endpoint;

    int iterationCount;

    public Node(String text) {
        this.text = text;
        endpoint = this.text.endsWith("Z");
        childArray = new Node[2];
    }

    public Node buildTreeItem(Map<String, Node> tree, String index) {
        if(tree.containsKey(index)) {
            return tree.get(index);
        } else {
            Node toReturn = new Node(index);
            tree.put(index, toReturn);
            return toReturn;
        }
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Node getStepLeft() {
        return stepLeft;
    }

    public void setStepLeft(Node stepLeft) {
        this.stepLeft = stepLeft;
        this.childArray[0] = stepLeft;
    }

    public Node getStepRight() {
        return stepRight;
    }

    public void setStepRight(Node stepRight) {
        this.stepRight = stepRight;
        this.childArray[1] = stepRight;
    }

    public Node step(Direction step) {
        return this.step(step.getIndex());
    }

    public Node step(int index) {
        return this.childArray[index];
    }

    public boolean isEndPoint() {
        return endpoint;
    }

    public static class NodeStateHolder {
        private final Node[] children;
        boolean isEnd = false;

        public NodeStateHolder(List<Node> data) {
            children = data.toArray(new Node[0]);
        }

        public void step(Direction step) {
            isEnd = true;
            for(int i = 0; i < children.length;i++) {
                children[i] = children[i].step(step);
                isEnd = isEnd && children[i].isEndPoint();
            }
        }

        public boolean endIndex() {
            return isEnd;
        }
    }

    public int getIterationCount() {
        return iterationCount;
    }

    public void setIterationCount(int iterationCount) {
        this.iterationCount = iterationCount;
    }
}
