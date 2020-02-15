package scrabble;

import scrabble.exceptions.InvalidSquareException;

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
        TRIPLE_LETTER
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

    /**
     * Accessor Method for the Tile on the Square
     *
     * @return The Tile on the Square
     */
    public Tile getTile(){
        return squareTile;
    }

    /**
     * Method to find if the Square has a Tile
     *
     * @return True if the Square has no Tile on it
     */
    public Boolean isEmpty(){
        return squareTile == null;
    }

    /**
     * Method to set the SquareType of a Square to NORMAL
     */
    public void setNormal(){
        squareType = SquareType.NORMAL;
    }

    /**
     * Mutator Method for Tile on Square
     *
     * @param tile Tile to be placed on the Square
     * @throws InvalidSquareException The Square has a Tile on it already
     */
    public void setTile(Tile tile){
        //If the square isEmpty set the tile
        if (isEmpty()){
            squareTile = tile;
        }
        //Else throw an Exception
        else{
            throw new InvalidSquareException("The Square has a Tile on it!");
        }
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
