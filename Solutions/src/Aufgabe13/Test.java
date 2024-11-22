package Aufgabe13; /**
 * Aufgabe 3
 * Wer hat was gemacht?
 * Usaid Al-Dabak, 12216291: Kommentare in Aufgabe13.Construction und Aufgabe13.Szenario, Welt Klasse implementiert, Aufgabe13.FuncCSVReader implementiert,
 * Stefan Slanar, 12327076: Aufgabe13.CSVReader, Aufgabe13.Landscape
 * Andrii Makarenko 12229205: Aufgabe13.Simulation, Aufgabe13.Test,
 */


import Aufgabe13.Construction;
import Aufgabe13.FuncCSVReader;
import Aufgabe13.Landscape;

import java.util.List;

public class Test {

    public static void main(String[] args) {
        int simulationYears = 100;
        String csvFilePath = "Datenbank_PP2.csv";

        World world = new World(simulationYears);

        FuncCSVReader reader = new FuncCSVReader();
        List<Szenario> scenarios = reader.loadScenariosFromCSV(csvFilePath); //Szenarien aus der Datenbank auslesen

        //Gebäuden mit Szenarien initialisieren und zur Welt hinzufügen
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

                world.addConstruction(construction);
            }
        }


        //Aufgabe13.Simulation laufen lassen
        Simulation simulation = new Simulation(world, simulationYears);
        simulation.runSimulation();
    }
}
