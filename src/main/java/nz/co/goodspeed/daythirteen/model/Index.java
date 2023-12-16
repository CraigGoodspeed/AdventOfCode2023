package nz.co.goodspeed.daythirteen.model;

public class Index {
    int index;
    int addMe;

    public Index(int index, int addMe) {
        this.index = index;
        this.addMe = addMe;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public int getAddMe() {
        return addMe;
    }

    @Override
    public String toString() {
        return "Index{" +
                "index=" + index +
                ", addMe=" + addMe +
                '}';
    }
}
