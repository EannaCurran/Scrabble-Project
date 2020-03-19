package scrabble.userInterface;

// Imports required for javaFx
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
import javafx.stage.Screen;
import scrabble.*;
import scrabble.exceptions.InvalidScrabbleException;


public class UserInterface extends Application{

    // Instance variables
    private Stage gameStage;
    private GridPane gameFrame;
    private TextField gameTextInput;
    private TextArea gameTextLog;
    private GridPane gameBoard;
    private Scrabble scrabble;

    // Variables used in gameFlow
    private int playerTurn = 0;
    private boolean setup = true;
    private boolean challenge = false;
    private boolean challengeMade = false;
    private boolean gameOver = false;
    private MoveInfo currentMove;


    /**
     * Main method to launch application
     * @param args: empty argumant
     */
    public static void main(String[] args) {

        // Method to launch application
        launch(args);
    }

    /**
     * Method to start the javaFx application
     * @param gameStage: Stage to be used in the application
     */
    @Override
    public void start(Stage gameStage) {

        // Creates a new instance of scrabble
        scrabble = new Scrabble();

        // Assigns the gameStage
        this.gameStage = gameStage;

        gameStage.setTitle("Scrabble");

        // Setting up of the JavaFx Elements
        gameFrame = new GridPane();
        gameTextInput = setUpTextInput();
        gameTextLog = setUpTextLog();
        gameTextLog.prefHeightProperty().bind(gameFrame.heightProperty());
        gameTextLog.prefWidthProperty().bind(gameFrame.widthProperty());
        gameBoard = setUpBoard(scrabble.getBoard());
        gameBoard.prefHeightProperty().bind(gameFrame.heightProperty());
        gameBoard.prefWidthProperty().bind(gameFrame.widthProperty());

        // Adds each of the elements to the Frame
        gameFrame.getChildren().add(gameBoard);
        gameFrame.add(gameTextLog,1,0);
        gameFrame.add(gameTextInput,1,1);

        // Sets up the javaFx scene
        Scene gameScene = new Scene(gameFrame, Screen.getPrimary().getBounds().getMaxX(),Screen.getPrimary().getBounds().getMaxY());

        gameFrame.prefWidthProperty().bind(gameScene.heightProperty());
        gameFrame.prefWidthProperty().bind(gameScene.widthProperty());
        gameStage.setScene(gameScene);
        //gameStage.setResizable(false);

        // Displays the javaFx scene
        gameStage.show();
    }

    /**
     * Method used to display the scrabble board
     * @param board: Scrabble board object
     * @return GridPane representing the game board
     */
    private GridPane setUpBoard(Board board) {

        // Declares a new gripPane
        GridPane gameBoard = new GridPane();

        // Sets the style of the gripPane
        gameBoard.setStyle("-fx-background-color: lightgray; -fx-vgap: 1; -fx-hgap: 1; -fx-padding: 1;-fx-border-color:black;");

        // Loop through each index of the scrabble board and creates labels to display their values
        for(int i = 0; i < 15; i++) {

            Label label1 = new Label(String.valueOf(i));
            Label label2 = new Label(String.valueOf((char)(i+65)));
            label1.prefHeightProperty().bind(gameBoard.heightProperty());
            label2.prefHeightProperty().bind(gameBoard.heightProperty());
            label1.prefWidthProperty().bind(gameBoard.widthProperty());
            label2.prefWidthProperty().bind(gameBoard.widthProperty());
            label1.setAlignment(Pos.CENTER);
            label2.setAlignment(Pos.CENTER);
            gameBoard.add(label1, i + 1, 0);
            gameBoard.add(label2, 16, i + 1);

        }

        // Loops through each row of the scrabble board
        for(int i = 1; i < 16; i++) {

            // Loops through each index of the current row of the scrabble board
            for(int j = 1; j < 16; j++) {

                // Creates a label to represent the current index
                Label label = new Label(" ");

                // Gets the type of the square at the current index, sets the labels color and text appropriately
                switch(board.getSquare(i-1,j-1).getType()) {
                    case NORMAL:
                        label.setStyle("-fx-background-color: #e6e7e8; -fx-border-color:black; -fx-alignment: center");
                        break;
                    case TRIPLE_WORD:
                        label.setStyle("-fx-background-color: #ed2207; -fx-border-color:black; -fx-alignment: center");
                        label.setText("3W");
                        break;

                    case START:
                    case DOUBLE_WORD:
                        label.setStyle("-fx-background-color: #2f6ced; -fx-border-color:black; -fx-alignment: center");
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

                // Adds the label representing the square in the scrabble board the the GripPane
                label.setPrefSize(55,55);
                gameBoard.add(label, j, i);
            }
        }

        // Returns the GripPane
        return gameBoard;
    }

    /**
     * Method used to visually update the board
     */
    private void updateBoard() {

        // Loops through each row of the scrabble board
        for(int i = 0; i < 15; i++) {

            // Loops through each index of the current row of the scrabble board
            for(int j = 0; j < 15; j++) {
                // Creates a label to represent the current index
                Label label = new Label(" ");

                // Checks if the current board square has a tile in it
                if(scrabble.getBoard().getSquare(i,j).isEmpty()) {

                    // Gets the type of the square at the current index, sets the labels color and text appropriately
                    switch (scrabble.getBoard().getSquare(i, j).getType()) {
                        case NORMAL:
                            label.setStyle("-fx-background-color: #e6e7e8; -fx-border-color:black; -fx-alignment: center");
                            break;
                        case TRIPLE_WORD:
                            label.setStyle("-fx-background-color: #ed2207; -fx-border-color:black; -fx-alignment: center");
                            label.setText("3W");
                            break;
                        case START:
                        case DOUBLE_WORD:
                            label.setStyle("-fx-background-color: #2f6ced; -fx-border-color:black; -fx-alignment: center");
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

                // Sets the style and text of the label accordingly if the current square contains a tile on it
                else{
                    label.setStyle("-fx-background-color: #f2c66d; -fx-border-color:black; -fx-alignment: center");
                    label.setText(scrabble.getBoard().getSquare(i,j).toString());
                }

                // Updates the current label
                label.setPrefSize(40,40);
                gameBoard.add(label, j+1, i+1);
            }
        }

    }


    /**
     * Method to setup the TextArea displaying the terminal output of the game
     * @return TextArea
     */
    private TextArea setUpTextLog() {

        // Creates a new TextArea
        TextArea text_flow = new TextArea();

        // Setup for the TextArea
        text_flow.setEditable(false);
        text_flow.setWrapText(true);
        text_flow.appendText("- Welcome to scrabble, please enter name for player 1 and 2\n");

        // Returns the TextArea
        return text_flow;
    }


    /**
     * Method to setup the TextField used to get user input for the game
     * @return Setup TextField
     */
    private TextField setUpTextInput() {

        // Creates a new TextField
        TextField gameText = new TextField();

        // Setup for event handler for when the user enters input into the TextField
        gameText.setOnKeyReleased(event -> {
            if (event.getCode() == KeyCode.ENTER && !(gameText.getText().equals("")) ){

                // Displays the users input to the gameTextLog
                gameTextLog.appendText(gameText.getText() + "\n");

                // Checks if the game has been setup, if not the setUpEvent is called to handle the input
                if(setup) {

                    setUpEvent(gameText);
                }

                // Checks if the game has ended, if so the gameOver is called to handle the input
                else if(gameOver) {

                    gameOverEvent(gameText);
                }

                else if(challengeMade){

                    challengeEvent(gameText);
                }

                // Otherwise the gameEvent method is called to handle the input
                else {

                    gameEvent(gameText);
                }
            }
        });

        // Returns the TextField
        return gameText;
    }


    /**
     * Method to handle user input when setting up the game of scrabble
     * @param gameText
     */
    private void setUpEvent(TextField gameText) {

        // Try catch to handles errors when setting up the each player
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

    /**
     *
     * @param gameText gameText to get user input
     */
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

                            gameTextLog.appendText("- Passed turn for " + scrabble.getPlayers()[playerTurn%2].getName() + "\n");
                            scrabble.getPlayers()[playerTurn % 2].getPlayerFrame().setToBlank();
                            playerTurn = (playerTurn + 1) % 2;
                            gameTextLog.appendText("- " + scrabble.getPlayers()[playerTurn % 2].getName() +"s move \n- " + scrabble.getPlayers()[playerTurn % 2].getPlayerFrame().toString() + "\n");

                            break;

                        case QUIT:

                            gameOver = true;

                            break;

                        case EXCHANGE:

                            try {

                                scrabble.getPlayers()[playerTurn].getPlayerFrame().swapTiles(text.getWord());
                                gameTextLog.appendText("- Selected tiles have been swapped\n");
                                playerTurn = (playerTurn + 1) % 2;
                                gameTextLog.appendText("- " + scrabble.getPlayers()[playerTurn % 2].getName() +"s move \n- " + scrabble.getPlayers()[playerTurn % 2].getPlayerFrame().toString() + "\n");

                            } catch (Exception e) {

                                gameTextLog.appendText("- Error: " + e.getMessage() + "\n");
                            }
                            break;

                        case PLACE_TILE:

                            try {

                                if (text.getStartPosition().length == 2 && Board.checkValidPosition(text.getStartPosition())) {

                                    currentMove = new MoveInfo(scrabble.getPlayers()[playerTurn % 2], text.getStartPosition(), text.getWordDirection(), text.getWord());
                                    scrabble.getBoard().placeTiles(currentMove);
                                    scrabble.getMoveHistory().add(currentMove);
                                }

                                else {
                                    throw new InvalidScrabbleException("Invalid Start Position Inputted.\n");
                                }

                                updateBoard();
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

                                gameTextLog.appendText("- " + scrabble.getPlayers()[playerTurn % 2].getName() +"s move \n- " + scrabble.getPlayers()[playerTurn % 2].getPlayerFrame().toString() + "\n");

                            } catch (Exception e) {
                                gameTextLog.appendText("- Error: " + e.getMessage() + "\n");
                            }
                            break;

                        case RESTART:
                            gameTextLog.appendText("- Cannot restart a game that has not ended (QUIT to end game)\n");
                            gameTextLog.appendText("- " + scrabble.getPlayers()[playerTurn % 2].getName() +"s move \n- " + scrabble.getPlayers()[playerTurn % 2].getPlayerFrame().toString() + "\n");
                            break;

                        default:
                            gameTextLog.appendText("- Error: Unknown command\n");
                    }
                }

                else{
                    try{

                        if(text.getWord()[0] == 'Y') {

                            //TODO Check Dictionary for Word - Sprint 4
                            gameTextLog.appendText("- Challenge has been made\n");
                            gameTextLog.appendText("- Challenge is currently handled manually, enter Y if challenge was successful or N if not\n");
                            challengeMade = true;
                            challenge = false;
                        }

                        else  if(text.getWord()[0] =='N'){

                            gameTextLog.appendText("- Challenged has not been made\n");
                            makeMove();
                            playerTurn = (playerTurn + 1) % 2;
                            gameTextLog.appendText("- " + scrabble.getPlayers()[playerTurn % 2].getName() +"s move \n- " + scrabble.getPlayers()[playerTurn % 2].getPlayerFrame().toString() + "\n");
                            challenge = false;
                        }

                        else{
                            gameTextLog.appendText("- Error: Incorrect input for Challenge (CHALLENGE Y/N)"  + "\n");
                        }

                    }catch(Exception e){
                        gameTextLog.appendText("- Error: Incorrect input for Challenge (CHALLENGE Y/N)"  + "\n");
                    }
                }

            }catch(Exception e){
                gameTextLog.appendText("- Error: " + e.getMessage() + "\n");
            }

        gameText.setText("");

        if(scrabble.isGameOver() || gameOver){
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
            gameTextLog.appendText("- Type QUIT to exit game or RESTART to start a new game!\n");
        }

    }

    private String gameHelp(){
        return "- Commands:\n" +
                "- HELP: Prints out the list of available commands\n" +
                "- PASS: Passes the current players turn\n" +
                "- QUIT: Ends the current game of scrabble\n" +
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

        gameText.setText("");

    }

    private  void challengeEvent(TextField gameText){

            String input = gameText.getCharacters().toString();
            System.out.println(input.equals("Y"));
            if(input.equals("Y")){
                gameTextLog.appendText("- Challenge has passed\n");
                scrabble.getBoard().removeMove(currentMove);
                scrabble.getPlayers()[playerTurn % 2].decreaseScore(currentMove.getMoveScore());
                scrabble.getPlayers()[playerTurn % 2].getPlayerFrame().setToBlank();
                updateBoard();
                playerTurn = (playerTurn%2) + 1;
                gameTextLog.appendText("- " + scrabble.getPlayers()[playerTurn % 2].getName() +"s move \n- " + scrabble.getPlayers()[playerTurn % 2].getPlayerFrame().toString() + "\n");
                challengeMade = false;
            }
            else if(input.equals("N")){
                gameTextLog.appendText("- Challenge has failed, players turn has been skipped\n");
                makeMove();
                gameTextLog.appendText("- " + scrabble.getPlayers()[playerTurn % 2].getName() +"s move \n- " + scrabble.getPlayers()[playerTurn % 2].getPlayerFrame().toString() + "\n");
            }
            else{
                gameTextLog.appendText("- Error: Unknown command for challenge \n");
            }
            gameText.setText("");


    }


    private void makeMove(){
        scrabble.getPlayers()[playerTurn % 2].getPlayerFrame().removeTiles(currentMove.getRequiredTiles());
        scrabble.getPlayers()[playerTurn % 2].increaseScore(currentMove.getMoveScore());
        scrabble.getPlayers()[playerTurn % 2].getPlayerFrame().fillFrame();
        gameTextLog.appendText("- " + scrabble.getPlayers()[playerTurn % 2].getName() + " move scored "+ currentMove.getMoveScore()+". Total score: "+scrabble.getPlayers()[playerTurn % 2].getScore()+ "\n");
        scrabble.getBoard().setWordSquaresNormal(currentMove.getPrimaryWord());
        scrabble.getPlayers()[playerTurn % 2].getPlayerFrame().setToBlank();
    }

}
