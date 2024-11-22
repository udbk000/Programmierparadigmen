import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class Simulation {
    private final World world;
    private final int maxYears;
    private final ExecutorService executor;

    public Simulation(World world, int maxYears) {
        this.world = world;
        this.maxYears = maxYears;
        this.executor = Executors.newFixedThreadPool(4);  // 4 Threads für parallele Ausführung
    }

    //Simulation für die festgelegte Dauer laufen lassen
    public void runSimulation() {
        System.out.println("\n=== Starting Simulation for " + maxYears + " Years ===");

        for (int year = 1; year <= maxYears; year++) {
            System.out.println("\n--- Year " + year + " ---");
            world.triggerNaturalDisaster();
            simulateYearlyAging();
        }

        //
        executor.shutdown();
        System.out.println("\n=== Simulation Completed ===");
    }

    // Parallele Älterung der Gebäuden
    private void simulateYearlyAging() {
        List<Construction> constructions = world.getConstructions();
        List<Callable<Void>> tasks = new ArrayList<>();

        for (Construction construction : constructions) {
            tasks.add(() -> {
                if (!construction.isDeconstructed()) {
                    construction.ageOneYear();
                }
                return null;
            });
        }

        try {
            executor.invokeAll(tasks);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
