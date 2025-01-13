package Aufgabe8;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Solution {
    private final HashMap<Coordinate, Block> blocks;
    private final double rating;

    public Solution(HashMap<Coordinate, Block> blocks, double rating) {
        this.blocks = blocks;
        this.rating = rating;
    }

    public static Stream<Solution> execute(int n, int m, int k) {
        if (n == 1) {
            Block block = new Block(new Coordinate(0, 0, 0));
            HashMap<Coordinate, Block> blocks = new HashMap<>();
            blocks.put(new Coordinate(0, 0, 0), block);

            double rating = ratings(block, blocks);

            return Stream.of(new Solution(blocks, rating));
        }


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
                .sorted(Comparator.comparingDouble(g -> -g.rating))
                .limit(k);
    }

    private static double ratings(Block block, HashMap<Coordinate, Block> blocks) {
        return thermalRating(block, blocks) + viewRating(block, blocks);
    }

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

    private static double thermalRating(Block block, HashMap<Coordinate, Block> blocks) {
        return Stream.of(
                sunFromEast(blocks).apply(block) ? 0.2 : 0.0,
                sunFromWest(blocks).apply(block) ? 0.1 : 0.0,
                sunFromSouth(blocks).apply(block) ? 0.5 : 0.0
        ).mapToDouble(Double::doubleValue).sum();
    }

    // berechnet Nähe eines Punktes im Raum zu anderen Blöcken
    private static Function<Coordinate, Double> proximity(Map<Coordinate, Block> blocks) {
        return position -> {
            // Berechne die minimale Distanz zu existierenden Blöcken
            double minDistance = blocks.keySet().stream()
                    .mapToDouble(existingPos -> distance(position, existingPos))
                    .min()
                    .orElse(Double.MAX_VALUE); // Falls keine Blöcke existieren, setze maximalen Wert

            // Proximity-Wert: Invertiere den Abstand und skaliere, z. B. für Werte zwischen 0 und 1
            return 1 / (1 + minDistance); // Verhindert Division durch 0
        };
    }

    // Hilfsmethode: Berechnet die euklidische Distanz zwischen zwei Punkten
    private static double distance(Coordinate a, Coordinate b) {
        int dx = a.x() - b.x();
        int dy = a.y() - b.y();
        int dz = a.z() - b.z();
        return Math.sqrt(dx * dx + dy * dy + dz * dz); // Euklidische Distanz
    }

    // berechnet einen Wert, der einer Strafbewertung eines Punktes basierend auf der Nähe zu den
    // Rändern eines definierten Raumbereichs entspricht
    private static Function<Coordinate, Double> edgePenalty(Map<Coordinate, Block> blocks) {
        // Definiere die Grenzen des Raums
        final int MIN_X = 0, MAX_X = 10; // Beispielgrenzen
        final int MIN_Y = 0, MAX_Y = 10;
        final int MIN_Z = 0, MAX_Z = 10;
        return position -> {
            // Berechne die Distanz zur nächsten Grenze in jeder Dimension
            int distanceToXEdge = Math.min(position.x() - MIN_X, MAX_X - position.x());
            int distanceToYEdge = Math.min(position.y() - MIN_Y, MAX_Y - position.y());
            int distanceToZEdge = Math.min(position.z() - MIN_Z, MAX_Z - position.z());
            // Gesamtstrafe basierend auf Nähe zu den Rändern (niedrigere Werte = näher am Rand)
            return 1.0 / (1 + distanceToXEdge)
                    + 1.0 / (1 + distanceToYEdge)
                    + 1.0 / (1 + distanceToZEdge); // Höhere Strafen für Positionen näher an den Rändern
        };
    }

    //TODO
    @Override
    public String toString() {
        return blocks.keySet().toString();
    }

    private static Function<Block, Boolean> sunFromEast(Map<Coordinate, Block> blocks) {
        return block -> {
            Coordinate position = block.getPosition();
            // Prüfe, ob ein Block östlich (positive x-Richtung) das Sonnenlicht blockiert
            for (int x = position.x() + 1; x <= Integer.MAX_VALUE; x++) {
                Coordinate testCoord = new Coordinate(x, position.y(), position.z());
                if (blocks.containsKey(testCoord)) {
                    return false; // Ein Block blockiert das Sonnenlicht
                }
            }
            return true; // Kein Block blockiert das Sonnenlicht
        };
    }

    private static Function<Block, Boolean> sunFromWest(Map<Coordinate, Block> blocks) {
        return block -> {
            Coordinate position = block.getPosition();
            // Prüfe, ob ein Block westlich (negative x-Richtung) das Sonnenlicht blockiert
            for (int x = position.x() - 1; x >= Integer.MIN_VALUE; x--) {
                Coordinate testCoord = new Coordinate(x, position.y(), position.z());
                if (blocks.containsKey(testCoord)) {
                    return false; // Ein Block blockiert das Sonnenlicht
                }
            }
            return true; // Kein Block blockiert das Sonnenlicht
        };
    }

    private static Function<Block, Boolean> sunFromSouth(Map<Coordinate, Block> blocks) {
        return block -> {
            Coordinate position = block.getPosition();
            // Prüfe, ob ein Block südlich (positive y-Richtung) das Sonnenlicht blockiert
            for (int y = position.y() + 1; y <= Integer.MAX_VALUE; y++) {
                Coordinate testCoord = new Coordinate(position.x(), y, position.z());
                if (blocks.containsKey(testCoord)) {
                    return false; // Ein Block blockiert das Sonnenlicht
                }
            }
            return true; // Kein Block blockiert das Sonnenlicht
        };
    }

    // TODO delete that
    public String toJSON() {
        return blocks.keySet().stream()
                .map(pos -> String.format("{\"x\":%d,\"y\":%d,\"z\":%d}", pos.x(), pos.y(), pos.z()))
                .collect(Collectors.joining(",", "[", "]"));
    }
}