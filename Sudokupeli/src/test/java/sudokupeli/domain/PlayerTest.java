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
import sudokupeli.domain.Player;
import static org.junit.Assert.*;

/**
 *
 * @author htomi
 */
public class PlayerTest {

    Player player;

    public PlayerTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        player = new Player("Matti");
    }

    @After
    public void tearDown() {
    }

    @Test
    public void constructorCreatesPlayerCorrectly() {

        assertEquals("Matti", player.getName());
        assertEquals(Integer.MAX_VALUE, player.getFastestTime());
    }

    @Test
    public void fastestTimeOfThePlayerCanBeChanged() {

        player.setTime(1000);
        assertEquals(1000, player.getFastestTime());
    }

    @Test
    public void fastestTimeOfThePlayerCannotBeChangedToNegative() {
        player.setTime(-1000);
        assertEquals(Integer.MAX_VALUE, player.getFastestTime());
    }

    @Test
    public void fastestTimeOfThePlayerCannotBeChangedToSlowerTime() {
        player.setTime(1000);
        player.setTime(2000);
        assertEquals(1000, player.getFastestTime());
    }

    @Test
    public void compareToWorksAsIdentedWhenPlayersHaveEqualTimes() {
        Player player2 = new Player("Pekka");
        assertEquals(player.compareTo(player2), 0);

    }

    @Test
    public void compareToWorksAsIdentedWhenPlayerHasSmallerTime() {
        Player player2 = new Player("Pekka");
        player.setTime(1000);
        assertEquals(player.compareTo(player2), -1);

    }
    
      @Test
    public void compareToWorksAsIdentedWhenPlayerHasGreaterTime() {
        Player player2 = new Player("Pekka");
        player2.setTime(1000);
        assertEquals(player.compareTo(player2), 1);

    }
    
    

}
