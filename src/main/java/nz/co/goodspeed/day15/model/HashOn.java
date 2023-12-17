package nz.co.goodspeed.day15.model;

public class HashOn {

    String data;
    public HashOn(String data) {
        this.data = data;
    }

    public int calculateHash() {
        int toReturn = 0;
        for(char ch : this.data.toCharArray()) {
            toReturn = performAlgorithm(toReturn, ch);
        }
        return toReturn;
    }

    private int performAlgorithm(int start, char value) {
        int toReturn = start;
        toReturn += (int)value;
        toReturn *= 17;
        return (toReturn % 256);
    }
}
