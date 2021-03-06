/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sudokupeli.domain;

import java.util.Random;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author htomi
 */
public class SudokuTest {

    Sudoku sudoku = new Sudoku();

    public SudokuTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    @Test
    public void constructorCreateSudokuArray() {
        int[][] array = sudoku.getSudoku();
        int xLenght = array[0].length;
        int yLenght = array.length;

        boolean validArray = false;
        if (xLenght == yLenght) {
            if (xLenght == 9) {
                validArray = true;
            }
        }

        assertTrue(validArray);

    }

    @Test
    public void filledSudokuHasNoZeroes() {
        sudoku.fillTiles();
        boolean noZeroes = true;
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                int value = sudoku.getTileValue(i, j);
                if (value == 0) {
                    noZeroes = false;
                }
            }
        }

        assertTrue(noZeroes);
    }

    @Test
    public void numberAppearsOnceInAColumnAndRowInFilledSudoku() {
        int[] numbers = new int[9];

        boolean doesNotAppearTwice = true;

        sudoku.fillTiles();
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                int value = sudoku.getTileValue(i, j);
                numbers[value - 1] += 1;
                int appearences = numbers[value - 1];
                if (appearences > 1) {
                    doesNotAppearTwice = false;
                }

            }
            numbers = new int[9];
        }

        assertTrue(doesNotAppearTwice);
    }

    @Test
    public void numberAppearsOnceInBoxInFilledSudoku() {
        sudoku.fillTiles();

        boolean doesNotAppearTwice = true;

        for (int i = 3; i <= 9; i += 3) {
            for (int j = 3; j <= 9; j += 3) {
                if (!appearsTwiceInABox(i, j)) {
                    doesNotAppearTwice = false;
                }
            }
        }

        assertTrue(doesNotAppearTwice);

    }

    @Test
    public void numbersWithValuZeroAreChangeAbleAndOthersAreNot() {
        Random rng = new Random();

        boolean isCorrectlyChangeAble = true;

        for (int repeatInitialization = 0; repeatInitialization < 10; repeatInitialization++) {
            sudoku.initializeSudoku(rng.nextInt(70));

            for (int i = 0; i < 9; i++) {
                for (int j = 0; j < 9; j++) {
                    int value = sudoku.getTileValue(i, j);
                    if (value == 0) {
                        if (!sudoku.numberCanBeChanged(i, j)) {
                            isCorrectlyChangeAble = false;
                        }
                    } else {
                        if (sudoku.numberCanBeChanged(i, j)) {
                            isCorrectlyChangeAble = false;
                        }
                    }

                }
            }
        }
        assertTrue(isCorrectlyChangeAble);
    }

    @Test
    public void solutionIsCorrectWorksAsIntended() {
        Random rng = new Random();

        boolean checkingWorks = true;
        this.sudoku.fillTiles();

        checkingWorks = this.sudoku.solutionIsCorrect();

        for (int repeatTest = 0; repeatTest < 1000; repeatTest++) {
            int[][] mockSudoku = new int[9][9];
            for (int i = 0; i < 9; i++) {
                for (int j = 0; j < 9; j++) {
                    int value = rng.nextInt(10);
                    mockSudoku[i][j] = value;
                }
            }
            this.sudoku.setSudoku(mockSudoku);

            if (this.sudoku.solutionIsCorrect()) {
                checkingWorks = false;
            }

        }

        assertTrue(checkingWorks);

    }

    @Test
    public void raisingATileValueWorksAsIntended() {
        this.sudoku.initializeSudoku(40);
        Random rng = new Random();

        boolean raisingWorks = true;
        for (int repeatTest = 0; repeatTest < 10000; repeatTest++) {
            int x = rng.nextInt(9);
            int y = rng.nextInt(9);
            for (int repeatRaise = 0; repeatRaise < 20; repeatRaise++) {
                int value = this.sudoku.getTileValue(x, y);
                if (!this.sudoku.numberCanBeChanged(x, y)) {
                    if (value != this.sudoku.raiseTileValue(x, y)) {
                        raisingWorks = false;
                    }

                } else {

                    if (value == 9) {
                        int raisedValue = this.sudoku.raiseTileValue(x, y);
                        if (raisedValue != 1) {
                            raisingWorks = false;
                        }
                    } else {
                        int raisedValue = this.sudoku.raiseTileValue(x, y);
                        if (raisedValue - value != 1) {
                            raisingWorks = false;
                        }
                    }

                }
            }

        }

        assertTrue(raisingWorks);
    }

    public boolean appearsTwiceInABox(int x, int y) {
        int[] numbers = new int[9];
        boolean doesNotAppearTwice = true;

        for (int i = x - 3; i < x; i++) {
            for (int j = y - 3; j < y; j++) {
                int value = sudoku.getTileValue(i, j);
                numbers[value - 1] += 1;
                int appearences = numbers[value - 1];
                if (appearences > 1) {
                    doesNotAppearTwice = false;
                }

            }
        }

        return doesNotAppearTwice;
    }

}
