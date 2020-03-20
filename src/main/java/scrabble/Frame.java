package scrabble;

import scrabble.exceptions.InvalidFrameException;
import scrabble.exceptions.InvalidTileException;

import java.util.ArrayList;

/**
 * This class represents the Player's Frame in Scrabble
 */
public class Frame {

    /**
     * ArrayList of Tiles in the Frame
     */
    private ArrayList<Tile> playerFrame = new ArrayList<>();

    /**
     * The reference to the pool to access Tiles from
     */
    private Pool framePool;

    /**
     * Max amount of Tiles in the Frame
     */
    public final static int FRAME_SIZE = 7;


    /**
     * Constructor for Frame
     *
     * @param pool: The reference to the Pool to access Tiles from
     */
    public Frame(Pool pool) {

        framePool = pool;

        // Fills the Frame with Tiles from the Pool
        fillFrame();
    }


    /**
     * Method to fill the Frame up to the maximum number of Tiles
     */
    public void fillFrame(){

        // Checks if the Frame already has 7 Tiles in it, if so the user is told
        if(this.playerFrame.size() == FRAME_SIZE){
            System.out.println("The frame is currently full");
        }

        // Adds Tiles to the Frame until the Frame size cap has been reached
        while(this.playerFrame.size() < FRAME_SIZE && !framePool.isEmpty()){
            this.addTile(framePool.removeTile());
        }
    }


    /**
     * Accessor method for playerFrame
     *
     * @return playerFrame: The ArrayList of Tiles in playerFrame
     */
    public ArrayList<Tile> returnFrame(){
        return playerFrame;
    }


    /**
     * Method to check if the playerFrame has Tiles in it
     *
     * @return boolean: Answer for if the playerFrame is empty
     */
    public boolean isEmpty(){
        return playerFrame.isEmpty();
    }


    /**
     * Method to remove a single Tile from the Frame
     *
     * @param i: Tile index to be removed
     * @throws InvalidTileException If index is not in the Frame
     */
    public void removeTile(int i){

        if(i >= 0 && i <= 6) {
            playerFrame.remove(i);
        }

        else{

            // Throws a InvalidTile Exception if the index is not in the range of the Frame size
            throw new InvalidFrameException("Index not in range of the frame");
        }
    }


    /**
     * Method to remove a single Tile from the Frame
     *
     * @param c: Character of the Tile to be removed
     * @throws InvalidTileException If Character is not in the Frame
     */
    public void removeTile(char c){

        //For Loop to check each tile in frame
        for(Tile t: playerFrame){

            //If c matches Character of the Tile
            if(t.getCharacter() == c){
                playerFrame.remove(t);
                return;
            }
        }
        throw new InvalidFrameException("Tile not in frame");
    }

    /**
     * Method to remove a single Tile from the Frame
     *
     * @param t: Tile to be removed
     */
    public void removeTile(Tile t){

        //For loop to check each tile
        for(Tile t2: playerFrame){

            if(t2 == t){
                playerFrame.remove(t);
                return;
            }
        }
    }

    /**
     * Method to remove a multiple Tile from the Frame
     *
     * @param word: Array of Tile Characters to be removed
     * @throws InvalidTileException If Tile is not in the Frame
     */
    public void removeTiles(char[] word){

        //If the word is invalidly long
        if(word.length > FRAME_SIZE){
            throw new IllegalArgumentException("Invalid number of characters to remove from frame");
        }
        //If the word is in the frame
        else if(checkTiles(word)){

            //For Loop to remove each Tile
            for(char c: word) {
                removeTile(c);
            }
        }
        else{
            throw new InvalidFrameException("Tiles not in the frame, therefore tiles cannot be removed");
        }

    }

    /**
     * Method to remove a list of Tiles from the Frame
     *
     * @param tiles: List of Tiles to be removed
     * @throws InvalidFrameException If the Tiles to be removed are in the Frame
     */
    public void removeTiles(ArrayList<Tile> tiles){

        // Checks if each Tile passed in is in the Frame
        if(checkTiles(tiles)){

            // Removes each Tile from the playerFrame
            for(Tile t: tiles){
                removeTile(t);
            }
        }

        // If a Tiles passed in are not in the playerFrame, then a InvalidFrameException is thrown
        else{
            throw new InvalidFrameException("Tiles not in the frame, therefore tiles cannot be removed");
        }

    }


    /**
     * Method to add a single Tile to the frame
     *
     * @param tile: To be added to the playerFrame
     * @throws InvalidFrameException The Frame is full
     */
    public void addTile(Tile tile){

        // Checks if the playerFrame is full, if so InvalidFrameException is thrown
        if (playerFrame.size() == FRAME_SIZE) {
            throw new InvalidFrameException("Frame can't contain more than 7 tiles");
        }

        // Adds the Tile to the playerFrame
        playerFrame.add(tile);
    }



    /**
     * Method which checks if a series of Tiles are currently in the Frame
     *
     * @param tiles: List of tiles to be checked
     * @return boolean: Result for if the Frame contains all the Tiles
     * @throws InvalidFrameException The Frame is empty
     */
    public boolean checkTiles(ArrayList<Tile> tiles){

        if(tiles.isEmpty()){
            throw new InvalidFrameException("Cannot check for 0 tiles in Frame");
        }

        Frame temp = this;

        //For loop to go through array of Tiles
        for(Tile tile: tiles){
            if(!(temp.checkTile(tile))){
                return false;
            }
            else{
                temp.removeTile(tile);
            }
        }

        return true;
    }


    /**
     * Method to check if a list of characters are in the Frame
     * @param word: List of characters to check
     * @return Boolean answer
     * @throws InvalidFrameException The Frame is empty
     */
    public boolean checkTiles(char[] word){

        if(word.length == 0){
            throw new InvalidFrameException("Cannot check for 0 tiles in Frame");
        }
        // Loops through each Character passed in

        ArrayList<Tile> temp = new ArrayList<>(playerFrame);


        // Checks if any of the of the character passed in not Tiles in the Frame, if so false is returned
        for (char c : word) {
            for (int j = 0; j < temp.size(); j++) {

                if (temp.get(j).getCharacter() == c) {

                    temp.remove(j);
                    break;
                }
            }
        }
        return temp.size() == playerFrame.size() - word.length;

    }



    /**
     * If the Tile is in the Frame
     *
     * @param tile: Tile to be checked if in Frame
     * @return If Tile is in the Frame
     */
    private boolean checkTile(Tile tile){

        //For loop to run through the frames tiles
        for(Tile t: playerFrame){

            if(t.equals(tile)){
                return true;
            }
        }
        return false;
    }




    /**
     * Method that retrieves a Tile with a given character from the Frame
     * @param c: Character of Tile wanted
     * @return Tile with given character
     * @throws InvalidFrameException The Tile is not in the Frame
     */
    public Tile getTile(char c) {

        // Loops through each Tile in Frame
        for (Tile tile : playerFrame) {

            // If the Frame contains a Tile with the wanted character, it is removed from the Frame and returned
            if (tile.getCharacter() == c) {
                return tile;

            }
        }
        throw new InvalidFrameException("Tiles are not in the players frame");
    }

    /**
     * Accessor method for Tile from Frame
     *
     * @param i index of the Tile in the Frame
     * @return The Tile at index i in the Frame
     * @throws InvalidFrameException The
     */
    public Tile getTile(int i){
        if(i < 0 || i > 6){
            throw new InvalidFrameException("Index not in range of Board");
        }
        return playerFrame.get(i);
    }


    /**
     * Method to retrieve a list of Tile with given characters from Frame
     * @param word list of wanted character Tiles
     * @return List of Tiles
     * @throws InvalidFrameException The Frame does not contain the Tiles
     */
    public ArrayList<Tile> getTiles(char[] word){

        // Creates a temporary ArrayList of Tiles
        ArrayList<Tile> temp = new ArrayList<>();

        // Checks if each character in word is in the Frame, if not exception is thrown
        if(checkTiles(word)){

            // Adds each of the Tiles of given characters to the ArrayList
            for(char letter: word){
                temp.add(getTile(letter));
            }
        }

        else{
            throw new InvalidFrameException("Frame doesn't have the requested tiles");
        }

        // Returns the ArrayList
        return temp;
    }




    /**
     * Method used to swap a number of Tiles from the Frame for new ones in the Pool
     *
     * @param tiles: List of Tiles to be removed
     *
     */
    public void swapTiles(char[] tiles){

        // Removes the Tiles passed in from the Frame
        Tile temp;
        for(char c: tiles){
            temp = getTile(c);
            if(temp.getCharacter() != ' ' && temp.getValue() == 0){
                throw new InvalidFrameException("Cannot swap blank tile with value set to it");
            }
            else{
                removeTile(c);
            }
        }

        // Fills the Frame with Tiles from the Pool
        fillFrame();

        // Returns the Tiles removed from the Frame back to the Pool
        for(char tile: tiles){
            this.framePool.receiveTile(new Tile(tile));
        }
    }


    /**
     * Method to set the Blank Tiles in Frame
     *
     * @param tileValues The chars to change the Blank Tiles to
     * @throws InvalidFrameException If too many chars are passed
     */
    public void setBlanks(char[] tileValues){
        //There is only 2 Blank Tiles in Scrabble
        if (tileValues.length <= 2){

            setToBlank();

            int j = 0;

            //For loop to run through each Tile to check if Blank
            for (int i = 0; i < playerFrame.size() && j < tileValues.length; i++) {
                //If Blank set to next char
                if (playerFrame.get(i).getCharacter() == ' '){
                    playerFrame.get(i).setCharacter(tileValues[j]);
                    j++;
                }
            }
        }
        else{
            throw new InvalidFrameException("There can only be a max of 2 Blank Tiles");
        }

    }

    /**
     * Method to set all Blank Tiles to Null
     */
    public void setToBlank(){
        for (Tile tile : playerFrame) {
            if (tile.getValue() == 0) {
                tile.setNull();
            }
        }
    }

    /**
     * Method to check if the Frame has any blank tiles
     * @return Boolean result to if the Frame has any blank tiles
     */
    public boolean hasBlank() {

        for (Tile tile : playerFrame) {
            if (tile.getValue() == 0) {
                return true;
            }
        }
        return false;
    }

    /**
     * Method to find the total value of the Tiles in a Frame
     *
     * @return The total value of the Tiles
     */
    public int tileValues(){
        int result = 0;

        for (Tile tile : playerFrame) {
            result += tile.getValue();
        }

        return result;
    }

    /**
     * Method overriding the toString method
     *
     * @return String: Formatted string of the Tiles in Frame
     */
    @Override
    public String toString(){

        // Creates start of string to display each Tile in the Frame
        String text = "Current Frame Contains: ";

        // Loops through each Tile in the Frame
        for(Tile tile: playerFrame){

            // Concatenates the character of each Tile to the string
            text = text.concat(tile.getCharacter() + " ");
        }

        // Returns the formatted text
        return text;
    }


}
