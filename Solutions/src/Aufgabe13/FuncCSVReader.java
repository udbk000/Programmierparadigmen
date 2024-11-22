package Aufgabe13;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * STYLE:
 * Diese Klasse ist funktional implementiert. Sie ersetzt die Klasse "Aufgabe13.CSVReader" durch eine funktionale Implementierung.
 * Die Implementierung der java.util.function Bibliothek ermöglicht die Implementierung von Functions, wodurch das Paradigma
 * in Java vollständig eingesetzt werden kann. Die Funktion createSzenario illustriert die Verwendung des Paradigmas.
 */

public class FuncCSVReader {
    public List<Szenario> loadScenariosFromCSV(String filePath) {
        List<Szenario> scenarios = new ArrayList<>();
        String line;
        String csvSplitBy = ",";
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            br.readLine();
            while ((line = br.readLine()) != null) {
                String[] fields = line.split(csvSplitBy);

                String name = fields[0];
                float co2Emission = Float.parseFloat(fields[1]);
                String material = fields[2];
                float waste = Float.parseFloat(fields[3]);
                float constructionCosts = Float.parseFloat(fields[4]);
                float mainCosts = Float.parseFloat(fields[5]);
                int materialAge = Integer.parseInt(fields[6]);

                Szenario scenario = new Szenario(name, co2Emission, material, waste, constructionCosts, mainCosts, materialAge, null);
                scenarios.add(scenario);
            }
        } catch (IOException e) {
            System.err.println("Error reading CSV file: " + e.getMessage());
        }
        return scenarios;
    }

    // Funktion, die eine Szene von der CSV-Zeile erstellt
    Function<String, Szenario> createSzenario = line -> {
        String[] fields = line.split(",");
        String name = fields[0];
        float co2Emission = Float.parseFloat(fields[1]);
        String material = fields[2];
        float waste = Float.parseFloat(fields[3]);
        float constructionCosts = Float.parseFloat(fields[4]);
        float mainCosts = Float.parseFloat(fields[5]);
        int materialAge = Integer.parseInt(fields[6]);
        return new Szenario(name, co2Emission, material, waste, constructionCosts, mainCosts, materialAge, null);
    };

    public List<Szenario> loadScenariosFromCSVParallel(String filePath) {
        try {
            return Files.lines(Paths.get(filePath)) // Datei Zeile für Zeile lesen
                    .skip(1) // Header überspringen
                    .parallel() // Parallele Verarbeitung der Daten
                    .map(createSzenario) // Umwandlung der Zeilen in Szenarien
                    .collect(Collectors.toList()); // Sammeln der Szenarien
        } catch (IOException e) {
            System.err.println("Error reading CSV file: " + e.getMessage());
            return Collections.emptyList();
        }
    }

}

