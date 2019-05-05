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

    private String file;

    private List<Player> highscores;

    /**
     * Luo uuden daon joka lukee parametrina annetun tiedoston ja alustaa
     * pistelistan sen perusteella.Mikäli tiedostoa ei ole, Dao luo sellaisen.
     *
     * @param file Luettava tiedosto
     */
    public FileHighScoreDao(String file) {
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
            try {
                FileWriter writer = new FileWriter(new File(file));
                writer.close();
            } catch (Exception ex) {

            }
        }

    }

    /**
     * Tallentaa tiedostoon daon sisäisen pistelistan
     *
     */
    public void save() {
        try (FileWriter writer = new FileWriter(new File(file))) {
            for (Player player : this.highscores) {
                writer.write(player.getName() + ":" + player.getFastestTime() + "\n");
            }
        } catch (Exception e) {

        }
    }

    /**
     * Lisää pistelistalle pelaajan ja järjestää pelaajat pienimmästä suurimpaan
     * nopeimpien aikojen perusteella.Mikäli pelaajia on tämän jälkeen yli
     * kymmenen,poistaa huonoimman pelaajan pistelistalta. Tämän pistelista
     * tallennetaan tiedostoon.
     *
     * @param player Lisättävä pelaaja
     */
    public void add(Player player) {

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

    /**
     * Hakee ja palauttaa pistelistala pelaajan parametrina annetun nimen
     * perusteella.
     *
     * @param name Haettavan pelaajan nimi.
     * @return Pelaajaa vastaava Player-olio.
     */
    public Player getPlayer(String name) {
        Player player = new Player("");
        for (int i = 0; i < this.highscores.size(); i++) {
            if (this.highscores.get(i).getName().equals(name)) {
                player = this.highscores.get(i);
            }
        }
        return player;
    }

    /**
     * Palauttaa daon sisäisen pistelistan.
     *
     * @return pistelista
     */
    public List<Player> getAll() {
        return this.highscores;
    }

}
