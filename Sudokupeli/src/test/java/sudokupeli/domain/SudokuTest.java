/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sudokupeli.domain;

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
    public void constructorCreatesEmptySudokuArray() {
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
    public void numberAppearsOnceInAColumnInFilledSudoku() {
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
    public void numberAppearsOnceInARowInFilledSudoku() {
        int[] numbers = new int[9];
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

    public boolean appearsTwiceInABox(int x, int y) {
        int[] numbers = new int[9];
        boolean doesNotAppearTwice = true;

        for (int i = x-3; i < x; i++) {
            for (int j= y-3 ; j < y; j++) {
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
