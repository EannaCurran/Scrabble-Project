package scrabble.exceptions;

/**
 * Custom Exception for Scrabble Class
 */
public class InvalidScrabbleException extends IllegalArgumentException{
    public  InvalidScrabbleException(String s){
        super(s);
    }
}