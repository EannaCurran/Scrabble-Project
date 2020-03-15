package scrabble;

import scrabble.exceptions.InvalidMoveInfoException;

import java.util.ArrayList;

/**
 * Object to store data on a move
 */
public class MoveInfo {

    /**
     * The main word the player is placing on the board
     */
    private Word primaryWord;

    /**
     * The required Tile char the player needs
     */
    private char[] requiredTiles;

    /**
     * Positions on the board for the required Tile
     */
    private int[][] requiredTilesPositions;

    /**
     * Array of all other words being created
     */
    private ArrayList<Word> auxiliaryWords;

    /**
     * Score value of the move
     */
    private int moveScore;

    /**
     * Player making the move
     */
    private Player player;

    /**
     * Accessor Method for auxiliaryWords
     *
     * @return auxiliaryWords
     */
    public ArrayList<Word> getAuxiliaryWords() {
        return auxiliaryWords;
    }

    /**
     * Accessor Method for requiredTiles
     *
     * @return requiredTiles
     */
    public char[] getRequiredTiles() {
        return requiredTiles;
    }

    /**
     * Accessor Method for requiredTilesPositions
     *
     * @return requiredTilesPositions
     */
    public int[][] getRequiredTilesPositions() {
        return requiredTilesPositions;
    }

    /**
     * Accessor Method for moveScore
     *
     * @return moveScore
     */
    public int getMoveScore() {
        return moveScore;
    }

    /**
     * Accessor Method for moveScore
     *
     * @return moveScore
     */
    public Word getPrimaryWord() {
        return primaryWord;
    }

    /**
     * Accessor Method for Player
     *
     * @return Player
     */
    public Player getPlayer() {
        return player;
    }

    /**
     * MoveInfo Constructor
     *
     * @param p Player making the move
     * @param coOrdinates Position of the first letter of the Word
     * @param d Direction of the Word
     * @param w Word in char array form
     * @throws InvalidMoveInfoException Player can not be Null
     */
    public MoveInfo(Player p, int[] coOrdinates, UserInput.Direction d, char[] w){

        //Create the Word for the move
        primaryWord = new Word(coOrdinates, d, w);

        auxiliaryWords = new ArrayList<>();

        if (p != null){
            player = p;
        }
        else{
            throw new InvalidMoveInfoException("Player can not be Null\n");
        }

    }


    /**
     * Mutator Method for Score
     *
     * @param score The Score of the move
     * @throws InvalidMoveInfoException The move must have a positive score
     */
    protected void setScore(int score){
        if (score < 0){
            throw new InvalidMoveInfoException("Invalid Move Score score must be positive\n");
        }
        else{
            moveScore = score;
        }
    }

    /**
     * Method to add auxiliary Words to the move
     *
     * @param auxWord Word to be added
     */
    public void addAuxiliaryWord(Word auxWord){
        auxiliaryWords.add(auxWord);
    }

    /**
     * Mutator Method for requiredTiles and requiredTilesPositions
     *
     * @param requiredTiles The Tiles required for the move
     * @param requiredTilesPositions The positions for the Tiles on the Board
     */
    public void setRequiredTiles(char[] requiredTiles, int[][] requiredTilesPositions) {

        // If there is between 1 - 7 tiles and their are positions for all Tiles
        if (requiredTiles.length > 0 && requiredTiles.length <= Frame.FRAME_SIZE && requiredTiles.length == requiredTilesPositions.length){
            this.requiredTiles = requiredTiles;
            this.requiredTilesPositions = requiredTilesPositions;
        }
    }


}
