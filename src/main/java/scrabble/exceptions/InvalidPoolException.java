package scrabble.exceptions;

import java.util.NoSuchElementException;

/**
 * Custom Exception for Pool Class
 */
public class InvalidPoolException extends NoSuchElementException {
    public  InvalidPoolException(String s){
        super(s);
    }
}