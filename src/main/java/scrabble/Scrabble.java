package scrabble;

import scrabble.exceptions.InvalidScrabbleException;
import java.util.ArrayList;

public class Scrabble {

    /**
     * The Scrabble Board
     */
    private Board board;

    /**
     * The Pool of Tiles for the Scrabble Game
     */
    private Pool pool;

    /**
     * Player one and two of the Scrabble Game
     */
    private Player[] players;

    /**
     * Player turn order
     *
     * False player one goes first
     * True Player two goes First
     */
    private Boolean playerOrder;

    /**
     * ArrayList to store a history of all previous moves
     */
    private ArrayList<MoveInfo> moveHistory;

    /**
     * Scrabble Game Constructor
     *
     * Creates a new game of Scrabble
     */
    public Scrabble(){
        board = new Board();

        pool = new Pool();

        players = new Player[2];

        playerOrder = false;

        moveHistory = new ArrayList<>();
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
            throw new InvalidScrabbleException("Please select either player 1 or 2.\n");
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
            throw new InvalidScrabbleException("Invalid Start Position Inputted.\n");
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



}
