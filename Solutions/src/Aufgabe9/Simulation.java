package Aufgabe9;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Die Simulation steuert die Bewegungen von Personen auf einem Fluchtwegenetz.
 * Es wird überprüft, ob Personen den Sammelpunkt erreichen, und deren Daten
 * werden gesammelt.
 */
public class Simulation {
    private final Wegenetz wegenetz;          // Das Wegenetz
    private final List<Thread> personThreads; // Liste aller Personen-Threads
    private final Sammelpunkt sammelpunkt;    // Der Sammelpunkt für die Personen
    private final int maxWaitSteps = 64;      // Maximale Anzahl von Warteschritten

    /**
     * Initialisiert eine neue Simulation mit einem Wegenetz und einer Anzahl von Personen.
     *
     * @param layout      2D-Array, das das Wegenetz beschreibt.
     * @param personCount Anzahl der Personen in der Simulation (muss > 0 sein).
     *
     * Vorbedingung: `layout` muss ein gültiges Rechteck sein; `personCount > 0`.
     * Nachbedingung: Die Simulation ist initialisiert, Personen sind zufällig positioniert.
     */
    public Simulation(String[] layout, int personCount) {
        this.wegenetz = new Wegenetz(layout); // Wegenetz initialisieren
        this.sammelpunkt = new Sammelpunkt(); // Sammelpunkt erstellen
        this.personThreads = new ArrayList<>();

        initializePersons(personCount); // Personen initialisieren
    }

    /**
     * Initialisiert Personen mit zufälligen Startpositionen im Wegenetz.
     *
     * @param personCount Anzahl der zu erstellenden Personen.
     *
     * Vorbedingung: `personCount > 0`.
     * Nachbedingung: Personen sind im Wegenetz initialisiert.
     */
    private void initializePersons(int personCount) {
        Random random = new Random();

        for (int i = 0; i < personCount; i++) {
            // Zufällige Startposition generieren
            int xL, yL, xR, yR;
            do {
                xL = random.nextInt(wegenetz.getRows());
                yL = random.nextInt(wegenetz.getCols());
                xR = xL;
                yR = yL + 1; // Rechte Fußposition ist benachbart
            } while (!wegenetz.isFieldFree(xL, yL) || !wegenetz.isFieldFree(xR, yR));

            // Person erstellen
            Person person = new Person(i + 1, xL, yL, xR, yR);
            Thread thread = new Thread(() -> simulatePerson(person));
            personThreads.add(thread);
        }
    }

    /**
     * Startet die Simulation und wartet auf den Abschluss aller Personen-Threads.
     *
     * Nachbedingung: Ergebnisse sind in der Datei `test.out` gespeichert.
     */
    public void start() {
        // Alle Threads starten
        for (Thread thread : personThreads) {
            thread.start();
        }

        // Auf die Beendigung aller Threads warten
        for (Thread thread : personThreads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        // Ergebnisse speichern
        sammelpunkt.saveToFile("test.out");
    }

    /**
     * Simuliert die Bewegung einer einzelnen Person auf dem Wegenetz.
     *
     * @param person Die zu simulierende Person.
     *
     * Vorbedingung: `person` muss gültige Startkoordinaten besitzen.
     * Nachbedingung: Die Person erreicht den Sammelpunkt oder überschreitet die maximale Wartezeit.
     */
    private void simulatePerson(Person person) {
        int xL = person.getLeftFoot()[0];
        int yL = person.getLeftFoot()[1];
        int xR = person.getRightFoot()[0];
        int yR = person.getRightFoot()[1];

        Random random = new Random();

        while (!wegenetz.isAccessPoint(xL, yL) && !wegenetz.isAccessPoint(xR, yR)) {
            synchronized (this) {
                // Gültige Bewegungen berechnen
                List<int[]> possibleMoves = calculateValidMoves(xL, yL, xR, yR);

                if (!possibleMoves.isEmpty()) {
                    // Zufällige Bewegung auswählen
                    int[] newPosition = possibleMoves.get(random.nextInt(possibleMoves.size()));
                    int newXL = newPosition[0];
                    int newYL = newPosition[1];
                    int newXR = newPosition[2];
                    int newYR = newPosition[3];

                    // Felder aktualisieren
                    wegenetz.freeField(xL, yL);
                    wegenetz.freeField(xR, yR);
                    person.move(newXL, newYL, newXR, newYR);
                    wegenetz.occupyField(newXL, newYL, 'L');
                    wegenetz.occupyField(newXR, newYR, 'R');

                    // Neue Position setzen
                    xL = newXL;
                    yL = newYL;
                    xR = newXR;
                    yR = newYR;

                    try {
                        Thread.sleep(random.nextInt(46) + 5); // Zwischen 5-50 ms warten
                    } catch (InterruptedException e) {
                        break;
                    }
                } else {
                    // Warten, wenn keine Bewegung möglich
                    person.waitStep();
                    if (person.getWaits() >= maxWaitSteps) {
                        break; // Maximal erlaubte Warteschritte erreicht
                    }
                    try {
                        Thread.sleep(random.nextInt(46) + 5);
                    } catch (InterruptedException e) {
                        break;
                    }
                }
            }
        }

        // Wenn Sammelpunkt erreicht
        if (wegenetz.isAccessPoint(xL, yL) || wegenetz.isAccessPoint(xR, yR)) {
            sammelpunkt.addPerson(person);
        }
    }


    /**
     * Berechnet alle möglichen Bewegungen für eine Person.
     *
     * @param xL, yL, xR, yR Position der Füße der Person.
     *
     * Vorbedingung: Gültige Koordinaten werden übergeben.
     * Nachbedingung: Gibt eine Liste von gültigen Bewegungen zurück.
     */
    private List<int[]> calculateValidMoves(int xL, int yL, int xR, int yR) {
        List<int[]> validMoves = new ArrayList<>();
        int[][] directions = {
                {-1, -1}, {-1, 0}, {-1, 1}, // Oben
                {0, -1},          {0, 1},  // Links/Rechts
                {1, -1}, {1, 0}, {1, 1}    // Unten
        };

        for (int[] dirL : directions) {
            for (int[] dirR : directions) {
                int newXL = xL + dirL[0];
                int newYL = yL + dirL[1];
                int newXR = xR + dirR[0];
                int newYR = yR + dirR[1];

                if (isValidMove(newXL, newYL, newXR, newYR, xL, yL, xR, yR)) {
                    validMoves.add(new int[]{newXL, newYL, newXR, newYR});
                }
            }
        }

        return validMoves;
    }

    // Validierungslogik für Bewegungen
    private boolean isValidMove(int newXL, int newYL, int newXR, int newYR, int oldXL, int oldYL, int oldXR, int oldYR) {
        return (wegenetz.isFieldFree(newXL, newYL) &&
                wegenetz.isFieldFree(newXR, newYR) &&
                !(newXL == oldXL && newYL == oldYL) &&
                !(newXR == oldXR && newYR == oldYR) &&
                Math.abs(newXL - newXR) <= 1 &&
                Math.abs(newYL - newYR) <= 1);
    }
}
