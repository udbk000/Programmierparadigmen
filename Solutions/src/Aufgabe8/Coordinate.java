package Aufgabe8;

import java.util.Objects;

/**
 * Die Klasse Coordinate repräsentiert eine Position im dreidimensionalen Raum (x, y, z).
 * Diese Klasse verwendet das `record`-Schlüsselwort, was bedeutet, dass sie unveränderlich (immutable) ist
 * und automatisch Konstruktoren, `equals`, `hashCode` und `toString` generiert.
 */
public record Coordinate(int x, int y, int z) {

    /**
     * Addiert die übergebene Koordinate zu der aktuellen Koordinate und gibt eine neue Koordinate zurück.
     * Da `record` unveränderlich ist, wird ein neues `Coordinate`-Objekt erstellt, anstatt das bestehende zu ändern.
     *
     * @param coordinate Die Koordinate, die zur aktuellen Koordinate addiert werden soll.
     * @return Eine neue `Coordinate`, die die Summe der beiden Koordinaten repräsentiert.
     */
    public Coordinate plus(Coordinate coordinate) {
        return new Coordinate(x + coordinate.x, y + coordinate.y, z + coordinate.z);
    }

    /**
     * Überschreibt die `hashCode`-Methode, um sicherzustellen, dass der Hash-Wert der Koordinate
     * basierend auf den x-, y- und z-Werten berechnet wird.
     * Dies ist besonders wichtig, wenn `Coordinate` als Schlüssel in Datenstrukturen wie `HashMap` verwendet wird.
     *
     * @return Der Hash-Wert der Koordinate.
     */
    @Override
    public int hashCode() {
        return Objects.hash(x, y, z);
    }
}
