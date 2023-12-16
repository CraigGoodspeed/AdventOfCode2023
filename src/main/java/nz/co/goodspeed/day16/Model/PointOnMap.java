package nz.co.goodspeed.day16.Model;

public enum PointOnMap {

    VERTICAL_SPLITTER('|'),
    HORIZONTAL_SPLITTER('-'),
    MIRROR_FORWARD('/'),
    MIRROR_BACKWARD('\\'),
    EMPTY('.');

    final char myCharacter;

    PointOnMap(char myCharacter) {
        this.myCharacter = myCharacter;
    }

    public static PointOnMap parseCharacter(char character) {
        for(PointOnMap item: PointOnMap.values()) {
            if(character  == item.myCharacter) {
                return item;
            }
        }
        return null;
    }

    public char getMyCharacter() {
        return myCharacter;
    }
}
