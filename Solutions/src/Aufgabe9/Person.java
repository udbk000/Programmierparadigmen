package Aufgabe9;

import java.io.Serializable;
/**
 * Repräsentiert eine Person in der Fluchtweg-Simulation.
 * Jede Person hat zwei Füße auf benachbarten Feldern, eine ID,
 * sowie Zähler für Bewegungen und Warteschritte.
 */
public class Person implements Serializable {
    private final int id;     // Eindeutige ID der Person
    private int moves;        // Anzahl der erfolgreichen Bewegungen
    private int waits;        // Anzahl der Warteschritte
    private int xL, yL;       // Position des linken Fußes
    private int xR, yR;       // Position des rechten Fußes

    /**
     * Erstellt eine neue Person mit ihrer ID und Startpositionen.
     *
     * @param id  Eindeutige Identifikationsnummer der Person (muss positiv sein).
     * @param xL  X-Koordinate des linken Fußes (muss >= 0 sein).
     * @param yL  Y-Koordinate des linken Fußes (muss >= 0 sein).
     * @param xR  X-Koordinate des rechten Fußes (muss >= 0 sein).
     * @param yR  Y-Koordinate des rechten Fußes (muss >= 0 sein).
     *
     * Vorbedingung: `xL`, `yL`, `xR`, `yR` müssen benachbarte Felder beschreiben.
     * Nachbedingung: Die Person ist mit den angegebenen Startwerten initialisiert.
     */
    public Person(int id, int xL, int yL, int xR, int yR) {
        this.id = id;
        this.moves = 0; // Anfangs keine Bewegungen
        this.waits = 0; // Anfangs keine Warteschritte
        this.xL = xL;   // X-Position des linken Fußes
        this.yL = yL;   // Y-Position des linken Fußes
        this.xR = xR;   // X-Position des rechten Fußes
        this.yR = yR;   // Y-Position des rechten Fußes
    }

    /**
     * Bewegt die Person auf neue benachbarte Felder.
     *
     * @param newXL X-Koordinate des linken Fußes (muss gültig sein).
     * @param newYL Y-Koordinate des linken Fußes (muss gültig sein).
     * @param newXR X-Koordinate des rechten Fußes (muss gültig sein).
     * @param newYR Y-Koordinate des rechten Fußes (muss gültig sein).
     *
     * Vorbedingung: Die neuen Koordinaten müssen benachbarte Felder beschreiben.
     * Nachbedingung: Die Position der Füße ist aktualisiert, und der Bewegungscounter wurde erhöht.
     */
    public synchronized void move(int newXL, int newYL, int newXR, int newYR) {
        this.xL = newXL;
        this.yL = newYL;
        this.xR = newXR;
        this.yR = newYR;
        this.moves++; // Bewegungsschritt zählen
    }

    /**
     * Inkrementiert den Warteschrittzähler der Person.
     *
     * Nachbedingung: Der Warteschrittzähler wurde um 1 erhöht.
     */
    public synchronized void waitStep() {
        this.waits++;
    }

    /**
     * Gibt die Anzahl der Bewegungen der Person zurück.
     *
     * @return Bewegungszähler.
     */
    public int getMoves() {
        return moves;
    }

    /**
     * Gibt die Anzahl der Warteschritte der Person zurück.
     *
     * @return Warteschrittezähler.
     */
    public int getWaits() {
        return waits;
    }

    /**
     * Gibt die ID der Person zurück.
     *
     * @return ID der Person.
     */
    public int getId() {
        return id;
    }

    /**
     * Gibt die Position des linken Fußes zurück.
     *
     * @return Ein Array mit [xL, yL].
     */
    public int[] getLeftFoot() {
        return new int[]{xL, yL};
    }

    /**
     * Gibt die Position des rechten Fußes zurück.
     *
     * @return Ein Array mit [xR, yR].
     */
    public int[] getRightFoot() {
        return new int[]{xR, yR};
    }

    /**
     * Liefert eine lesbare Darstellung der Person.
     *
     * @return String mit ID, Bewegungs- und Warteschritten sowie Fußpositionen.
     */
    @Override
    public String toString() {
        return String.format(
                "Person[ID=%d, Moves=%d, Waits=%d, LeftFoot=(%d,%d), RightFoot=(%d,%d)]",
                id, moves, waits, xL, yL, xR, yR
        );
    }
}

