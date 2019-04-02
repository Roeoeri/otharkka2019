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
public class Player implements Comparable<Player>{
    
    private String name;
    private int fastestTime;
    
    public Player(String name){
        this.name = name;
        this.fastestTime = Integer.MAX_VALUE;
    }
    
    public String getName(){
        return this.name;
    }
    
    public int getFastestTime(){
        return this.fastestTime;
    }
    
    public void setTime(int time){
        if(time < this.fastestTime && time >= 0){
             this.fastestTime = time;
        }
        
    }
    
    
    @Override
    public int compareTo(Player player){
        if(this.fastestTime < player.getFastestTime()){
            return -1;
        }
         if(this.fastestTime> player.getFastestTime()){
            return 1;
        }
        return 0;
        
    }
    
}
