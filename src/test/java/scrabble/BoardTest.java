package scrabble;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import scrabble.exceptions.InvalidBoardException;
import scrabble.exceptions.InvalidFrameException;
import scrabble.exceptions.InvalidMoveInfoException;
import scrabble.exceptions.InvalidWordException;

import static org.junit.jupiter.api.Assertions.*;


public class BoardTest {

    // Declaring variables for testing
    private Board boardTest;
    private Pool poolTest;
    private Player playerTest;

    @BeforeEach
    void setup() {
        // Setting up variables used in testing
        poolTest = new Pool();
        boardTest = new Board();
        playerTest = new Player("Test" , poolTest);
    }


    //Tests board is printed correctly
    @Test
    @DisplayName("Testing a new board prints correctly")
    void newBoardPrintTest()
    {
        String startingBoard = "\t\t\t\t\t\t\t\tScrabble Board\n" +
                "  | 1  | 2  | 3  | 4  | 5  | 6  | 7  | 8  | 9  | 10 | 11 | 12 | 13 | 14 | 15 |\n" +
                "______________________________________________________________________________\n" +
                " 1| 3W |    |    | 2L |    |    |    | 3W |    |    |    | 2L |    |    | 3W |\n" +
                "______________________________________________________________________________\n" +
                " 2|    | 2W |    |    |    | 3L |    |    |    | 3L |    |    |    | 2W |    |\n" +
                "______________________________________________________________________________\n" +
                " 3|    |    | 2W |    |    |    | 2L |    | 2L |    |    |    | 2W |    |    |\n" +
                "______________________________________________________________________________\n" +
                " 4| 2L |    |    | 2W |    |    |    | 2L |    |    |    | 2W |    |    | 2L |\n" +
                "______________________________________________________________________________\n" +
                " 5|    |    |    |    | 2W |    |    |    |    |    | 2W |    |    |    |    |\n" +
                "______________________________________________________________________________\n" +
                " 6|    | 3L |    |    |    | 3L |    |    |    | 3L |    |    |    | 3L |    |\n" +
                "______________________________________________________________________________\n" +
                " 7|    |    | 2L |    |    |    | 2L |    | 2L |    |    |    | 2L |    |    |\n" +
                "______________________________________________________________________________\n" +
                " 8| 3W |    |    | 2L |    |    |    | ** |    |    |    | 2L |    |    | 3W |\n" +
                "______________________________________________________________________________\n" +
                " 9|    |    | 2L |    |    |    | 2L |    | 2L |    |    |    | 2L |    |    |\n" +
                "______________________________________________________________________________\n" +
                "10|    | 3L |    |    |    | 3L |    |    |    | 3L |    |    |    | 3L |    |\n" +
                "______________________________________________________________________________\n" +
                "11|    |    |    |    | 2W |    |    |    |    |    | 2W |    |    |    |    |\n" +
                "______________________________________________________________________________\n" +
                "12| 2L |    |    | 2W |    |    |    | 2L |    |    |    | 2W |    |    | 2L |\n" +
                "______________________________________________________________________________\n" +
                "13|    |    | 2W |    |    |    | 2L |    | 2L |    |    |    | 2W |    |    |\n" +
                "______________________________________________________________________________\n" +
                "14|    | 2W |    |    |    | 3L |    |    |    | 3L |    |    |    | 2W |    |\n" +
                "______________________________________________________________________________\n" +
                "15| 3W |    |    | 2L |    |    |    | 3W |    |    |    | 2L |    |    | 3W |";
        assertEquals(startingBoard, boardTest.toString());
    }



    //Testing that a letter overwrites the centre square correctly for a letter worth one point
    @Test
    @DisplayName("Letter overwrites the centre square correctly for a letter worth one point")
    void centreSquareTestLetterOnePoint()
    {
        boardTest.getSquare(7,7).setTile(new Tile('A'));

        String boardPrintTest = "\t\t\t\t\t\t\t\tScrabble Board\n" +
            "  | 1  | 2  | 3  | 4  | 5  | 6  | 7  | 8  | 9  | 10 | 11 | 12 | 13 | 14 | 15 |\n" +
            "______________________________________________________________________________\n" +
            " 1| 3W |    |    | 2L |    |    |    | 3W |    |    |    | 2L |    |    | 3W |\n" +
            "______________________________________________________________________________\n" +
            " 2|    | 2W |    |    |    | 3L |    |    |    | 3L |    |    |    | 2W |    |\n" +
            "______________________________________________________________________________\n" +
            " 3|    |    | 2W |    |    |    | 2L |    | 2L |    |    |    | 2W |    |    |\n" +
            "______________________________________________________________________________\n" +
            " 4| 2L |    |    | 2W |    |    |    | 2L |    |    |    | 2W |    |    | 2L |\n" +
            "______________________________________________________________________________\n" +
            " 5|    |    |    |    | 2W |    |    |    |    |    | 2W |    |    |    |    |\n" +
            "______________________________________________________________________________\n" +
            " 6|    | 3L |    |    |    | 3L |    |    |    | 3L |    |    |    | 3L |    |\n" +
            "______________________________________________________________________________\n" +
            " 7|    |    | 2L |    |    |    | 2L |    | 2L |    |    |    | 2L |    |    |\n" +
            "______________________________________________________________________________\n" +
            " 8| 3W |    |    | 2L |    |    |    |A  1|    |    |    | 2L |    |    | 3W |\n" +
            "______________________________________________________________________________\n" +
            " 9|    |    | 2L |    |    |    | 2L |    | 2L |    |    |    | 2L |    |    |\n" +
            "______________________________________________________________________________\n" +
            "10|    | 3L |    |    |    | 3L |    |    |    | 3L |    |    |    | 3L |    |\n" +
            "______________________________________________________________________________\n" +
            "11|    |    |    |    | 2W |    |    |    |    |    | 2W |    |    |    |    |\n" +
            "______________________________________________________________________________\n" +
            "12| 2L |    |    | 2W |    |    |    | 2L |    |    |    | 2W |    |    | 2L |\n" +
            "______________________________________________________________________________\n" +
            "13|    |    | 2W |    |    |    | 2L |    | 2L |    |    |    | 2W |    |    |\n" +
            "______________________________________________________________________________\n" +
            "14|    | 2W |    |    |    | 3L |    |    |    | 3L |    |    |    | 2W |    |\n" +
            "______________________________________________________________________________\n" +
            "15| 3W |    |    | 2L |    |    |    | 3W |    |    |    | 2L |    |    | 3W |";

            assertEquals(boardPrintTest, boardTest.toString(),"The board did not print the way it was expected");
    }

    //Testing that a letter overwrites the centre square correctly for a letter worth ten points
    @Test
    @DisplayName("Letter overwrites the centre square correctly for a letter worth ten points")
    void centreSquareTestLetterTenPoints()
    {
        boardTest.getSquare(7,7).setTile(new Tile('Q'));

        String boardPrintTest = "\t\t\t\t\t\t\t\tScrabble Board\n" +
                "  | 1  | 2  | 3  | 4  | 5  | 6  | 7  | 8  | 9  | 10 | 11 | 12 | 13 | 14 | 15 |\n" +
                "______________________________________________________________________________\n" +
                " 1| 3W |    |    | 2L |    |    |    | 3W |    |    |    | 2L |    |    | 3W |\n" +
                "______________________________________________________________________________\n" +
                " 2|    | 2W |    |    |    | 3L |    |    |    | 3L |    |    |    | 2W |    |\n" +
                "______________________________________________________________________________\n" +
                " 3|    |    | 2W |    |    |    | 2L |    | 2L |    |    |    | 2W |    |    |\n" +
                "______________________________________________________________________________\n" +
                " 4| 2L |    |    | 2W |    |    |    | 2L |    |    |    | 2W |    |    | 2L |\n" +
                "______________________________________________________________________________\n" +
                " 5|    |    |    |    | 2W |    |    |    |    |    | 2W |    |    |    |    |\n" +
                "______________________________________________________________________________\n" +
                " 6|    | 3L |    |    |    | 3L |    |    |    | 3L |    |    |    | 3L |    |\n" +
                "______________________________________________________________________________\n" +
                " 7|    |    | 2L |    |    |    | 2L |    | 2L |    |    |    | 2L |    |    |\n" +
                "______________________________________________________________________________\n" +
                " 8| 3W |    |    | 2L |    |    |    |Q 10|    |    |    | 2L |    |    | 3W |\n" +
                "______________________________________________________________________________\n" +
                " 9|    |    | 2L |    |    |    | 2L |    | 2L |    |    |    | 2L |    |    |\n" +
                "______________________________________________________________________________\n" +
                "10|    | 3L |    |    |    | 3L |    |    |    | 3L |    |    |    | 3L |    |\n" +
                "______________________________________________________________________________\n" +
                "11|    |    |    |    | 2W |    |    |    |    |    | 2W |    |    |    |    |\n" +
                "______________________________________________________________________________\n" +
                "12| 2L |    |    | 2W |    |    |    | 2L |    |    |    | 2W |    |    | 2L |\n" +
                "______________________________________________________________________________\n" +
                "13|    |    | 2W |    |    |    | 2L |    | 2L |    |    |    | 2W |    |    |\n" +
                "______________________________________________________________________________\n" +
                "14|    | 2W |    |    |    | 3L |    |    |    | 3L |    |    |    | 2W |    |\n" +
                "______________________________________________________________________________\n" +
                "15| 3W |    |    | 2L |    |    |    | 3W |    |    |    | 2L |    |    | 3W |";

        assertEquals(boardPrintTest, boardTest.toString(),"The board did not print the way it was expected");
    }



    //Testing that a letter overwrites a blank square correctly with a Tile worth one point
    @Test
    @DisplayName("Letter overwrites a blank square correctly with a tile worth one point")
    void blankSquareTestSingleDigits()
    {
        boardTest.getSquare(5,7).setTile(new Tile('A'));

        String boardPrintTest = "\t\t\t\t\t\t\t\tScrabble Board\n" +
                "  | 1  | 2  | 3  | 4  | 5  | 6  | 7  | 8  | 9  | 10 | 11 | 12 | 13 | 14 | 15 |\n" +
                "______________________________________________________________________________\n" +
                " 1| 3W |    |    | 2L |    |    |    | 3W |    |    |    | 2L |    |    | 3W |\n" +
                "______________________________________________________________________________\n" +
                " 2|    | 2W |    |    |    | 3L |    |    |    | 3L |    |    |    | 2W |    |\n" +
                "______________________________________________________________________________\n" +
                " 3|    |    | 2W |    |    |    | 2L |    | 2L |    |    |    | 2W |    |    |\n" +
                "______________________________________________________________________________\n" +
                " 4| 2L |    |    | 2W |    |    |    | 2L |    |    |    | 2W |    |    | 2L |\n" +
                "______________________________________________________________________________\n" +
                " 5|    |    |    |    | 2W |    |    |    |    |    | 2W |    |    |    |    |\n" +
                "______________________________________________________________________________\n" +
                " 6|    | 3L |    |    |    | 3L |    |A  1|    | 3L |    |    |    | 3L |    |\n" +
                "______________________________________________________________________________\n" +
                " 7|    |    | 2L |    |    |    | 2L |    | 2L |    |    |    | 2L |    |    |\n" +
                "______________________________________________________________________________\n" +
                " 8| 3W |    |    | 2L |    |    |    | ** |    |    |    | 2L |    |    | 3W |\n" +
                "______________________________________________________________________________\n" +
                " 9|    |    | 2L |    |    |    | 2L |    | 2L |    |    |    | 2L |    |    |\n" +
                "______________________________________________________________________________\n" +
                "10|    | 3L |    |    |    | 3L |    |    |    | 3L |    |    |    | 3L |    |\n" +
                "______________________________________________________________________________\n" +
                "11|    |    |    |    | 2W |    |    |    |    |    | 2W |    |    |    |    |\n" +
                "______________________________________________________________________________\n" +
                "12| 2L |    |    | 2W |    |    |    | 2L |    |    |    | 2W |    |    | 2L |\n" +
                "______________________________________________________________________________\n" +
                "13|    |    | 2W |    |    |    | 2L |    | 2L |    |    |    | 2W |    |    |\n" +
                "______________________________________________________________________________\n" +
                "14|    | 2W |    |    |    | 3L |    |    |    | 3L |    |    |    | 2W |    |\n" +
                "______________________________________________________________________________\n" +
                "15| 3W |    |    | 2L |    |    |    | 3W |    |    |    | 2L |    |    | 3W |";

        assertEquals(boardPrintTest, boardTest.toString(),"The board did not print the way it was expected");
    }



    //Testing that a letter overwrites a blank square correctly with a Tile worth ten points
    @Test
    @DisplayName("Letter overwrites a blank square correctly with a tile worth ten points")
    void blankSquareTestDoubleDigets()
    {
        boardTest.getSquare(5,7).setTile(new Tile('Q'));

        String boardPrintTest = "\t\t\t\t\t\t\t\tScrabble Board\n" +
                "  | 1  | 2  | 3  | 4  | 5  | 6  | 7  | 8  | 9  | 10 | 11 | 12 | 13 | 14 | 15 |\n" +
                "______________________________________________________________________________\n" +
                " 1| 3W |    |    | 2L |    |    |    | 3W |    |    |    | 2L |    |    | 3W |\n" +
                "______________________________________________________________________________\n" +
                " 2|    | 2W |    |    |    | 3L |    |    |    | 3L |    |    |    | 2W |    |\n" +
                "______________________________________________________________________________\n" +
                " 3|    |    | 2W |    |    |    | 2L |    | 2L |    |    |    | 2W |    |    |\n" +
                "______________________________________________________________________________\n" +
                " 4| 2L |    |    | 2W |    |    |    | 2L |    |    |    | 2W |    |    | 2L |\n" +
                "______________________________________________________________________________\n" +
                " 5|    |    |    |    | 2W |    |    |    |    |    | 2W |    |    |    |    |\n" +
                "______________________________________________________________________________\n" +
                " 6|    | 3L |    |    |    | 3L |    |Q 10|    | 3L |    |    |    | 3L |    |\n" +
                "______________________________________________________________________________\n" +
                " 7|    |    | 2L |    |    |    | 2L |    | 2L |    |    |    | 2L |    |    |\n" +
                "______________________________________________________________________________\n" +
                " 8| 3W |    |    | 2L |    |    |    | ** |    |    |    | 2L |    |    | 3W |\n" +
                "______________________________________________________________________________\n" +
                " 9|    |    | 2L |    |    |    | 2L |    | 2L |    |    |    | 2L |    |    |\n" +
                "______________________________________________________________________________\n" +
                "10|    | 3L |    |    |    | 3L |    |    |    | 3L |    |    |    | 3L |    |\n" +
                "______________________________________________________________________________\n" +
                "11|    |    |    |    | 2W |    |    |    |    |    | 2W |    |    |    |    |\n" +
                "______________________________________________________________________________\n" +
                "12| 2L |    |    | 2W |    |    |    | 2L |    |    |    | 2W |    |    | 2L |\n" +
                "______________________________________________________________________________\n" +
                "13|    |    | 2W |    |    |    | 2L |    | 2L |    |    |    | 2W |    |    |\n" +
                "______________________________________________________________________________\n" +
                "14|    | 2W |    |    |    | 3L |    |    |    | 3L |    |    |    | 2W |    |\n" +
                "______________________________________________________________________________\n" +
                "15| 3W |    |    | 2L |    |    |    | 3W |    |    |    | 2L |    |    | 3W |";

        assertEquals(boardPrintTest, boardTest.toString(),"The board did not print the way it was expected");
    }



    //Board Reset test
    @Test
    @DisplayName("Testing that squares are empty after resetting the board")
    void resetBoardForEmptySquares()
    {
        //Adding tiles to the board
        boardTest.getSquare(7,7).setTile(new Tile('N'));
        boardTest.getSquare(7,8).setTile(new Tile('Y'));
        boardTest.getSquare(8,8).setTile(new Tile('C'));
        //Resetting the board
        boardTest.resetBoard();

        // assertAll so that all assertions are run and reported together
        assertAll("Testing squares that had tiles are now removed",
                //Checking square (7,7)
                () -> assertTrue(boardTest.getSquare(7,7).isEmpty(),"\nThe square isn't empty after a board reset."),
                //Checking square (7,8)
                () -> assertTrue(boardTest.getSquare(7,8).isEmpty(),"\nThe square isn't empty after a board reset."),
                //Checking square (7,9)
                () -> assertTrue(boardTest.getSquare(7,9).isEmpty(),"\nThe square isn't empty after a board reset.")
                );
    }



    @Test
    @DisplayName("Testing that squares are of correct type after resetting the board")
    void resettingTheBoardForTypeCheck()
    {
        //Adding tiles to the board
        boardTest.getSquare(7,7).setTile(new Tile('N'));
        boardTest.getSquare(7,8).setTile(new Tile('Y'));
        boardTest.getSquare(8,8).setTile(new Tile('C'));
        //Resetting the board
        boardTest.resetBoard();

        // assertAll so that all assertions are run and reported together
        assertAll("Testing squares are the correct type",
                //Checking square (7,7) for type START
                () -> assertEquals(Square.SquareType.START, boardTest.getSquare(7,7).getType(),"\nThe expected Square type isn't correct after being reset."),
                //Checking square (7,8) for type NORMAL
                () -> assertEquals(Square.SquareType.NORMAL, boardTest.getSquare(7,8).getType(),"\nThe expected Square type isn't correct after being reset."),
                //Checking square (8,8) for type DOUBLE_LETTER
                () -> assertEquals(Square.SquareType.DOUBLE_LETTER, boardTest.getSquare(8,8).getType(),"\nThe expected Square type isn't correct after being reset.")
        );
    }



    @Test
    @DisplayName("Testing the coordinates i and j for the method getSquare")
    void coordinateInsideCorners()
    {
        // assertAll so that all assertions are run and reported together
        assertAll("Testing the coordinates i and j are inside the Board",

                // Asserts that exception not thrown for 0,0
                () ->assertDoesNotThrow(() -> boardTest.getSquare(0,0), "getSquare threw a InvalidBoardException when the coordinates are (0,0).\n"),
                // Asserts that exception not thrown for 14,14
                () ->assertDoesNotThrow(() -> boardTest.getSquare(14,14), "getSquare threw a InvalidBoardException when the coordinates are (14,14).\n"),
                // Asserts that exception not thrown for 0,14
                () ->assertDoesNotThrow(() -> boardTest.getSquare(0,14), "getSquare threw a InvalidBoardException when the coordinates are (0,14).\n"),
                // Asserts that exception not thrown for 14,0
                () ->assertDoesNotThrow(() -> boardTest.getSquare(14,0), "getSquare threw a InvalidBoardException when the coordinates are (14,0).\n")
        );
    }



    @Test
    @DisplayName("Testing the coordinates i and j are outside the Board")
    void coordinateOutsideCorners()
    {
        // assertAll so that all assertions are run and reported together
        assertAll("Testing the coordinates i and j are inside the Board",

                // Asserts that exception is thrown for -1,-1
                () ->assertThrows(InvalidBoardException.class,() -> boardTest.getSquare(-1,-1), "getSquare threw a InvalidBoardException when the coordinates are (-1,-1).\n"),
                // Asserts that exception is thrown for 15,15
                () ->assertThrows(InvalidBoardException.class,() -> boardTest.getSquare(15,15), "getSquare threw a InvalidBoardException when the coordinates are (15,15).\n"),
                // Asserts that exception is thrown for -1,15
                () ->assertThrows(InvalidBoardException.class,() -> boardTest.getSquare(-1,15), "getSquare threw a InvalidBoardException when the coordinates are (-1,15).\n"),
                // Asserts that exception is thrown for 15,-1
                () ->assertThrows(InvalidBoardException.class,() -> boardTest.getSquare(15,-1), "getSquare threw a InvalidBoardException when the coordinates are (15,-1).\n")
        );
    }



    @Test
    @DisplayName("Testing that Tiles can be placed on the board")
    void testPlaceTile()
    {
        // Creates and places new Tile on the board
        Tile tile = new Tile('A');
        boardTest.placeTile(tile, 7, 7);

        // Asserts that the new Tile has been placed on the Board at 7,7
        assertEquals(tile, boardTest.getSquare(7,7).getTile());
    }



    @Test
    @DisplayName("Testing that a number of Tiles can be placed at a time")
    void testPlaceTiles()
    {
        // Clearing Frame of tiles and adding 3 new set Tiles to it
        playerTest.getPlayerFrame().returnFrame().clear();
        playerTest.getPlayerFrame().addTile(new Tile('A'));
        playerTest.getPlayerFrame().addTile(new Tile('A'));
        playerTest.getPlayerFrame().addTile(new Tile('A'));

        // Creating position and word
        int[] position = {7,7};
        char[] testWord = {'A','A','A'};

        // Places the Tiles with characters from testWord on the given positions
        boardTest.placeTiles(new MoveInfo( playerTest, position, UserInput.Direction.HORIZONTAL, testWord));

        // assertAll so that all assertions are run and reported together
        assertAll("Testing that each of the Tiles have been placed on the Board\n",

                // Asserts that there is a Tile with character 'A' at 7,7
                () -> assertEquals(boardTest.getSquare(7,7).getTile(), new Tile('A'), "Tile has been placed at 7,7\n"),

                // Asserts that there is a Tile with character 'A' at 7,8
                () -> assertEquals(boardTest.getSquare(7,8).getTile(), new Tile('A'), "Tile has been placed at 7,8\n"),

                // Asserts that there is a Tile with character 'A' at 7,9
                () -> assertEquals(boardTest.getSquare(7,9).getTile(), new Tile('A'), "Tile has been placed at 7,7\n")
        );

    }



    @Test
    @DisplayName("Testing validating for when the  Player doesn't has the Tiles in their Frame")
    void testCheckPlayerDoesNotHaveTiles()
    {
        // Clears and places new Tiles into Players Frame
        playerTest.getPlayerFrame().returnFrame().clear();
        playerTest.getPlayerFrame().addTile(new Tile('A'));
        playerTest.getPlayerFrame().addTile(new Tile('B'));
        playerTest.getPlayerFrame().addTile(new Tile('C'));

        // Creates lists of characters that the player doesn't have
        char[] emptyChar = {};
        char[] charPlayerDoesNotHave = {'E','F','G'};
        char[] charPlayerHasSome = {'A', 'E', 'C'};
        char[] charPlayerHasRepeat = {'A', 'A', 'C'};

        // assertAll so that all assertions are run and reported together
        assertAll("Testing that the Player doesn't have Tiles with certain characters in their Frame\n",

                // Asserts that throws for checking an empty list
                () -> assertThrows(InvalidFrameException.class, ()-> boardTest.checkPlayerHasTiles(playerTest, emptyChar), "Cannot check for no characters in Frame\n"),

                // Asserts that false is returned for checking a list of characters not in the Frame
                () -> assertFalse(boardTest.checkPlayerHasTiles(playerTest, charPlayerDoesNotHave),"Player doesn't have the necessary tiles\n"),

                // Asserts that false is returned for checking a list of characters with some not in the Frame
                () -> assertFalse(boardTest.checkPlayerHasTiles(playerTest, charPlayerHasSome),"Player doesn't have the necessary tiles\n"),

                // Asserts that false is returned for checking a list of characters with two of a character that there is only one of
                () -> assertFalse(boardTest.checkPlayerHasTiles(playerTest, charPlayerHasRepeat), "Player doesn't have the necessary tiles\n")
        );
    }



    @Test
    @DisplayName("Testing validating for when the Player has the Tiles in their Frame")
    void testCheckPlayerHasTiles()
    {
        // Clears and places new Tiles into Players Frame
        playerTest.getPlayerFrame().returnFrame().clear();
        playerTest.getPlayerFrame().addTile(new Tile('A'));
        playerTest.getPlayerFrame().addTile(new Tile('B'));
        playerTest.getPlayerFrame().addTile(new Tile('C'));
        playerTest.getPlayerFrame().addTile(new Tile('C'));
        playerTest.getPlayerFrame().addTile(new Tile('C'));
        playerTest.getPlayerFrame().addTile(new Tile('C'));
        playerTest.getPlayerFrame().addTile(new Tile('C'));


        // Creates list of characters that the player has
        char[] charPlayerHas = {'A', 'B', 'C' ,'C','C' ,'C','C' };

        // Asserts that an exception is not thrown for a list of characters in the Frame
        assertTrue(boardTest.checkPlayerHasTiles(playerTest, charPlayerHas), "Player has the necessary tiles\n");
    }



    @Test
    @DisplayName("Testing validation for lists of invalid co-ordinates to place Tiles on the Board")
    void testCheckInvalidValidPosition()
    {
        // Create invalid positions
        int[] invalidLargePosition = {15,15};
        int[] invalidNegativePosition = {-1,-1};
        int[] invalidNoPositions = {};
        int[] invalidMoreThanTwoPosition = {1,1,2};

        // assertAll so that all assertions are run and reported together
        assertAll("Testing that the player cannot input invalid co-ordinates for their move\n",

                //  Asserts that returns false for invalid position at 15,15
                () -> assertFalse(Board.checkValidPosition(invalidLargePosition), "Position not on board\n"),

                //  Asserts that returns false for invalid position at -1,-1
                () -> assertFalse(Board.checkValidPosition(invalidNegativePosition), "Position not on board\n"),

                //  Asserts that an exception is thrown for an empty position
                () -> assertThrows(InvalidBoardException.class, () -> Board.checkValidPosition(invalidNoPositions), "Invalid number of positions entered\n"),

                //  Asserts that an exception is thrown for a position with more than 2 ints
                () -> assertThrows(InvalidBoardException.class, () -> Board.checkValidPosition(invalidMoreThanTwoPosition), "Invalid number of positions entered\n")
        );
    }



    @Test
    @DisplayName("Testing validation for lists of valid co-ordinates to place Tiles on the Board")
    void testCheckValidValidPosition()
    {
        // Creates a 2d array of valid positions
        int[][] validPositions = {{14,14}, {0,14}, {0,0}};

        // assertAll so that all assertions are run and reported together
        assertAll("Testing that the player input valid co-ordinates for their move\n",
            // Asserts that true is returned for a valid position
            () -> assertTrue(Board.checkValidPosition(validPositions[0]), "checkValidPosition did not return true for tiles within range of board\n"),
            // Asserts that true is returned for a valid position
            () -> assertTrue(Board.checkValidPosition(validPositions[1]), "checkValidPosition did not return true for tiles within range of board\n"),
            // Asserts that true is returned for a valid position
            () -> assertTrue(Board.checkValidPosition(validPositions[2]), "checkValidPosition did not return true for tiles within range of board\n")
        );
    }

    //TODO checkWordConnects

    @Test
    @DisplayName("Board Test checkValidMove")
    void boardTestCheckValidMove(){

        playerTest.getPlayerFrame().returnFrame().clear();
        playerTest.getPlayerFrame().addTile(new Tile('C'));
        playerTest.getPlayerFrame().addTile(new Tile('A'));
        playerTest.getPlayerFrame().addTile(new Tile('T'));
        playerTest.getPlayerFrame().addTile(new Tile('D'));
        playerTest.getPlayerFrame().addTile(new Tile('O'));
        playerTest.getPlayerFrame().addTile(new Tile('G'));
        playerTest.getPlayerFrame().addTile(new Tile('S'));

        // assertAll so that all assertions are run and reported together
        assertAll("Testing checkValidMove for valid input\n",
                //Assert True for valid input
                ()-> assertTrue(boardTest.checkValidMove(new MoveInfo(playerTest, new int[]{7,7}, UserInput.Direction.VERTICAL, new char[]{'C','A','T'})),"Valid Move did not return True for valid input\n"),
                //Assert True for valid input
                ()-> assertTrue(boardTest.checkValidMove(new MoveInfo(playerTest, new int[]{7,7}, UserInput.Direction.HORIZONTAL, new char[]{'D','O','G', 'S'})),"Valid Move did not return True for valid input\n")
        );
    }

    @Test
    @DisplayName("Board Test checkValidMove Invalid Position")
    void boardTestCheckValidMoveInvalidPosition() {

        playerTest.getPlayerFrame().returnFrame().clear();
        playerTest.getPlayerFrame().addTile(new Tile('C'));
        playerTest.getPlayerFrame().addTile(new Tile('A'));
        playerTest.getPlayerFrame().addTile(new Tile('T'));

        // assertAll so that all assertions are run and reported together
        assertAll("Testing checkValidMove for invalid Position\n",
                //Assert throws for invalid position
                () -> assertThrows(InvalidWordException.class, ()-> boardTest.checkValidMove(new MoveInfo(playerTest, new int[]{-1, -1}, UserInput.Direction.VERTICAL, new char[]{'C', 'A', 'T'})), "checkValidMove did not throw InvalidWordException for {-1,-1}\n"),
                //Assert throws for invalid position
                () -> assertThrows(InvalidWordException.class, ()-> boardTest.checkValidMove(new MoveInfo(playerTest, new int[]{15, 15}, UserInput.Direction.VERTICAL, new char[]{'C', 'A', 'T'})), "checkValidMove did not throw InvalidWordException for {15,15}\n"),
                //Assert throws for invalid position
                () -> assertThrows(InvalidBoardException.class, ()-> boardTest.checkValidMove(new MoveInfo(playerTest, new int[]{7}, UserInput.Direction.VERTICAL, new char[]{'C', 'A', 'T'})), "checkValidMove did not throw InvalidBoardException for invalid length\n"),
                //Assert throws for invalid position
                () -> assertThrows(InvalidBoardException.class, ()-> boardTest.checkValidMove(new MoveInfo(playerTest, new int[]{7,7,7}, UserInput.Direction.VERTICAL, new char[]{'C', 'A', 'T'})), "checkValidMove did not throw InvalidBoardException for invalid length\n")
        );

    }

    @Test
    @DisplayName("Board Test checkValidMove Invalid Position")
    void boardTestCheckValidMoveInvalidTiles() {

        playerTest.getPlayerFrame().returnFrame().clear();
        playerTest.getPlayerFrame().addTile(new Tile('C'));
        playerTest.getPlayerFrame().addTile(new Tile('A'));
        playerTest.getPlayerFrame().addTile(new Tile('T'));


        // assertAll so that all assertions are run and reported together
        assertAll("Testing checkValidMove for invalid Tiles\n",
                //Assert throws for invalid Tile
                () -> assertThrows(InvalidMoveInfoException.class, ()-> boardTest.checkValidMove(new MoveInfo(playerTest, new int[]{7,7}, UserInput.Direction.VERTICAL, new char[]{'C', 'A', 'T', 'T'})), "checkValidMove did not throw InvalidMoveInfoException for double of a letter player has one of\n"),
                //Assert throws for invalid Tile
                () -> assertThrows(InvalidWordException.class, ()-> boardTest.checkValidMove(new MoveInfo(playerTest, new int[]{7,7}, UserInput.Direction.VERTICAL, new char[]{'C', 'A', 'T', 'T', 'A', 'C', 'C', 'A', 'T', 'C', 'A', 'T', 'C', 'A', 'T', 'A'})), "checkValidMove did not throw InvalidWordException for word of length 16\n"),
                //Assert throws for invalid Tile
                () -> assertThrows(InvalidMoveInfoException.class, ()-> boardTest.checkValidMove(new MoveInfo(playerTest, new int[]{7,7}, UserInput.Direction.VERTICAL, new char[]{'C', 'A', 'P'})), "checkValidMove did not throw InvalidMoveInfoException for Tile the player does not have\n"),
                //Assert throws for invalid Tile
                () -> assertThrows(InvalidWordException.class, ()-> boardTest.checkValidMove(new MoveInfo(playerTest, new int[]{7,7}, UserInput.Direction.VERTICAL, new char[]{})), "checkValidMove did not throw InvalidBoardException for empty word\n"),
                //Assert throws for invalid Tile
                () -> assertThrows(InvalidWordException.class, ()-> boardTest.checkValidMove(new MoveInfo(playerTest, new int[]{7,7}, UserInput.Direction.VERTICAL, new char[]{'5', 'A', 'T'})), "checkValidMove did not throw InvalidBoardException for number Tile\n")
        );
    }

    @Test
    @DisplayName("Board Test checkValidMove Invalid First Position")
    void boardTestCheckValidMoveFirstPos() {

        playerTest.getPlayerFrame().returnFrame().clear();
        playerTest.getPlayerFrame().addTile(new Tile('C'));
        playerTest.getPlayerFrame().addTile(new Tile('A'));
        playerTest.getPlayerFrame().addTile(new Tile('T'));

        // assertAll so that all assertions are run and reported together
        assertAll("Testing checkValidMove for First Position\n",
                //Assert throws for invalid position
                () -> assertThrows(InvalidMoveInfoException.class, ()-> boardTest.checkValidMove(new MoveInfo(playerTest, new int[]{0,0}, UserInput.Direction.VERTICAL, new char[]{'C', 'A', 'T'})), "checkValidMove did not throw InvalidMoveInfoException for a first move not on the start square\n"),
                //Assert return true for valid start position
                () -> assertTrue(boardTest.checkValidMove(new MoveInfo(playerTest, new int[]{7,7}, UserInput.Direction.VERTICAL, new char[]{'C', 'A', 'T'})), "checkValidMove did not return true for starting on the start square\n"),
                //Assert return true for valid start position
                () -> assertTrue(boardTest.checkValidMove(new MoveInfo(playerTest, new int[]{6,7}, UserInput.Direction.VERTICAL, new char[]{'C', 'A', 'T'})), "checkValidMove did not return true for starting before Vertically the start square\n"),
                //Assert return true for valid start position
                () -> assertTrue(boardTest.checkValidMove(new MoveInfo(playerTest, new int[]{7,6}, UserInput.Direction.HORIZONTAL, new char[]{'C', 'A', 'T'})), "checkValidMove did not return true for starting before Horizontally the start square\n")
        );
    }

    @Test
    @DisplayName("Testing combination of Words with placeTiles")
    void boardTestPlaceTiles(){

        //Set Up Player
        playerTest.getPlayerFrame().returnFrame().clear();
        playerTest.getPlayerFrame().addTile(new Tile('A'));
        playerTest.getPlayerFrame().addTile(new Tile('T'));
        playerTest.getPlayerFrame().addTile(new Tile('M'));
        playerTest.getPlayerFrame().addTile(new Tile('N'));
        playerTest.getPlayerFrame().addTile(new Tile('Y'));
        playerTest.getPlayerFrame().addTile(new Tile('S'));
        playerTest.getPlayerFrame().addTile(new Tile('I'));

        //Board State after the move
        String boardPrintTest = "\t\t\t\t\t\t\t\tScrabble Board\n" +
                "  | 1  | 2  | 3  | 4  | 5  | 6  | 7  | 8  | 9  | 10 | 11 | 12 | 13 | 14 | 15 |\n" +
                "______________________________________________________________________________\n" +
                " 1| 3W |    |    | 2L |    |    |    | 3W |    |    |    | 2L |    |    | 3W |\n" +
                "______________________________________________________________________________\n" +
                " 2|    | 2W |    |    |    | 3L |    |    |    | 3L |    |    |    | 2W |    |\n" +
                "______________________________________________________________________________\n" +
                " 3|    |    | 2W |    |    |    | 2L |    | 2L |    |    |    | 2W |    |    |\n" +
                "______________________________________________________________________________\n" +
                " 4| 2L |    |    | 2W |    |    |    | 2L |    |    |    | 2W |    |    | 2L |\n" +
                "______________________________________________________________________________\n" +
                " 5|    |    |    |    | 2W |    |    |    |    |    | 2W |    |    |    |    |\n" +
                "______________________________________________________________________________\n" +
                " 6|    | 3L |    |    |    | 3L |    |    |    | 3L |    |    |    | 3L |    |\n" +
                "______________________________________________________________________________\n" +
                " 7|    |    | 2L |    |    |    | 2L |M  3| 2L |    |    |    | 2L |    |    |\n" +
                "______________________________________________________________________________\n" +
                " 8| 3W |    |    | 2L |    |    |    |A  1|T  1|    |    | 2L |    |    | 3W |\n" +
                "______________________________________________________________________________\n" +
                " 9|    |    | 2L |    |    |    | 2L |N  1| 2L |    |    |    | 2L |    |    |\n" +
                "______________________________________________________________________________\n" +
                "10|    | 3L |    |    |    | 3L |    |Y  4|    | 3L |    |    |    | 3L |    |\n" +
                "______________________________________________________________________________\n" +
                "11|    |    |    |    | 2W |    |I  1|S  1|    |    | 2W |    |    |    |    |\n" +
                "______________________________________________________________________________\n" +
                "12| 2L |    |    | 2W |    |    |    | 2L |    |    |    | 2W |    |    | 2L |\n" +
                "______________________________________________________________________________\n" +
                "13|    |    | 2W |    |    |    | 2L |    | 2L |    |    |    | 2W |    |    |\n" +
                "______________________________________________________________________________\n" +
                "14|    | 2W |    |    |    | 3L |    |    |    | 3L |    |    |    | 2W |    |\n" +
                "______________________________________________________________________________\n" +
                "15| 3W |    |    | 2L |    |    |    | 3W |    |    |    | 2L |    |    | 3W |";

        //Move Set Up
        MoveInfo move1 = new MoveInfo(playerTest, new int[]{7,7}, UserInput.Direction.HORIZONTAL, new char[]{'A', 'T'});
        MoveInfo move2 = new MoveInfo(playerTest, new int[]{6,7}, UserInput.Direction.VERTICAL, new char[]{'M', 'A', 'N', 'Y'});
        MoveInfo move3 = new MoveInfo(playerTest, new int[]{10,6}, UserInput.Direction.HORIZONTAL, new char[]{'I', 'S'});

        // assertAll so that all assertions are run and reported together
        assertAll("Testing placeTiles for combination of Words\n",
                //Assert does not throw for move
                () -> assertDoesNotThrow(()-> boardTest.placeTiles(move1), "placeTiles throw Exception for start move\n"),
                //Assert does not throw for move
                () -> assertDoesNotThrow(()-> boardTest.placeTiles(move2), "placeTiles throw Exception for crossing move\n"),
                //Assert does not throw for move
                () -> assertDoesNotThrow(()-> boardTest.placeTiles(move3), "placeTiles throw Exception for touching move\n"),
                //Assert returns correct string
                () -> assertEquals(boardPrintTest, boardTest.toString(), "The Board has the incorrect Board State after moves\n")
        );
    }

    @Test
    @DisplayName("Testing calculateScore with combination of Words")
    void boardTestCalculateScore(){

        //Set Up Player
        playerTest.getPlayerFrame().returnFrame().clear();
        playerTest.getPlayerFrame().addTile(new Tile('A'));
        playerTest.getPlayerFrame().addTile(new Tile('T'));
        playerTest.getPlayerFrame().addTile(new Tile('M'));
        playerTest.getPlayerFrame().addTile(new Tile('N'));
        playerTest.getPlayerFrame().addTile(new Tile('Y'));
        playerTest.getPlayerFrame().addTile(new Tile('S'));
        playerTest.getPlayerFrame().addTile(new Tile('I'));

        //Move Set Up
        MoveInfo move1 = new MoveInfo(playerTest, new int[]{7,7}, UserInput.Direction.HORIZONTAL, new char[]{'A', 'T'});
        MoveInfo move2 = new MoveInfo(playerTest, new int[]{6,7}, UserInput.Direction.VERTICAL, new char[]{'M', 'A', 'N', 'Y'});
        MoveInfo move3 = new MoveInfo(playerTest, new int[]{10,6}, UserInput.Direction.HORIZONTAL, new char[]{'I', 'S'});

        //Place and calculate score
        boardTest.placeTiles(move1);
        boardTest.calculateScore(move1);
        boardTest.setWordSquaresNormal(move1.getPrimaryWord());
        boardTest.placeTiles(move2);
        boardTest.calculateScore(move2);
        boardTest.setWordSquaresNormal(move2.getPrimaryWord());
        boardTest.placeTiles(move3);
        boardTest.calculateScore(move3);
        boardTest.setWordSquaresNormal(move3.getPrimaryWord());

        // assertAll so that all assertions are run and reported together
        assertAll("Testing calculateScore with combination of Words\n",
                //Assert returns correct score
                () -> assertEquals(4, move1.getMoveScore(), "start move had the incorrect score\n"),
                //Assert returns correct score
                () -> assertEquals(9, move2.getMoveScore(), "crossing move had the incorrect score\n"),
                //Assert returns correct score
                () -> assertEquals(12, move3.getMoveScore(), "touch move had the incorrect score\n")
        );
    }

    @Test
    @DisplayName("Testing calculateScore with Bingo")
    void boardTestCalculateScoreBingo(){

        //Set Up Player
        playerTest.getPlayerFrame().returnFrame().clear();
        playerTest.getPlayerFrame().addTile(new Tile('A'));
        playerTest.getPlayerFrame().addTile(new Tile('A'));
        playerTest.getPlayerFrame().addTile(new Tile('A'));
        playerTest.getPlayerFrame().addTile(new Tile('A'));
        playerTest.getPlayerFrame().addTile(new Tile('A'));
        playerTest.getPlayerFrame().addTile(new Tile('A'));
        playerTest.getPlayerFrame().addTile(new Tile('A'));

        //Move Set Up
        MoveInfo move = new MoveInfo(playerTest, new int[]{7,4}, UserInput.Direction.HORIZONTAL, new char[]{'A', 'A','A','A','A','A','A'});

        //Place and calculate score
        boardTest.placeTiles(move);
        boardTest.calculateScore(move);

        //Assert the Bingo move score
        assertEquals(64, move.getMoveScore(), "Bingo move had the incorrect score\n");
    }


    @Test
    @DisplayName("Board Test setWordSquaresNormal ")
    void boardTestSetWordSquaresNormal(){

        //Set Up Player
        playerTest.getPlayerFrame().returnFrame().clear();
        playerTest.getPlayerFrame().addTile(new Tile('A'));
        playerTest.getPlayerFrame().addTile(new Tile('A'));
        playerTest.getPlayerFrame().addTile(new Tile('A'));
        playerTest.getPlayerFrame().addTile(new Tile('A'));
        playerTest.getPlayerFrame().addTile(new Tile('A'));
        playerTest.getPlayerFrame().addTile(new Tile('A'));
        playerTest.getPlayerFrame().addTile(new Tile('A'));

        //Move Set Up
        MoveInfo move = new MoveInfo(playerTest, new int[]{7,7}, UserInput.Direction.HORIZONTAL, new char[]{'A', 'A','A','A','A','A','A'});

        //Place and calculate score
        boardTest.placeTiles(move);
        boardTest.calculateScore(move);
        boardTest.setWordSquaresNormal(move.getPrimaryWord());

        // assertAll so that all assertions are run and reported together
        assertAll("Testing calculateScore with combination of Words\n",
                //Assert Square is set to Normal
                () -> assertEquals(Square.SquareType.NORMAL, boardTest.getSquare(7,7).getType(), "Square was not set to Normal\n"),
                //Assert Square is set to Normal
                () -> assertEquals(Square.SquareType.NORMAL, boardTest.getSquare(7,8).getType(), "Square was not set to Normal\n"),
                //Assert Square is set to Normal
                () -> assertEquals(Square.SquareType.NORMAL, boardTest.getSquare(7,9).getType(), "Square was not set to Normal\n"),
                //Assert Square is set to Normal
                () -> assertEquals(Square.SquareType.NORMAL, boardTest.getSquare(7,10).getType(), "Square was not set to Normal\n"),
                //Assert Square is set to Normal
                () -> assertEquals(Square.SquareType.NORMAL, boardTest.getSquare(7,11).getType(), "Square was not set to Normal\n"),
                //Assert Square is set to Normal
                () -> assertEquals(Square.SquareType.NORMAL, boardTest.getSquare(7,12).getType(), "Square was not set to Normal\n"),
                //Assert Square is set to Normal
                () -> assertEquals(Square.SquareType.NORMAL, boardTest.getSquare(7,13).getType(), "Square was not set to Normal\n")
        );
    }

    @Test
    @DisplayName("Board Test removeMove ")
    void boardTestRemoveMove(){

        //Set Up Player
        playerTest.getPlayerFrame().returnFrame().clear();
        playerTest.getPlayerFrame().addTile(new Tile('C'));
        playerTest.getPlayerFrame().addTile(new Tile('A'));
        playerTest.getPlayerFrame().addTile(new Tile('T'));


        //Move Set Up
        MoveInfo move = new MoveInfo(playerTest, new int[]{7,7}, UserInput.Direction.HORIZONTAL, new char[]{'C', 'A','T'});

        //Place and remove
        boardTest.placeTiles(move);
        boardTest.removeMove(move);

        // assertAll so that all assertions are run and reported together
        assertAll("Testing calculateScore with combination of Words\n",
                //Assert Square is set to Empty
                () -> assertTrue(boardTest.getSquare(7,7).isEmpty(), "Square was not set to Empty\n"),
                //Assert Square is set to Empty
                () -> assertTrue(boardTest.getSquare(7,8).isEmpty(), "Square was not set to Empty\n"),
                //Assert Square is set to Empty
                () -> assertTrue(boardTest.getSquare(7,9).isEmpty(), "Square was not set to Empty\n")
        );
    }


}