package scrabble.exceptions;

/**
 * Custom Exception for Word Class
 */
public class InvalidWordException extends IllegalArgumentException{
    public  InvalidWordException(String s){
        super(s);
    }
}