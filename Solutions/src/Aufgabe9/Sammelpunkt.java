package Aufgabe9;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Repräsentiert den Sammelpunkt für Personen in der Simulation.
 * Speichert die Daten von Personen, die den Sammelpunkt erreicht haben.
 */
public class Sammelpunkt {
    private final List<Person> sammlung; // Liste aller Personen am Sammelpunkt

    /**
     * Erstellt einen neuen Sammelpunkt.
     *
     * Nachbedingung: Eine leere Liste für Personen ist initialisiert.
     */
    public Sammelpunkt() {
        this.sammlung = new ArrayList<>();
    }

    /**
     * Fügt eine Person zur Sammlung hinzu.
     *
     * @param person Die Person, die den Sammelpunkt erreicht hat.
     *
     * Vorbedingung: `person` ist nicht null.
     * Nachbedingung: Die Person wird zur Liste hinzugefügt.
     */
    public synchronized void addPerson(Person person) {
        assert person != null : "Person darf nicht null sein.";
        sammlung.add(person);
    }

    /**
     * Speichert die Daten aller Personen in einer Datei.
     *
     * @param fileName Der Name der Ausgabedatei.
     *
     * Nachbedingung: Die Datei enthält die Daten aller Personen.
     */
    public void saveToFile(String fileName) {
        try (FileWriter writer = new FileWriter(fileName)) {
            for (Person person : sammlung) {
                writer.write(person.toString() + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

