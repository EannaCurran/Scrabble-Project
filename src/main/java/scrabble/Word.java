package scrabble;


import scrabble.exceptions.InvalidWordException;

public class Word {

    private int[] startPosition;

    private  char[] word;

    private  UserInput.Direction direction;

    public Word(int[] coOrdinates, UserInput.Direction d, char[] w){
        if (Board.checkValidPosition(coOrdinates) && validWord(w)){
            startPosition = coOrdinates;
            direction = d;
            word = w;
        }else {
            throw new InvalidWordException("The word co-ordinates must be on the Board\nThe word must between 1 - 15 capital letters long\n");
        }
    }

    public char[] getWord() {
        return word;
    }

    public UserInput.Direction getDirection() {
        return direction;
    }

    public int[] getStartPosition() {
        return startPosition;
    }

    private boolean validWord(char[] w){
        //A word must have between 1 - 15 letters and contain only capital letters
        return new String(w).matches("^[A-Z]{1,15}+");
    }
}
