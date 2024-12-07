package Aufgabe6;

/**
 * This interface defines an office. An office can consist of several rooms from the Room interface.
 * Every office has a unique and identifying number.
 */
public interface OfficeInt {
    /**
     * This method returns the unique number of this office.
     * @return unique int for this office, >0
     */
    int getOfficeNumber();

    /**
     * This method returns the total area of all SideRooms inside this office.
     * @return total surface area of all SideRooms in this office, in m^2, >=0
     */
    float getSideRoomsArea();

    /**
     * THis method returns the entire surface area of this office in m^2.
     * @return total surface area of entire office in m^2 >= 0
     */
    float getEntireArea();

    /**
     * This method adds a new room to this office.
     * @param toAdd a Room that is being added to this office, != null
     */
    void addRoom(Room toAdd);

    /**
     * This method removes the given room. If this Room is not part of this office, nothing happens.
     * @param toRemove the Room being removed from this office. != null
     */
    void removeRoom(Room toRemove);

    /**
     * This method calculates the average surface area of all usable rooms in this office in m^2
     * @return the average area of the usable rooms in this office in m^2, >=0
     */
    float getAvgAreaUsableRooms();

    /**
     * This method calculates the average area of all WindowRooms in this office in m^2
     * @return the average area of WindowRooms in this office in m^2, >= 0
     */
    float getAvgAreaWindowRooms();

    /**
     * This method calculates the average area of all LightRooms in this office in m^2
     * @return the average area of LightRooms in this office in m^2, >= 0
     */
    float getAvgAreaLightRooms();

    /**
     * This method calculates the average area of storage rooms in this office in m^2.
     * @return the average area of rooms serving as storage in this office in m^2, >= 0
     */
    float getAvgAreaStorage();

    /**
     * This method calculates the average amount of workspaces in all bureaus in this office.
     * @return the average amount of workspaces in all bureaus in this office, >= 0
     */
    int getAvgWorkspace();

    /**
     * This method calculates the average ratio between window surface area and actual surface area in all WindowRooms
     * in this office. If the room is a lightroom, the result is 0.
     * @return the ratio between window surface area and actual surface area in all WindowRooms, >= 0
     */
    float getAvgRelationWindowToArea();

    /**
     * This method calculates the average ratio between luminous flux and area in all LightRooms. The result is given in Lux.
     * @return the ratio between luminous flux and area in all LightRooms in Lux, >= 0
     */
    float getAvgRelationLightToArea();
}
