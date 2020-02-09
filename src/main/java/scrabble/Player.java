package scrabble;

import scrabble.exceptions.InvalidPlayerNameException;
import scrabble.exceptions.InvalidPlayerScoreException;

/**
 * Class that represents a player. Contains the information of each players
 */
public class Player {

    /**
     * Reference to the pool of the game
     */
    private Pool poolRef;

    /**
     * The variable that stores the score of a player
     */
    private int score;

    /**
     * The variable that stores the name of a player
     */
    private String name;

    /**
     * The variable that stores a player's frame
     */
    private Frame playerFrame;


    /**
     * Player Constructor
     *
     * @param namePlayer String for the name of the Player
     * @param pool Reference to the Pool of the game
     * @throws InvalidPlayerNameException If inputted name is invalid
     */
    public Player(String namePlayer, Pool pool)
    {
        if (validateName(namePlayer)) {
            this.score = 0;
            this.name = namePlayer;
            this.poolRef = pool;
            this.playerFrame = new Frame(poolRef);
        }
        else{
            throw new InvalidPlayerNameException("Invalid name for Player.\nPlayer's name must be less than of equal to 24 characters\nPlayer's can only contain letters and digits\nPlayer's name can not be null\nInputted name: " + namePlayer);
        }
    }


    /**
     * Method to validate the Player Name
     * Checks if name is not empty, less than than 24 characters or contains only letters and digits
     *
     * @param n Name variable for a player
     * @return True if name is valid
     */
    private boolean validateName(String n)
    {
        // Checks that name is not null, empty, greater than 24 character and only contain letter and digits
        return (n != null && n.length() > 0 && n.length() <= 24 && n.matches("^\\w+$"));
    }

    /**
     * Method to validate the score
     * Checks if the score value is not negative
     *
     * @param scoreTest Score variable
     * @return True if valid Score
     */
    private boolean validateScore(int scoreTest)
    {
        return scoreTest >= 0;
    }


    /**
     * Resetting the score and name of the player
     *
     * @param newName The new player name after the player is reset
     * @throws InvalidPlayerNameException If inputted name is invalid
     */
    public void playerReset(String newName)
    {
        if(validateName(newName)) {
            this.playerFrame = new Frame(poolRef);
            this.score = 0;
            this.name = newName;
        }
        else{
            throw new InvalidPlayerNameException("Invalid name for Player.\nPlayer's name must be less than of equal to 24 characters\nPlayer's can only contain letters and digits\nPlayer's name can not be null\nInputted name: " + newName);
        }
    }


    /**
     * Accessor Method for Name
     *
     * @return The name of the player
     */
    public String getName() {
        return name;
    }


    /**
     * Mutator Method for name
     *
     * @param name The new Name of the Player
     * @throws InvalidPlayerNameException If inputted name is invalid
     */
    public void setName(String name) {

        if (validateName(name)) {

            this.name = name;
        }
        else{
            throw new InvalidPlayerNameException("Invalid name for Player.\nPlayer's name must be less than of equal to 24 characters\nPlayer's can only contain letters and digits\nPlayer's name can not be null\nInputted name: " + name);
        }
    }


    /**
     * Accessor Method for Player Score
     *
     * @return The Player's Score
     */
    public int getScore() {
        return score;
    }


    /**
     * Mutator method for score to increase the players score
     *
     * @param scoreIncrease The value for the score to be increased by
     * @throws InvalidPlayerScoreException If player's score is increased by a negative value
     */
    public void increaseScore(int scoreIncrease)
    {
        if (validateScore(scoreIncrease)) {
            this.score += scoreIncrease;
        }
        else {
            throw new InvalidPlayerScoreException("Players Score can not be increased by a negative value");
        }
    }


    /**
     * Mutator method for score to decrease the players score
     *
     * @param scoreDecrease The value for the score to be increased by
     * @throws InvalidPlayerScoreException If a negative value is passed into Decrease_Score
     */
    public void decreaseScore(int scoreDecrease)
    {
        if (validateScore(scoreDecrease)) {
            this.score += scoreDecrease;
        }
        else {
            throw new InvalidPlayerScoreException("Players Score can not be decreased by a negative value");
        }
    }


    /**
     * Accessing Method player's frame
     *
     * @return The value of playerFrame
     */
    public Frame getPlayerFrame()
    {
        return this.playerFrame;
    }


    /**
     * A toString method to print the Player class variables
     * @return The Player class variables
     */
    @Override
    public String toString() {

        return "Name: " + this.getName() + "\nScore: " + this.getScore() + "\nFrame: " + this.getPlayerFrame();
    }
}
