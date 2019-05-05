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
import javafx.scene.layout.HBox;
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
        window.setResizable(false);
        VBox welcomeGroup = new VBox(175);
        Scene welcomeView = new Scene(welcomeGroup);
       

        welcomeGroup.setPrefSize(745, 780);
        welcomeGroup.setPadding(new Insets(50, 260, 50, 260));
        welcomeGroup.setStyle("-fx-background-color: white;");

        Label welcomeTitle = new Label("Tervetuloa");
        welcomeTitle.setFont(new Font(40));

        Button showLogin = createMenuButton("Pelaa");
        Button showStatistics = createMenuButton("Tilastot");

        showLogin.setOnAction((event) -> {
            window.setScene(createGameScene(window,welcomeView));

        });
        
        showStatistics.setOnAction((event) ->{
            window.setScene(createStatisticsScene(window,welcomeView));
        
        });

        welcomeGroup.getChildren().add(welcomeTitle);
        welcomeGroup.getChildren().add(showLogin);
        welcomeGroup.getChildren().add(showStatistics);

        window.setScene(welcomeView);
        window.show();

    }
    
    public Scene createStatisticsScene(Stage window, Scene welcomeView){
        VBox statisticsGroup = new VBox(25);
        statisticsGroup.setPrefSize(745, 780);
        statisticsGroup.setPadding(new Insets(50, 175, 50, 175));
        statisticsGroup.setStyle("-fx-background-color: white;");
        
        List<Player> ranking = this.highscores.asList();
        
        Label title = new Label("Ranking");
        title.setFont(new Font(40));
        statisticsGroup.getChildren().add(title);
        
        for(int i = 0; i<ranking.size(); i++){
            Player player = ranking.get(i);
            Label row = new Label((i+1) + " " +  player.getName() + " ajalla " + player.getFastestTime() + " sekuntia ");
            row.setFont(new Font(20));
            statisticsGroup.getChildren().add(row);
        }
        
        Button backButton = createMenuButton("Takaisin");
        backButton.setOnAction((event) ->{
            window.setScene(welcomeView);
        });
        
        statisticsGroup.getChildren().add(backButton);
 
        return new Scene(statisticsGroup);
    }
    
    public Scene createGameScene(Stage window, Scene welcomeView){
        
        Label loginTitle = new Label("Luo Peli");
        loginTitle.setFont(Font.font(40));

        TextField usernameField = new TextField();
        usernameField.setStyle("-fx-background-color: white;"
                + "             -fx-border-color:black;"
                + "             -fx-border-width: 1 1 1 1 ;");

        Slider difficultySlider = drawDifficultySlider();

        Button createUserButton = new Button("Sudokuun!");
        createUserButton.setStyle("-fx-background-color: White;"
                + "   -fx-border-color:black;");

        Label invalidUsernameLabel = new Label("");

        VBox loginGroup = new VBox(20);
        loginGroup.setPrefSize(745, 780);
        loginGroup.getChildren().add(loginTitle);
        loginGroup.getChildren().add(new Label("Syötä nimimerkki"));
        loginGroup.getChildren().add(usernameField);
        loginGroup.getChildren().add(new Label("Valitse vaikeustaso"));
        loginGroup.getChildren().add(difficultySlider);
        loginGroup.getChildren().add(createUserButton);
        loginGroup.getChildren().add(invalidUsernameLabel);

        loginGroup.setPadding(new Insets(50, 50, 50, 50));
        loginGroup.setStyle("-fx-background-color: white;");

        clock = new StopWatch();

        Scene loginView = new Scene(loginGroup);

        Label playerInfo = new Label("Sudoku ilmestyy tänne");

        BorderPane sudokuGroup = new BorderPane();
        sudokuGroup.setLeft(playerInfo);
        sudokuGroup.setStyle("-fx-background-color: white;");

        Scene sudokuView = new Scene(sudokuGroup);

        createUserButton.setOnAction((ActionEvent event) -> {
            String username = usernameField.getText().trim();
            usernameField.clear();
            int usernameLenght = username.length();
            if (usernameLenght < 1 || usernameLenght > 8) {
                invalidUsernameLabel.setText("Käyttäjätunnuksen pitää olla 1-8 merkkiä");
                return;
            }
            this.currentPlayer = new Player(username);
            this.sudoku = new Sudoku();
            this.sudoku.initializeSudoku(difficulty);

            sudokuGroup.setBottom(drawSudoku());

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

        Button checkAnswer = new Button("Tarkista");
        checkAnswer.setStyle("-fx-background-color: White;"
                + "   -fx-border-color:black;"
                + "   -fx-text-fill: Green;");

        Button concede = new Button("Luovuta");
        concede.setStyle("-fx-background-color: White;"
                + "   -fx-border-color:black;"
                + "   -fx-text-fill: Red;");

        HBox gamecontrols = new HBox();

        gamecontrols.getChildren().add(checkAnswer);
        gamecontrols.getChildren().add(concede);

        sudokuGroup.setRight(gamecontrols);

        concede.setOnAction((event) -> {
            userTimeAnimator.stop();
            clock.stop();
            clock.reset();
            window.setScene(welcomeView);

        });
        checkAnswer.setOnAction((event) -> {
            final boolean isCorrect = sudoku.solutionIsCorrect();
            if (isCorrect) {
                userTimeAnimator.stop();
                int time = (int) clock.getTime() / 1000;
                clock.stop();
                clock.reset();
                currentPlayer.setTime(time);
                String error = "";
                try {
                    this.highscores.addPlayer(currentPlayer);
                } catch (Exception e) {
                    error = "Aikasi lisäämisessä tapahtui virhe, aikaasi ei välttämättä tallenneta";
                }
                window.setScene(createCorrectAnswerScene(window,loginView, error));
            } else {
                playerInfo.setText("Väärin, yritä uudestaan");
            }

        });
        
        return loginView;
    }

    public Scene createCorrectAnswerScene(Stage window, Scene nextView, String error) {
        VBox correctAnswerGroup = new VBox(175);
        correctAnswerGroup.setPrefSize(745, 780);
        correctAnswerGroup.setPadding(new Insets(50, 175, 50, 175));
        correctAnswerGroup.setStyle("-fx-background-color: white;");
        
        Label welcomeTitle = new Label("Oikea Vastaus!");
        welcomeTitle.setFont(new Font(40));
        
        Button playAgain = createMenuButton("Pelaa uudestaan");
        
        playAgain.setOnAction((event) ->{
            window.setScene(nextView);
        });
        
        correctAnswerGroup.getChildren().add(welcomeTitle);
        correctAnswerGroup.getChildren().add(new Label(error));
        correctAnswerGroup.getChildren().add(playAgain);
        

        return new Scene(correctAnswerGroup);
    }

    public Button createMenuButton(String title) {
        Button button = new Button(title);
        button.setFont(new Font(30));
        button.setMaxSize(300, 50);
        button.setStyle("-fx-background-color: white;"
                + "   -fx-border-color:black;");

        return button;
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
                String border = "1 1 1 1;";
                if (x % 3 == 0 && x < 9) {
                    border = "1 5 1 1;";
                }
                if (y % 3 == 0 && y < 9) {
                    border = "1 1 5 1;";
                }
                if (x % 3 == 0 && y % 3 == 0 && y < 9 && x < 9) {
                    border = "1 5 5 1;";
                }
                if (value == 0) {
                    tile.setText(" ");
                    tile.setStyle("-fx-background-color: white;"
                            + "   -fx-border-color:black;"
                            + "   -fx-border-width: " + border
                            + "   -fx-text-fill: blue;");
                } else {
                    tile.setText("" + value);
                    tile.setStyle("-fx-background-color: white;"
                            + "   -fx-border-color:black;"
                            + "   -fx-border-width: " + border);
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
