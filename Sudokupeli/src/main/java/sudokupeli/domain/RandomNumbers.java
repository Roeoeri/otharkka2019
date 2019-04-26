/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sudokupeli.domain;

import java.util.Random;

/**
 *
 * @author htomi
 */
public class RandomNumbers {

    Random rng;

    public RandomNumbers() {
        rng = new Random();
    }

    public int getRandomNumber() {
        return rng.nextInt(9);
    }

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
