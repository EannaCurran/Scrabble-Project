package scrabble;

/**
 * The Square Class represents the square on the Scrabble Board as objects
 */
public class Square {

    /**
     * SquareType is an Enum of possible Types of Square on the Scrabble Board
     */
    enum SquareType{
        NORMAL,
        START,
        DOUBLE_WORD,
        TRIPLE_WORD,
        DOUBLE_LETTER,
        TRIPLE_LETTER;
    }

    /**
     * The Tile on the Square on the Board
     */
    private Tile squareTile;

    /**
     * The SquareType of the Square
     */
    private SquareType squareType;

    /**
     * Square Constructor
     *
     * @param type The SquareType of the Square
     */
    public Square(SquareType type){
        squareType = type;
        squareTile = null;
    }

    /**
     * Accessor Method for the SquareType of the Square
     *
     * @return The SquareType of the Square
     */
    public SquareType getType(){
        return squareType;
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
