package Aufgabe8;

/**
 * Test.java - Hauptklasse für die Optimierung der Würfelanordnung.
 * Ziel: Demonstration der Optimierung mit verschiedenen Bewertungsfunktionen
 * im funktionalen Paradigma.
 */
import java.util.*;
import java.util.stream.*;

public class Test {
    public static void main(String[] args) {
        System.out.println("Starte Optimierung der Würfelanordnung...");

        // Beispielwerte für die Tests
        int[] nValues = {10, 15, 20}; // Anzahl der Würfel
        int m = 5; // Maximale Höhe
        int k = 5; // Anzahl zurückzugebender Lösungen

        // Verschiedene Bewertungsfunktionen definieren
        Evaluator thermalEvaluator = Evaluator::thermalEvaluation;
        Evaluator customEvaluator = Evaluator::customEvaluation;

        // Optimierungsläufe mit verschiedenen Parametern und Evaluationsmethoden
        for (int i = 0; i < nValues.length; i++) {
            int n = nValues[i];
            List<CubeStructure> solutions = CubePlacer.findBestStructures(n, m, k, thermalEvaluator);
            System.out.printf("Ergebnisse für n=%d, m=%d, k=%d:%n", n, m, k);
            solutions.forEach(s -> System.out.println(s.toString()));
        }

        // Alternative Bewertungsfunktion
        List<CubeStructure> alternativeSolutions = CubePlacer.findBestStructures(15, 5, 5, customEvaluator);
        System.out.println("Alternative Bewertungsfunktion:");
        alternativeSolutions.forEach(s -> System.out.println(s.toString()));
    }
}
