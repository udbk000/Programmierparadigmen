package Aufgabe6;

/**
 * This class implements the interface UsableRoom. WindowRooms are usable rooms which have windows. WindowRooms can be used either as a bureau or as a storage.
 * Look into interface UsableRoom or Room for further information.
 */
public class WindowRoom implements UsableRoom{
    private float length;
    private float width;
    private String name;
    private float windowArea;



    @Override
    public float area() {
        return 0;
    }

    @Override
    public float getLength() {
        return 0;
    }

    @Override
    public float getWidth() {
        return 0;
    }

    @Override
    public String getName() {
        return null;
    }

    @Override
    public float getWindowArea() {
        return 0;
    }

    @Override
    public int getLumen() {
        return 0;
    }

    @Override
    public void changePurpose() {

    }

    @Override
    public int getWorkplace() {
        return 0;
    }

    @Override
    public float getStorage() {
        return 0;
    }
}
