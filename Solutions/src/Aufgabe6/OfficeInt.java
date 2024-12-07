package Aufgabe6;

/**
 * This interface defines an office. An office can consist of several rooms from the Room interface.
 * Every office has a unique and identifying number.
 */
public interface OfficeInt {

    int getOfficeNumber();

    float getSideRoomsArea();

    float getEntireArea();

    void addRoom(Room toAdd);

    void removeRoom();

    float getAvgAreaUsableRooms();

    float getAvgAreaWindowRooms();

    float getAvgAreaLightRooms();

    float getAvgAreaStorage();

    float getAvgWorkspace();

    float getAvgRelationWindowToArea();

    float getvAvgRelationLightToArea();
}
