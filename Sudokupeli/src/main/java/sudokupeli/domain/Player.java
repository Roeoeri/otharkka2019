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
public class Player implements Comparable<Player> {

    /**
     * Pelaajan nimimerkki
     */
    private String name;
    /**
     * Nopein aika, jossa pelaaja on ratkaissut sudokun
     */
    private int fastestTime;

    /**
     * Luo uuden pelaajan halutulla nimimerkillä ja asettaa pelaajalle alustavan
     * nopeimman ajan.
     *
     * @param name pelaajan nimimerkki
     */
    public Player(String name) {
        this.name = name;
        this.fastestTime = Integer.MAX_VALUE;
    }

    /**
     *
     * @return Pelaajan nimimerkki
     */
    public String getName() {
        return this.name;
    }
    
    /**
     * 
     * @return Pelaajan nopein aika
     */
    public int getFastestTime() {
        return this.fastestTime;
    }

    public void setTime(int time) {
        if (time < this.fastestTime && time >= 0) {
            this.fastestTime = time;
        }

    }

    @Override
    public int compareTo(Player player) {
        if (this.fastestTime < player.getFastestTime()) {
            return -1;
        }
        if (this.fastestTime > player.getFastestTime()) {
            return 1;
        }
        return 0;

    }

}
