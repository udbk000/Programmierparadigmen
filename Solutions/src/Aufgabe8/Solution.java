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
    //TODO
    private static Function<Coordinate, Double> proximity(Map<Coordinate, Block> blocks) {
        return position -> {
            var distance = 1;
            return distance / 25.0; // Scaled penalty
        };
    }

    //TODO
    private static Function<Coordinate, Double> edgePenalty(Map<Coordinate, Block> blocks) {
        return position -> {
            return 1.0;
        };
    }

    //TODO
    @Override
    public String toString() {
        return blocks.keySet().toString();
    }

    private static Function<Block, Boolean> sunFromEast(Map<Coordinate, Block> blocks) {
        return block -> true;
    }

    //TODO
    private static Function<Block, Boolean> sunFromWest(Map<Coordinate, Block> blocks) {
        return block -> true;
    }

    //TODO
    private static Function<Block, Boolean> sunFromSouth(Map<Coordinate, Block> blocks) {
        return block -> true;
    }

    // TODO delete that
    public String toJSON() {
        return blocks.keySet().stream()
                .map(pos -> String.format("{\"x\":%d,\"y\":%d,\"z\":%d}", pos.x(), pos.y(), pos.z()))
                .collect(Collectors.joining(",", "[", "]"));
    }
}