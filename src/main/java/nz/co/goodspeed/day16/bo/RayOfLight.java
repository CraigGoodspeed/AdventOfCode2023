package nz.co.goodspeed.day16.bo;

import nz.co.goodspeed.day16.Model.Coordinates;
import nz.co.goodspeed.day16.Model.Direction;
import nz.co.goodspeed.day16.Model.Map;
import nz.co.goodspeed.day16.Model.PointOnMap;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class RayOfLight {
    Coordinates currentLocation;
    Coordinates previousLocation;
    Set<Coordinates> history;

    public RayOfLight(Coordinates currentLocation, Coordinates previousLocation) {
        this.currentLocation = currentLocation;
        this.previousLocation = previousLocation;
        history = new HashSet<>();
    }

    public void travel(Map map, Direction heading) {
        history.add(currentLocation);
        PointOnMap currentPoint = map.getPointOnMap(currentLocation);
        List<Direction> newDirectionBasedOnCurrentDirectionAndPoint =
            calculateNewDirection(heading, currentPoint);
        this.previousLocation = this.currentLocation;
        //only ever two from new Direction, so we dont need to loop
        //lets first walk though my path
        //then walk through "friends"
        if(newDirectionBasedOnCurrentDirectionAndPoint != null) {
            if(newDirectionBasedOnCurrentDirectionAndPoint.size() > 1) {
                Coordinates nextStep = calculateNextStepFromLocationAndDirection(
                        newDirectionBasedOnCurrentDirectionAndPoint.get(1),
                        this.currentLocation
                );
                if(map.isOnMap(nextStep) && neverBeenThatWayBefore(nextStep)) {
                    RayOfLight nextItem = new RayOfLight(
                            nextStep,
                            this.currentLocation
                    );
                    nextItem.setHistory(this.getHistory());
                    nextItem.travel(map, newDirectionBasedOnCurrentDirectionAndPoint.get(1));
                    history.addAll(nextItem.getHistory());
                }
            }
            Coordinates nextStep = calculateNextStepFromLocationAndDirection(
                    newDirectionBasedOnCurrentDirectionAndPoint.get(0),
                    this.currentLocation
            );
            if(map.isOnMap(nextStep) && neverBeenThatWayBefore(nextStep)) {
                this.previousLocation = this.currentLocation;
                this.currentLocation = nextStep;
                travel(map,newDirectionBasedOnCurrentDirectionAndPoint.get(0));
            }

        }
    }

    private boolean neverBeenThatWayBefore(Coordinates coordinates) {
        return !this.getHistory().contains(coordinates);
    }

    private Coordinates calculateNextStepFromLocationAndDirection(Direction direction, Coordinates currentLocation) {
        Coordinates toReturn = new Coordinates(currentLocation.getX(), currentLocation.getY(), direction);
        Coordinates addMe =
        switch (direction) {
            case TOP_TO_BOTTOM -> new Coordinates(1,0, null);
            case BOTTOM_TO_TOP -> new Coordinates(-1, 0, null);
            case RIGHT_TO_LEFT -> new Coordinates(0, -1, null);
            case LEFT_TO_RIGHT -> new Coordinates(0,1, null);
        };

        toReturn.setX(toReturn.getX() + addMe.getX());
        toReturn.setY(toReturn.getY() + addMe.getY());

        return toReturn;

    }

    private List<Direction> calculateNewDirection(Direction direction, PointOnMap currentPoint) {
        if(currentPoint == PointOnMap.EMPTY) {
            return List.of(direction);
        }

        if(direction == Direction.TOP_TO_BOTTOM) {
            if(currentPoint == PointOnMap.MIRROR_FORWARD) {
                return List.of(Direction.RIGHT_TO_LEFT);
            } else if (currentPoint == PointOnMap.MIRROR_BACKWARD) {
                return List.of(Direction.LEFT_TO_RIGHT);
            } else if (currentPoint == PointOnMap.VERTICAL_SPLITTER) {
                return List.of(Direction.TOP_TO_BOTTOM);
            } else if(currentPoint == PointOnMap.HORIZONTAL_SPLITTER) {
                return List.of(Direction.RIGHT_TO_LEFT, Direction.LEFT_TO_RIGHT);
            }
        }
        else if (direction == Direction.BOTTOM_TO_TOP) {
            if(currentPoint == PointOnMap.MIRROR_FORWARD) {
                return List.of(Direction.LEFT_TO_RIGHT);
            } else if (currentPoint == PointOnMap.MIRROR_BACKWARD) {
                return List.of(Direction.RIGHT_TO_LEFT);
            } else if (currentPoint == PointOnMap.VERTICAL_SPLITTER) {
                return List.of(Direction.BOTTOM_TO_TOP);
            } else if(currentPoint == PointOnMap.HORIZONTAL_SPLITTER) {
                //spawnAnewRayOfLight --> left to right
                return List.of(Direction.RIGHT_TO_LEFT, Direction.LEFT_TO_RIGHT);
            }
        }
        else if (direction == Direction.LEFT_TO_RIGHT) {
            if(currentPoint == PointOnMap.MIRROR_FORWARD) {
                return List.of(Direction.BOTTOM_TO_TOP);
            } else if (currentPoint == PointOnMap.MIRROR_BACKWARD) {
                return List.of(Direction.TOP_TO_BOTTOM);
            } else if (currentPoint == PointOnMap.VERTICAL_SPLITTER) {
                //spawnAnewRayOfLight --> top to bottom
                return List.of(Direction.BOTTOM_TO_TOP, Direction.TOP_TO_BOTTOM);
            } else if(currentPoint == PointOnMap.HORIZONTAL_SPLITTER) {
                return List.of(Direction.LEFT_TO_RIGHT);
            }
        }
        else if (direction == Direction.RIGHT_TO_LEFT) {
            if(currentPoint == PointOnMap.MIRROR_FORWARD) {
                return List.of(Direction.TOP_TO_BOTTOM);
            } else if (currentPoint == PointOnMap.MIRROR_BACKWARD) {
                return List.of(Direction.BOTTOM_TO_TOP);
            } else if (currentPoint == PointOnMap.VERTICAL_SPLITTER) {
                return List.of(Direction.BOTTOM_TO_TOP, Direction.TOP_TO_BOTTOM);
            } else if(currentPoint == PointOnMap.HORIZONTAL_SPLITTER) {
                return List.of(Direction.RIGHT_TO_LEFT);
            }
        }
        return null;
    }

    protected Direction calculateDirection() {
        if(previousLocation == null) {
            return Direction.LEFT_TO_RIGHT;
        }
        int x = currentLocation.getX() - previousLocation.getX();
        int y = currentLocation.getY() - previousLocation.getY();

        if(x == 0 && y > 0) {
            return Direction.LEFT_TO_RIGHT;
        }
        else if (
                x == 0 && y < 0
        ) {
            return Direction.RIGHT_TO_LEFT;
        }
        else if(y == 0 &&  x > 0) {
            return Direction.TOP_TO_BOTTOM;
        }
        else if(y == 0 && x < 0) {
            return Direction.BOTTOM_TO_TOP;
        }
        return null;
    }

    public Set<Coordinates> getHistory() {
        return history;
    }

    public void setHistory(Set<Coordinates> history) {
        this.history = history;
    }
}
