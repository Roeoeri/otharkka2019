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
public class RandomNumbersTest {
    
    RandomNumbers rng;
    
    public RandomNumbersTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        rng = new RandomNumbers();
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void randomNumbersReturnsPossibleNumber(){
        boolean correctNumber = true;
        for(int i = 0; i<1000; i++){
            int number = rng.getRandomNumber();
            if(number < 0 || number > 8){
                correctNumber = false;
            }
        }
        
        assertTrue(correctNumber);
    }
    
    @Test
    public void randomNumbersReturnsAListOfPossibleNumbers(){
        boolean correctNumbers = true;
        for(int i = 0; i<1000; i++){
            int[] numbers = rng.getRandomNumbers();
            for(int j = 0; j<numbers.length; j++){
                int number = numbers[j];
                if(number <=0 || number >9){
                    correctNumbers = false;
                }
            }
        }
        assertTrue(correctNumbers);
    }
}
