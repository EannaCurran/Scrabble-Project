package scrabble;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


public class ScrabbleTest {

    Scrabble Test;

    @BeforeEach
    void setup()
    {
        //Initialising a game of scrabble and assigning names to the players
        Test = new Scrabble();
        Test.createPlayer("Killian", 0);
        Test.createPlayer("John", 1);

    }

    //Test to check if Game Over method returns true
    @Test
    @DisplayName("Test to check if Game Over method returns true")
    void IsGameOverTestTrue()
    {
        //A while loop to empty the pool
        while(!Test.getPool().isEmpty())
        {
            Test.getPool().removeTile();
        }

        //Creating a reference to the players
        Player[] PlayerTest = Test.getPlayers();
        //Creating a reference to a player's frame
        Frame frameTest1 = PlayerTest[0].getPlayerFrame();
        //Empty the player's frame
        frameTest1.returnFrame().clear();

        assertTrue(Test.isGameOver());
    }

    //Test to check if Game Over method returns false when frame is not empty
    @Test
    @DisplayName("Test to check if Game Over method returns false when frame is not empty")
    void IsGameOverTestFalseFrameNotEmpty()
    {
        //Loop empties the pool
        while(!Test.getPool().isEmpty())
        {
            Test.getPool().removeTile();
        }

        assertFalse(Test.isGameOver());
    }

    //Test to check if Game Over method returns false when pool is not empty
    @Test
    @DisplayName("Test to check if Game Over method returns false when pool is not empty")
    void IsGameOverTestFalsePoolNotEmpty()
    {

        //Creating a reference to the players
        Player[] PlayerTest = Test.getPlayers();
        //Creating a reference to a player's frame
        Frame frameTest1 = PlayerTest[0].getPlayerFrame();
        //Empty the player's frame
        frameTest1.returnFrame().clear();

        assertFalse(Test.isGameOver());
    }


    //Test to check if GameOver method correctly decreases the player's score
    @Test
    @DisplayName("Test to check if GameOver method correctly decreases the player's score")
    void GameOverScoreDecrease()
    {
        //Creating a reference to the players
        Player[] PlayerTest = Test.getPlayers();

        //Sets the score of both players to 100
        PlayerTest[0].increaseScore(100);
        PlayerTest[1].increaseScore(100);


        //Clears the frame of player 2 and sets it to specific Tiles to calculate the score decrease
        Frame frameTest1 = PlayerTest[0].getPlayerFrame();
        frameTest1.returnFrame().clear();
        frameTest1.addTile(new Tile('A'));
        frameTest1.addTile(new Tile('B'));
        frameTest1.addTile(new Tile('C'));

        //Clears the frame of player 2 and sets it to specific Tiles to calculate the score decrease
        Frame frameTest2 = PlayerTest[1].getPlayerFrame();
        frameTest2.returnFrame().clear();
        frameTest2.addTile(new Tile('E'));
        frameTest2.addTile(new Tile('K'));
        frameTest2.addTile(new Tile('Z'));

        //Calls game over method which calculates the score decrease
        Test.gameOver();

        assertEquals(93, PlayerTest[0].getScore(), "The final score of player 1 was the not the expected score after points were deduced after the game ended.");
        assertEquals(84, PlayerTest[1].getScore(), "The final score of player 1 was the not the expected score after points were deduced after the game ended.");
    }

}
