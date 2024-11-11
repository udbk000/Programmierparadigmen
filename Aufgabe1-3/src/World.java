import java.util.*;
import java.util.concurrent.*;

/**
 * Diese Klasse ist eine parallel laufende Welt, die von allen Gebäuden adressiert werden kann. Sie bildet eine Umgebung, in
 * der beliebig viele Gebäude existieren. Durch Ereignisse, die in dieser Welt passieren, werden alle Gebäude betroffen.
 */

/**
 * STYLE:
 * Diese Klasse ist parallel programmiert. Auf diese Welt können alle Gebäude, die in dieser existieren, zugreifen. Sie beinhaltet
 * einen Ansatz, der multithreaded ist.
 */

public class World {
    private List<Construction> constructions; // Diese List beinhaltet alle Constructions, die in dieser Welt existieren
    private int worldAge;  // Die Dauer, wie lange die Welt existieren soll (in Jahre)
    private Random random;
    private ExecutorService executor;

    public World(int worldAge) {
        this.worldAge = worldAge;
        this.constructions = new ArrayList<>();
        this.random = new Random();
        this.executor = Executors.newFixedThreadPool(4);  // 4 Threads für parallele Ausführung
    }

    // Gebäude zur Welt hinzufügen
    public void addConstruction(Construction construction) {
        constructions.add(construction);
    }

    // Simulation der Welt starten
    public void simulate() {
        List<Callable<Void>> tasks = new ArrayList<>();

        for (Construction construction : constructions) {
            tasks.add(() -> {
                for (int year = 0; year < worldAge; year++) {
                    construction.ageOneYear();
                    construction.catastrophe();

                    if (construction.isDeconstructed()) {
                        break;  // Falls das Gebäude abgerissen wurde, stoppen
                    }
                }
                return null;
            });
        }

        // Parallelisierung mit ExecutorService
        try {
            executor.invokeAll(tasks);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    // Zufällige Naturkatastrophe auslösen
    public void triggerNaturalDisaster() {
        int disasterChance = random.nextInt(100);
        if (disasterChance < 30) {
            System.out.println("A natural disaster occurs!");
            for (Construction construction : constructions ){
                construction.catastrophe();
            }
        }
    }

    // Welt simulieren, Gebäude altern und Katastrophen auftreten lassen
    public void runSimulation() {
        for (int i = 0; i < worldAge; i++) {
            triggerNaturalDisaster();  // Jede Runde könnte ein Naturereignis auslösen
            simulate();  // Gebäude altern lassen
        }
    }

    // Ressourcen freigeben
    public void shutdown() {
        executor.shutdown();
    }
}
