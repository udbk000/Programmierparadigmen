package Aufgabe8;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Die Klasse Block repräsentiert einen Würfel im Raum mit einer festen Position.
 * Ein Block hat Nachbarseiten ("sidePartsOfBlock"), eine Oberseite ("top") und eine Unterseite ("bottom"),
 * die Verbindungen zu anderen Blöcken darstellen.
 */
public class Block {
    // Die Position des Blocks im Raum (als Koordinate).
    private final Coordinate position;

    // Map, die die Nachbarn auf den Seiten speichert. Der Boolean-Wert gibt an, ob diese Seite mit einem Nachbarblock verbunden ist.
    private final Map<Coordinate, Boolean> sidePartsOfBlock;

    // Die Oberseite des Blocks mit der zugehörigen Koordinate und einem Verbindungsstatus.
    private Map.Entry<Coordinate, Boolean> top;

    // Die Unterseite des Blocks mit der zugehörigen Koordinate und einem Verbindungsstatus.
    private Map.Entry<Coordinate, Boolean> bottom;

    /**
     * Konstruktor: Initialisiert einen neuen Block mit einer bestimmten Position.
     * Die Nachbarn (oben, unten, und die Seiten) werden mit der Ausgangsposition berechnet.
     *
     * @param position Die Koordinate, an der der Block platziert wird.
     */
    public Block(Coordinate position) {
        this.position = position;

        // Initialisierung der Seitenpositionen relativ zur Blockposition.
        this.sidePartsOfBlock = new HashMap<>();
        this.sidePartsOfBlock.put(position.plus(new Coordinate(0, 1, 0)), false);  // rechte Seite
        this.sidePartsOfBlock.put(position.plus(new Coordinate(1, 0, 0)), false);  // vordere Seite
        this.sidePartsOfBlock.put(position.plus(new Coordinate(0, -1, 0)), false); // linke Seite
        this.sidePartsOfBlock.put(position.plus(new Coordinate(-1, 0, 0)), false); // hintere Seite

        // Ober- und Unterseite initialisieren
        this.top = Map.entry(position.plus(new Coordinate(0, 0, 1)), false);    // obere Seite
        this.bottom = Map.entry(position.plus(new Coordinate(0, 0, -1)), false); // untere Seite
    }

    /**
     * Gibt einen Stream der Einträge (Koordinate, Boolean) zurück,
     * die alle seitlichen Nachbarn des Blocks darstellen.
     *
     * @return Stream mit den Einträgen der Seiten des Blocks.
     */
    public Stream<Map.Entry<Coordinate, Boolean>> getSidePartsOfBlock() {
        return sidePartsOfBlock.entrySet().stream();
    }

    /**
     * Ermittelt die möglichen Positionen, an denen ein neuer Block platziert werden kann.
     * Ein Block kann platziert werden, wenn die Position noch nicht belegt ist,
     * eine gültige Platzierung bezüglich der Z-Koordinate vorliegt und
     * die maximale Höhe (m) nicht überschritten wird.
     *
     * @param alreadySet Die bereits gesetzten Positionen.
     * @param m          Die maximale Höhe (Z-Koordinate).
     * @return Liste der möglichen Positionen.
     */
    public List<Coordinate> possibleStates(Set<Coordinate> alreadySet, int m) {
        List<Coordinate> allSides = new ArrayList<>(sidePartsOfBlock.keySet());
        allSides.add(top.getKey()); // Fügt die Oberseite zur Liste der möglichen Positionen hinzu.

        // Filtert alle Positionen, die frei, gültig platziert und unterhalb der maximalen Höhe sind.
        return allSides.stream()
                .filter(pos -> !alreadySet.contains(pos)           // Position ist noch nicht belegt
                        && validZPlacement(pos, alreadySet)        // Unterbau vorhanden
                        && pos.z() < m)                            // Höhe innerhalb des Limits
                .collect(Collectors.toList());
    }

    /**
     * Fügt einen benachbarten Block hinzu und verbindet den aktuellen Block mit diesem Nachbarn.
     * Aktualisiert den Status der entsprechenden Nachbarseite auf "true".
     *
     * @param block Der benachbarte Block, der hinzugefügt werden soll.
     */
    public void addNeighbour(Block block) {
        // Verhindert Selbstverbindungen
        if (block.position.equals(position)) return;

        // Verbindung mit der Oberseite
        if (block.position.equals(top.getKey()) && !top.getValue()) {
            top = Map.entry(top.getKey(), true);
        }
        // Verbindung mit der Unterseite
        else if (block.position.equals(bottom.getKey()) && !bottom.getValue()) {
            bottom = Map.entry(bottom.getKey(), true);
        }
        // Verbindung mit einer seitlichen Nachbarschaftsposition
        else if (sidePartsOfBlock.containsKey(block.position) && !sidePartsOfBlock.get(block.position)) {
            sidePartsOfBlock.put(block.position, true);
        }
    }

    /**
     * Überprüft, ob die Z-Platzierung für eine Position gültig ist.
     * Eine Position ist gültig, wenn sie entweder auf der Ebene z=0 liegt oder
     * unter ihr bereits ein Block vorhanden ist.
     *
     * @param position   Die zu prüfende Position.
     * @param alreadySet Die bereits gesetzten Koordinaten.
     * @return true, wenn die Platzierung gültig ist, sonst false.
     */
    private static boolean validZPlacement(Coordinate position, Set<Coordinate> alreadySet) {
        if (position.z() == 0) return true; // Auf dem Boden platzieren ist immer erlaubt.
        var down = new Coordinate(0, 0, -1); // Position unterhalb (in z-Richtung -1)
        return alreadySet.contains(position.plus(down)); // Überprüfen, ob unterhalb ein Block existiert.
    }

    /**
     * Gibt die Position des Blocks zurück.
     *
     * @return Die Position des Blocks als Coordinate.
     */
    public Coordinate getPosition() {
        return position;
    }
}
