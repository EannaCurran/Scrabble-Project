package scrabble.exceptions;

//Custom Exception for Tile Class
public class InvalidTileException extends IllegalArgumentException{
    public  InvalidTileException(String s){
        super(s);
    }
}
