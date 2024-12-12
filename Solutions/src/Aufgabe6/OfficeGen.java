package Aufgabe6;

/**
 * This class is a generic implementation of the OfficeInt interface. Methods behave as described in OfficeInt.
 * @param <X> a subtype of Room
 */
public class OfficeGen<X extends Room> implements OfficeInt{
    /**
     * This list contains all rooms inside this office.
     */
    RoomList roomList = new RoomList(null);

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
        return roomList.getAvgSideArea();
    }

    @Override
    public float getEntireArea() {
        return roomList.getEntireArea();
    }

    @Override
    public void addRoom(Room toAdd) {
        roomList.addLast(toAdd);

    }

    @Override
    public void removeRoom(Room toRemove) {
        roomList.remove(toRemove);
    }

    @Override
    public float getAvgAreaUsableRooms() {
        return roomList.getAvgUsableArea();
    }

    @Override
    public float getAvgAreaWindowRooms() {
        return roomList.getAvgWindowroomArea();
    }

    @Override
    public float getAvgAreaLightRooms() {
        return roomList.getAvgLightroomArea();
    }

    @Override
    public float getAvgAreaStorage() {
        return roomList.getAvgStorageArea();
    }

    @Override
    public int getAvgWorkspace() {
        return roomList.getAvgWorklpaceArea();
    }

    @Override
    public float getAvgRelationWindowToArea() {
        return roomList.getWindowToArea();
    }

    @Override
    public float getAvgRelationLightToArea() {
        return roomList.getLightToArea();
    }
}
