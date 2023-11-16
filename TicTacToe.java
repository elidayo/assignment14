import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class TicTacToe extends Application {

    private static final int BOARD_SIZE = 5;
    private char currentPlayer = 'X';
    private Button[][] buttons = new Button[BOARD_SIZE][BOARD_SIZE];

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        GridPane gridPane = createAndConfigureGridPane();

        for (int row = 0; row < BOARD_SIZE; row++) {
            for (int col = 0; col < BOARD_SIZE; col++) {
                Button button = createButton();
                buttons[row][col] = button;
                gridPane.add(button, col, row);
            }
        }

        Scene scene = new Scene(gridPane, 300, 300);
        primaryStage.setTitle("Tic Tac Toe");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private GridPane createAndConfigureGridPane() {
        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        return gridPane;
    }

    private Button createButton() {
        Button button = new Button();
        button.setMinSize(60, 60);
        button.setOnAction(e -> onButtonClick(button));
        return button;
    }

    private void onButtonClick(Button button) {
        if (button.getText().isEmpty()) {
            button.setText(String.valueOf(currentPlayer));
            if (checkForWinner()) {
                announceWinner();
            } else {
                currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
            }
        }
    }

    private boolean checkForWinner() {
        return checkRows() || checkColumns() || checkDiagonals();
    }

    private boolean checkRows() {
        for (int row = 0; row < BOARD_SIZE; row++) {
            if (checkRowCol(buttons[row][0], buttons[row][1], buttons[row][2], buttons[row][3], buttons[row][4])) {
                return true;
            }
        }
        return false;
    }

    private boolean checkColumns() {
        for (int col = 0; col < BOARD_SIZE; col++) {
            if (checkRowCol(buttons[0][col], buttons[1][col], buttons[2][col], buttons[3][col], buttons[4][col])) {
                return true;
            }
        }
        return false;
    }

    private boolean checkDiagonals() {
        return checkRowCol(buttons[0][0], buttons[1][1], buttons[2][2], buttons[3][3], buttons[4][4]) ||
               checkRowCol(buttons[0][4], buttons[1][3], buttons[2][2], buttons[3][1], buttons[4][0]);
    }

    private boolean checkRowCol(Button b1, Button b2, Button b3, Button b4, Button b5) {
        return (!b1.getText().isEmpty() && b1.getText().equals(b2.getText()) && b2.getText().equals(b3.getText())
                && b3.getText().equals(b4.getText()) && b4.getText().equals(b5.getText()));
    }

    private void announceWinner() {
        System.out.println("Player " + currentPlayer + " wins!");
    }
}
