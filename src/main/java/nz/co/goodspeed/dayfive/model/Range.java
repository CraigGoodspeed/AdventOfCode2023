package nz.co.goodspeed.dayfive.model;

public class Range {
    long destination;
    long source;
    long range;
    long calculatedEndDestination;
    long calculatedEndSource;
    Types sourceType;
    Types destinationType;

    public Range(long destination, long source, long range, String sourceType, String destinationType) {
        this.destination = destination;
        this.range = range;
        this.source = source;
        this.sourceType = Types.valueOf(sourceType.toUpperCase());
        this.destinationType = Types.valueOf(destinationType.toUpperCase());
        this.calculatedEndDestination = this.destination + this.range - 1;
        this.calculatedEndSource = this.source + this.range - 1;
    }

    public Range(long destination, long source, long range, Types sourceType, Types destinationType) {
        this.destination = destination;
        this.range = range;
        this.source = source;
        this.sourceType = sourceType;
        this.destinationType = destinationType;
        this.calculatedEndDestination = this.destination + this.range - 1;
        this.calculatedEndSource = this.source + this.range - 1;
    }

    public long getDestination() {
        return destination;
    }

    public long getSource() {
        return source;
    }

    public long getRange() {
        return range;
    }

    public long getCalculatedEndDestination() {
        return calculatedEndDestination;
    }

    public long getCalculatedEndSource() {
        return calculatedEndSource;
    }

    public Types getSourceType() {
        return sourceType;
    }

    public Types getDestinationType() {
        return destinationType;
    }

    public long getDestinationIndex(long sourceValue) {
        return getDestination() + (sourceValue - getSource());
    }

    public long getSourceIndex(long destinationIndex) {
        return destinationIndex + (getSource() - getDestination());
    }
}
