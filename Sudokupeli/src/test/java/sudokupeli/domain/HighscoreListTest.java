/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sudokupeli.domain;

import java.io.File;
import java.io.FileWriter;
import sudokupeli.domain.HighscoreList;
import sudokupeli.domain.Player;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import sudokupeli.dao.FileHighScoreDao;

/**
 *
 * @author htomi
 */
public class HighscoreListTest {
    
    HighscoreList list;
    
    public HighscoreListTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() throws Exception {
        String file = "HighScoreListTest.txt";
        FileWriter writer = new FileWriter(new File(file));
        list = new HighscoreList(new FileHighScoreDao(file));
        writer.close();
        
    }
    
    @After
    public void tearDown() {
    }
    
    @Test
    public void playerCanBeAdded() throws Exception{
        Player player = new Player("Jaska");
        player.setTime(1500);
        int size = list.asList().size();
        list.addPlayer(player);
        assertEquals(list.asList().size(),1);
    }
    
    @Test 
    public void noMoreThen10PlayerCanbeAdded() throws Exception{ 
        for(int i = 0; i<100; i++){
            Player player = new Player("Jaska");
            list.addPlayer(player);
        }
        assertEquals(list.asList().size(),10);   
    }
    
    @Test
    public void getPlayerReturnsCorrectPlayer() throws Exception{
        Player player1 = new Player("Jaska");
        player1.setTime(1000);
        list.addPlayer(player1);
        
        Player player2 = new Player("Pekka");
        player2.setTime(22222);
        list.addPlayer(player2);
        
        assertEquals(list.getPlayer("Pekka").getFastestTime(),22222);
    }
    
    @Test
    public void HighScoreListKeepsTrackOfTheHighScoresCorrectly() throws Exception{
          for(int i = 0; i<10; i++){
            Player player = new Player("Jaska");
            list.addPlayer(player);
        }
          
          Player player = new Player("Ykkonen");
          player.setTime(0);
          list.addPlayer(player);
          
          Player player3 = new Player("Kolmonen");
          player3.setTime(200);
          list.addPlayer(player3);
          
          
          Player player2 = new Player("Kakkonen");
          player2.setTime(100);
          list.addPlayer(player2);
          
          
         assertEquals(list.asList().get(0).getFastestTime(),0);
         assertEquals(list.asList().get(1).getFastestTime(),100);
         assertEquals(list.asList().get(2).getFastestTime(),200);
          
        
    }

 
}
