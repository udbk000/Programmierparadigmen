package Aufgabe8;


import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Block {
    private final Coordinate position;
    private final Map<Coordinate, Boolean> sidePartsOfBlock;
    private Map.Entry<Coordinate, Boolean> top;
    private Map.Entry<Coordinate, Boolean> bottom;

    public Block(Coordinate position) {
        this.position = position;
        this.sidePartsOfBlock = new HashMap<>();
        this.sidePartsOfBlock.put(position.plus(new Coordinate(0, 1, 0)), false);
        this.sidePartsOfBlock.put(position.plus(new Coordinate(1, 0, 0)), false);
        this.sidePartsOfBlock.put(position.plus(new Coordinate(0, -1, 0)), false);
        this.sidePartsOfBlock.put(position.plus(new Coordinate(-1, 0, 0)), false);
        this.top = Map.entry(position.plus(new Coordinate(0, 0, 1)), false);
        this.bottom = Map.entry(position.plus(new Coordinate(0, 0, -1)), false);
    }

    public Stream<Map.Entry<Coordinate, Boolean>> getSidePartsOfBlock() {
        return sidePartsOfBlock.entrySet().stream();
    }

    public List<Coordinate> possibleStates(Set<Coordinate> alreadySet, int m) {

        List<Coordinate> allSides = new ArrayList<>(sidePartsOfBlock.keySet());
        allSides.add(top.getKey());

        return allSides.stream()
                .filter(pos -> !alreadySet.contains(pos)
                        && validZPlacement(pos, alreadySet)
                        && pos.z() < m)
                .collect(Collectors.toList());
    }

    public void addNeighbour(Block block) {
        if (block.position.equals(position)) return;

        if (block.position.equals(top.getKey()) && !top.getValue()) {
            top = Map.entry(top.getKey(), true);
        } else if (block.position.equals(bottom.getKey()) && !bottom.getValue()) {
            bottom = Map.entry(bottom.getKey(), true);
        } else if (sidePartsOfBlock.containsKey(block.position) && !sidePartsOfBlock.get(block.position)) {
            sidePartsOfBlock.put(block.position, true);
        }
    }

    private static boolean validZPlacement(Coordinate position, Set<Coordinate> alreadySet) {
        if (position.z() == 0) return true;
        var down = new Coordinate(0, 0, -1);
        return alreadySet.contains(position.plus(down));
    }






    public Coordinate getPosition() {
        return position;
    }
}