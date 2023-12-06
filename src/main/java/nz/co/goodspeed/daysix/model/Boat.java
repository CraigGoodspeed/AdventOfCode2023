package nz.co.goodspeed.daysix.model;

public class Boat {
    long distance, time;

    public Boat(long distance, long time) {
        this.distance = distance;
        this.time = time;
    }

    public int countTheWins() {
        int toReturn = 0;
        for(long i = 1; i < time; i++) {
            if(isAWin(i))
                toReturn++;
        }
        return toReturn;
    }

    public boolean isAWin(long charge) {
        long speed = charge;
        return ((time - charge) * (speed)) - distance > 0;
    }
}
