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
        Test = new Scrabble();

        Test.createPlayer("Killian", 0);
        Test.createPlayer("John", 1);

    }

    //Test to check if Game Over method returns true
    @Test
    @DisplayName("Test to check if Game Over method returns true")
    void IsGameOverTestTrue()
    {

        while(!Test.getPool().isEmpty())
        {
            Test.getPool().removeTile();
        }

        Player[] PlayerTest = Test.getPlayers();
        Frame frameTest1 = PlayerTest[0].getPlayerFrame();
        frameTest1.returnFrame().clear();

        assertTrue(Test.isGameOver());
    }

    //Test to check if Game Over method returns false when frame is not empty
    @Test
    @DisplayName("Test to check if Game Over method returns false when frame is not empty")
    void IsGameOverTestFalseFrameNotEmpty()
    {

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

        Player[] PlayerTest = Test.getPlayers();
        Frame frameTest1 = PlayerTest[0].getPlayerFrame();
        frameTest1.returnFrame().clear();

        assertFalse(Test.isGameOver());
    }


    //Test to check if GameOver method correctly decreases the player's score
    @Test
    @DisplayName("Test to check if GameOver method correctly decreases the player's score")
    void GameOverScoreDecrease()
    {
        Player[] PlayerTest = Test.getPlayers();

        PlayerTest[0].increaseScore(100);

        Frame frameTest1 = PlayerTest[0].getPlayerFrame();
        frameTest1.returnFrame().clear();
        frameTest1.addTile(new Tile('A'));
        frameTest1.addTile(new Tile('B'));
        frameTest1.addTile(new Tile('C'));

        Frame frameTest2 = PlayerTest[0].getPlayerFrame();
        frameTest2.returnFrame().clear();
        frameTest2.addTile(new Tile('E'));
        frameTest2.addTile(new Tile('K'));
        frameTest2.addTile(new Tile('Z'));

        Test.gameOver();

        assertEquals(93, PlayerTest[0].getScore(), "The final score of player 1 was the not the expected score after points were deduced after the game ended.");
        assertEquals(84, PlayerTest[1].getScore(), "The final score of player 1 was the not the expected score after points were deduced after the game ended.");
    }

}
