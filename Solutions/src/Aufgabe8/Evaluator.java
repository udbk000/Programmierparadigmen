package Aufgabe8;


/**
 * Evaluator.java - Bewertungsfunktionen zur Optimierung der Strukturen.
 * Definiert als statische Methoden für funktionale Nutzung.
 */
public class Evaluator {
    /**
     * Bewertet eine Struktur basierend auf einem thermischen Modell.
     * @param structure Die zu bewertende Struktur.
     * @return Bewertung als double-Wert.
     */
    public static double thermalEvaluation(CubeStructure structure) {
        return structure.cubes.stream().mapToDouble(Evaluator::evaluateCube).sum();
    }

    /**
     * Bewertet eine Struktur mit einer benutzerdefinierten Logik.
     * @param structure Die zu bewertende Struktur.
     * @return Bewertung als double-Wert.
     */
    public static double customEvaluation(CubeStructure structure) {
        return structure.cubes.stream().mapToDouble(cube -> 1.0 / (cube.z + 1)).sum();
    }

    private static double evaluateCube(Cube cube) {
        double score = 0.0;
        if (cube.z == 0) score += 1.0;
        return score;
    }
}