package Aufgabe9;

import java.util.LinkedList;
import java.util.Queue;

public class Wegenetz {
    private final char[][] grid; // Originales Layout
    private final char[][] directions; // Richtungsfelder
    private final int rows;
    private final int cols;

    public Wegenetz(String[] layout) {
        this.rows = layout.length;
        this.cols = layout[0].length();
        this.grid = new char[rows][cols];
        this.directions = new char[rows][cols];

        // Initialisierung des Wegenetzes
        for (int i = 0; i < rows; i++) {
            grid[i] = layout[i].toCharArray();
            directions[i] = layout[i].toCharArray(); // Erstmal Kopie
        }

        // Richtungspfeile berechnen
        calculateDirections();
    }

    // BFS zur Berechnung der Richtungen
    private void calculateDirections() {
        Queue<int[]> queue = new LinkedList<>();

        // Sammelpunkte in die Queue einfügen
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols - 1; j++) {
                if (grid[i][j] == 'S') {
                    queue.add(new int[]{i, j});
                    directions[i][j] = 'S';
                }
            }
        }

        // Bewegungsrichtungen
        int[][] moves = {
                {-1, 0}, // Norden
                {1, 0},  // Süden
                {0, -1}, // Westen
                {0, 1}   // Osten
        };
        char[] symbols = {'^', 'v', '<', '>'};

        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int x = current[0];
            int y = current[1];

            for (int k = 0; k < moves.length; k++) {
                int newX = x + moves[k][0];
                int newY = y + moves[k][1];

                if (isValid(newX, newY) && directions[newX][newY] == '.') {
                    directions[newX][newY] = symbols[k];
                    queue.add(new int[]{newX, newY});
                }
            }
        }
    }

    private boolean isValid(int x, int y) {
        return x >= 0 && x < rows && y >= 0 && y < cols && grid[x][y] == '.';
    }

    public synchronized boolean isFieldFree(int x, int y) {
        return grid[x][y] == '.';
    }

    public synchronized void occupyField(int x, int y, char symbol) {
        grid[x][y] = symbol;
    }

    public synchronized void freeField(int x, int y) {
        grid[x][y] = '.';
    }

    public void printWegenetz() {
        System.out.println("Wegenetz mit Richtungen:");
        for (char[] row : directions) {
            System.out.println(new String(row));
        }
    }

    public int getRows() {
        return rows;
    }

    public int getCols() {
        return cols;
    }

    public synchronized boolean isAccessPoint(int x, int y) {
        if (x >= 0 && x < rows && y >= 0 && y < cols) {
            return grid[x][y] == 'S'; // Prüfen, ob das Feld ein Sammelpunkt ist
        }
        return false; // Ungültige Koordinaten
    }

}
