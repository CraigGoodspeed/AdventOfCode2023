package nz.co.goodspeed.dayone;

public class IntegerFinder {

    String left;
    String right;
    Integer value;

    public IntegerFinder(String toParse) {
        left = getLeftMostInteger(toParse);
        right = getRightMostInteger(toParse);
        value = Integer.parseInt(String.format("%s%s", left,right));
    }

    private boolean isInt(char val) {
        return  48 <= (int)val && (int)val <= 57;
    }

    private String getLeftMostInteger(String item) {
        for(char i : item.toCharArray()) {
            if(isInt(i)) {
              return String.valueOf(i);
            }
        }
        throw new RuntimeException("no integer found");
    }

    private String getRightMostInteger(String item) {
        char[] data = item.toCharArray();
        for(int i = data.length - 1; i >= 0; i--) {
            if(isInt(data[i])) {
                return String.valueOf(data[i]);
            }
        }
        throw new RuntimeException("no integer found");
    }

    public String getLeft() {
        return left;
    }

    public String getRight() {
        return right;
    }

    public Integer getValue() {
        return value;
    }
}
