package Aufgabe8;

/**
 * Cube.java - Einfache Klasse zur Darstellung eines Würfels.
 */
public class Cube {
    public int x;
    public int y;
    public int z;

    public Cube(int x, int y, int z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    /**
     * Überprüft, ob der aktuelle Würfel einen anderen Würfel stützt.
     *
     * @param other Der andere Würfel.
     * @return true, wenn dieser Würfel den anderen stützt.
     */
    public boolean isSupporting(Cube other) {
        return this.x == other.x && this.y == other.y && this.z == other.z - 1;
    }
}
