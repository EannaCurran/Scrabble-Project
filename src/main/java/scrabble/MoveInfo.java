package scrabble;

import scrabble.exceptions.InvalidMoveInfoException;

import java.util.ArrayList;

/**
 * Object to store data on a move
 */
public class MoveInfo {

    private Word primaryWord;

    private char[] requiredTiles;
    private int[][] requiredTilesPositions;

    private ArrayList<Word> auxiliaryWords;

    int moveScore;

    Player player;

    public ArrayList<Word> getAuxiliaryWords() {
        return auxiliaryWords;
    }

    public char[] getRequiredTiles() {
        return requiredTiles;
    }

    public int[][] getRequiredTilesPositions() {
        return requiredTilesPositions;
    }

    public int getMoveScore() {
        return moveScore;
    }

    public Word getPrimaryWord() {
        return primaryWord;
    }

    public Player getPlayer() {
        return player;
    }

    public MoveInfo(Player p, int[] coOrdinates, UserInput.Direction d, char[] w){
        primaryWord = new Word(coOrdinates, d, w);


        if (p != null){
            player = p;
        }
        else{
            throw new InvalidMoveInfoException("Player can not be Null\n");
        }

    }

    protected void setScore(int score){
        if (score < 0){
            throw new InvalidMoveInfoException("Invalid Move Score score must be positive\n");
        }
        else{
            moveScore = score;
        }
    }

    public void addAuxiliaryWord(Word auxWord){
        auxiliaryWords.add(auxWord);
    }

    public void setRequiredTiles(char[] requiredTiles, int[][] requiredTilesPositions) {
        if (requiredTiles.length > 0 && requiredTiles.length <= Frame.FRAME_SIZE && requiredTiles.length == requiredTilesPositions.length){
            this.requiredTiles = requiredTiles;
            this.requiredTilesPositions = requiredTilesPositions;
        }
    }


}
