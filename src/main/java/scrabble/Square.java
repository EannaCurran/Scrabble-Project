package scrabble;

public class Square {

    enum SquareType{
        NORMAL,
        START,
        DOUBLE_WORD,
        TRIPLE_WORD,
        DOUBLE_LETTER,
        TRIPLE_LETTER;
    }

    public Square(SquareType type){

    }

    public SquareType getType(){
        return null;
    }

    public Tile getTile(){
        return null;
    }

    public Boolean isEmpty(){
        return false;
    }

    public void setNormal(){

    }

    public void setTile(Tile tile){

    }

    @Override
    public String toString() {
        return super.toString();
    }
}
