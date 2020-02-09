package scrabble.exceptions;

//Custom Exception for Score in Player Class
public class InvalidPlayerScoreException extends IllegalArgumentException{
    public  InvalidPlayerScoreException(String s){
        super(s);
    }
}
