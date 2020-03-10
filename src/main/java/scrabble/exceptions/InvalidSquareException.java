package scrabble.exceptions;

/**
 * Custom Exception for Square Class
 */
public class InvalidSquareException extends IllegalArgumentException{
    public  InvalidSquareException(String s){
        super(s);
    }
}