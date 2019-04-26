/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sudokupeli.domain;

import java.util.List;
import sudokupeli.dao.FileHighScoreDao;

/**
 *
 * @author htomi
 */
public class HighscoreList {

    /**
     * Lista jossa säilytetään pelaajien nopeimpia ratkaisuaikoja
     */
    private List<Player> highscore;
    /**
     * Dao joka huolehtii pelaajien tallentamisesta
     */
    private FileHighScoreDao scoreDao;

    /**
     * Luo uuden pistelistan, joka käyttää parametrina annettua daoa.
     *
     * @param dao Dao joka huolehtii pelaajien tallentamisesta
     * @throws Exception
     */
    public HighscoreList(FileHighScoreDao dao) throws Exception {
        this.scoreDao = dao;
        this.highscore = scoreDao.getAll();
    }

    /**
     * Lisää pistelistalle uuden pelaajan.
     *
     * @param player lisättävä pelaaja
     * @throws Exception
     */
    public void addPlayer(Player player) throws Exception {
        scoreDao.add(player);
        updateRanking();
    }

    /**
     *
     * @return palauttaa pistelistan
     */
    public List<Player> asList() {
        return this.highscore;
    }

    /**
     * Palauttaa listalla olevan pelaajan nimen perusteella
     *
     * @param name pelaajan nimi
     * @return Player olio
     */
    public Player getPlayer(String name) {
        return scoreDao.getPlayer(name);
    }

    /**
     * Päivittää pistelistan
     */
    public void updateRanking() {
        this.highscore = scoreDao.getAll();
    }

}
