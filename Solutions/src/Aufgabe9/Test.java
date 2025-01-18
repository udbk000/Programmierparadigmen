package Aufgabe9;

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

