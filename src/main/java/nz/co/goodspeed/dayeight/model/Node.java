package nz.co.goodspeed.dayeight.model;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public class Node {
    String text;

    Node stepLeft;

    Node stepRight;

    boolean endpoint;

    public Node(String text) {
        this.text = text;
        endpoint = this.text.endsWith("Z");
    }

    public Node(String text, String encodedText, Map<String, Node> tree) {
        this.text = text;
        //   (AAA,GGG)
        encodedText = encodedText.replace(" ", "");
        encodedText = encodedText.replace("(","");
        encodedText = encodedText.replace(")","");
        String[] leftAndRight = encodedText.split(",");
        stepLeft = buildTreeItem(tree, leftAndRight[0]);
        stepRight = buildTreeItem(tree, leftAndRight[1]);
        endpoint = this.text.endsWith("Z");
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
    }

    public Node getStepRight() {
        return stepRight;
    }

    public void setStepRight(Node stepRight) {
        this.stepRight = stepRight;
    }

    public Node step(Direction step) {
        return step == Direction.LEFT ?
                getStepLeft() :
                getStepRight();
    }

    public Node step(int index) {
        return index == Direction.LEFT.getIndex() ?
                getStepLeft() :
                getStepRight();
    }

    public boolean isEndPoint() {
        return endpoint;
    }
}
