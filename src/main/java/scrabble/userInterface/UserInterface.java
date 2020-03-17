package scrabble.userInterface;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.control.TextArea;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import scrabble.*;


public class UserInterface extends Application{

    private Stage gameStage;
    private GridPane gameFrame;
    private TextField gameTextInput;
    private TextArea gameTextLog;
    private GridPane gameBoard;
    private Scrabble scrabble;
    private int playerTurn = 0;
    private boolean setup = true;
    private boolean challenge = false;
    private boolean gameOver = false;
    private MoveInfo currentMove;

    public static void main(String[] args) {
        launch(args);

    }

    @Override
    public void start(Stage gameStage) {

        scrabble = new Scrabble();
        this.gameStage = gameStage;

        gameStage.setTitle("Scrabble");

        gameFrame = new GridPane();
        gameTextInput = setUpTextInput();
        gameTextLog = setUpTextLog();
        gameBoard = setUpBoard(scrabble.getBoard());

        gameFrame.getChildren().add(gameBoard);
        gameFrame.add(gameTextLog,1,0);
        gameFrame.add(gameTextInput,1,1);

        Scene gameScene = new Scene(gameFrame);
        gameStage.setScene(gameScene);
        gameStage.setResizable(false);
        gameStage.show();
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
                gameBoard.add(label, j, i);

            }
        }

        return gameBoard;
    }

    private void updateBoard(){
        for(int i = 0; i < 15; i++) {

            for(int j = 0; j < 15; j++) {

                Label label = new Label(" ");
                if(scrabble.getBoard().getSquare(i,j).isEmpty()) {

                    switch (scrabble.getBoard().getSquare(i, j).getType()) {
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
                }
                else{
                    label.setStyle("-fx-background-color: #f2c66d; -fx-border-color:black; -fx-alignment: center");
                    label.setText(scrabble.getBoard().getSquare(i,j).toString());
                }

                label.setPrefSize(40,40);
                gameBoard.add(label, j+1, i+1);

            }
        }

    }




    private TextArea setUpTextLog() {
        TextArea text_flow = new TextArea();
        text_flow.setEditable(false);
        text_flow.setWrapText(true);
        text_flow.appendText("- Welcome to scrabble, please enter name for player 1 and 2\n");

        return text_flow;
    }

    private TextField setUpTextInput() {
        TextField gameText = new TextField();
        gameText.setOnKeyReleased(event -> {
            if (event.getCode() == KeyCode.ENTER && !(gameText.getText().equals("")) ){

                gameTextLog.appendText(gameText.getText() + "\n");
                if(setup){
                    setUpEvent(gameText);

                }
                else if(gameOver){
                    gameOverEvent(gameText);
                }
                else {
                    gameEvent(gameText);
                }
            }
        });

        return gameText;
    }

    private void setUpEvent(TextField gameText) {
        try{
            scrabble.createPlayer(gameText.getCharacters().toString(), playerTurn);
            gameTextLog.appendText("- Player " + (playerTurn+1) + " name set to " + scrabble.getPlayers()[playerTurn].getName() + "\n");
            playerTurn++;
        }catch (Exception e){
            gameTextLog.appendText(e.getMessage() + "\n");
        }

        if(playerTurn == 2) {

            playerTurn = 0;
            gameTextLog.appendText("- "+scrabble.getPlayers()[playerTurn].getName() +"s move \n- " + scrabble.getPlayers()[playerTurn % 2].getPlayerFrame().toString() + "\n");

            setup = false;
        }

        gameText.setText("");
    }


    private void gameEvent(TextField gameText) {
        UserInput text;
        try {
                text = UserInput.parseInput(gameText.getCharacters().toString());


                if(!challenge) {
                    switch (text.getInputType()) {

                        case HELP:

                            gameTextLog.appendText(gameHelp());
                            break;

                        case PASS:

                            gameTextLog.appendText("- Passed turn for " + (playerTurn + 1) + "\n");
                            scrabble.getPlayers()[playerTurn % 2].getPlayerFrame().setToBlank();
                            playerTurn = (playerTurn + 1) % 2;
                            break;

                        case QUIT:

                            System.exit(0);
                            break;

                        case EXCHANGE:

                            try {

                                scrabble.getPlayers()[playerTurn].getPlayerFrame().swapTiles(text.getWord());

                                gameTextLog.appendText("- Selected tiles have been swapped\n");
                                playerTurn = (playerTurn + 1) % 2;
                            } catch (Exception e) {

                                gameTextLog.appendText("- Error: " + e.getMessage() + "\n");
                            }
                            break;

                        case PLACE_TILE:

                            try {

                                scrabble.playerMove(text.getStartPosition(), text.getWordDirection(), text.getWord(), scrabble.getPlayers()[playerTurn]);
                                updateBoard();
                                currentMove = scrabble.getMoveHistory().get(scrabble.getMoveHistory().size() - 1);
                                challenge = true;
                                gameTextLog.appendText("- Does " + scrabble.getPlayers()[(playerTurn + 1) % 2].getName() + " want to challenge this move? (CHALLENGE <Y/N>)\n");

                            } catch (Exception e) {
                                gameTextLog.appendText("- Error: " + e.getMessage() + "\n");
                            }
                            break;

                        case BLANK:

                            try {

                                scrabble.getPlayers()[playerTurn].getPlayerFrame().setBlanks(text.getWord());
                                gameTextLog.appendText("- Blank tile has been set\n");

                            } catch (Exception e) {
                                gameTextLog.appendText("- Error: " + e.getMessage() + "\n");
                            }
                            break;

                        default:

                            gameTextLog.appendText("- Error: Unknown command\n");
                    }
                }
                else{
                    try{
                        System.out.println();
                        if(text.getWord()[0] == 'Y'){
                            //TODO Check Dictionary for Word - Sprint 4
                            gameTextLog.appendText("- Challenged has been made\n");
                        }
                        else{
                            gameTextLog.appendText("- Challenged has been passed\n");
                        }

                            gameTextLog.appendText("- " + scrabble.getPlayers()[playerTurn % 2].getName() + " move scored "+ currentMove.getMoveScore()+". Total score: "+scrabble.getPlayers()[playerTurn % 2].getScore()+ "\n");
                            scrabble.getBoard().setWordSquaresNormal(currentMove.getPrimaryWord());
                            playerTurn = (playerTurn + 1) % 2;
                            gameTextLog.appendText("- " + scrabble.getPlayers()[playerTurn % 2].getName() +"s move \n- " + scrabble.getPlayers()[playerTurn % 2].getPlayerFrame().toString() + "\n");



                        challenge = false;

                    }catch(Exception e){
                        gameTextLog.appendText("- Error: " + e.getMessage() + "\n");
                    }
                }

            }catch(Exception e){
                gameTextLog.appendText("- Error: " + e.getMessage() + "\n");
            }

        gameText.setText("");

        if(scrabble.isGameOver()){
            scrabble.gameOver();
            gameOver = true;
            gameTextLog.appendText("- GAME OVER!\n");
            if(scrabble.getPlayers()[0].getScore() > scrabble.getPlayers()[1].getScore()){
                gameTextLog.appendText("- " + scrabble.getPlayers()[0].getName() + " WINS!\n");
            }
            else if (scrabble.getPlayers()[0].getScore() > scrabble.getPlayers()[1].getScore()){
                gameTextLog.appendText("- THE GAME HAS ENDED IN A DRAW!\n");
            }
            else{
                gameTextLog.appendText("- " + scrabble.getPlayers()[1].getName() + " WINS!\n");
            }
            gameTextLog.appendText("- Type QUIT to exit game or RESET to start a new game!\n");
        }

    }

    private String gameHelp(){
        return "- Commands:\n" +
                "- HELP: Prints out the list of available commands\n" +
                "- PASS: Passes the current players turn\n" +
                "- QUIT: Exists the current game of scrabble\n" +
                "- EXCHANGE <Letters>: Exchanges the letters in the frame with random letters in the pool\n" +
                "- BLANK <Letter>: Sets the blank tile in a players frame to a letter\n" +
                "- CHALLENGE <Y/N>: Command at the end of each turn to challenge the other player move\n" +
                "- RESTART: Launches a new game, can only be called at the end of a game\n" +
                "- <Grid Reference> <Direction> <Letters>: Places the letters starting at the grid reference and going in the given direction (Eg H7 A HELLO)\n";
    }

    private void gameOverEvent(TextField gameText){

        UserInput text;
        try {
            text = UserInput.parseInput(gameText.getCharacters().toString());
            switch (text.getInputType()){
                case QUIT:
                    System.exit(0);
                    break;
                case RESTART:
                    gameStage.close();
                    Platform.runLater(() -> new UserInterface().start(new Stage()));
                    break;
                default:
                    gameTextLog.appendText("- Error: Unknown command\n");
            }
        }catch(Exception e){

            gameTextLog.appendText("- Error: " + e.getMessage() + "\n");
        }

    }
}
