/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sudokupeli.dao;

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import sudokupeli.domain.Player;

/**
 *
 * @author htomi
 */
public class FileHighScoreDao {

    String file;
    List<Player> highscores;

    public FileHighScoreDao(String file) throws Exception {
        this.file = file;
        this.highscores = new ArrayList<>();
        try {
            Scanner scan = new Scanner(new File(file));
            while (scan.hasNextLine()) {
                String[] parts = scan.nextLine().split(":");
                String name = parts[0];
                int time = Integer.parseInt(parts[1]);
                Player player = new Player(name);
                player.setTime(time);
                this.highscores.add(player);
            }
        } catch (Exception e) {
            FileWriter writer = new FileWriter(new File(file));
            writer.close();
        }

    }

    public void save() throws Exception {
        try (FileWriter writer = new FileWriter(new File(file))) {
            for (Player player : this.highscores) {
                writer.write(player.getName() + ":" + player.getFastestTime() + "\n");
            }
        }
    }

    public void add(Player player) throws Exception {

        if (this.highscores.isEmpty() || this.highscores.size() < 10) {
            this.highscores.add(player);
            Collections.sort(highscores);
            save();
            return;
        }

        if (player.getFastestTime() <= this.highscores.get(9).getFastestTime()) {
            this.highscores.add(player);
            Collections.sort(highscores);
            save();
            if (this.highscores.size() > 10) {
                this.highscores.remove(10);
                save();
            }
        }

    }

    public Player getPlayer(String name) {
        Player palautus = new Player("");
        for (int i = 0; i < this.highscores.size(); i++) {
            if (this.highscores.get(i).getName().equals(name)) {
                palautus = this.highscores.get(i);
            }
        }
        return palautus;
    }

    public List<Player> getAll() {
        return this.highscores;
    }

}
