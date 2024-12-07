package Aufgabe6;

/**
 * A non-generic implementation of OfficeInt. Methods behave as described in OfficeInt.
 */
public class Office implements OfficeInt {
    RoomList sideRooms;
    RoomList windowRooms;
    RoomList lightRooms;

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
