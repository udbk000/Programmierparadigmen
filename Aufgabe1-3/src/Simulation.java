/** this class performs the simulation of the lifespan of the buildings. Therefore, it offers all
 * necessary methods to start and end a simulation. This class calls the methods offered in implementations
 * of "Building".
 * Its methods are called in the "Test" class in order to start a simulation.
 */
import java.util.Random;
public class Simulation {

    private final Building building;
    private final int maxYears;
    private final Random random;
    private final String buildingType;

    public Simulation(Building building, int maxYears) {
        this.building = building;
        this.maxYears = maxYears;
        this.random = new Random();
        if(building instanceof minBuilding)
            buildingType = "minimal building";
        else if (building instanceof ecoBuilding)
            buildingType = "eco building";
        else
            buildingType = "high quality building";
    }

    //Methode mit einer schleife, die die simulation maxYears lang laufen lässt
    public void runSimulation() {
        System.out.println("Starting simulation for " + maxYears + " years for the " + buildingType + "...");

        for (int year = 1; year <= maxYears; year++) {
            if (building.isDeconstructed()) {
                System.out.println("The " + buildingType + " has been deconstructed after " + (year - 1) + " years.");
                break;
            }
            System.out.println("\nYear " + year + " of the " + buildingType + ":");
            building.ageOneYear();
            simulateRandomEvents();
            if (random.nextInt(100) < 15) {
                building.renovate();
            }
        }

        if (!building.isDeconstructed()) {
            System.out.println("\nThe" + buildingType + " reached its maximum lifespan of " + maxYears + " years.");
        }

        printSimulationResults();
    }

    //Methode für eine zufällige erzeugung einer katastrophe
    private void simulateRandomEvents() {
        int eventChance = random.nextInt(100);
        if (eventChance < 3) {
            int eventType = random.nextInt(3);
            switch (eventType) {
                case 0:
                    building.fire();
                    break;
                case 1:
                    building.earthquake();
                    break;
                case 2:
                    building.flooding();
                    break;
            }
        }
    }

    //Methode für ausgabe der simulationsergebnisse
    public void printSimulationResults() {
        System.out.println("\n--- Simulation Results: " + buildingType + " ---");
        building.printAvgStats();
        building.printCostsByDecade();
        building.printSatisfactionByDecade();
        System.out.printf("Sustainability Index: %d%n", building.susIndex());
    }
}
