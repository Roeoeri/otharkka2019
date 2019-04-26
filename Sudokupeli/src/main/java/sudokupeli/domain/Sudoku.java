/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sudokupeli.domain;

/**
 *
 * @author htomi
 */
public class Sudoku {

    private int[][] sudokuGrid;
    private boolean[][] changeableNumbers = new boolean[9][9];
    private RandomNumbers rng = new RandomNumbers();

    public Sudoku() {
        this.sudokuGrid = new int[9][9];
    }

    public boolean solutionIsCorrect() {
        for (int x = 0; x < 9; x++) {
            for (int y = 0; y < 9; y++) {
                if (sudokuGrid[x][y] == 0) {
                    return false;
                }
                int value = sudokuGrid[x][y];
                sudokuGrid[x][y] = 0;
                if (!numberCanBePlaced(x, y, value)) {
                    sudokuGrid[x][y] = value;
                    return false;
                }
                sudokuGrid[x][y] = value;
            }
        }
        return true;
    }

    public boolean numberCanBePlaced(int x, int y, int value) {

        if (rowHasANumber(x, value)) {
            return false;
        }

        if (columnHasANumber(y, value)) {
            return false;
        }

        if (boxHasANumber(x, y, value)) {
            return false;
        }

        return true;
    }

    public boolean boxHasANumber(int x, int y, int value) {
        int squareXLimit = 3 * Math.floorDiv(x, 3);
        int squareYLimit = 3 * Math.floorDiv(y, 3);

        for (int i = squareXLimit; i < squareXLimit + 3; i++) {
            for (int j = squareYLimit; j < squareYLimit + 3; j++) {
                if (sudokuGrid[i][j] == value) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean rowHasANumber(int x, int value) {
        for (int i = 0; i < 9; i++) {
            if (sudokuGrid[x][i] == value) {
                return true;
            }
        }
        return false;
    }

    public boolean columnHasANumber(int y, int value) {
        for (int i = 0; i < 9; i++) {
            if (sudokuGrid[i][y] == value) {
                return true;
            }
        }
        return false;
    }

    public int[] getEmptyTile() {
        int[] coordinates = new int[2];
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (sudokuGrid[i][j] == 0) {
                    coordinates[0] = i;
                    coordinates[1] = j;
                    return coordinates;
                }
            }
        }

        coordinates[0] = -99;
        coordinates[1] = -99;

        return coordinates;
    }

    public int getTileValue(int x, int y) {
        return sudokuGrid[x][y];
    }

    public int raiseTileValue(int x, int y) {
        if (changeableNumbers[x][y]) {
            int value = sudokuGrid[x][y];
            value++;
            if (value == 10) {
                value = 1;
            }
            sudokuGrid[x][y] = value;
            return value;
        }

        return sudokuGrid[x][y];
    }

    public boolean numberCanBeChanged(int x, int y) {
        return changeableNumbers[x][y];
    }

    public void initializeSudoku(int difficulty) {
        sudokuGrid = new int[9][9];

        fillTiles();

        for (int i = 0; i < difficulty; i++) {
            int x = rng.getRandomNumber();
            int y = rng.getRandomNumber();
            sudokuGrid[x][y] = 0;
            changeableNumbers[x][y] = true;
        }
    }

    public boolean fillTiles() {
        int[] coordinates = getEmptyTile();
        int x = coordinates[0];
        int y = coordinates[1];
        int[] randomNumbers = rng.getRandomNumbers();

        if (x == -99) {
            return true;
        }

        for (int i = 0; i < 9; i++) {
            int value = randomNumbers[i];
            if (numberCanBePlaced(x, y, value)) {
                sudokuGrid[x][y] = value;
                if (fillTiles()) {
                    return true;
                }
                sudokuGrid[x][y] = 0;
            }
        }

        return false;
    }

    public int[][] getSudoku() {
        return this.sudokuGrid;
    }

    public void setSudoku(int[][] sudoku) {
        this.sudokuGrid = sudoku;
    }

}
