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

    Office() {
        officeNum++;
    }

    @Override
    public int getOfficeNumber() {
        return officeNum;
    }

    @Override
    public float getSideRoomsArea() {
        return sideRooms.getArea();
    }

    @Override
    public float getEntireArea() {
        return sideRooms.getArea() + windowRooms.getArea() + lightRooms.getArea();
    }

    @Override
    public void addRoom(Room toAdd) {
        if(toAdd instanceof SideRoom) {
            sideRooms.addLast(toAdd);
        }
        else if (toAdd instanceof WindowRoom) {
            windowRooms.addLast(toAdd);
        }
        else if (toAdd instanceof LightRoom) {
            lightRooms.addLast(toAdd);
        }
        else {
            throw new IllegalArgumentException("Illegal room type");
        }
    }

    @Override
    public void removeRoom(Room toRemove) {
        if(toRemove instanceof SideRoom) {
            sideRooms.remove(toRemove);
        }
        else if (toRemove instanceof WindowRoom) {
            windowRooms.remove(toRemove);
        }
        else if (toRemove instanceof LightRoom) {
            lightRooms.remove(toRemove);
        }
        else {
            throw new IllegalArgumentException("Illegal room type");
        }
    }

    @Override
    public float getAvgAreaUsableRooms() {
        int usableRoomsCount = windowRooms.getCount() + lightRooms.getCount();
        float usableRoomsArea = windowRooms.getArea() + lightRooms.getArea();
        if(usableRoomsCount == 0)
            return 0;
        return usableRoomsArea/usableRoomsCount;
    }

    @Override
    public float getAvgAreaWindowRooms() {
        int windowRoomCount = windowRooms.getCount();
        float windowRoomArea = windowRooms.getArea();
        if(windowRoomCount == 0)
            return 0;
        return windowRoomArea/windowRoomCount;
    }

    @Override
    public float getAvgAreaLightRooms() {
        int lightRoomCount = lightRooms.getCount();
        float lightRoomArea = lightRooms.getArea();
        if(lightRoomCount == 0)
            return 0;
        return lightRoomArea/lightRoomCount;
    }

    @Override
    public float getAvgAreaStorage() {
        int storageCount = sideRooms.getCount();
        if(storageCount == 0)
            return 0;
        float storageArea = sideRooms.getStorageSum();
        return storageArea/storageCount;
    }

    @Override
    public int getAvgWorkspace() {
        int workspaceCount = windowRooms.getCount() + lightRooms.getCount();
        if(workspaceCount == 0)
            return 0;
        int workspaceArea = windowRooms.getWorkplaceSum();
        return workspaceArea/workspaceCount;
    }

    @Override
    public float getAvgRelationWindowToArea() {
        return windowRooms.getWindowToArea();
    }

    @Override
    public float getAvgRelationLightToArea() {
        return lightRooms.getLightToArea();
    }
}
