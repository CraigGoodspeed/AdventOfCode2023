package nz.co.goodspeed.day15.model;

public class HashOn {

    String data;
    boolean addMe = false;

    String lettersToHash;
    Integer lense;

    int blockNumber = -1;
    public HashOn(String data) {
        this.data = data;
        this.addMe = this.data.contains("=");
        String splitChar = this.addMe ? "=" : "-";
        String[] items = this.data.split(splitChar);
        this.lettersToHash = items[0];
        if(addMe)
            this.lense = Integer.parseInt(items[1]);
        blockNumber = calculateHash();
    }

    public int calculateHash() {
        int toReturn = 0;
        for(char ch : this.lettersToHash.toCharArray()) {
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

    public String getData() {
        return data;
    }

    public boolean isAddMe() {
        return addMe;
    }

    public String getLettersToHash() {
        return lettersToHash;
    }

    public Integer getLense() {
        return lense;
    }

    public int getBlockNumber() {
        return blockNumber;
    }

}
