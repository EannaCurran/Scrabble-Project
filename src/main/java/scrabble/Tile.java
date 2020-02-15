package scrabble;

import scrabble.exceptions.InvalidTileException;

import java.util.ArrayList;

/**
 * Class that represent a Character Tile for the Game of Scrabble
 */
public class Tile implements Comparable<Tile> {


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
     * Method to compare Tile objects by Character then Value
     *
     * @param t Tile for this tile to be compared to
     * @return Returns 0 if equal. Returns 1 if this Character is greater or Characters are equals and this Value is greater else returns -1
     */
    @Override
    public int compareTo(Tile t) {
        int result;

        //If they are equal result = 0
        if (this.equals(t)){
            result = 0;
        }
        //Else if the characters are equal
        else if(this.getCharacter() == t.getCharacter()){
            //If this value is greater result = 1 else result = -1
            result = this.getValue() > t.getValue()? 1 : -1;
        }
        //Else compare Characters
        else {
            //If this Character is greater result = 1 else result = -1
            result = this.getCharacter() > t.getCharacter()? 1 : -1;
        }

        return result;

    }

    /**
     * Method to see if this Tile equals another object
     *
     * @param obj Object to be compared to
     * @return Returns True if the objects are equal
     */
    @Override
    public boolean equals(Object obj) {

        //If obj is an instance of Tile
        if (obj instanceof Tile){
            //If the Character and Value are the same then return
            return this.getCharacter() == ((Tile) obj).getCharacter() && this.getValue() == ((Tile) obj).getValue();
        }
        //Else use super.equals
        else {
            return super.equals(obj);
        }
    }

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