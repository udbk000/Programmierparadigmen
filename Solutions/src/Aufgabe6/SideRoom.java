package Aufgabe6;

/**
 * This class describes a "SideRoom", which refers to a secondary room which does not serve a specific purpose.
 * There is not much information needed from such rooms. Examples of such rooms in an office could be a hallway, restrooms, etc.
 * The only information that these rooms provide is the area of such room.
 */
public class SideRoom implements Room {
    private float length; //the length of this room, length >= 0
    private float width; //the width of this room, width >= 0

    /**
     * This constructor initializes this as a SideRoom. The only necessary information for a SideRoom are its length and with.
     * @param l defines the length of this room, must be a floating point number, l >= 0
     * @param w defines the width of this room, must be a floating point number, w >= 0
     */
    public SideRoom(float l, float w){
        length = l;
        width = w;
    }

    /**
     * This method calculates the area of this SideRoom.
     * @return the area of this SideRoom in m^2. Can only be positive or zero.
     */
    @Override
    public float area() {
        return length*width;
    }

    @Override
    public float getLength() {
        return length;
    }

    @Override
    public float getWidth() {
        return width;
    }


    /**
     * This method is not supported for a SideRoom.
     * @return null
     */
    @Override
    public String getName() {
        return null;
    }

    /**
     * This method is not supported for a SideRoom.
     * @return -1
     */
    @Override
    public float getWindowArea() {

        return -1;
    }

    /**
     * This method is not supported for a SideRoom.
     * @return -1
     */
    @Override
    public int getLumen() {

        return -1;
    }

    /**
     * This method is not supported for a SideRoom.
     */
    @Override
    public void changePurpose() {
        ;
    }

    /**
     * This method is not supported for a SideRoom.
     * @return -1
     */
    @Override
    public int getWorkplace() {
        return -1;
    }

    /**
     * This method is not supported for a SideRoom.
     * @return -1
     */
    @Override
    public float getStorage() {
        return -1;
    }
}
