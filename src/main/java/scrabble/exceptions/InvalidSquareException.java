package scrabble.exceptions;

//Custom Exception for Frame Class
public class InvalidSquareException extends IllegalArgumentException{
    public  InvalidSquareException(String s){
        super(s);
    }
}