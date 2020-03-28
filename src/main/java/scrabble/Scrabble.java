package scrabble;

import scrabble.exceptions.InvalidScrabbleException;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;


public class Scrabble {

    /**
     * The Scrabble Board
     */
    private Board board;

    /**
     * HashSet to store the dictionary of valid words
     */
    private HashSet<String> dictionary;

    /**
     * The Pool of Tiles for the Scrabble Game
     */
    private Pool pool;

    /**
     * Player one and two of the Scrabble Game
     */
    private Player[] players;

    /**
     * ArrayList to store a history of all previous moves
     */
    private ArrayList<MoveInfo> moveHistory;

    /**
     * Scrabble Game Constructor
     *
     * Creates a new game of Scrabble
     */
    public Scrabble() throws FileNotFoundException {
        board = new Board();

        pool = new Pool();

        players = new Player[2];

        moveHistory = new ArrayList<>();

        dictionary = new HashSet(2677753);

        loadDictionary();
    }

    /**
     * Accessor Method for Board
     *
     * @return The Board
     */
    public Board getBoard(){
        return this.board;
    }

    /**
     * Accessor Method for Pool
     *
     * @return The Poll
     */
    public Pool getPool(){
        return this.pool;
    }

    /**
     * Accessor Method for Players
     *
     * @return The Players array
     */
    public Player[] getPlayers(){
        return this.players;
    }

    /**
     * Accessor Method for MoveHistory
     *
     * @return The MoveHistory ArrayList
     */
    public ArrayList<MoveInfo> getMoveHistory() {
        return this.moveHistory;
    }



    /**
     * Method to create a player with an inputted name
     *
     * @param name The name of the player
     * @param playerNumber The index of the player in the players array
     * @throws InvalidScrabbleException If the index is out of bounds of the players array
     */
    public void createPlayer(String name, int playerNumber){
        if (playerNumber <= 0 || playerNumber < players.length){
            players[playerNumber] = new Player(name, pool);
        }
        else{
            throw new InvalidScrabbleException("Please select either player 1 or 2.");
        }
    }

    /**
     * Method to complete a Player move
     *
     * @param startPosition Start position of the Word
     * @param direction Direction of the Word
     * @param word Word in char array form
     * @param player Player making the move
     */
    public void playerMove(int[] startPosition, UserInput.Direction direction, char[] word, Player player){
        if (startPosition.length == 2 && Board.checkValidPosition(startPosition)) {

            MoveInfo move = new MoveInfo(player, startPosition, direction, word);
            board.placeTiles(move);
            player.getPlayerFrame().removeTiles(move.getRequiredTiles());
            moveHistory.add(move);

            player.getPlayerFrame().setToBlank();

        }
        else {
            throw new InvalidScrabbleException("Invalid Start Position Inputted.");
        }
    }


    /**
     * Method to check if the game is over
     *
     * @return True if the game is over
     */
    public boolean isGameOver(){
        return pool.isEmpty() && (players[0].getPlayerFrame().isEmpty() || players[1].getPlayerFrame().isEmpty());
    }

    /**
     * Method to subtract Frame Tile values from Players' scores at the end of the game
     */
    public void gameOver(){

        for (int i = 0; i < players.length; i++) {
            players[i].decreaseScore(players[i].getPlayerFrame().tileValues());
        }
    }

    /**
     * Method to scan in the dictionary for the game of Scrabble
     *
     * @throws FileNotFoundException
     */
    private void loadDictionary() throws FileNotFoundException {

            String currentWord;

            //Create scanner for the dictionary text file
            Scanner scanner = new Scanner(getClass().getResourceAsStream("sowpods.txt"));

            //While loop to scan in each word in the dictionary file
            while (scanner.hasNext()) {
                currentWord = scanner.nextLine().toUpperCase();

                if (currentWord != null) {
                    //Add the word to the dictionary HashSet
                    dictionary.add(currentWord);
                }
            }

            scanner.close();
    }

    /**
     * Method to validate the words created in a move
     *
     * @param move The move to validate
     * @return
     */
    public boolean dictionaryWords(MoveInfo move){
        boolean result = true;
        //First word is the primary word of the move
        String currentWord = new String(move.getPrimaryWord().getWord());

        //If the primary word is not in the dictionary
        if (!dictionary.contains(currentWord)){
            result = false;
        }
        else {
            //For loop to check each auxiliary word is in the for loop
            for (int i = 0; i < move.getAuxiliaryWords().size() && result; i++) {

                currentWord = new String(move.getAuxiliaryWords().get(i).getWord());

                //If word is not in the dictionary
                if (!dictionary.contains(currentWord)){
                    result = false;
                }
            }
        }

        return result;
    }

}
