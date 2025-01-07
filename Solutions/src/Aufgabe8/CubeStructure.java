package Aufgabe8;

import java.util.HashSet;
import java.util.Set;

/**
 * CubeStructure.java - Datenstruktur für die Anordnung der Würfel.
 * Funktional gestaltet: Unveränderliche Strukturen bei Hinzufügen von Würfeln.
 */
public class CubeStructure {
    final Set<Cube> cubes = new HashSet<>();
    final int maxHeight;

    public CubeStructure(int maxHeight) {
        this.maxHeight = maxHeight;
    }

    /**
     * Überprüft, ob ein Würfel an einer bestimmten Position platziert werden kann.
     * @param cube Der zu platzierende Würfel.
     * @return true, wenn der Würfel platziert werden kann.
     */
    public boolean canPlace(Cube cube) {
        return cube.z <= maxHeight && !cubes.contains(cube) && hasValidSupport(cube);
    }

    /**
     * Erstellt eine neue Struktur mit einem zusätzlichen Würfel.
     * @param cube Der hinzuzufügende Würfel.
     * @return Neue CubeStructure mit dem neuen Würfel.
     */
    public CubeStructure addCube(Cube cube) {
        CubeStructure newStructure = new CubeStructure(maxHeight);
        newStructure.cubes.addAll(this.cubes);
        newStructure.cubes.add(cube);
        return newStructure;
    }

    /**
     * Überprüft, ob der Würfel eine gültige Stützung hat.
     * @param cube Der zu überprüfende Würfel.
     * @return true, wenn der Würfel gestützt ist.
     */
    public boolean hasValidSupport(Cube cube) {
        return cubes.stream().anyMatch(c -> c.isSupporting(cube)) || cube.z == 0;
    }

    /**
     * Erzeugt eine String-Darstellung der Struktur für die Ausgabe.
     * @return String-Darstellung der Höhenkarte.
     */
    @Override
    public String toString() {
        int[][] heightMap = getHeightMap();
        StringBuilder map = new StringBuilder();
        for (int[] row : heightMap) {
            for (int h : row) {
                map.append(h == 0 ? " " : (h < 10 ? h : (char) ('A' + (h - 10))));
            }
            map.append("\n");
        }
        return map.toString();
    }

    private int[][] getHeightMap() {
        int size = 20;
        int[][] map = new int[size][size];
        cubes.forEach(c -> map[c.x][c.y] = Math.max(map[c.x][c.y], c.z + 1));
        return map;
    }
}

/**
 * Cube.java - Einfache Klasse zur Darstellung eines Würfels.
 */
class Cube {
    int x, y, z;

    public Cube(int x, int y, int z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    /**
     * Überprüft, ob der aktuelle Würfel einen anderen Würfel stützt.
     * @param other Der andere Würfel.
     * @return true, wenn dieser Würfel den anderen stützt.
     */
    public boolean isSupporting(Cube other) {
        return this.x == other.x && this.y == other.y && this.z == other.z - 1;
    }
}

