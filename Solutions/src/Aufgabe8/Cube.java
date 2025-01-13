package Aufgabe8;

/**
 * Die Klasse Cube repräsentiert einen Würfel im dreidimensionalen Raum mit den Koordinaten (x, y, z).
 * Ein Würfel kann sich an einer bestimmten Position befinden und andere Würfel "stützen".
 */
public class Cube {
    // Attribute zur Speicherung der Position des Würfels im Raum.
    public int x; // x-Koordinate
    public int y; // y-Koordinate
    public int z; // z-Koordinate

    /**
     * Konstruktor: Erstellt einen neuen Würfel mit den angegebenen Koordinaten (x, y, z).
     *
     * @param x Die x-Koordinate des Würfels.
     * @param y Die y-Koordinate des Würfels.
     * @param z Die z-Koordinate des Würfels.
     */
    public Cube(int x, int y, int z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    /**
     * Überprüft, ob der aktuelle Würfel den anderen Würfel "stützt".
     * Ein Würfel stützt einen anderen, wenn er direkt unter ihm liegt (gleiche x- und y-Koordinate, z-Koordinate ist um 1 kleiner).
     *
     * @param other Der andere Würfel, der geprüft wird.
     * @return true, wenn dieser Würfel den anderen Würfel stützt, sonst false.
     */
    public boolean isSupporting(Cube other) {
        return this.x == other.x    // Gleiche x-Koordinate
                && this.y == other.y  // Gleiche y-Koordinate
                && this.z == other.z - 1; // Liegt genau unter dem anderen Würfel (z - 1)
    }
}
