package scrabble;

import scrabble.exceptions.InvalidFrameException;
import scrabble.exceptions.InvalidTileException;

import java.util.ArrayList;

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
    private final static int FRAME_SIZE = 7;


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
            System.out.println("The frame was full");
        }

        // Adds Tiles to the Frame until the Frame size cap has been reached
        while(this.playerFrame.size() < FRAME_SIZE){
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
     * @throws InvalidTileException: If index is not in the Frame
     */
    public void removeTile(int i){

        if(i >= 0 && i <= 6) {
            playerFrame.remove(i);
        }

        else{

            // Throws a InvalidTile Exception if the index is not in the range of the Frame size
            throw new InvalidFrameException("Index not in range of Frame");
        }
    }


    /**
     * Method to remove a single Tile from the Frame
     *
     * @param c: Character of the Tile to be removed
     * @throws InvalidTileException: If Character is not in the Frame
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
     * @throws InvalidTileException: If Tile is not in the Frame
     */
    public void removeTiles(char[] word){

        //If the word is invalidly long
        if(word.length > 7){
            throw new IllegalArgumentException("Invalid number of characters to find in remove in Frame");
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
     * @throws InvalidFrameException: If the Tiles to be removed are in the Frame
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

        // Returns the removed Tiles
    }


    /**
     * Method to add a single Tile to the frame
     *
     * @param tile: To be added to the playerFrame
     */
    public void addTile(Tile tile){

        // Checks if the playerFrame is full, if so InvalidFrameException is thrown
        if (playerFrame.size() == 7) {
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
     */
    public boolean checkTiles(ArrayList<Tile> tiles){

        if(tiles.isEmpty()){
            throw new IllegalArgumentException("Cannot check for 0 tiles in Frame");
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
     */
    public boolean checkTiles(char[] word){

        if(word.length == 0){
            throw new IllegalArgumentException("Cannot check for 0 tiles in Frame");
        }
        // Loops through each Character passed in
        ArrayList<Tile> temp = new ArrayList<Tile>();

        for(Tile tile: playerFrame){
            temp.add(tile);
        }


        // Checks if any of the of the character passed in not Tiles in the Frame, if so false is returned
        for (int i = 0; i < temp.size(); i++) {
            for (int j = 0; j < word.length; j++) {

                if (temp.get(i).getCharacter() == word[j]) {

                    temp.remove(i);
                    i--;
                    break;
                }
            }
        }
        return temp.size() == 0;

    }
        // If all the Tiles passed in are in the Frame, true is returned



    /**
     * Method to check if the Frame has a Tile with a given character
     * @param letter: Character to check if it is within Frame
     * @return Boolean answer
     */
     private boolean checkTile(char letter){

        // Loops through each Tile in Frame
        for(Tile t: playerFrame){

            // If any Tile in the Frame has the same character as letter, true is returned, if not false is returned
            if(t.getCharacter() == letter){
                return true;
            }
        }
        return false;
    }


    /**
     * If the Tile is in the Frame
     *
     * @param tile
     * @return
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
     */
    public Tile getTile(char c) {

        // Loops through each Tile in Frame
        for (int i = 0; i < playerFrame.size(); i++) {

            // If the Frame contains a Tile with the wanted character, it is removed from the Frame and returned
            if (playerFrame.get(i).getCharacter() == c) {
                return playerFrame.get(i);

            }

        }
        throw new IllegalArgumentException("Tile not in board");
    }

    /**
     * Accessor method for Tile from Frame
     *
     * @param i index of the Tile in the Frame
     * @return The Tile at index i in the Frame
     */
    public Tile getTile(int i){
        if(i < 0 || i > 6){
            throw new IllegalArgumentException("Index not in range of Board");
        }
        return playerFrame.get(i);
    }


    /**
     * Method to retrieve a list of Tile with given characters from Frame
     * @param word list of wanted character Tiles
     * @return List of Tiles
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
    public void swapTiles(ArrayList<Tile> tiles){

        // Removes the Tiles passed in from the Frame
        removeTiles(tiles);

        // Fills the Frame with Tiles from the Pool
        fillFrame();

        // Returns the Tiles removed from the Frame back to the Pool
        for(Tile tile: tiles){
            this.framePool.receiveTile(tile);
        }
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

    public static void main(String[] args) {
        Pool pool = new Pool();
        Frame frame = new Frame(pool);
    }

}
