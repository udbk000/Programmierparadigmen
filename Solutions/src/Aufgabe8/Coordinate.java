package Aufgabe8;

import java.util.Objects;

public record Coordinate(int x, int y, int z) {
    public Coordinate plus(Coordinate coordinate) {
        return new Coordinate(x + coordinate.x, y + coordinate.y, z + coordinate.z);
    }
    @Override
    public int hashCode() {
        return Objects.hash(x, y, z);
    }
}
