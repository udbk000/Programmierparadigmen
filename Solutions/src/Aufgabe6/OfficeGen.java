package Aufgabe6;

/**
 * This class is a generic implementation of the OfficeInt interface. Methods behave as described in OfficeInt.
 * @param <X> a subtype of Room
 */
public class OfficeGen<X extends Room> implements OfficeInt{
    /**
     * This list contains all rooms inside this office.
     */
    MyList<X, X, X> RoomList = new MyList<>();

    /**
     * This method returns a unique integer identifying this Office.
     * @return a unique integer
     */
    @Override
    public int getOfficeNumber() {
        return hashCode();

    }

    @Override
    public float getSideRoomsArea() {
        return 0;
    }

    @Override
    public float getEntireArea() {
        return 0;
    }

    @Override
    public void addRoom(Room toAdd) {
        RoomList.add((X) toAdd);

    }

    @Override
    public void removeRoom(Room toRemove) {
        RoomList.remove((X) toRemove);

    }

    @Override
    public float getAvgAreaUsableRooms() {
        return 0;
    }

    @Override
    public float getAvgAreaWindowRooms() {
        return 0;
    }

    @Override
    public float getAvgAreaLightRooms() {
        return 0;
    }

    @Override
    public float getAvgAreaStorage() {
        return 0;
    }

    @Override
    public int getAvgWorkspace() {
        return 0;
    }

    @Override
    public float getAvgRelationWindowToArea() {
        return 0;
    }

    @Override
    public float getAvgRelationLightToArea() {
        return 0;
    }
}
