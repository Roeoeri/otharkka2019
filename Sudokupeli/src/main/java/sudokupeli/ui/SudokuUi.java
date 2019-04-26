package sudokupeli.ui;

import java.util.List;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.StringConverter;
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
    int difficulty;

    public SudokuUi() throws Exception {
        this.dao = new FileHighScoreDao("highscores.txt");
        highscores = new HighscoreList(dao);
        difficulty = 40;
    }

    @Override
    public void start(Stage window) {

        this.sudoku = new Sudoku();

        Label usernameLabel = new Label("Syötä nimimerkki");
        TextField usernameField = new TextField();

        Slider difficultySlider = drawDifficultySlider();

        Button createUserButton = new Button("Sudokuun!");
        Label invalidUsernameLabel = new Label("");

        GridPane loginGroup = new GridPane();
        loginGroup.setPrefSize(600, 200);
        loginGroup.add(usernameLabel, 0, 0);
        loginGroup.add(usernameField, 1, 0);
        loginGroup.add(difficultySlider, 1, 2);
        loginGroup.add(createUserButton, 1, 3);
        loginGroup.add(invalidUsernameLabel, 1, 4);

        loginGroup.setHgap(20);
        loginGroup.setVgap(20);
        loginGroup.setPadding(new Insets(20, 20, 20, 20));

        clock = new StopWatch();

        Scene loginView = new Scene(loginGroup);

        Label playerInfo = new Label("Sudoku ilmestyy tänne");

        BorderPane sudokuGroup = new BorderPane();
        sudokuGroup.setTop(playerInfo);

        Scene sudokuView = new Scene(sudokuGroup);

        createUserButton.setOnAction((ActionEvent event) -> {
            String username = usernameField.getText().trim();
            int usernameLenght = username.length();
            if (usernameLenght < 1 || usernameLenght > 8) {
                invalidUsernameLabel.setText("Käyttäjätunnuksen pitää olla 1-8 merkkiä");
                return;
            }
            this.currentPlayer = new Player(username);
            System.out.println(difficulty);
            this.sudoku.initializeSudoku(difficulty);

            sudokuGroup.setCenter(drawSudoku());

            window.setScene(sudokuView);

            clock.start();
            userTimeAnimator = new AnimationTimer() {
                long previous = 0;

                @Override
                public void handle(long current) {
                    if (current - previous < 1000000000) {
                        return;
                    }
                    playerInfo.setText(currentPlayer.getName() + ", olet käyttänyt " + clock.getTime() / 1000 + " sekuntia sudokun ratkaisemiseen");

                    this.previous = current;
                }
            };
            userTimeAnimator.start();

        });

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
                playerInfo.setText("Oikein, peli päättyy");
            } else {
                playerInfo.setText("Väärin, yritä uudestaan");
            }

        });

        window.setScene(loginView);
        window.show();

    }

    public GridPane drawSudoku() {
        GridPane tiles = new GridPane();

        for (int x = 1; x <= 9; x++) {
            for (int y = 1; y <= 9; y++) {
                Button tile = new Button(" ");
                tile.setFont(Font.font("Monospaced", 40));
                tile.setOnAction((e) -> {
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

        return tiles;
    }

    public Slider drawDifficultySlider() {
        Slider difficultySlider = new Slider();
        difficultySlider.setMin(10);
        difficultySlider.setMax(70);
        difficultySlider.setValue(40);
        difficultySlider.setShowTickLabels(true);
        difficultySlider.setShowTickMarks(true);
        difficultySlider.setMajorTickUnit(30);
        difficultySlider.setMinorTickCount(5);

        difficultySlider.setLabelFormatter(new StringConverter<Double>() {
            @Override
            public String toString(Double n) {
                if (n < 40) {
                    return "Helppo";
                }
                if (n > 40) {
                    return "Vaikea";
                }

                return "Normaali";
            }

            @Override
            public Double fromString(String s) {
                switch (s) {
                    case "Helppo":
                        return 10d;
                    case "Vaikea":
                        return 70d;
                    default:
                        return 40d;
                }
            }
        });

        difficultySlider.valueChangingProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> source, Boolean oldValue, Boolean newValue) {
                difficulty = (int) difficultySlider.getValue();
            }
        });

        return difficultySlider;
    }

    public static void main(String[] args) {
        launch(SudokuUi.class);
    }

}
