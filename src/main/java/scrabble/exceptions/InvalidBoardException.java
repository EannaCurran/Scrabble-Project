package scrabble.exceptions;

//Custom Exception for Frame Class
public class InvalidBoardException extends IllegalArgumentException{
    public  InvalidBoardException(String s){
        super(s);
    }
}