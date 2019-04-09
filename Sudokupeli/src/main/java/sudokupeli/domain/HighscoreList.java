/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sudokupeli.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import sudokupeli.dao.FileHighScoreDao;

/**
 *
 * @author htomi
 */
public class HighscoreList {

    private List<Player> highscore;
    private FileHighScoreDao scoreDao;

    public HighscoreList(FileHighScoreDao dao) throws Exception {
        this.scoreDao = dao;
        this.highscore = scoreDao.getAll();
    }

    public void addPlayer(Player player) throws Exception {
        scoreDao.add(player);
        updateRanking();
    }

    public List<Player> asList() {
        return this.highscore;
    }

    public Player getPlayer(String name) {
        return scoreDao.getPlayer(name);
    }

    public void updateRanking() {
        this.highscore = scoreDao.getAll();
    }

}
