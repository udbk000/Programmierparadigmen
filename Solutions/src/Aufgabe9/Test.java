package Aufgabe9;

/*
Wer hat was gemacht?
Gruppenmitglied 1: Design und Implementierung der Datenstrukturen für das Wegenetz und Personen.
Gruppenmitglied 2: Entwicklung der Logik für die Bewegungen und die Synchronisation der Threads.
Gruppenmitglied 3: Umsetzung der Interprozesskommunikation (Serialisierung/Deserialisierung) und des Testprogramms.
 */

// Beispiel für die Verwendung
public class Test {
    public static void main(String[] args) {
        String[] layout = {
                ".....S",
                ".....S",
                ".....S",
                "....."
        };

        Wegenetz wegenetz = new Wegenetz(layout);
        wegenetz.printWegenetz();
    }
}

