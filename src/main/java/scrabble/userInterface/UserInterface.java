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
import scrabble.exceptions.InvalidFrameException;
import scrabble.exceptions.InvalidScrabbleException;

import java.io.FileNotFoundException;
import java.util.Random;


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
    private boolean gameOver = false;
    private MoveInfo currentMove;


    /**
     * Main method to launch application
     * @param args: empty argument
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
        try {
            scrabble = new Scrabble();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        // Assigns the gameStage
        this.gameStage = gameStage;

        gameStage.setTitle("Scrabble");

        // Setting up of the JavaFx Elements
        gameFrame = new GridPane();
        gameTextInput = setUpTextInput();
        gameTextLog = setUpTextLog();
        gameBoard = setUpBoard(scrabble.getBoard());

        // Dynamic sizing for gameBoard and gameTextLog
        gameTextLog.prefHeightProperty().bind(gameFrame.heightProperty());
        gameTextLog.prefWidthProperty().bind(gameFrame.widthProperty());
        gameBoard.prefHeightProperty().bind(gameFrame.heightProperty());
        gameBoard.prefWidthProperty().bind(gameFrame.widthProperty());

        // Adds each of the elements to the Frame
        gameFrame.getChildren().add(gameBoard);
        gameFrame.add(gameTextLog,1,0);
        gameFrame.add(gameTextInput,1,1);

        // Sets up the javaFx scene and size
        Scene gameScene = new Scene(gameFrame, Screen.getPrimary().getBounds().getMaxX() * .85,Screen.getPrimary().getBounds().getMaxY() * .85);

        gameStage.setScene(gameScene);

        // Sets minimum size of screen
        gameStage.setMinHeight(480);
        gameStage.setMinWidth(720);

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

            //Dynamic sizing for each label according to the size of the gameBoard
            label1.prefHeightProperty().bind(gameBoard.heightProperty());
            label2.prefHeightProperty().bind(gameBoard.heightProperty());
            label1.prefWidthProperty().bind(gameBoard.widthProperty());
            label2.prefWidthProperty().bind(gameBoard.widthProperty());

            label1.setAlignment(Pos.CENTER);
            label2.setAlignment(Pos.CENTER);

            // Adding labels with number to the top of the board
            gameBoard.add(label1, i + 1, 0);

            //Adding labels with letters to the right side of the board
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
                label.setPrefSize(55,55);
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
     * @param gameText: TextField containing the players input
     */
    private void setUpEvent(TextField gameText) {


        // Try catch to handles errors when setting up the each player
        try {

            // Creates a player with the given gameTExt input
            scrabble.createPlayer(gameText.getCharacters().toString(), playerTurn);

            // Displays the players name to the user and increases the number of players
            gameTextLog.appendText("- Player " + (playerTurn+1) + " name set to " + scrabble.getPlayers()[playerTurn].getName() + "\n");
            playerTurn++;
        }catch (Exception e){
            gameTextLog.appendText(e.getMessage() + "\n");
        }

        // If two players have been created the beginning of the game is displayed
        if(playerTurn == 2) {

            // PlayerTurn set to a random player, now is used to contain which players turn it is
            Random r = new Random();
            playerTurn = r.nextInt(2);

            // Displays the rules to the user at the start of the game alongside the first players frame
            gameTextLog.appendText(gameHelp());
            gameTextLog.appendText("- " + scrabble.getPlayers()[playerTurn % 2].getName() +"s move (" + scrabble.getPlayers()[playerTurn % 2].getScore() + ") \n- " + scrabble.getPlayers()[playerTurn % 2].getPlayerFrame().toString() + "\n");

            // Setup set to false so setupEvent cannot be called
            setup = false;
        }

        // Sets input box to blank
        gameText.setText("");
    }

    /**
     * Method to handle the main game events which occur during scrabble
     * @param gameText TextField containing the players input
     */
    private void gameEvent(TextField gameText) {

        // Variable to hold the parsed form of the players input
        UserInput text;

        // Try catch for parsing game event
        try {

            // Parses the gameText input
            text = UserInput.parseInput(gameText.getCharacters().toString());

            // Check for if a challenge can be made
            if(!challenge) {

                // Switch statement to get the type of input from the player
                switch (text.getInputType()) {

                    // Case for when input is type HELP
                    case HELP:

                        // Game rules are appended to the gameTextLog
                        gameTextLog.appendText(gameHelp());

                        // Displays the current players frame to the gameTextLog
                        gameTextLog.appendText("- " + scrabble.getPlayers()[playerTurn % 2].getName() +"s move (" + scrabble.getPlayers()[playerTurn % 2].getScore() + ") \n- " + scrabble.getPlayers()[playerTurn % 2].getPlayerFrame().toString() + "\n");
                        break;

                    // Case for when the input is type PASS
                    case PASS:

                        // Passes the turn of the current player
                        gameTextLog.appendText("- Passed turn for " + scrabble.getPlayers()[playerTurn%2].getName() + "\n");
                        scrabble.getPlayers()[playerTurn % 2].getPlayerFrame().setToBlank();
                        playerTurn = (playerTurn + 1) % 2;

                        // Displays the current players frame to the gameTextLog
                        gameTextLog.appendText("- " + scrabble.getPlayers()[playerTurn % 2].getName() +"s move (" + scrabble.getPlayers()[playerTurn % 2].getScore() + ") \n- " + scrabble.getPlayers()[playerTurn % 2].getPlayerFrame().toString() + "\n");
                        break;

                    // Case for when the input is type QUIT
                    case QUIT:

                        // Sets the gameOver boolean check to true;
                        gameOver = true;
                        break;

                    // Case for when the input is type EXCHANGE
                    case EXCHANGE:

                        // Try catch for if the swapping of tiles fails
                        try {

                            // Swaps the tiles inputted in text with random tiles in the pool
                            scrabble.getPlayers()[playerTurn].getPlayerFrame().checkTiles(text.getWord());
                            scrabble.getPlayers()[playerTurn].getPlayerFrame().swapTiles(text.getWord());

                            // Displays that the swap has been made and goes to the next players turn
                            gameTextLog.appendText("- Selected tiles have been swapped\n");
                            playerTurn = (playerTurn + 1) % 2;
                            gameTextLog.appendText("- " + scrabble.getPlayers()[playerTurn % 2].getName() +"s move (" + scrabble.getPlayers()[playerTurn % 2].getScore() + ") \n- " + scrabble.getPlayers()[playerTurn % 2].getPlayerFrame().toString() + "\n");

                        // Catches and displays any exceptions thrown
                        } catch (Exception e) {
                            gameTextLog.appendText("- Error: " + e.getMessage() + "\n");
                        }
                        break;

                    // Case for when the input is type PLACE_TILE
                    case PLACE_TILE:

                        // Try catch for if the selected tiles cannot be placed on the board
                        try {

                            // Checks that the starting position is valid
                            if (text.getStartPosition().length == 2 && Board.checkValidPosition(text.getStartPosition())) {

                                    // Temporarily stores the information regarding the current move
                                    currentMove = new MoveInfo(scrabble.getPlayers()[playerTurn % 2], text.getStartPosition(), text.getWordDirection(), text.getWord());

                                    // Displays the move on the board and adds the move to the moveHistory
                                    scrabble.getBoard().placeTiles(currentMove);
                                    scrabble.getMoveHistory().add(currentMove);
                                }

                            // Throws exception if an invalid stating position is entered
                            else {
                                throw new InvalidScrabbleException("Invalid Start Position Inputted.\n");
                            }

                            // Updates the visuals of the board
                            updateBoard();

                            // Lets the next player challenge the move that will be made
                            challenge = true;
                            gameTextLog.appendText("- Does " + scrabble.getPlayers()[(playerTurn + 1) % 2].getName() + " want to challenge this move? (CHALLENGE <Y/N>)\n");

                        // Catches and displays any exceptions thrown
                        } catch (Exception e) {
                                gameTextLog.appendText("- Error: " + e.getMessage() + "\n");
                        }
                        break;

                    // Case for when the input is type BLANK
                    case BLANK:

                        // Try catch for if an exception is thrown
                        try {

                            // Checks if the player has a blank tile
                            if(scrabble.getPlayers()[playerTurn].getPlayerFrame().hasBlank()) {

                                // Sets the blank tile to the inputted character
                                scrabble.getPlayers()[playerTurn].getPlayerFrame().setBlanks(text.getWord());

                                // Displays that the blank tile has been set and the current players frame to the gameTextLog
                                gameTextLog.appendText("- Blank tile has been set\n");
                                gameTextLog.appendText("- " + scrabble.getPlayers()[playerTurn % 2].getName() + "s move (" + scrabble.getPlayers()[playerTurn % 2].getScore() + ") \n- " + scrabble.getPlayers()[playerTurn % 2].getPlayerFrame().toString() + "\n");
                            }

                            // Throws new exception if the player has no blank tile
                            else {
                                throw new InvalidFrameException("Player does not have a blank tile to set");
                            }

                        // Catches and displays any exceptions thrown
                        } catch (Exception e) {
                            gameTextLog.appendText("- Error: " + e.getMessage() + "\n");
                        }
                        break;

                    // Case for when the input type is RESTART
                    case RESTART:

                        // Displays the conditions needed to restart to the user
                        gameTextLog.appendText("- Cannot restart a game that has not ended (QUIT to end game)\n");
                        gameTextLog.appendText("- " + scrabble.getPlayers()[playerTurn % 2].getName() +"s move (" + scrabble.getPlayers()[playerTurn % 2].getScore() + ") \n- " + scrabble.getPlayers()[playerTurn % 2].getPlayerFrame().toString() + "\n");
                        break;

                    // Case for any unknown input type
                    default:
                            gameTextLog.appendText("- Error: Unknown command\n");
                }
            }

            // Condition for when a challenge can be made for the other user
            else {

                // Try catch for challenge input
                try {

                    // Checks if the player has indicated that want to make a challenge
                    if(text.getWord()[0] == 'Y') {

                        // Displays this to the player and sets boolean challengeMade to true to enter the challengeEvent
                        gameTextLog.appendText("- Challenge has been made\n");

                        // Checks if the word is in the dictionary
                        boolean checkChallenge = scrabble.dictionaryWords(currentMove);

                        // Checks if the challenge was successful
                        if (!checkChallenge) {

                            // Displays to the players that the challenge was successful
                            gameTextLog.appendText("- Challenge was successful, players move has been removed\n");

                            // Removes the move from the board
                            scrabble.getBoard().removeMove(currentMove);

                            // Updates the current players frame
                            scrabble.getPlayers()[playerTurn % 2].getPlayerFrame().setToBlank();

                            // Updates the visuals of the board
                            updateBoard();

                            // Moves onto the next players turn and displays their frame
                            playerTurn = (playerTurn % 2) + 1;
                        } else {
                            // Displays the result to the players
                            gameTextLog.appendText("- Challenge has failed, challengers turn has been skipped\n");

                            /// Calls the makeMove method to carry out the most recent move
                            makeMove();

                            // Skips the turn of the player who made the challenge
                        }
                        gameTextLog.appendText("- " + scrabble.getPlayers()[playerTurn % 2].getName() + "s move (" + scrabble.getPlayers()[playerTurn % 2].getScore() + ") \n- " + scrabble.getPlayers()[playerTurn % 2].getPlayerFrame().toString() + "\n");
                        challenge = false;
                    }

                    // Checks if the user has indicated that do not want to make a move
                    else if(text.getWord()[0] =='N') {

                        // Displays this to the players
                        gameTextLog.appendText("- Challenge has not been made\n");

                        // Calls the makeMove method to carry out the most recent move
                        makeMove();

                        // Moves to the next players turn
                        playerTurn = (playerTurn + 1) % 2;
                        gameTextLog.appendText("- " + scrabble.getPlayers()[playerTurn % 2].getName() +"s move (" + scrabble.getPlayers()[playerTurn % 2].getScore() + ") \n- " + scrabble.getPlayers()[playerTurn % 2].getPlayerFrame().toString() + "\n");

                        // Sets challenge to false
                        challenge = false;
                    }

                    // Displays the correct format of input if the user did not input Y or N
                    else{
                        gameTextLog.appendText("- Error: Incorrect input for Challenge (CHALLENGE Y/N)"  + "\n");
                    }

                    // Displays the correct format of input if the user did not input CHALLENGE
                } catch(Exception e) {
                    gameTextLog.appendText("- Error: Incorrect input for Challenge (CHALLENGE Y/N)"  + "\n");
                }
            }

        // Catches any exception thrown from parsing gameText
        } catch(Exception e) {
            gameTextLog.appendText("- Error: " + e.getMessage() + "\n");
        }

        // Clears input in gameText
        gameText.setText("");

        // Checks if the game is over or if the player has quit the game
        if(scrabble.isGameOver() || gameOver){

            // Calls gameOver method
            scrabble.gameOver();

            // Sets boolean gameOver to enter gameOverEvent
            gameOver = true;

            // Displays the end of game and scores of each player
            gameTextLog.appendText("- GAME OVER!\n");
            gameTextLog.appendText("- "+scrabble.getPlayers()[0].getName() +" SCORE: " + scrabble.getPlayers()[0].getScore()+"\n");
            gameTextLog.appendText("- "+scrabble.getPlayers()[1].getName() +" SCORE: " + scrabble.getPlayers()[1].getScore()+"\n");

            // Checks which player has the highest score and displays them as winner
            if(scrabble.getPlayers()[0].getScore() > scrabble.getPlayers()[1].getScore()) {

                gameTextLog.appendText("- " + scrabble.getPlayers()[0].getName() + " WINS!\n");
            }

            else if(scrabble.getPlayers()[0].getScore() < scrabble.getPlayers()[1].getScore()) {

                gameTextLog.appendText("- " + scrabble.getPlayers()[1].getName() + " WINS!\n");
            }

            else {

                gameTextLog.appendText("- THE GAME HAS ENDED IN A DRAW!\n");
            }

            // Displays options for the gameOverEvent
            gameTextLog.appendText("- Type QUIT to exit game or RESTART to start a new game!\n");
        }

    }


    /**
     * Method to return a string containing information about each command in the game
     * @return String containing the commands of the game
     */
    private String gameHelp(){
        return "- Commands:\n" +
                "- HELP: Prints out the list of available commands.\n" +
                "- PASS: Passes the current players turn.\n" +
                "- QUIT: Ends the current game of Scrabble.\n" +
                "- EXCHANGE [Characters]: Exchanges the letters in the frame with random letters in the pool (Eg: EXCHANGE AB).\n" +
                "- BLANK [Characters]: Sets the blank tiles in a players frame to a letter, only functional if a blank tile is in the players frame (Eg: BLANK A).\n" +
                "- CHALLENGE [Y/N]: Allows the opponent to challenge a move to be made by the other player, only available after a player has inputted a move.\n" +
                "- RESTART: Launches a new game, can only be called at the end of a game.\n" +
                "- [Grid Reference] [Direction] [Characters] : Places the characters starting at the grid reference and going in the given direction onto the board (Eg: H7 A HELLO).\n";
    }


    /**
     * Method to handle events after the end of the fame
     * @param gameText: TextField containing the players input
     */
    private void gameOverEvent(TextField gameText){

        // Variable to hold the parsed form of the players input
        UserInput text;

        // Try catch for parsing end of game events
        try {

            // Parses the gameText input
            text = UserInput.parseInput(gameText.getCharacters().toString());

            // Switch statement to get the type of input from the player
            switch (text.getInputType()) {

                // Case for when the input is type QUIT
                case QUIT:

                    // Program is exited
                    System.exit(0);
                    break;

                // Case for when the input is type RESTART
                case RESTART:

                    // Closes the current stage and launches a new stage
                    gameStage.close();
                    Platform.runLater(() -> new UserInterface().start(new Stage()));
                    break;

                // Case for any unknown input type
                default:
                    gameTextLog.appendText("- Error: Unknown command for end of game (QUIT/RESTART)\n");
            }

        // Catches and displays any exceptions thrown
        } catch(Exception e) {

            gameTextLog.appendText("- Error: " + e.getMessage() + "\n");
        }

        // Clears input in gameText
        gameText.setText("");

    }

    /**
     * Method to execute the most recent move a player has made
     */
    private void makeMove(){

        // Removes the tiles for the move from the players frame
        scrabble.getPlayers()[playerTurn % 2].getPlayerFrame().removeTiles(currentMove.getRequiredTiles());

        // Increases the players score by the amount of points the move has made
        scrabble.getPlayers()[playerTurn % 2].increaseScore(currentMove.getMoveScore());

        // Refills the players frame
        scrabble.getPlayers()[playerTurn % 2].getPlayerFrame().fillFrame();

        // Displays the points scored by the move to the player
        gameTextLog.appendText("- " + scrabble.getPlayers()[playerTurn % 2].getName() + " move scored "+ currentMove.getMoveScore()+". Total score: "+scrabble.getPlayers()[playerTurn % 2].getScore()+ "\n");

        // Sets any squares that tiles have been placed on in the move to a normal type square
        scrabble.getBoard().setWordSquaresNormal(currentMove.getPrimaryWord());

        // Resets any blank tiles in the players frame
        scrabble.getPlayers()[playerTurn % 2].getPlayerFrame().setToBlank();
    }
}
