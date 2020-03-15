package scrabble;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import scrabble.exceptions.InvalidInputException;

import static org.junit.jupiter.api.Assertions.*;

public class UserInputTest {

    private UserInput inputTest;

    @BeforeEach
    void setup()
    {
        inputTest = null;
    }

    //Test to check when user inputs quit and the programme returns the QUIT type enum.
    @Test
    @DisplayName("Testing the input 'QUIT' returns the input type 'QUIT'")
    void inputQuit()
    {
        inputTest.parseInput("QUIT");
        UserInput QuitTest = null;
        QuitTest.parseInput("Quit");
        UserInput quitTest = null;
        quitTest.parseInput("quit");

        assertAll("Testing the different ways to input quit\n",
                () -> assertEquals(UserInput.UserInputType.QUIT, inputTest.getInputType(), "\nThe user input 'QUIT' did not give the input type 'QUIT'"),
                () -> assertEquals(UserInput.UserInputType.QUIT, QuitTest.getInputType(), "\nThe user input 'Quit' did not give the input type 'QUIT'"),
                () -> assertEquals(UserInput.UserInputType.QUIT, quitTest.getInputType(), "\nThe user input 'quit' did not give the input type 'QUIT'")
        );

    }

    //Test to check when user inputs PASS and the programme returns the PASS type enum.
    @Test
    @DisplayName("Testing the input 'PASS' returns the input type 'PASS'")
    void inputPASS()
    {
        inputTest.parseInput("PASS");
        UserInput PassTest = null;
        PassTest.parseInput("Pass");
        UserInput passTest = null;
        passTest.parseInput("pass");

        assertAll("Testing the different ways to input PASS\n",
                () -> assertEquals(UserInput.UserInputType.PASS, inputTest.getInputType(), "\nThe user input 'PASS' did not give the input type 'PASS'"),
                () -> assertEquals(UserInput.UserInputType.PASS, PassTest.getInputType(), "\nThe user input 'Pass' did not give the input type 'PASS'"),
                () -> assertEquals(UserInput.UserInputType.PASS, passTest.getInputType(), "\nThe user input 'pass' did not give the input type 'PASS'")
        );

    }

    //Test to check when user inputs help and the programme returns the HELP type enum.
    @Test
    @DisplayName("Testing the input 'HELP' returns the input type 'HELP'")
    void inputHELP()
    {
        inputTest.parseInput("HELP");
        UserInput HelpTest = null;
        HelpTest.parseInput("Help");
        UserInput helpTest = null;
        helpTest.parseInput("help");

        assertAll("Testing the different ways to input HELP\n",
                () -> assertEquals(UserInput.UserInputType.HELP, inputTest.getInputType(), "\nThe user input 'HELP' did not give the input type 'HELP'"),
                () -> assertEquals(UserInput.UserInputType.HELP, HelpTest.getInputType(), "\nThe user input 'Help' did not give the input type 'HELP'"),
                () -> assertEquals(UserInput.UserInputType.HELP, helpTest.getInputType(), "\nThe user input 'help' did not give the input type 'HELP'")
        );
    }

    //Test to check input parses correctly when user is exchanging Tiles
    @Test
    @DisplayName("Testing when exchanging Tiles that is output is parsed correctly")
    void exchangeTest()
    {
        inputTest.parseInput("EXCHANGE A B C");
        char[] expectedResult = {'A', 'B', 'C'};
        assertEquals(UserInput.UserInputType.EXCHANGE, inputTest.getInputType());
        assertEquals(expectedResult, inputTest.getWord(), "The parsed input did not give the expected result." );
    }

    //Test to check when user inputs EXCHANGE and the programme returns the EXCHANGE type enum.
    @Test
    @DisplayName("Testing the input 'EXCHANGE' returns the input type 'EXCHANGE'")
    void inputEXCHANGE()
    {
        inputTest.parseInput("EXCHANGE");
        UserInput ExchangeTest = null;
        ExchangeTest.parseInput("Exchange");
        UserInput exchangeTest = null;
        exchangeTest.parseInput("exchange");

        assertAll("Testing the different ways to input EXCHANGE\n",
                () -> assertEquals(UserInput.UserInputType.EXCHANGE, inputTest.getInputType(), "\nThe user input 'EXCHANGE' did not give the input type 'EXCHANGE'"),
                () -> assertEquals(UserInput.UserInputType.EXCHANGE, ExchangeTest.getInputType(), "\nThe user input 'Help' did not give the input type 'EXCHANGE'"),
                () -> assertEquals(UserInput.UserInputType.EXCHANGE, exchangeTest.getInputType(), "\nThe user input 'help' did not give the input type 'EXCHANGE'")
        );
    }


    //Test to check when user inputs BLANK and the programme returns the BLANK type enum.
    @Test
    @DisplayName("Testing the input 'BLANK' returns the input type 'BLANK'")
    void inputBLANK()
    {
        inputTest.parseInput("BLANK");
        UserInput BlankTest = null;
        BlankTest.parseInput("Blank");
        UserInput blankTest = null;
        blankTest.parseInput("blank");

        assertAll("Testing the different ways to input BLANK\n",
                () -> assertEquals(UserInput.UserInputType.BLANK, inputTest.getInputType(), "\nThe user input 'BLANK' did not give the input type 'BLANK'"),
                () -> assertEquals(UserInput.UserInputType.BLANK, BlankTest.getInputType(), "\nThe user input 'Blank' did not give the input type 'BLANK'"),
                () -> assertEquals(UserInput.UserInputType.BLANK, blankTest.getInputType(), "\nThe user input 'blank' did not give the input type 'BLANK'")
        );
    }


    //Test to check input parses correctly when user is exchanging Tiles
    @Test
    @DisplayName("Testing when assigning letters to blank tiles, that the output is parsed correctly")
    void blankTest()
    {
        inputTest.parseInput("BLANK A B C");
        char[] expectedResult = {'A', 'B', 'C'};
        assertEquals(UserInput.UserInputType.BLANK, inputTest.getInputType(), "\nThe user input 'BLANK' did not give the input type 'BLANK'");
        assertEquals(expectedResult, inputTest.getWord(), "The parsed input did not give the expected result." );
    }


    //Test to check that a user input for placing a word on the Board is parsed and stored correctly
    @Test
    @DisplayName("Test to check that a user input for placing a word on the Board is parsed and stored correctly")
    void inputPlaceWordTest()
    {
        inputTest.parseInput("H8 A TEST");
        int[] coordinateTest = {8, 8};
        char[] wordTest = {'T', 'E', 'S', 'T'};

        assertAll("Testing the input for placing a word is stored correctly.",
                () -> assertEquals(coordinateTest, inputTest.getStartPosition(), "\nThe coordinates inputted and parsed are not the expected coordinates."),
                () -> assertEquals(UserInput.Direction.HORIZONTAL, inputTest.getWordDirection(), "\nThe expected direction does not match the actual direction"),
                () -> assertEquals(UserInput.UserInputType.PLACE_TILE, inputTest.getInputType(), "\nThe expected type PLACE_TILE does not match the actual type."),
                () -> assertEquals(wordTest, inputTest.getWord(), "\nThe inputted word does not match the expected word")
        );
    }

    //Test to check that an invalid input gives an 'ERROR' UserInputType enum
    @Test
    @DisplayName("Testing that an invalid input gives an 'ERROR' UserInputType enum")
    void invalidInputErrorTest()
    {
        //Invalid Coordinate
        inputTest.parseInput("P12 A TEST");

        //Invalid Command
        UserInput commandTest = null;
        commandTest.parseInput("HELPME");

        assertAll("Testing the different invalid inputs\n",
                () -> assertEquals(UserInput.UserInputType.ERROR, inputTest.getInputType(), "\nThe invalid user input did not give the input type 'ERROR'."),
                () -> assertEquals(UserInput.UserInputType.ERROR, commandTest.getInputType(), "\nThe invalid user input did not give the input type 'ERROR'.")
                );

    }

}
