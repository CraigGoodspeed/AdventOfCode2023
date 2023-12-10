package nz.co.goodspeed.dayten.model;

import java.util.function.BiFunction;
import java.util.function.Function;

public enum ConnectionPoint {

    NORTH_TO_SOUTH('|',
            (a,b) -> {
                if(a.getX() > b.getX()) {
                    return new PointHelper(1,0);
                }
                return new PointHelper(-1, 0);
            }
        ),
    EAST_TO_WEST('-', (a,b) -> {
        if(a.getY() > b.getY()) {
            return new PointHelper(0,1);
        }
        return new PointHelper(0, -1);
    }),
    NORTH_TO_EAST('L',(a,b) -> {
        if(a.getY() < b.getY())
            return new PointHelper(-1, 0);
        return new PointHelper(0,1);
    }),
    NORTH_TO_WEST('J',
            (a,b) -> {
                if(a.getX() > b.getX()) //come in from the north! --> head west
                    return new PointHelper(0, -1);
                return new PointHelper(-1, 0);
            }
    ),
    SOUTH_TO_WEST('7', (a,b) -> {
        if(a.getY() > b.getY()) //came in from west, head south
            return new PointHelper(1, 0);
        return new PointHelper(0,-1);
    }),
    SOUTH_TO_EAST('F',
            (a,b) -> {
                if(a.getY() < b.getY()) //come from left -- head south
                    return new PointHelper(1, 0);
                return new PointHelper(0, 1);
            }),

    GROUND('.', null),
    START('S', null);

    final char symbol;

    final BiFunction<PointHelper, PointHelper, PointHelper> isInversed;

    ConnectionPoint(char symbol, BiFunction<PointHelper, PointHelper, PointHelper> fn) {
        this.symbol = symbol;
        this.isInversed = fn;
    }

    public char getSymbol() {
        return this.symbol;
    }

    public static ConnectionPoint parseSymbol(char symbol) {
        for (ConnectionPoint cp : ConnectionPoint.values()) {
            if(cp.getSymbol() == symbol)
                return cp;
        }
        return null;
    }

    public PointHelper getDestinationPoint(PointHelper sourcePoint, PointHelper currentPoint) {
        if(isInversed == null)
            return null;

        PointHelper movement = isInversed.apply(currentPoint,sourcePoint);
        return new PointHelper(currentPoint.getX() + movement.getX(),  currentPoint.getY() + movement.getY());
    }
}
