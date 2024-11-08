/**
 * Aufgabe 2
 * Wer hat was gemacht?
 * Usaid Al-Dabak, 12216291: Datenbank angelegt, Klasse für Gebäude und Szenario implementiert.
 * Stefan Slanar, 12327076: CSVReader, Landscape
 * Andrii Makarenko 12229205: Simulation, Test,
 */

/*this class calls the beginning of the simulation, returns the results and is
 * the main point of interaction.
 */
import java.util.List;

public class Test {
    /*
    *Style: Die Test-Klasse liest Szenarien (aus CSV oder festgelegt) und durchläuft alle Kombinationen von Szenario und Landscape, um eine Vielzahl von Simulationen ohne manuelles Eingreifen zu ermöglichen.
     */

    public static void main(String[] args) {
        String csvFilePath = "Datenbank_PP2.csv";

        CSVReader reader = new CSVReader();
        List<Szenario> scenarios = reader.loadScenariosFromCSV(csvFilePath);


        for (Szenario scenario : scenarios) {
            for (Landscape landscape : Landscape.values()) {
                Szenario scenarioWithLandscape = new Szenario(
                        scenario.getName(),
                        scenario.getCo2Emission(),
                        scenario.getMaterial(),
                        scenario.getWaste(),
                        scenario.getConstructionCosts(),
                        scenario.getMainCosts(),
                        scenario.getMaterialAge(),
                        landscape
                );


                int inhabitants = 100;
                int area = 5000;
                Construction construction = new Construction(scenarioWithLandscape, inhabitants, area);

                System.out.println("\n--- Simulation for " + scenario.getName() + " in " + landscape + " ---");
                Simulation simulation = new Simulation(construction);
                simulation.runSimulation();
                System.out.println("--- End of Simulation ---\n");
            }
        }
    }
}
