package Aufgabe9;
/**https://chatgpt.com/share/678be650-2498-800c-86a6-79bbfc7e3fb0
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

