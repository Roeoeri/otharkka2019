/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sudokupeli.domain;

import java.util.Random;

/**
 * Tarjoaa sudokun kannalta hyödyllisiä satunnaisia lukuja ja
 * satunnaislukulistoja.
 *
 */
public class RandomNumbers {

    Random rng;

    /**
     * Alustaa uuden RandomNumbers olion ja sen käyttöön uuden Random olion
     */
    public RandomNumbers() {
        rng = new Random();
    }

    /**
     *
     * @return Satunnainen luku väliltä 0-8
     */
    public int getRandomNumber() {
        return rng.nextInt(9);
    }

    /**
     *
     * @return 9-alkioinen taulukko, jossa on satunnaisessa järjestyksessä luvut
     * väliltä 1-9
     */
    public int[] getRandomNumbers() {
        int[] numbers = new int[9];
        for (int i = 0; i < numbers.length; i++) {
            numbers[i] = i + 1;
        }

        for (int i = numbers.length - 1; i > 0; i--) {
            int j = rng.nextInt(i);
            int previous = numbers[j];
            numbers[j] = numbers[i];
            numbers[i] = previous;

        }
        return numbers;
    }

}
