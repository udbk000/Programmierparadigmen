/** this class calls the beginning of the simulation, returns the results and is
 * the main point of interaction.
 */
public class Test {
    public static void main(String[] args) {

        int life_expectancy_eco = 50;
        int life_expectancy_min = 50;
        int life_expectancy_hiQ = 100;

        for (int i = 0; i < 10; i++) {
            int inhabitants = (int) (Math.random() * 100 + 40);

            Building eco = new ecoBuilding(inhabitants);
            Building min = new minBuilding(inhabitants);
            Building hiQ = new hiQBuilding(inhabitants);

            System.out.println("\nRun nr " + (i+1) + ":");

            Simulation ecoSim = new Simulation(eco, life_expectancy_eco);
            Simulation minSim = new Simulation(min, life_expectancy_min);
            Simulation hiQSim = new Simulation(hiQ, life_expectancy_hiQ);

            ecoSim.runSimulation();
            minSim.runSimulation();
            hiQSim.runSimulation();

        }

    }
}
