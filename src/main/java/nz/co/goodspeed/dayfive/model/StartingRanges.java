package nz.co.goodspeed.dayfive.model;

import java.util.ArrayList;
import java.util.List;

public class StartingRanges {

    public class StartAndEnd {
        Long start,end;
        public StartAndEnd(Long start, Long count) {
            this.start = start;
            this.end = this.start + count;
        }

        public Long getStart() {
            return start;
        }

        public Long getEnd() {
            return end;
        }
    }

    List<StartAndEnd> myItems;

    public StartingRanges(long[] input) {
            myItems = new ArrayList<>();
            for(int i = 0; i < input.length; i+=2) {
                myItems.add(new StartAndEnd(input[i], input[i+1]));
            }
    }


    public List<StartAndEnd> getMyItems() {
        return myItems;
    }

}
