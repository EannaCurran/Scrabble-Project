package scrabble;

import scrabble.exceptions.InvalidSquareException;

/**
 * The Square Class represents the square on the Scrabble Board as objects
 */
public class Square {

    /**
     * SquareType is an Enum of possible Types of Square on the Scrabble Board
     */
    public enum SquareType{
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
     *
     * @return The SquareType of the Square before setting to normal
     */
    public SquareType setNormal(){
        SquareType temp = squareType;

        squareType = SquareType.NORMAL;

        return temp;
    }

    /**
     * Mutator Method for Tile on Square
     *
     * @param tile Tile to be placed on the Square
     * @throws InvalidSquareException The Square has a Tile on it already or the Tile is a null Tile
     */
    public void setTile(Tile tile){
        //If the square isEmpty set the tile
        if (isEmpty()){
            //If the tile is a Blank tile set to null
            if (tile.getCharacter() == ' '){
                throw new InvalidSquareException("The Square can not contain a Null Tile");
            }
            else{
                squareTile = tile;
            }
        }
        //Else throw an Exception
        else{
            throw new InvalidSquareException("The Square has a Tile on it!");
        }
    }

    /**
     * toString Method for Square
     *
     * @return the String form of Square
     */
    @Override
    public String toString() {
        String result;

        //If the Square isEmpty string will be of SquareType
        if (isEmpty()){

            //Switch Statement to find the corresponding SquareType string for the Square
            switch (squareType){

                case TRIPLE_WORD:
                    result = " 3W ";
                    break;

                case TRIPLE_LETTER:
                    result = " 3L ";
                    break;

                case DOUBLE_WORD:
                    result = " 2W ";
                    break;

                case DOUBLE_LETTER:
                    result = " 2L ";
                    break;

                case START:
                    result = " ** ";
                    break;

                default:
                    result = "    ";
            }
        }
        //Else string is the Character and Value of the Tile on Square
        else {
            //The Character of the Tile
            result = getTile().getCharacter() + " ";

            //If the Value is a single digit
            if (getTile().getValue() < 10){
                result = result + " " + getTile().getValue();
            }
            //Else the value is a double digit
            else {
                result = result + getTile().getValue();
            }
        }

        return result;
    }
}
