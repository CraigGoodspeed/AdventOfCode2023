package nz.co.goodspeed.dayfive.model;

import nz.co.goodspeed.dayfive.Threadable;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.LongStream;

public class Calculator {
    List<Range> ranges;

    long[] startingPoints;

    List<Long> endIndex = new ArrayList<>();

    List<Long> minValues = new ArrayList<>();

    public Calculator(String content) {
        ranges = new ArrayList<>();
        String[] data = content.split("\\n");

        String seedsData = data[0];
        startingPoints = Arrays.stream(seedsData.split(":")[1].split("\\s")).filter(s -> !s.isEmpty())
                .mapToLong(Long::parseLong).toArray();
        String source = null;
        String destination = null;
        for(int i = 0; i < data.length; i++) {
            if(data[i].isEmpty()) {
                continue;
            }
            if(data[i].contains("-to-")) {
                String[] sourceDestination = data[i].split("\\s");
                sourceDestination = sourceDestination[0].split("-to-");
                source  = sourceDestination[0];
                destination = sourceDestination[1];
            }
            else if(source != null && destination != null) {
                long[] map = Arrays.stream(data[i].split("\\s"))
                        .mapToLong(Long::parseLong).toArray();
                ranges.add(
                        new Range(
                            map[0],
                            map[1],
                            map[2],
                            source,
                            destination
                        )
                );
            }

        }


    }

    public List<Range> getRanges() {
        return ranges;
    }

    public long[] getStartingPoints() {
        return startingPoints;
    }

    public Range getDestinationIndex(Types sourceType, long sourceIndex) {
        return getRanges().stream().filter(i -> i.getSourceType().getIndex() == sourceType.getIndex()
                && i.getSource() <= sourceIndex
                && i.getCalculatedEndSource() >= sourceIndex).findFirst().orElse(
                new Range(sourceIndex, sourceIndex, 1L, sourceType, sourceType.getNext())
        );
    }

    public List<Long> getExtremes(List<StartingRanges.StartAndEnd> startAndEnd) throws InterruptedException {
        List<Long> extremes = new ArrayList<>();
        List<Range> seeds = ranges.stream().filter(range ->
                range.getSourceType().getIndex() == 0
        ).toList();
        List<Thread> runningCalculations = new ArrayList<>();
        List<Threadable> val = new ArrayList<>();

        seeds.forEach(
                seed -> {
                    for(StartingRanges.StartAndEnd item : startAndEnd) {
                        long start = item.getStart() > seed.getSource() ? item.getStart() : seed.getSource();
                        long end = item.getEnd() > seed.getCalculatedEndSource() ? seed.getCalculatedEndSource() : item.getEnd();
                        if(end > start) {
                            Threadable threadable = new Threadable(start, end, this);
                            val.add(threadable);
                            Thread thread = new Thread(threadable);
                            runningCalculations.add(thread);
                            thread.start();
                        }
                    }
                }
        );

        for(Thread thread : runningCalculations) {
            thread.join();
        }
        for (Threadable thrd: val) {
            minValues.add(thrd.getMin());
        }
        return minValues;
    }

    private long checkItems(List<Long> extremes) {
        long returnMe = -1;
        for(long i : extremes) {
            Range item = getDestinationIndex(Types.SEED, i);
            long index = item.getDestinationIndex(i);
            do {
                item = getDestinationIndex(item.getDestinationType(), index);
                index = item.getDestinationIndex(index);
            } while (item.getDestinationType() != null);
            if(returnMe == -1 || returnMe > index)
                returnMe = index;
        }
        System.out.println("got this one");
        return returnMe;
    }
}
