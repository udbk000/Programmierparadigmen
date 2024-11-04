import java.util.Random;

public class Simulation {
    /*
    * Style: Die Simulation-Klasse verwendet OOP-Prinzipien, indem sie mit Objekten wie Construction, Szenario, und Landscape interagiert. Die Simulation kapselt die Logik der Alterung und Katastrophen in einer übersichtlichen Struktur.
    * */

    private final Construction building;
    private final int maxYears;
    private final Random random;

    public Simulation(Construction building) {
        this.building = building;
        this.maxYears = building.getLifeExpect();
        this.random = new Random();
    }

    /**
     * Runs the simulation for the specified maxYears or until the building is deconstructed.
     */
    public void runSimulation() {
        System.out.println("Starting simulation for " + maxYears + " years for the " + building.getScenario().getName() + "...");

        for (int year = 1; year <= maxYears; year++) {
            if (building.isDeconstructed()) {
                System.out.println("The building has been deconstructed after " + (year - 1) + " years.");
                break;
            }
            building.ageOneYear();

            // Simulate random disasters specific to the building's landscape
            simulateRandomEvents();

            // Random chance to trigger a renovation
            if (random.nextInt(100) < 15) {
                building.renovate();
            }
        }
        if (!building.isDeconstructed()) {
            System.out.println("\nThe building reached its maximum simulated lifespan of " + maxYears + " years.");
        }

        printSimulationResults();
    }

    /**
     * Simulates random disasters based on the building's landscape.
     */
    private void simulateRandomEvents() {
        Landscape landscape = building.getScenario().getLandscape();
        float[] catastropheFactors = landscape.getCatastropheFactor();
        String[] disasters = landscape.getDisasters();

        for (int i = 0; i < disasters.length; i++) {
            if (random.nextFloat() < catastropheFactors[i]) {
                System.out.println(landscape.triggerRandomDisaster() + " affects the building.");
                building.catastrophe();
                break; // Only one disaster per year per building
            }
        }
    }

    /**
     * Prints the results of the simulation, including final condition and maintenance costs.
     */
    public void printSimulationResults() {
        building.printStats();
    }

}