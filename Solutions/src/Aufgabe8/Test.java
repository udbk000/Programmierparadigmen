package Aufgabe8;

import java.util.*;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Wer hat was gemacht?
 * Usaid Al-Dabak, 12216291: Block, Coordinate
 * Stefan Slanar, 12327076: CubePlacer, CubeStructure, Solution
 * Andrii Makarenko, 12229205: Cube, Test
 */

/**
 * Test.java - Hauptklasse zur Optimierung der Würfelanordnung.
 */
public class Test {

    public static void main(String[] args) {
        // Alle Testfälle werden als `List` von `TestCase`-Objekten definiert und nacheinander verarbeitet.
        List<TestCase> testCases = List.of(
                new TestCase(50, 10, 5),
                new TestCase(500, 50, 15),
                new TestCase(2000, 100, 50)
        );

        testCases.forEach(Test::runTestCase); // Testfälle ausführen
    }

    /**
     * Führt alle Tests für ein bestimmtes Test-Case aus und gibt die Ergebnisse aus.
     *
     * @param testCase Ein `TestCase` mit Werten für n, m und k.
     */
    private static void runTestCase(TestCase testCase) {
        System.out.println("--- Test Case --- (n=" + testCase.n() + ", m=" + testCase.m() + ", k=" + testCase.k() + ")");

        // Alle Tests werden als `Supplier<String>` definiert, um verspätete Ausführung zu ermöglichen.
        List<Supplier<String>> tests = List.of(
                () -> testResult("Coordinate plus() test",
                        () -> new Coordinate(1, 1, 1).plus(new Coordinate(2, 2, 2)).equals(new Coordinate(3, 3, 3))),
                () -> testResult("Block side parts test",
                        () -> new Block(new Coordinate(0, 0, 0)).getSidePartsOfBlock().count() == 4),
                () -> testResult("Block possible states test",
                        () -> {
                            Block block = new Block(new Coordinate(0, 0, 0));
                            Set<Coordinate> alreadySet = Set.of(new Coordinate(1, 0, 0));
                            List<Coordinate> possibleStates = block.possibleStates(alreadySet, testCase.m());
                            return possibleStates.contains(new Coordinate(0, 1, 0))
                                    && !possibleStates.contains(new Coordinate(1, 0, 0));
                        }),
                () -> testResult("Block add neighbour test",
                        () -> {
                            Block block = new Block(new Coordinate(0, 0, 0));
                            Block neighborBlock = new Block(new Coordinate(0, 1, 0));
                            block.addNeighbour(neighborBlock);
                            return block.getSidePartsOfBlock()
                                    .anyMatch(entry -> entry.getKey().equals(neighborBlock.getPosition()) && entry.getValue());
                        }),
                () -> testResult("Cube support test",
                        () -> {
                            Cube c1 = new Cube(0, 0, 0);
                            Cube c2 = new Cube(0, 0, 1);
                            return c2.isSupporting(c1) && !c1.isSupporting(c2);
                        }),
                () -> testResult("CubeStructure addCube test",
                        () -> {
                            CubeStructure structure = new CubeStructure(testCase.m());
                            Cube newCube = new Cube(0, 0, 0);
                            return structure.addCube(newCube).cubes.contains(newCube);
                        }),
                () -> testResult("CubeStructure valid support test",
                        () -> {
                            CubeStructure structure = new CubeStructure(testCase.m()).addCube(new Cube(0, 0, 0));
                            return structure.hasValidSupport(new Cube(0, 0, 1));
                        }),
                () -> testResult("CubePlacer findBestStructures test",
                        () -> {
                            List<CubeStructure> bestStructures = CubePlacer.findBestStructures(
                                    testCase.n(), testCase.m(), testCase.k(), structure -> structure.cubes.size());
                            return bestStructures.size() <= testCase.k()
                                    && bestStructures.stream().allMatch(s -> s.cubes.size() == testCase.n());
                        }),
                () -> testResult("Solution execute test",
                        () -> {
                            List<Solution> solutions = Solution.execute(testCase.n(), testCase.m(), testCase.k()).toList();
                            return solutions.size() <= testCase.k()
                                    && solutions.stream().allMatch(solution -> solution.blocks.size() == testCase.n());
                        }),
                () -> testResult("Block toString test",
                        () -> "[1, 2, 3]".equals(new Block(new Coordinate(1, 2, 3)).getPosition().toString())),
                () -> testResult("Edge penalty test",
                        () -> {
                            Map<Coordinate, Block> map = new HashMap<>();
                            map.put(new Coordinate(0, 0, 0), new Block(new Coordinate(0, 0, 0)));
                            return Solution.edgePenalty(map).apply(new Coordinate(1, 1, 1)) > 0;
                        })
        );

        // Führt alle Tests aus und sammelt die Ergebnisse
        tests.stream()
                .map(Supplier::get)
                .forEach(System.out::println);
    }

    /**
     * Hilfsmethode zur Durchführung eines einzelnen Tests.
     *
     * @param testName Der Name des Tests.
     * @param test     Eine Funktion, die `true` zurückgibt, wenn der Test erfolgreich war.
     * @return Ein formatiertes Ergebnis als String.
     */
    private static String testResult(String testName, Supplier<Boolean> test) {
        return testName + ": " + (test.get() ? "Passed" : "Failed");
    }

    /**
     * Datenklasse für einen Testfall mit Parametern `n`, `m`, `k`.
     */
    private record TestCase(int n, int m, int k) {
    }
}
