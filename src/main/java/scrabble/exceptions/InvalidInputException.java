package scrabble.exceptions;

/**
 * Custom exception for UserInput Class
 */
public class InvalidInputException extends IllegalArgumentException{
    public  InvalidInputException(String s){
        super(s);
    }
}
