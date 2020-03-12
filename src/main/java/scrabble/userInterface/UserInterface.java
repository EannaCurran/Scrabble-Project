package scrabble.userInterface;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.control.TextArea;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import scrabble.Board;
import scrabble.Player;
import scrabble.Pool;
import scrabble.UserInput;



public class UserInterface extends Application{

    private Board board;
    private Pool pool;
    private Player player1;
    private Player player2;

    private GridPane gameFrame;
    private TextField gameTextInput;
    private TextArea gameTextLog;
    private GridPane gameBoard;

    public static void main(String[] args) {

        launch(args);

    }

    @Override
    public void start(Stage gameStage) {

        board = new Board();
        pool = new Pool();

        gameStage.setTitle("Scrabble");

        gameFrame = new GridPane();
        gameTextInput = setUpTextInput();
        gameTextLog = setUpTextLog();
        gameBoard = setUpBoard(board);
        setUpPlayers();

        gameFrame.getChildren().add(gameBoard);
        gameFrame.add(gameTextLog,1,0);
        gameFrame.add(gameTextInput,1,1);

        Scene gameScene = new Scene(gameFrame);

        gameStage.setScene(gameScene);
        gameStage.sizeToScene();
        gameStage.show();

    }

    private void setUpPlayers() {
    }


    private GridPane setUpBoard(Board board){

        GridPane gameBoard = new GridPane();
        gameBoard.setStyle("-fx-background-color: lightgray; -fx-vgap: 1; -fx-hgap: 1; -fx-padding: 1;-fx-border-color:black;");

        for(int i = 0; i < 15; i++) {

            Label label = new Label(String.valueOf(i));
            label.setPrefSize(40, 40);
            label.setAlignment(Pos.CENTER);
            gameBoard.add(label, i + 1, 0);

        }

        for(int i = 0; i < 15; i++) {
            Label label = new Label(String.valueOf((char)(i+65)));
            label.setPrefSize(40, 40);
            label.setAlignment(Pos.CENTER);
            gameBoard.add(label, 16, i+1);
        }

        for(int i = 1; i < 16; i++) {

            for(int j = 1; j < 16; j++) {

                Label label = new Label(" ");

                switch(board.getSquare(i-1,j-1).getType()){
                    case NORMAL:
                        label.setStyle("-fx-background-color: #e6e7e8; -fx-border-color:black; -fx-alignment: center");
                        break;
                    case START:
                    case TRIPLE_WORD:
                        label.setStyle("-fx-background-color: #ed2207; -fx-border-color:black; -fx-alignment: center");
                        label.setText("3W");
                        break;
                    case DOUBLE_WORD:
                        label.setStyle("-fx-background-color: #0241ed; -fx-border-color:black; -fx-alignment: center");
                        label.setText("2W");
                        break;
                    case DOUBLE_LETTER:
                        label.setStyle("-fx-background-color: #64a4e8; -fx-border-color:black; -fx-alignment: center");
                        label.setText("2L");
                        break;
                    case TRIPLE_LETTER:
                        label.setStyle("-fx-background-color: #e3625d; -fx-border-color: black; -fx-alignment: center");
                        label.setText("3L");
                        break;

                }

                label.setPrefSize(40,40);
                gameBoard.add(label, i, j);

            }
        }

        return gameBoard;
    }



    private TextArea setUpTextLog() {
        TextArea text_flow = new TextArea();
        text_flow.setEditable(false);
        text_flow.setWrapText(true);
        text_flow.appendText("Welcome to scrabble, please enter player 1 name\n");

        return text_flow;
    }

    private TextField setUpTextInput() {
        TextField gameText = new TextField();
        gameText.setOnKeyReleased(event -> {
            if (event.getCode() == KeyCode.ENTER && !(gameText.getText().equals(""))){
                gameEvent(gameText);
            }
        });

        return gameText;
    }



    private void gameEvent(TextField gameText) {
        UserInput text = UserInput.parseInput(gameText.getCharacters().toString());

        switch(text.getInputType()) {
            case HELP:
                gameTextLog.appendText("- Get Gud\n");
                break;
            case PASS:
                gameTextLog.appendText("- Passed Turn\n");
                break;
            case QUIT:
                gameTextLog.appendText("- I would quit but I can't\n");
                break;
            case EXCHANGE:
                gameTextLog.appendText("- I have passed the ties your welcome make a better word\n");
            case PLACE_TILE:
                gameTextLog.appendText("- Tile have been placed\n");
            default:
                gameTextLog.appendText("- Error please try again\n");
        }
        gameText.setText("");
    }
}
