package Aufgabe6;

/**
 * This class implements the interface UsableRoom. WindowRooms are usable rooms which have windows. WindowRooms can be used either as a bureau or as a storage.
 * Look into interface UsableRoom or Room for further information.
 */
public class WindowRoom implements UsableRoom{
    /**
     * Variable length is used to store the length of the room in m. Can only be positive or zero.
     * Not changeable.
     */
    private final float length;

    /**
     * Variable width is used to store the width of the room in m. Can only be positive or zero.
     * Not changeable.
     */
    private final float width;

    /**
     * This floating point number stores the room's window surface area. It can only be positive.
     * It is not changeable.
     */
    private final float windowArea;

    /**
     * This String stores the WindowRoom's unique name. The name is generated in the constructor and is not changeable.
     */
    private final String roomName;

    /**
     * This int stores the nameID of this WindowRoom. NameID's identify a room correctly, yet they remain
     * easy to memorize or understand. The room's name is generated by concatenation of this ID to the name of the room type, i.e. "WindowRoom".
     * This variable can only grow. If a room is removed from the office, its ID is never used again.
     */
    private static int roomID = 0;

    /**
     * This integer is the internal definition of the room's purpose. As only two possible purposes exist, 1 is used to
     * imply that the room is being used as a bureau, 2 is used to indicate the usage as storage. As the default value
     * is 0, this value signalizes that the room has not yet been dedicated to a specific purpose.
     * Purpose, once it has been modified, can only accept the values 1 or 2. If the value has not been set, the default value
     * is 0 signifying no purpose.
     */
    private int purpose;

    /**
     * This constructor initializes a new instance of WindowRoom. The window surface area is randomly generated
     * using the surface area of the room itself. The room's name is generated internally.
     * @param l >= 0; the length of this room
     * @param w >= 0; the width of this room
     */
    public WindowRoom(float l, float w){
       roomName =  getUniqueWindowRoomName();
       length = l;
       width = w;
       windowArea = (length*width) * (float) Math.random() + 0.1f; // generate a positive window surface area
    }

    /**
     * This method calculates the surface area of this entire WindowRoom.
     * @return a floating point number >=0, the surface area of this room in m^2.
     */
    @Override
    public float area() {
        return length*width;
    }

    /**
     * @return the length of this WindowRoom in m, >= 0
     */
    @Override
    public float getLength() {
        return length;
    }

    /**
     * @return the width of this WindowRoom in m, >=0
     */
    @Override
    public float getWidth() {
        return width;
    }

    /**
     * This method is called in order to get the unique and identifying name of this WindowRoom.
     * @return a String containing the unique name of this WindowRoom
     */
    @Override
    public String getName() {
        return roomName;
    }

    /**
     * Helper method to generate a unique roomName. This method can only be called once per instance.
     * @return a unique String to be used as roomName.
     */
    private synchronized String getUniqueWindowRoomName(){
        String ret = "WindowRoom-" + (++roomID);
        return ret;
    }

    /**
     * This method returns the window surface area of this WindowRoom in m^2.
     * @return the window surface area, float number > 0.
     */
    @Override
    public float getWindowArea() {
        return windowArea;
    }

    /**
     * This method always returns -1, as the luminous flux is not calculated for WindowRooms.
     * @return -1
     */
    @Override
    public int getLumen() {
        return -1;
    }

    /**
     * Given a UsableRoom, this method will change the room's designated purpose. Usable rooms can either be used as a bureau or as storage rooms.
     * Therefore, this method acts as a switch between these two purposes. The first call of this method sets the purpose to bureau.
     */
    @Override
    public void changePurpose() {
        if(purpose == 0){
            purpose = 1; //set purpose to "bureau"
            System.out.println("The purpose of window room " + getName() + " has been initially set to bureau.");
        } else if(purpose == 1){
            purpose = 2;
            System.out.println("The purpose of window room " + getName() + " has been set to storage.");
        } else if(purpose == 2){
            System.out.println("The purpose of window room " + getName() + " has been set to bureau.");
            purpose = 1;
        }

    }

    /**
     * Given a usable room with its purpose being a bureau, this method will return an int which represents the amount of
     * different workplaces inside this room. A call of this method in a usable room that does not serve as a bureau will always return 0.
     * @return an integer >=0 counting the amount of distinct workplaces
     */
    @Override
    public int getWorkplace() {
        if(purpose == 1){
            return (int) (length * width * Math.random()) +1; //Simulationslogik, zu vernachlässigen
        }
        else return 0;
    }

    /**
     * Given a usable room with its purpose being a storage room, this method will return the volume of this room in m^3.
     * A call of this method in a usable room that does not serve as a storage unit will always return 0.
     * @return a floating point number >=0 representing the max volume available for storage purposes in this room
     */
    @Override
    public float getStorage() {
        if(purpose == 2){
            return width*length*2.5f; // height of every room is set to 2.5m as per definition in "Room"
        }
        else return 0;
    }
}