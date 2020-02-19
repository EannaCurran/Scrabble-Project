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
     * @return temp: The tile that was removed
     * @throws InvalidTileException: If index is not in the Frame
     */
    Tile removeTile(int i){

        // Creates a temporary hold for the Tile to be removed
        Tile temp = playerFrame.get(i);

        // Checks that the index of the Tile in within the range of the Frame size
        if(i >= 0 && i < playerFrame.size()) {
            playerFrame.remove(i);
        }

        else{

            // Throws a InvalidTile Exception if the index is not in the range of the Frame size
            throw new InvalidTileException("Tile index not in frame");
        }

        // Returns the Tile that was removed
        return temp;
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
     * Method to remove a list of Tiles from the Frame
     *
     * @param tiles: List of Tiles to be removed
     * @return removedTiles: Tiles that were removed from the frmae
     * @throws InvalidFrameException: If the Tiles to be removed are in the Frame
     */
    public ArrayList<Tile> removeTiles(ArrayList<Tile> tiles){

        // Creates an empty ArrayList to store the Tiles that are removed from playerFrame
        ArrayList<Tile> removedTiles = new ArrayList<>();

        // Checks if each Tile passed in is in the Frame
        try {
            // Removes each Tile from the playerFrame
            for (Tile t : tiles) {
                removedTiles.add(removeTile(playerFrame.indexOf(t)));
            }
        }catch(Exception e){

        // If a Tiles passed in are not in the playerFrame, then a InvalidFrameException is thrown
            throw new InvalidFrameException("Tiles not in the frame, therefore tiles cannot be removed");
        }

        // Returns the removed Tiles
        return removedTiles;
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
     * Method which checks if a series of Tiles are currently in the Frame
     *
     * @param tiles: List of tiles to be checked
     * @return boolean: Result for if the Frame contains all the Tiles
     */
    public boolean checkTiles(ArrayList<Tile> tiles){
        for(Tile tile: tiles){
            if(!(playerFrame.contains(tile))){
                return false;
            }
        }
        return true;

    }

    public boolean checkWord(char[] word){
        // Loops through each Tile passed in

        Frame tempFrame = (Frame)playerFrame.clone();
        for(char a: word) {

            // Checks if any of the of the Tiles passed in are not in the Frame, if so false is returned
            if ((tempFrame.checkTile(a))) {
                tempFrame.getTile(a);
            }
            else{
                return false;
            }
        }

        // If all the Tiles passed in are in the Frame, true is returned
        return true;
    }

    public boolean checkTile(char a){
        for(Tile t: playerFrame){
            if(t.getCharacter() == a){
                return true;
            }
        }
        return false;
    }

    public Tile getTile(char a){
        Tile temp = new Tile(' ');
        for(Tile t: playerFrame){
            if(t.getCharacter() == a){
                temp = playerFrame.get(playerFrame.indexOf(t));
                playerFrame.remove(t);
                break;

            }
        }
        return temp;
    }

    public ArrayList<Tile> getTiles(char[] word){
        ArrayList<Tile> temp = new ArrayList<Tile>();
        if(checkWord(word) == true){
            for(char a: word){
                temp.add(getTile(a));
            }
        }
        else{
            throw new InvalidFrameException("Frame doesn't have the requested tiles");
        }

        return temp;
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
    }

}
