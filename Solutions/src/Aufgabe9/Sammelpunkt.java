package Aufgabe9;

// Sammelpunkt.java
import java.util.ArrayList;
import java.util.List;

public class Sammelpunkt {
    private List<Person> sammlung;

    public Sammelpunkt() {
        this.sammlung = new ArrayList<>();
    }

    public synchronized void addPerson(Person person) {
        sammlung.add(person);
    }

    public void saveToFile(String fileName) {
        // Ergebnisse in test.out schreiben
    }
}
