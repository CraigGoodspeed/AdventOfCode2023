package nz.co.goodspeed.daythree.model;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LineTest2 {

    @Test
    public void checkItems() {
            String[] data = new String[] {
                    "1.1",
                    "1*1",
                    "1.1"

            };

            List<Line> items = new ArrayList<>();
            Line previous = null;
            for(String dat : data) {
                items.add(new Line(dat, previous));
                previous = items.get(items.size() -1);
            }

            for(int i = 0; i < items.size() - 1; i++) {
                items.get(i).setNext(items.get(i + 1));
            }

            int result = 0;
            for(Line item: items) {
                int addMe = item.findAllIntegersAdjacentToStar().stream().reduce(0, Integer::sum);
                result += addMe;
            }

            assertEquals(result, 0);
        }
    }

