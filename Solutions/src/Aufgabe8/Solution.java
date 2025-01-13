package Aufgabe8;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Die Klasse Solution repräsentiert eine Lösung in Form einer Struktur aus Blöcken.
 * Sie enthält Methoden zur Berechnung und Bewertung möglicher Lösungen im Hinblick auf Faktoren
 * wie Thermik und Sicht.
 */
public class Solution {
    // Eine HashMap, die die Position eines Blocks mit dem zugehörigen Block verknüpft.
    final HashMap<Coordinate, Block> blocks;

    // Bewertung (Rating) der Lösung, die Faktoren wie Nähe, Sonnenlicht usw. berücksichtigt.
    private final double rating;

    /**
     * Konstruktor für eine neue Lösung.
     *
     * @param blocks Eine HashMap, die die Positionen und zugehörigen Blöcke enthält.
     * @param rating Die Bewertung der Lösung.
     */
    public Solution(HashMap<Coordinate, Block> blocks, double rating) {
        this.blocks = blocks;
        this.rating = rating;
    }

    /**
     * Diese Methode generiert eine Liste möglicher Lösungen.
     * Die Lösungen werden nach ihrer Bewertung sortiert, und nur die besten k-Lösungen werden zurückgegeben.
     *
     * @param n Die Anzahl der zu platzierenden Blöcke.
     * @param m Die maximale Höhe (z-Koordinate).
     * @param k Die maximale Anzahl an Lösungen, die zurückgegeben werden.
     * @return Ein Stream von Lösungen.
     */
    public static Stream<Solution> execute(int n, int m, int k) {
        if (n == 1) {
            // Erster Block wird immer bei (0, 0, 0) platziert.
            Block block = new Block(new Coordinate(0, 0, 0));
            HashMap<Coordinate, Block> blocks = new HashMap<>();
            blocks.put(new Coordinate(0, 0, 0), block);

            // Bewertung des ersten Blocks.
            double rating = ratings(block, blocks);

            return Stream.of(new Solution(blocks, rating));
        }

        // Rekursive Platzierung neuer Blöcke.
        return execute(n - 1, m, k)
                .flatMap(solution -> solution.blocks.values().stream()
                        .flatMap(block -> block.possibleStates(solution.blocks.keySet(), m).stream()
                                .map(pos -> {
                                    Block newBlock = new Block(pos);
                                    solution.blocks.values().forEach(newBlock::addNeighbour);

                                    HashMap<Coordinate, Block> newBlocks = new HashMap<>(solution.blocks);
                                    newBlocks.put(newBlock.getPosition(), newBlock);

                                    double newRating = solution.rating + ratings(newBlock, newBlocks);
                                    return new Solution(newBlocks, newRating);
                                })))
                .sorted(Comparator.comparingDouble(g -> -g.rating)) // Absteigend sortiert nach Bewertung.
                .limit(k); // Begrenzung auf die k besten Lösungen.
    }

    /**
     * Berechnet die Gesamtbewertung eines Blocks anhand der Thermal- und Sichtbewertung.
     *
     * @param block  Der Block, der bewertet wird.
     * @param blocks Die Gesamtheit aller vorhandenen Blöcke.
     * @return Die Gesamtbewertung des Blocks.
     */
    private static double ratings(Block block, HashMap<Coordinate, Block> blocks) {
        return thermalRating(block, blocks) + viewRating(block, blocks);
    }

    /**
     * Berechnet die Sichtbewertung eines Blocks.
     * Ein Block erhält eine bessere Bewertung, wenn sich um ihn herum Platz befindet.
     *
     * @param block  Der Block, der bewertet wird.
     * @param blocks Die Gesamtheit aller vorhandenen Blöcke.
     * @return Die Sichtbewertung.
     */
    private static double viewRating(Block block, HashMap<Coordinate, Block> blocks) {
        return block.getSidePartsOfBlock()
                .mapToDouble(direction -> {
                    if (blocks.containsKey(direction.getKey())) return 0;

                    double proximityPenalty = proximity(blocks).apply(direction.getKey());
                    double edgePenalty = edgePenalty(blocks).apply(direction.getKey());

                    return proximityPenalty * edgePenalty;
                })
                .sum();
    }

    /**
     * Berechnet die Thermikbewertung eines Blocks basierend darauf, ob er von Sonnenlicht erreicht wird.
     *
     * @param block  Der Block, der bewertet wird.
     * @param blocks Die Gesamtheit aller vorhandenen Blöcke.
     * @return Die Thermikbewertung.
     */
    private static double thermalRating(Block block, HashMap<Coordinate, Block> blocks) {
        return Stream.of(
                sunFromEast(blocks).apply(block) ? 0.2 : 0.0,  // Sonnenlicht von Osten
                sunFromWest(blocks).apply(block) ? 0.1 : 0.0,  // Sonnenlicht von Westen
                sunFromSouth(blocks).apply(block) ? 0.5 : 0.0  // Sonnenlicht von Süden
        ).mapToDouble(Double::doubleValue).sum();
    }

    /**
     * Berechnet die Nähe eines Punktes zu anderen Blöcken.
     *
     * @param blocks Die Gesamtheit aller vorhandenen Blöcke.
     * @return Eine Funktion, die für eine gegebene Position den Proximity-Wert zurückgibt.
     */
    private static Function<Coordinate, Double> proximity(Map<Coordinate, Block> blocks) {
        return position -> {
            double minDistance = blocks.keySet().stream()
                    .mapToDouble(existingPos -> distance(position, existingPos))
                    .min()
                    .orElse(Double.MAX_VALUE); // Falls keine Blöcke existieren, maximale Distanz.
            return 1 / (1 + minDistance); // Proximity-Wert: Je näher, desto höher.
        };
    }

    /**
     * Berechnet die euklidische Distanz zwischen zwei Koordinaten.
     *
     * @param a Die erste Koordinate.
     * @param b Die zweite Koordinate.
     * @return Die Distanz zwischen den beiden Punkten.
     */
    private static double distance(Coordinate a, Coordinate b) {
        int dx = a.x() - b.x();
        int dy = a.y() - b.y();
        int dz = a.z() - b.z();
        return Math.sqrt(dx * dx + dy * dy + dz * dz);
    }

    /**
     * Berechnet eine Strafbewertung basierend auf der Nähe zu den Rändern des Raums.
     *
     * @param blocks Die Gesamtheit aller vorhandenen Blöcke.
     * @return Eine Funktion, die für eine Koordinate den Randstrafwert berechnet.
     */
    static Function<Coordinate, Double> edgePenalty(Map<Coordinate, Block> blocks) {
        final int MIN_X = 0, MAX_X = 10; // Beispielwerte
        final int MIN_Y = 0, MAX_Y = 10;
        final int MIN_Z = 0, MAX_Z = 10;
        return position -> {
            int distanceToXEdge = Math.min(position.x() - MIN_X, MAX_X - position.x());
            int distanceToYEdge = Math.min(position.y() - MIN_Y, MAX_Y - position.y());
            int distanceToZEdge = Math.min(position.z() - MIN_Z, MAX_Z - position.z());
            return 1.0 / (1 + distanceToXEdge)
                    + 1.0 / (1 + distanceToYEdge)
                    + 1.0 / (1 + distanceToZEdge);
        };
    }

    /**
     * Prüft, ob der Block von Osten Sonnenlicht erhält.
     *
     * @param blocks Die Gesamtheit aller vorhandenen Blöcke.
     * @return Eine Funktion, die angibt, ob Sonnenlicht von Osten vorhanden ist.
     */
    private static Function<Block, Boolean> sunFromEast(Map<Coordinate, Block> blocks) {
        return block -> {
            Coordinate position = block.getPosition();
            for (int x = position.x() + 1; x <= Integer.MAX_VALUE; x++) {
                if (blocks.containsKey(new Coordinate(x, position.y(), position.z()))) {
                    return false;
                }
            }
            return true;
        };
    }

    /**
     * Prüft, ob der Block von Westen Sonnenlicht erhält.
     */
    private static Function<Block, Boolean> sunFromWest(Map<Coordinate, Block> blocks) {
        return block -> {
            Coordinate position = block.getPosition();
            for (int x = position.x() - 1; x >= Integer.MIN_VALUE; x--) {
                if (blocks.containsKey(new Coordinate(x, position.y(), position.z()))) {
                    return false;
                }
            }
            return true;
        };
    }

    /**
     * Prüft, ob der Block von Süden Sonnenlicht erhält.
     */
    private static Function<Block, Boolean> sunFromSouth(Map<Coordinate, Block> blocks) {
        return block -> {
            Coordinate position = block.getPosition();
            for (int y = position.y() + 1; y <= Integer.MAX_VALUE; y++) {
                if (blocks.containsKey(new Coordinate(position.x(), y, position.z()))) {
                    return false;
                }
            }
            return true;
        };
    }

    /**
     * Gibt eine String-Darstellung der Blöcke in der Lösung zurück.
     *
     * @return String mit allen Koordinaten der Blöcke.
     */
    @Override
    public String toString() {
        return blocks.keySet().stream()
                .map(pos -> String.format("Coordinate: (x=%d, y=%d, z=%d)", pos.x(), pos.y(), pos.z()))
                .collect(Collectors.joining(", ", "Blocks: [", "]"));
    }
}
