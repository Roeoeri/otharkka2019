package sudokupeli.ui;

import java.awt.Color;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import sudokupeli.domain.Player;
import sudokupeli.domain.Sudoku;
import org.apache.commons.lang3.time.StopWatch;
import sudokupeli.dao.FileHighScoreDao;
import sudokupeli.domain.HighscoreList;

public class SudokuUi extends Application {

    Player currentPlayer;
    Sudoku sudoku;
    StopWatch clock;
    AnimationTimer userTimeAnimator;
    FileHighScoreDao dao;
    HighscoreList highscores;

    public SudokuUi() throws Exception {
        this.dao = new FileHighScoreDao("highscores.txt");
        highscores = new HighscoreList(dao);
    }

    @Override
    public void start(Stage window) {
        Label usernameLabel = new Label("Syötä nimimerkki");
        TextField usernameField = new TextField();

        Button createUserButton = new Button("Sudokuun!");
        Label invalidUsernameLabel = new Label("");

        GridPane loginGroup = new GridPane();
        loginGroup.setPrefSize(600, 200);
        loginGroup.add(usernameLabel, 0, 0);
        loginGroup.add(usernameField, 1, 0);
        loginGroup.add(createUserButton, 1, 2);
        loginGroup.add(invalidUsernameLabel, 1, 3);

        loginGroup.setHgap(20);
        loginGroup.setVgap(20);
        loginGroup.setPadding(new Insets(20, 20, 20, 20));

        clock = new StopWatch();

        Scene loginView = new Scene(loginGroup);

        Label placeholder = new Label("Sudoku ilmestyy tänne");

        BorderPane sudokuGroup = new BorderPane();
        sudokuGroup.setTop(placeholder);

        Scene sudokuView = new Scene(sudokuGroup);

        createUserButton.setOnAction((event) -> {
            String username = usernameField.getText().trim();
            int usernameLenght = username.length();
            if (usernameLenght < 1 || usernameLenght > 8) {
                invalidUsernameLabel.setText("Käyttäjätunnuksen pitää olla 1-8 merkkiä");
                return;
            }
            Player player = new Player(username);
            this.currentPlayer = player;
            placeholder.setText("Pelaaja: " + this.currentPlayer.getName());

            window.setScene(sudokuView);

            clock.start();
            userTimeAnimator = new AnimationTimer() {
                long previous = 0;

                @Override
                public void handle(long current) {
                    if (current - previous < 1000000000) {
                        return;
                    }
                    placeholder.setText(currentPlayer.getName() + ", olet käyttänyt " + clock.getTime() / 1000 + " sekuntia sudokun ratkaisemiseen");

                    this.previous = current;
                }
            };
            userTimeAnimator.start();

        });

        this.sudoku = new Sudoku();
        this.sudoku.initializeSudoku();

        GridPane tiles = new GridPane();
        for (int x = 1; x <= 9; x++) {
            for (int y = 1; y <= 9; y++) {
                Button tile = new Button(" ");
                tile.setFont(Font.font("Monospaced", 40));
                tile.setOnAction((event) -> {
                    int yCoordinate = GridPane.getRowIndex(tile) - 1;
                    int xCoordinate = GridPane.getColumnIndex(tile) - 1;
                    if (sudoku.numberCanBeChanged(xCoordinate, yCoordinate)) {
                        int value = sudoku.raiseTileValue(xCoordinate, yCoordinate);
                        tile.setText("" + value);
                    }

                });
                int value = sudoku.getTileValue(x - 1, y - 1);
                if (value == 0) {
                    tile.setText(" ");
                } else {
                    tile.setText("" + value);
                    tile.setStyle("-fx-text-fill: red;");
                }
                tiles.add(tile, x, y);

            }
        }

        sudokuGroup.setCenter(tiles);

        VBox highScoreGroup = new VBox();
        highScoreGroup.getChildren().add(new Label("Pelaajat jotka ovat ratkaisseet sudokun nopeimmin: "));
        List<Player> players = this.highscores.asList();
        for (int i = 0; i < players.size(); i++) {
            highScoreGroup.getChildren().add(new Label(players.get(i).getName() + " ajalla " + players.get(i).getFastestTime() + " sekuntia"));
        }

        sudokuGroup.setRight(highScoreGroup);

        Button checkAnswer = new Button("Tarkista");
        sudokuGroup.setBottom(checkAnswer);

        checkAnswer.setOnAction((event) -> {
            final boolean isCorrect = sudoku.solutionIsCorrect();
            if (isCorrect) {
                userTimeAnimator.stop();
                int time = (int) clock.getTime() / 1000;
                clock.stop();
                currentPlayer.setTime(time);
                try {
                    this.highscores.addPlayer(currentPlayer);
                } catch (Exception e) {
                    System.out.println(e);
                }
                placeholder.setText("Oikein, peli päättyy");
            } else {
                placeholder.setText("Väärin, yritä uudestaan");
            }

        });

        window.setScene(loginView);
        window.show();

    }

    public static void main(String[] args) {
        launch(SudokuUi.class);
    }

}
