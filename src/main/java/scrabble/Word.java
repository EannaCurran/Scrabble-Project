package scrabble;

import scrabble.exceptions.InvalidWordException;

/**
 * Word Class to store information about a word on the board
 */
public class Word {

    /**
     * Start Position of the word
     */
    private int[] startPosition;

    /**
     * The array of the characters in the word
     */
    private char[] word;

    /**
     * The direction of the word
     */
    private UserInput.Direction direction;

    /**
     * Word Constructor
     *
     * @param coOrdinates The starting position on the board (row, col)
     * @param d The direction of the Word
     * @param w The word in char array form
     * @throws InvalidWordException The Word must be between 1 - 15 letters
     */
    public Word(int[] coOrdinates, UserInput.Direction d, char[] w){
        if (Board.checkValidPosition(coOrdinates) && validWord(w) && d != null){
            startPosition = coOrdinates;
            direction = d;
            word = w;
        }else {
            throw new InvalidWordException("The word co-ordinates must be on the Board.\nThe word must between 1 - 15 capital letters long.");
        }
    }

    /**
     * Accessor Method for the Word
     *
     * @return The word in char array form
     */
    public char[] getWord() {
        return word;
    }

    /**
     * Accessor Method for the Direction
     *
     * @return The Direction of the Word
     */
    public UserInput.Direction getDirection() {
        return direction;
    }

    /**
     * Accessor Method for the Start Position
     *
     * @return The co-ordinates for the Start Position (row, col)
     */
    public int[] getStartPosition() {
        return startPosition;
    }

    /**
     * Check that a word is made of valid tiles
     *
     * @param w The Word in Char array form
     * @return True if valid Word
     */
    private boolean validWord(char[] w){
        //A word must have between 1 - 15 letters and contain only capital letters
        return new String(w).matches("^[A-Z]{1,15}+");
    }

    /**
     * toString method for Word
     *
     * @return Word in string form
     */
    @Override
    public String toString() {
        return word + " " + startPosition + " " + direction;
    }
}
