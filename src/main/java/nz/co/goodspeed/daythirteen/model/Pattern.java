package nz.co.goodspeed.daythirteen.model;

import java.util.ArrayList;
import java.util.List;

public class Pattern {
    List<Line> vertical;
    List<Line> horizontal;

    public Pattern() {
        vertical = new ArrayList<>();
        horizontal = new ArrayList<>();
    }

    public List<Line> getVertical() {
        return vertical;
    }

    public void setVertical(List<Line> vertical) {
        this.vertical = vertical;
    }

    public List<Line> getHorizontal() {
        return horizontal;
    }

    public void setHorizontal(List<Line> horizontal) {
        this.horizontal = horizontal;
    }


}
