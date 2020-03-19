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


        PlayerTest[0].getPlayerFrame();


        assertTrue(Test.isGameOver());
    }

    //Test to check if Game Over method returns false when frame is not empty
    @Test
    @DisplayName("Test to check if Game Over method returns false when frame is not empty")
    void IsGameOverTestFalse()
    {

        while(!Test.getPool().isEmpty())
        {
            Test.getPool().removeTile();
        }

        assertFalse(Test.isGameOver());
    }



}
