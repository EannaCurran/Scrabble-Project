package scrabble;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;

import static org.junit.jupiter.api.Assertions.*;


public class ScrabbleTest {

    Scrabble Test;
    MoveInfo moveTest;
    Word wordTest;

    @BeforeEach
    void setup()
    {
        //Initialising a game of scrabble and assigning names to the players
        try {
            Test = new Scrabble();
            Test.createPlayer("Killian", 0);
            Test.createPlayer("John", 1);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

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


    //Test to check that dictionaryWords returns true for a valid word
    @Test
    @DisplayName("Test to check that dictionaryWords returns true for a valid word")
    void scrabbleTestDictionaryWords(){

        //Initalising coordinate and word array
        int[] coordinateTest = {8,8};
        //Creating reference to players
        Player[] PlayerTest = Test.getPlayers();
        char[] wordTest = "TEST".toCharArray();

        //Initalising moveTest object
        moveTest = new MoveInfo(PlayerTest[0], coordinateTest, UserInput.Direction.HORIZONTAL, wordTest);
        assertTrue(Test.dictionaryWords(moveTest), "The placed word was expected to be true");
    }

    //Test to check that dictionaryWords returns true for a valid word connected to another valid word
    @Test
    @DisplayName("Test to check that dictionaryWords returns true for a valid word connected to another valid word")
    void AuxiliaryDictionaryWordTest(){

        //Initalising coordinate and word array
        int[] coordinateTest = {8,8};
        //Creating reference to players
        Player[] PlayerTest = Test.getPlayers();
        char[] word = "TEST".toCharArray();

        //Initalising a new word
        wordTest = new Word(coordinateTest, UserInput.Direction.VERTICAL, word);
        //Initalising moveTest object
        moveTest = new MoveInfo(PlayerTest[0], coordinateTest, UserInput.Direction.HORIZONTAL, word);
        //Adds auxiliary word
        moveTest.addAuxiliaryWord(wordTest);
        assertTrue(Test.dictionaryWords(moveTest), "The placed word and it's auxiliary word was expected to be a valid dictionary word");
    }


    //Test to check that dictionaryWords returns true for a valid word that extends another valid word
    @Test
    @DisplayName("Test to check that dictionaryWords returns true for a valid word that extends another valid word")
    void AuxiliaryDictionaryWordExtensionTest(){

        //Initalising coordinate and word array
        int[] coordinateTest = {8,8};
        //Creating reference to players
        Player[] PlayerTest = Test.getPlayers();
        char[] word = "TEST".toCharArray();
        char[] extension = "TESTS".toCharArray();

        //Initalising a new word
        wordTest = new Word(coordinateTest, UserInput.Direction.VERTICAL, word);
        //Initalising moveTest object
        moveTest = new MoveInfo(PlayerTest[0], coordinateTest, UserInput.Direction.VERTICAL, extension);
        //Adds auxiliary word
        moveTest.addAuxiliaryWord(wordTest);
        assertTrue(Test.dictionaryWords(moveTest), "The placed word and its auxiliary word was expected to be a valid dictionary word");
    }

    //Test to check that dictionaryWords returns false for a invalid word
    @Test
    @DisplayName("Test to check that dictionaryWords returns false for a invalid word")
    void scrabbleTestInvalidDictionaryWords(){

        //Initalising coordinate and word array
        int[] coordinateTest = {8,8};
        //Creating reference to players
        Player[] PlayerTest = Test.getPlayers();
        char[] wordTest = "YABBL".toCharArray();
        //Initalising moveTest object
        moveTest = new MoveInfo(PlayerTest[0], coordinateTest, UserInput.Direction.HORIZONTAL, wordTest);
        assertFalse(Test.dictionaryWords(moveTest), "The placed word was expected to be a invalid dictionary word");
    }

    //Test to check that dictionaryWords returns false for a invalid word connected to another valid word
    @Test
    @DisplayName("Test to check that dictionaryWords returns false for a invalid word connected to another valid word")
    void InvalidAuxiliaryDictionaryWordTest(){

        //Initalising coordinate and word array
        int[] coordinateTest = {8,8};
        //Creating reference to players
        Player[] PlayerTest = Test.getPlayers();
        char[] word = "TEEER".toCharArray();
        char[] auxWord = "TEST".toCharArray();

        //Initalising a new word
        wordTest = new Word(coordinateTest, UserInput.Direction.VERTICAL, auxWord);
        //Initalising moveTest object
        moveTest = new MoveInfo(PlayerTest[0], coordinateTest, UserInput.Direction.HORIZONTAL, word);
        //Adds auxiliary word
        moveTest.addAuxiliaryWord(wordTest);
        assertFalse(Test.dictionaryWords(moveTest), "The placed word was expected to be a invalid dictionary word");
    }

    //Test to check that dictionaryWords returns false for a invalid word that extends another valid word
    @Test
    @DisplayName("Test to check that dictionaryWords returns false for a invalid word that extends another invalid word")
    void InvaildAuxiliaryDictionaryWordExtensionTest(){

        //Initalising coordinate and word array
        int[] coordinateTest = {8,8};
        //Creating reference to players
        Player[] PlayerTest = Test.getPlayers();
        char[] word = "TEST".toCharArray();
        char[] extension = "TESTINGER".toCharArray();

        //Initalising a new word
        wordTest = new Word(coordinateTest, UserInput.Direction.VERTICAL, word);
        //Initalising moveTest object
        moveTest = new MoveInfo(PlayerTest[0], coordinateTest, UserInput.Direction.VERTICAL, extension);
        //Adds auxiliary word
        moveTest.addAuxiliaryWord(wordTest);
        assertFalse(Test.dictionaryWords(moveTest), "The placed word was expected to be a invalid dictionary word");
    }

}
