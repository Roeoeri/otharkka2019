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
 * Tarjoaa ominaisuuksia pistelistojen hallinnointiin.
 */
public class HighscoreList {

    private List<Player> highscore;

    private FileHighScoreDao scoreDao;

    /**
     * Luo uuden pistelistan, joka käyttää parametrina annettua daoa pistelistan
     * tallentamiseen levylle.
     *
     * @param dao Dao joka huolehtii pelaajien tallentamisesta
     */
    public HighscoreList(FileHighScoreDao dao) {
        this.scoreDao = dao;
        try {
            this.highscore = scoreDao.getAll();
        } catch (Exception e) {

        }
    }

    /**
     * Lisää pistelistalle uuden pelaajan, eli uuden Player-olion.
     *
     * @param player lisättävä pelaaja
     */
    public void addPlayer(Player player) {
        scoreDao.add(player);
        updateRanking();
    }

    /**
     *
     * @return Palauttaa pistelistan Player olioiden listana.
     */
    public List<Player> asList() {
        return this.highscore;
    }

    /**
     * Palauttaa listalla olevan Player-olion pelaajan nimen perusteella
     *
     * @param name Haettavan pelaajan nimi
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
