package Aufgabe6;

/**
 * This class is a generic implementation of the OfficeInt interface. Methods behave as described in OfficeInt.
 * @param <X> a subtype of Room
 */
public class OfficeGen<X extends Room> implements OfficeInt{
    /**
     * This list contains all rooms inside this office.
     */
    MyList<X> RoomList = new MyList<>();

    /**
     * This variable is unique for each and every office, identifying every office. This variable can only grow, therefore it is always >= 0.
     * Once an office is removed, the officeNum is never used again.
     */
    private static int officeNum = 0;


    @Override
    public int getOfficeNumber() {
        return 0;
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

    }

    @Override
    public void removeRoom(Room toRemove) {

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
