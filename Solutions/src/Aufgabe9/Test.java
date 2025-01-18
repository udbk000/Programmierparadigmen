package Aufgabe9;
/**https://chatgpt.com/share/678be650-2498-800c-86a6-79bbfc7e3fb0
/*
Wer hat was gemacht?
Gruppenmitglied 1: Design und Implementierung der Datenstrukturen für das Wegenetz und Personen.
Gruppenmitglied 2: Entwicklung der Logik für die Bewegungen und die Synchronisation der Threads.
Gruppenmitglied 3: Umsetzung der Interprozesskommunikation (Serialisierung/Deserialisierung) und des Testprogramms.
 */

/**
 * Testklasse für die Fluchtweg-Simulation.
 * Führt verschiedene Tests mit unterschiedlichen Wegenetzen und Personenzahlen durch.
 */
public class Test {
    /**
     * Startpunkt des Testprogramms.
     * Führt die Simulation mit mehreren Konfigurationen durch.
     *
     * @param args übergebenes String Array des Wegenetzes in Form einer Textdatei
     *
     * Vorbedingung: Das Programm benötigt keinen Eingriff von außen.
     * Nachbedingung: Simulationsergebnisse werden in "test.out" gespeichert.
     */
    public static void main(String[] args) {
        // Beispielkonfigurationen
        String[] layout1 = {
                ".....S",
                ".....S",
                ".....S",
                "....."
        };

        String[] layout2 = {
                "...S..",
                "...S..",
                "......",
                "...S..",
                "......"
        };

        // Test 1
        System.out.println("Starte Simulation 1...");
        Simulation simulation1 = new Simulation(layout1, 5);
        simulation1.start();

        // Test 2
        System.out.println("Starte Simulation 2...");
        Simulation simulation2 = new Simulation(layout2, 10);
        simulation2.start();
    }
}

