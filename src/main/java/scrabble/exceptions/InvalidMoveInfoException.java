package scrabble.exceptions;

/**
 * Custom Exception for MoveInfo Class
 */
public class InvalidMoveInfoException extends IllegalArgumentException{
    public  InvalidMoveInfoException(String s){
        super(s);
    }
}