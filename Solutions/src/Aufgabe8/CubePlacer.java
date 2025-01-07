package Aufgabe8;

/**
 * CubePlacer.java - Kernalgorithmus zur Optimierung der Würfelplatzierung.
 * Funktional gestaltet: Rekursion und Streams zur Generierung neuer Strukturen.
 */
import java.util.*;
import java.util.function.ToDoubleFunction;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CubePlacer {
    /**
     * Findet die besten Würfelstrukturen basierend auf der Bewertungsfunktion.
     * @param n Anzahl der Würfel.
     * @param maxHeight Maximale Höhe der Struktur.
     * @param k Anzahl der zurückzugebenden besten Strukturen.
     * @param evaluator Bewertungsfunktion.
     * @return Liste der besten Strukturen.
     */
    public static List<CubeStructure> findBestStructures(int n, int maxHeight, int k, ToDoubleFunction<CubeStructure> evaluator) {
        if (n == 1) {
            CubeStructure initial = new CubeStructure(maxHeight);
            initial = initial.addCube(new Cube(0, 0, 0));
            return Collections.singletonList(initial);
        }

        List<CubeStructure> previousSolutions = findBestStructures(n - 1, maxHeight, k, evaluator);
        List<CubeStructure> newSolutions = new ArrayList<>();

        for (CubeStructure structure : previousSolutions) {
            newSolutions.addAll(
                    generateNextStructures(structure)
                            .filter(cubeStructure -> evaluator.applyAsDouble(cubeStructure) > 0)
                            .collect(Collectors.toList())
            );
        }

        return newSolutions.stream()
                .sorted(Comparator.comparingDouble(evaluator::applyAsDouble).reversed())
                .limit(k)
                .collect(Collectors.toList());
    }

    /**
     * Generiert mögliche neue Strukturen basierend auf einer bestehenden.
     * @param structure Die Ausgangsstruktur.
     * @return Stream neuer Strukturen.
     */
    private static Stream<CubeStructure> generateNextStructures(CubeStructure structure) {
        List<CubeStructure> structures = new ArrayList<>();
        for (int x = 0; x < 10; x++) {
            for (int y = 0; y < 10; y++) {
                for (int z = 0; z < structure.maxHeight; z++) {
                    Cube newCube = new Cube(x, y, z);
                    if (structure.canPlace(newCube)) {
                        structures.add(structure.addCube(newCube));
                    }
                }
            }
        }
        return structures.stream();
    }
}
