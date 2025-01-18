package Aufgabe9;

import java.io.Serializable;

public class Person implements Serializable {
    private final int id;     // Eindeutige ID der Person
    private int moves;        // Anzahl der Bewegungen
    private int waits;        // Anzahl der Warteschritte

    private int xL, yL;       // Position des linken Fußes
    private int xR, yR;       // Position des rechten Fußes

    // Konstruktor: Initialisiert die Person mit ihrer ID und Startpositionen
    public Person(int id, int xL, int yL, int xR, int yR) {
        this.id = id;
        this.moves = 0; // Anfangs keine Bewegungen
        this.waits = 0; // Anfangs keine Warteschritte
        this.xL = xL;   // X-Position des linken Fußes
        this.yL = yL;   // Y-Position des linken Fußes
        this.xR = xR;   // X-Position des rechten Fußes
        this.yR = yR;   // Y-Position des rechten Fußes
    }

    // Synchronisierte Methode, um die Position der Person zu aktualisieren
    public synchronized void move(int newXL, int newYL, int newXR, int newYR) {
        this.xL = newXL;
        this.yL = newYL;
        this.xR = newXR;
        this.yR = newYR;
        this.moves++; // Bewegungsschritt zählen
    }

    // Synchronisierte Methode, um einen Warteschritt zu zählen
    public synchronized void waitStep() {
        this.waits++;
    }

    // Getter für die Anzahl der Bewegungen
    public int getMoves() {
        return moves;
    }

    // Getter für die Anzahl der Warteschritte
    public int getWaits() {
        return waits;
    }

    // Getter für die ID der Person
    public int getId() {
        return id;
    }

    // Getter für die Position des linken Fußes
    public int[] getLeftFoot() {
        return new int[]{xL, yL};
    }

    // Getter für die Position des rechten Fußes
    public int[] getRightFoot() {
        return new int[]{xR, yR};
    }

    // ToString-Methode für Debugging und Ausgabe
    @Override
    public String toString() {
        return String.format(
                "Person[ID=%d, Moves=%d, Waits=%d, LeftFoot=(%d,%d), RightFoot=(%d,%d)]",
                id, moves, waits, xL, yL, xR, yR
        );
    }
}

