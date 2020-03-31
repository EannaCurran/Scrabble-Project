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
        inputTest = UserInput.parseInput("QUIT");
        UserInput QuitTest = UserInput.parseInput("Quit");
        UserInput quitTest = UserInput.parseInput("quit");

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
        inputTest= UserInput.parseInput("PASS");
        UserInput PassTest = UserInput.parseInput("Pass");
        UserInput passTest = UserInput.parseInput("pass");

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
        inputTest = UserInput.parseInput("HELP");
        UserInput HelpTest = UserInput.parseInput("Help");
        UserInput helpTest = UserInput.parseInput("help");


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
        inputTest = UserInput.parseInput("exchange KILLIAN");
        char[] expectedResult = {'K', 'I', 'L', 'L', 'I', 'A', 'N'};
        assertEquals(UserInput.UserInputType.EXCHANGE, inputTest.getInputType());
        assertArrayEquals(expectedResult, inputTest.getWord(), "The parsed input did not give the expected result." );
    }

    //Test to check when user inputs EXCHANGE and the programme returns the EXCHANGE type enum.
    @Test
    @DisplayName("Testing the input 'EXCHANGE' returns the input type 'EXCHANGE'")
    void inputEXCHANGE()
    {
        inputTest = UserInput.parseInput("EXCHANGE KILLIAN");
        UserInput ExchangeTest = UserInput.parseInput("Exchange ABC");
        UserInput exchangeTest = UserInput.parseInput("exchange TEST");

        assertAll("Testing the different ways to input EXCHANGE\n",
                () -> assertEquals(UserInput.UserInputType.EXCHANGE, inputTest.getInputType(), "\nThe user input 'EXCHANGE' did not give the input type 'EXCHANGE'"),
                () -> assertEquals(UserInput.UserInputType.EXCHANGE, ExchangeTest.getInputType(), "\nThe user input 'Exchange' did not give the input type 'EXCHANGE'"),
                () -> assertEquals(UserInput.UserInputType.EXCHANGE, exchangeTest.getInputType(), "\nThe user input 'exchange' did not give the input type 'EXCHANGE'")
        );
    }


    //Test to check when user inputs BLANK and the programme returns the BLANK type enum.
    @Test
    @DisplayName("Testing the input 'BLANK' returns the input type 'BLANK'")
    void inputBLANK()
    {
        inputTest = UserInput.parseInput("BLANK KILLIAN");
        UserInput BlankTest = UserInput.parseInput("Blank TEST");
        UserInput blankTest = UserInput.parseInput("blank ABC");

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
        inputTest = UserInput.parseInput("BLANK ABC");
        char[] expectedResult = {'A', 'B', 'C'};
        assertEquals(UserInput.UserInputType.BLANK, inputTest.getInputType(), "\nThe user input 'BLANK' did not give the input type 'BLANK'");
        assertArrayEquals(expectedResult, inputTest.getWord(), "The parsed input did not give the expected result." );

    }


    //Test to check that a user input for placing a word on the Board is parsed and stored correctly
    @Test
    @DisplayName("Test to check that a user input for placing a word on the Board is parsed and stored correctly")
    void inputPlaceWordTest()
    {
        inputTest = UserInput.parseInput("I8 A TEST");
        int[] coordinateTest = {8, 8};
        char[] wordTest = {'T', 'E', 'S', 'T'};
        System.out.println("GET WORD" + inputTest.getWord());
        System.out.println(wordTest);

        assertAll("Testing the input for placing a word is stored correctly.",
                () -> assertArrayEquals(coordinateTest, inputTest.getStartPosition(), "\nThe coordinates inputted and parsed are not the expected coordinates."),
                () -> assertEquals(UserInput.Direction.HORIZONTAL, inputTest.getWordDirection(), "\nThe expected direction does not match the actual direction"),
                () -> assertEquals(UserInput.UserInputType.PLACE_TILE, inputTest.getInputType(), "\nThe expected type PLACE_TILE does not match the actual type."),
                () -> assertArrayEquals(wordTest, inputTest.getWord(), "\nThe inputted word does not match the expected word")
        );
    }

    //Test to check that an invalid input gives an 'ERROR' UserInputType enum
    @Test
    @DisplayName("Testing that an invalid input gives an 'ERROR' UserInputType enum")
    void invalidInputErrorTest()
    {
        //Invalid Coordinate
        inputTest = UserInput.parseInput("P12 A TEST");

        //Invalid Command
        UserInput commandTest = UserInput.parseInput("HELPME");;

        assertAll("Testing the different invalid inputs\n",
                () -> assertEquals(UserInput.UserInputType.ERROR, inputTest.getInputType(), "\nThe invalid user input did not give the input type 'ERROR'."),
                () -> assertEquals(UserInput.UserInputType.ERROR, commandTest.getInputType(), "\nThe invalid user input did not give the input type 'ERROR'.")
                );

    }

    //Test to check when user inputs CHALLENGE and the programme returns the CHALLENGE type enum.
    //Test to check when user inputs BLANK and the programme returns the BLANK type enum.
    @Test
    @DisplayName("Testing the input 'CHALLENGE' returns the input type 'CHALLENGE'")
    void inputCHALLENGE()
    {
        inputTest = UserInput.parseInput("CHALLENGE Y");
        UserInput ChallengeTestN = UserInput.parseInput("Challenge N");
        UserInput challengeTesty = UserInput.parseInput("challenge y");
        UserInput ChallengeTestn = UserInput.parseInput("Challenge n");

        assertAll("Testing the different ways to input BLANK\n",
                () -> assertEquals(UserInput.UserInputType.CHALLENGE, inputTest.getInputType(), "\nThe user input 'CHALLENGE' did not give the input type 'CHALLENGE'"),
                () -> assertEquals(UserInput.UserInputType.CHALLENGE, ChallengeTestN.getInputType(), "\nThe user input 'Challenge' did not give the input type 'CHALLENGE'"),
                () -> assertEquals(UserInput.UserInputType.CHALLENGE, challengeTesty.getInputType(), "\nThe user input 'challenge' did not give the input type 'CHALLENGE'"),
                () -> assertEquals(UserInput.UserInputType.CHALLENGE, ChallengeTestn.getInputType(), "\nThe user input 'CHALLENGE' did not give the input type 'CHALLENGE'")
        );
    }



    @Test
    @DisplayName("Testing when assigning letters to blank tiles, that the output is parsed correctly")
    void challengeTest()
    {
        inputTest = UserInput.parseInput("BLANK ABC");
        char[] expectedResult = {'A', 'B', 'C'};

        assertEquals(UserInput.UserInputType.BLANK, inputTest.getInputType(), "\nThe user input 'BLANK' did not give the input type 'BLANK'");
        assertArrayEquals(expectedResult, inputTest.getWord(), "The parsed input did not give the expected result." );

    }

    //Test to check when user inputs RESTART and the programme returns the RESTART type enum.
    @Test
    @DisplayName("Testing the input 'RESTART' returns the input type 'RESTART'")
    void inputRESTART()
    {
        inputTest = UserInput.parseInput("RESTART");
        UserInput RestartTest = UserInput.parseInput("Restart");
        UserInput restartTest = UserInput.parseInput("restart");

        assertAll("Testing the different ways to input PASS\n",
                () -> assertEquals(UserInput.UserInputType.RESTART, inputTest.getInputType(), "\nThe user input 'RESTART' did not give the input type 'RESTART'"),
                () -> assertEquals(UserInput.UserInputType.RESTART, RestartTest.getInputType(), "\nThe user input 'Restart' did not give the input type 'RESTART'"),
                () -> assertEquals(UserInput.UserInputType.RESTART, restartTest.getInputType(), "\nThe user input 'restart' did not give the input type 'RESTART'")
        );

    }

    //Test to check when user inputs name, the programme returns the NAME type enum.
    @Test
    @DisplayName("Test to check when user inputs name, the programme returns the NAME type enum")
    void inputName()
    {
        inputTest = UserInput.parseInput("NAME KILLIAN");
        UserInput NameTest = UserInput.parseInput("Name Killian");
        UserInput nameTest = UserInput.parseInput("name killian");

        assertAll("Testing the different ways to input NAME\n",
                () -> assertEquals(UserInput.UserInputType.NAME, inputTest.getInputType(), "\nThe user input 'NAME' did not give the input type 'NAME'"),
                () -> assertEquals(UserInput.UserInputType.NAME, NameTest.getInputType(), "\nThe user input 'Name' did not give the input type 'NAME'"),
                () -> assertEquals(UserInput.UserInputType.NAME, nameTest.getInputType(), "\nThe user input 'name' did not give the input type 'NAME'")
        );
    }

    //Tests to check if the inputted name for a player is stored correctly
    @Test
    @DisplayName("Check if the inputted name for a player is stored correctly")
    void nameTest()
    {
        inputTest = UserInput.parseInput("NAME Killian");
        UserInput nameTest = UserInput.parseInput("Name xXx_QuIcKsCoPeR_420_xXx");

        assertAll("Testing that an inputted name for a player is stored correctly",
                () -> assertEquals("Killian", inputTest.getName(), "\nThe parsed input of a user's name did not give the expected result."),
                () -> assertEquals("xXx_QuIcKsCoPeR_420_xXx", nameTest.getName(), "\nThe parsed input of a user's name did not give the expected result.")
    );
    }


}
