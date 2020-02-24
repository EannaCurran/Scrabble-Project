package scrabble;

import scrabble.exceptions.InvalidPoolException;
import scrabble.exceptions.InvalidTileException;

import java.util.ArrayList;
import java.util.Random;

/**
 * Class that represent a Character Tile for the Game of Scrabble
 */
public class Pool {

    /**
     * The ArrayList of all the tiles in the pool
     */
    private ArrayList<Tile> poolTiles;

    /**
     * Random used for randomly picking tiles from the bag
     */
    private Random randomPool;

    /**
     * Standard English Tiles
     */
    private final static char[][] standardEngTiles = {{'A', 9}, {'B', 2}, {'C', 2}, {'D', 4}, {'E', 12}, {'F', 2}, {'G', 3}, {'H', 2}, {'I', 9}, {'J', 1}, {'K', 1}, {'L', 4}, {'M', 2}, {'N', 6}, {'O', 8}, {'P', 2}, {'Q', 1}, {'R', 6}, {'S', 4}, {'T', 6}, {'U', 4}, {'V', 2}, {'W', 2}, {'X', 1}, {'Y', 2}, {'Z', 1}, {' ', 2}, };


    /**
     * Function to fill the array with the set amount of each Tile in the standard English rules
     *
     * @throws InvalidTileException: Throws exception
     */
    public void poolFill() throws InvalidTileException {

        // Creates a new array list for poolTiles
        ArrayList<Tile> poolFile = new ArrayList<>();

        // For loop to run through each letter in the alphabet and empty tile in the standardEngTiles array
        for (int i = 0; i < 27; i++){

            // For loop to add the amount of tile of letter i specified in standardEngTiles
            for(int j = 0; j < standardEngTiles[i][1]; j++){

                // Adds each to the Pool
                poolTiles.add(new Tile(standardEngTiles[i][0]));
            }
        }
    }


    /**
     * Pool toString Method
     *
     * @return Pool information in the form of a String
     */
    @Override
    public String toString() {

        // Sets the start of the result string
        StringBuilder result = new StringBuilder("Pool: Size: " + poolTiles.size() + "\n");

        // For loop to run through each Tile in the poolTiles array and toString them
        for (Tile poolTile : poolTiles) {
            result.append(poolTile.toString()).append("\n");
        }

        // Return the result string
        return result.toString();
    }


    /**
     * Method to remove a random tile from the pool and return the tile that was removed
     *
     * @return The tile which was randomly removed from the pool
     * @throws InvalidPoolException If the pool is empty
     */
    public Tile removeTile () throws InvalidPoolException{
        if (isEmpty()){
            throw new InvalidPoolException("There is no tiles to remove");
        }
        else {

            // Calls randomPoolIndex to select a random tile in the pool to remove
            return poolTiles.remove(randomPoolIndex());
        }
    }


    /**
     * Method to take in a tile and add it to a pool
     *
     * @param tileAdded The tile to be added to the pool
     */
    public void receiveTile(Tile tileAdded){
        poolTiles.add(tileAdded);
    }


    /**
     * Method to get a random index in poolTiles between 0 - poolTiles size - 1
     *
     * @return A random index in poolTiles
     */
    private int randomPoolIndex(){

        // Return a random int between 0 - poolTiles.size-1
        return randomPool.nextInt(poolTiles.size());
    }


    /**
     * Method to find the number of tiles in pool
     *
     * @return The number of tiles in pool
     */
    public int tilesInPool(){
        return poolTiles.size();
    }


    /**
     * Method to Check if the pool is empty
     *
     * @return True if pool is empty
     */
    public boolean isEmpty(){
        return poolTiles.size() == 0;
    }


    /**
     * Pool constructor
     */
    public Pool() {

        // Creates an ArrayList of Tiles
        poolTiles = new ArrayList<>();

        // Sets the seed of randomPool to the time that the Pool was created
        randomPool = new Random(System.currentTimeMillis());

        // Fills the Pool with Tiles
        this.poolFill();
    }
}
