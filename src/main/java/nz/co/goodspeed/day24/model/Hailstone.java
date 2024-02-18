package nz.co.goodspeed.day24.model;

public class Hailstone {
    long positionX,positionY,positionZ;
    long velocityX, velocityY, velocityZ;

    public Hailstone(long positionX, long positionY, long positionZ, long velocityX, long velocityY, long velocityZ) {
        this.positionX = positionX;
        this.positionY = positionY;
        this.positionZ = positionZ;
        this.velocityX = velocityX;
        this.velocityY = velocityY;
        this.velocityZ = velocityZ;
    }

    public long getPositionX() {
        return positionX;
    }

    public void setPositionX(long positionX) {
        this.positionX = positionX;
    }

    public long getPositionY() {
        return positionY;
    }

    public void setPositionY(long positionY) {
        this.positionY = positionY;
    }

    public long getPositionZ() {
        return positionZ;
    }

    public void setPositionZ(long positionZ) {
        this.positionZ = positionZ;
    }

    public long getVelocityX() {
        return velocityX;
    }

    public void setVelocityX(long velocityX) {
        this.velocityX = velocityX;
    }

    public long getVelocityY() {
        return velocityY;
    }

    public void setVelocityY(long velocityY) {
        this.velocityY = velocityY;
    }

    public long getVelocityZ() {
        return velocityZ;
    }

    public void setVelocityZ(long velocityZ) {
        this.velocityZ = velocityZ;
    }

    public void moveNanoSeconds(long nanoSeconds) {
        setPositionX(
                getPositionX()
                +
                (nanoSeconds * getVelocityX())
        );

        setPositionY(
                getPositionY()
                        +
                        (nanoSeconds * getVelocityY())
        );

        setPositionZ(
                getPositionZ()
                        +
                        (nanoSeconds * getVelocityZ())
        );
    }

    public long calculateCollisionNanos(Hailstone collidesWith) {
        //simple linear algebra, a = 2x + 2y
        //when we calculate collision we equate a = a
        return -1l;
    }
}
