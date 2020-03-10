package scrabble.exceptions;

//Custom Exception for Frame Class
public class InvalidScrabbleException extends IllegalArgumentException{
    public  InvalidScrabbleException(String s){
        super(s);
    }
}