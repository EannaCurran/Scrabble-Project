package scrabble.exceptions;

/**
 * Custom Exception for Frame Class
 */
public class InvalidFrameException extends IllegalArgumentException{
    public  InvalidFrameException(String s){
        super(s);
    }
}