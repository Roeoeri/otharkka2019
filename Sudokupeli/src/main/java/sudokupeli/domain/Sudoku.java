/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sudokupeli.domain;

/**
 *
 * Sudokuluokka tarjoaa ominaisuuksia sudokun luomiseen ja hallinnointiin.
 */
public class Sudoku {

    private int[][] sudokuGrid;

    private boolean[][] changeableNumbers = new boolean[9][9];

    private RandomNumbers rng = new RandomNumbers();

    /**
     * Luo uuden Sudokun ja alustaa uuden 9x9 sudokua kuvaavan numeroruudukon
     * arvoilla 0.
     */
    public Sudoku() {
        this.sudokuGrid = new int[9][9];
    }

    /**
     *
     * Tarkistaa, muodostavatko luokan sisäisen sudokuruudukon ruutujen arvot
     * oikean ratkaisun.
     *
     * @return True, mikäli ratkaisu on oikein. Muutoin false.
     */
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

    private boolean numberCanBePlaced(int x, int y, int value) {

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

    private boolean boxHasANumber(int x, int y, int value) {
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

    private boolean rowHasANumber(int x, int value) {
        for (int i = 0; i < 9; i++) {
            if (sudokuGrid[x][i] == value) {
                return true;
            }
        }
        return false;
    }

    private boolean columnHasANumber(int y, int value) {
        for (int i = 0; i < 9; i++) {
            if (sudokuGrid[i][y] == value) {
                return true;
            }
        }
        return false;
    }

    private int[] getEmptyTile() {
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

    /**
     * Palauttaa ruudukossa koordinaateissa x,y sijaitsevan ruudun arvon.
     *
     * @param x x-koordinaatti
     * @param y y-koordinaatti
     * @return koordinaateissa sijaitseva arvo
     */
    public int getTileValue(int x, int y) {
        return sudokuGrid[x][y];
    }

    /**
     * Nostaa ruudukossa koordinaateissa x,y sijaitsevan ruudun arvoa yhdellä.
     * Mikäli arvo kasvaa yli yhdeksän, asetetaan arvoksi 1.
     *
     * @param x x-koordinaatti
     * @param y y-koordinaatti
     * @return ruudussa sijaitseva arvo sen jälkeen, kun siihen on lisätty 1.
     */
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

    /**
     * Tarkistaa, voidaanko ruudukossa koordinaateissa x,y sijaitsevan ruudun
     * arvoa vaihtaa.(Eli tarkistaa, ettei ruutu ole esitäytetty)
     *
     * @param x ruudun x-koordinaatti
     * @param y ruudun y-koordinaatti
     * @return True, jos arvoa voidaan vaihtaa (arvo ei ole esitäytetty).
     * Muutoin false
     */
    public boolean numberCanBeChanged(int x, int y) {
        return changeableNumbers[x][y];
    }

    /**
     * Täyttää sudokun satunnaisilla luvuilla, jonka jälkeen asettaa luvun
     * difficulty suuruisen määrän verran satunnaisia ruutuja arvolle nolla ja
     * asettaa ne muokattaviksi.
     *
     * @param difficulty Luku jonka verran satunnaisia ruutuja asetetaan arvolle
     * nolla ja muokattaviksi.
     */
    public void initializeSudoku(int difficulty) {
        this.sudokuGrid = new int[9][9];
        this.changeableNumbers = new boolean[9][9];

        fillTiles();

        for (int i = 0; i < difficulty; i++) {
            int x = rng.getRandomNumber();
            int y = rng.getRandomNumber();
            sudokuGrid[x][y] = 0;
            changeableNumbers[x][y] = true;
        }
    }

    /**
     * Täyttää sudokun rekursiivisesti satunnaisilla luvuilla.
     *
     * @return True kun vapaita ruutuja ei enää ole
     */
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

    /**
     * Palauttaa sudokuruudukon 9*9 kaksiulotteisena numerotaulukkona
     *
     * @return sudokuruudukko kaksiulotteisena 9x9 numerotaulukkona
     */
    public int[][] getSudoku() {
        return this.sudokuGrid;
    }

    /**
     * Asettaa sudokun arvoksi parametrina annetun 9x9 numerotaulukon.
     *
     * @param sudoku
     */
    public void setSudoku(int[][] sudoku) {
        this.sudokuGrid = sudoku;
    }

}
