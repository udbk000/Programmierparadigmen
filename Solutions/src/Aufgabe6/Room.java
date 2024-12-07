package Aufgabe6;

/**
 * This interface describes a room in an office with its functionality. Rooms can be subdivided into several types of Rooms, which will
 * be defined by subtypes of this interface. Rooms can have different purposes. One aspect that is applicable to each and
 * every room is its area. Rooms that implement this interface are always viewed as either rectangular or square-shaped in order
 * to keep the simulation simple and easily understandable. Additionally, the height of every room is set to 2.5 m.
 * Check all method descriptions in order to understand how method calls behave in different
 * subtypes of this interface.
 */
public interface Room {
    /**
     * This method calculates the area of the entire room.
     * @return Area in m^2 as a floating point number. Can only be positive or zero.
     */
    float area();

    /**
     * This method returns the length in m for every room it is called in.
     * @return the length of this room in m, length >=0
     */
    float getLength();

    /**
     * This method returns the width in m for every room it is called in.
     * @return the width of this room in m, width >= 0
     */
    float getWidth();

    /**
     * Usable Rooms get designated names. This method makes it possible to find out a usable room's name.
     * @return a String which represents the name of the usable room, null else
     */
    String getName();

    /**
     * Given a usable room with windows, a call of this method returns the surface area of all windows combined inside this usable room.
     * The unit is m^2.
     * @return a floating point number >=0 if the Room is usable, -1 else
     */
    float getWindowArea();

    /**
     * Given a usable room without windows, a call of this method returns the luminous flux in Lumen (lm).
     * @return the luminous flux in Lumen if the Room is a LightRoom, -1 else
     */
    int getLumen();

    /**
     * Given a usable room, this method will change the room's designated purpose. Usable rooms can either be used as a bureau or as storage rooms.
     * Therefore, this method acts as a switch between these two purposes. If the method is called in a room that is not a usable room, the method will not do anything.
     */
    void changePurpose();

    /**
     * Given a usable room with its purpose being a bureau, this method will return an int which represents the amount of
     * different workplaces inside this room. A call of this method in a usable room that does not serve as a bureau will always return 0.
     * @return an integer >=0 counting the amount of distinct workplaces, -1 if the room is not usable
     */
    int getWorkplace();

    /**
     * Given a usable room with its purpose being a storage room, this method will return the volume of this room in m^3.
     * A call of this method in a usable room that does not serve as a storage unit will always return 0.
     * @return a floating point number >=0 representing the max volume available for storage purposes in this room, -1 if the room is not usable
     */
    float getStorage();

}
