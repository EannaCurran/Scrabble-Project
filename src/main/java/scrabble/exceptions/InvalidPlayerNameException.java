package scrabble.exceptions;

//Custom Exception for Name in Player Class
public class InvalidPlayerNameException extends IllegalArgumentException{
    public  InvalidPlayerNameException(String s){
        super(s);
    }
}
