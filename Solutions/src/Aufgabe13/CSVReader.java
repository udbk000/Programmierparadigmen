package Aufgabe13;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CSVReader {
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
}
