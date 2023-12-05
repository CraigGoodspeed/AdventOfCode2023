package nz.co.goodspeed.dayfive;

import nz.co.goodspeed.dayfive.model.Calculator;
import nz.co.goodspeed.dayfive.model.Range;
import nz.co.goodspeed.dayfive.model.Types;

import java.util.stream.LongStream;

public class Threadable implements Runnable {

    long start, end, value;
    Calculator calculator;
    public Threadable(long start, long end, Calculator calculator) {
        this.start = start;
        this.end = end;
        this.calculator = calculator;
    }
    @Override
    public void run() {
        final long[] toReturn = {-1};
        LongStream.range(start, end)
                .forEach(
                        i -> {
                            Range destIndex = calculator.getDestinationIndex(Types.SEED, i);
                            long index = destIndex.getDestinationIndex(i);
                            do {
                                destIndex = calculator.getDestinationIndex(destIndex.getDestinationType(), index);
                                index = destIndex.getDestinationIndex(index);
                            } while (destIndex.getDestinationType() != null);
                            if(toReturn[0] == -1 || toReturn[0] > index)
                                toReturn[0] = index;
                        }
                );
        value = toReturn[0];
    }

    public long getMin() {
        return value;
    }
}
