package scrabble;

import scrabble.exceptions.InvalidTileException;

import java.util.ArrayList;

/**
 * Class that represent a Character Tile for the Game of Scrabble
 */
public class Tile {


    /**
     * The variable that stores the character of the Tile
     * Valid chars are Capital Letters and ' ' (marks blank tile)
     */
    private char character;


    /**
     * The variable that stores the score value of the Tile
     */
    private short value;


    /**
     * Method to return the value of a char for tiles
     *
     * @param c Char that value needs to be found
     * @return Short value of inputted char
     */
    private short charValue(char c){

        //Switch statement for each chars and their values
        switch (c) {

            //Value of 2
            case 'D':
            case 'G':
                return 2;

            //Value of 3
            case 'B':
            case 'C':
            case 'M':
            case 'P':
                return 3;

            //Value of 4
            case 'F':
            case 'H':
            case 'V':
            case 'W':
            case 'Y':
                return 4;

            //Value of 5
            case 'K':
                return 5;

            //Value of 8
            case 'J':
            case 'X':
                return 8;

            //Value of 10
            case 'Q':
            case 'Z':
                return 10;

            //Value of 0
            case ' ':
                return 0;

            //Default Value of 1
            default:
                return 1;
        }
    }


    /**
     * Method to check is a char is a letter or blank
     *
     * @param c Character of the tile to be checked
     * @return Boolean true if char is valid
     */
    private boolean validChar(char c){ return (c >= 'A' && c <= 'Z') || c == ' '; }


    /**
     * Accessor method for value of the tile
     *
     * @return Short value of the tile
     */
    public short getValue() {
        return value;
    }


    /**
     * Accessor method for character of the Tile
     *
     * @return Char of the Tile
     */
    public char getCharacter() {
        return character;
    }


    /**
     * Method to change the character of a blank Tile
     *
     * @param character New char for the Tile
     * @throws InvalidTileException If an invalid char inputted or the Tile is not blank
     */
    public void setCharacter(char character) throws InvalidTileException {

        // Checks that the current Tile has not character set
        if (this.getCharacter() == ' '){

            // Checks that the character c is valid
            if (validChar(character)) {

                // Sets character to the character
                this.character = character;
            }
            else{
                throw new InvalidTileException("Invalid char inputted");
            }
        }
        else{
            throw new InvalidTileException("Only a blank tile can have its character changed");
        }
    }


    /**
     * Constructor for Tile
     *
     * @param c Character for the Tile
     * @throws InvalidTileException If an invalid character is inputted
     */
    public Tile(char c) throws InvalidTileException {

        // Checks that the character c is valid
        if (validChar(c)){

            // Sets character to c
            character = c;

            // Calls charValue to find the value of the tile
            value = charValue(c);
        }
        else{
            throw new InvalidTileException("Invalid character was inputted");
        }
    }

    /**
     * toString Method for Tile
     *
     * @return The string of Tile
     */
    @Override
    public String toString() {
        return "Tile: Char: " +  character + " Value: " + value;
    }


    /**
     * Main Function
     *
     * @param args Arguments
     */
    public static void main(String[] args) {

        ArrayList<Tile> testArray = new ArrayList<>();

        for (char i = 'A'; i <= 'Z'; i++){

            try {
                testArray.add( i - 'A', new Tile(i));
            } catch (InvalidTileException e) {
                e.printStackTrace();
                System.out.println("Failed to create tile: " + i);
                return;
            }

            for (Tile tile : testArray) {
                System.out.println(tile);
            }

        }
    }
}