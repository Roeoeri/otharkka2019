package sudokupeli.ui;

import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import sudokupeli.domain.Player;

public class SudokuUi extends Application {

    Player currentPlayer;

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

        Scene LoginView = new Scene(loginGroup);

        Label placeholder = new Label("Sudoku ilmestyy tänne");

        StackPane sudokuGroup = new StackPane();
        sudokuGroup.setPrefSize(600, 600);
        sudokuGroup.getChildren().add(placeholder);
        sudokuGroup.setAlignment(Pos.CENTER);

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
            placeholder.setText(this.currentPlayer.getName() + ", sudokusi illmestyy tänne");
            window.setScene(sudokuView);
        });

        window.setScene(LoginView);
        window.show();

    }

    public static void main(String[] args) {
        launch(SudokuUi.class);
    }

}
