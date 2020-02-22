package scrabble;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import scrabble.exceptions.InvalidBoardException;
import static org.junit.jupiter.api.Assertions.*;


public class BoardTest {

    private Board boardTest;
    private Pool poolTest;
    private Player playerTest;
    @BeforeEach
    void setup()
    {
        poolTest = new Pool();
        boardTest = new Board();
        playerTest = new Player("Test" , poolTest);
    }

    // BOARD TOSTRING TESTS

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


    //GETSQUARE TESTS

    @Test
    @DisplayName("Testing the coordinates i and j for the method getSquare")
    void coordinateInsideCorners()
    {
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
    void testPlaceTiles(){

        char[] testWord = new char[7];

        testWord[0] = playerTest.getPlayerFrame().getTile(0).getCharacter();
        testWord[1] = playerTest.getPlayerFrame().getTile(0).getCharacter();
        testWord[2] = playerTest.getPlayerFrame().getTile(0).getCharacter();

        Tile tile1 = playerTest.getPlayerFrame().getTile(0);

        int[][] positions = {{7,7},{7,8},{7,9}};

        boardTest.placeTiles(playerTest, testWord, positions);

        assertAll("Testing that each of the Tiles have been placed on the Board\n",
                () -> assertEquals(boardTest.getSquare(7,7).getTile(), tile1));
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

        assertAll("Testing that the Player doesn't have Tiles with certain characters in their Frame\n",

                // Asserts that an exception is thrown for checking an empty list
                () -> assertThrows(IllegalArgumentException.class,() -> boardTest.checkPlayerHasTiles(playerTest, emptyChar), "Cannot check for no characters in Frame\n"),
                // Asserts that an exception is thrown for checking a list of characters not in the Frame
                () -> assertThrows(InvalidBoardException.class,() -> boardTest.checkPlayerHasTiles(playerTest, charPlayerDoesNotHave),"Player doesn't have the necessary tiles\n"),
                // Asserts that an exception is thrown for checking a list of characters with some not in the Frame
                () -> assertThrows(InvalidBoardException.class,() -> boardTest.checkPlayerHasTiles(playerTest, charPlayerHasSome),"Player doesn't have the necessary tiles\n")
        );
    }



    @Test
    @DisplayName("Testing validating for when the Player has the Tiles in their Frame")
    void testCheckPlayerHasTiles()
    {
        // Clears and places new Tiles into Players Frame
        //playerTest.getPlayerFrame().returnFrame().clear();
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
        assertDoesNotThrow(() -> boardTest.checkPlayerHasTiles(playerTest, charPlayerHas), "Player has the necessary tiles\n");
    }



    @Test
    @DisplayName("Testing validation for lists of invalid co-ordinates to place Tiles on the Board")
    void testCheckInvalidValidPosition()
    {
        // Creates a 2d array of invalid positions
        int[][] invalidLargePositions = {{15,15}};
        int[][] invalidNegativePositions = {{-1,-1}};
        int[][] invalidMixedPositions = {{3,2}, {15,15}};
        int[][] invalidNoPositions = {};
        int[][] invalidMoreThanSevenPositions = {{1,1}, {1,2} , {1,3}, {1,4}, {1,5}, {1,6}, {1,7}, {1,8}};

        assertAll("Testing that the player cannot input invalid co-ordinates for their move\n",

                //  Asserts that an exception is thrown for singular invalid position at 15,15
                () -> assertThrows(InvalidBoardException.class, () -> boardTest.checkValidPosition(invalidLargePositions), "Position not on board\n"),

                //  Asserts that an exception is thrown for singular invalid position at -1,-1
                () -> assertThrows(InvalidBoardException.class, () -> boardTest.checkValidPosition(invalidNegativePositions), "Position not on board\n"),

                //  Asserts that an exception is thrown for a mix of valid and invalid positions
                () -> assertThrows(InvalidBoardException.class, () -> boardTest.checkValidPosition(invalidMixedPositions), "Position not on board\n"),

                //  Asserts that an exception is thrown for an empty list positions
                () -> assertThrows(InvalidBoardException.class, () -> boardTest.checkValidPosition(invalidNoPositions), "Invalid number of positions entered\n"),

                //  Asserts that an exception is thrown for a list of more than 7 positions
                () -> assertThrows(InvalidBoardException.class, () -> boardTest.checkValidPosition(invalidMoreThanSevenPositions), "Invalid number of positions entered\n")
        );
    }



    @Test
    @DisplayName("Testing validation for lists of valid co-ordinates to place Tiles on the Board")
    void testCheckValidValidPosition()
    {
        // Creates a 2d array of valid positions
        int[][] validPositions = {{14,14}, {13,14}, {0,0}};

        // Asserts that an exception is not thrown for a list of valid positions
        assertDoesNotThrow(() -> boardTest.checkValidPosition(validPositions), "checkValidPosition does not throw exception for tiles within range of board\n");
    }



    @Test
    @DisplayName("Testing validation for invalid number of Tiles to place at a time")
    void testInvalidWordLength()
    {
        // Creates arrays of characters of invalid sizes
        char[] emptyWord = {};
        char[] invalidSizeWord = {'A','A','A','A','A','A','A','A'};

        assertAll("Testing that the player cannot request to put down a word outside of the range of their Frame\n",

                // Asserts that an exception is thrown for a list of no characters
                () -> assertThrows(InvalidBoardException.class, () -> boardTest.checkWordLength(emptyWord), "Word must be longer than 0 tiles\n"),

                // Asserts that an exception is thrown for a list of more than 7 characters
                () -> assertThrows(InvalidBoardException.class, () -> boardTest.checkWordLength(invalidSizeWord), "Cannot place more than 7 tiles\n")
        );
    }



    @Test
    @DisplayName("Testing validation for valid number of Tiles to place at a time")
    void testValidWordTest()
    {
        // Creates array of characters of a valid size
        char[] validSizeWord = {'A','A','A','A'};

        // Asserts that an exception is not thrown for an array of valid size
        assertDoesNotThrow(() -> boardTest.checkWordLength(validSizeWord), "checkWordLength does not throw exception because valid size of word\n");
    }



    @Test
    @DisplayName("Testing validation that Players cannot place Tiles on positions already containing Tiles")
    void testCheckPositionContainsTile()
    {
        // PLaces a tile on the Board at 7,7
        boardTest.placeTile(new Tile('A'), 7,7);

        // Creates 2d arrays of positions containing a position which already has a Tile on it
        int[][] positionOfTile = {{7,7}};
        int[][] positionsContainingTile = {{8,9},{6,2}, {7,7}};

        assertAll("Testing that player cannot put Tiles on Board positions already containing Tiles",

                // Asserts that an exception is thrown for the position 7,7
                () -> assertThrows(InvalidBoardException.class, () -> boardTest.checkPositionContainsTile(positionOfTile), "Position already contains a Tile\n"),

                // Asserts that an exception is thrown for an array of positions containing 7,7
                () -> assertThrows(InvalidBoardException.class, () -> boardTest.checkPositionContainsTile(positionsContainingTile), "Position already contains a Tile\n")
        );
    }



    @Test
    @DisplayName("Testing validation that Player can place Tiles on positions that don't contain Tiles")
    void testPositionsNotContainingTiles()
    {
        // Creates a list position that have no tile
        int[][] positionOfNoTile = {{8,8}, {9,9}};

        // Asserts that no exception is thrown for positions that have no Tiles
        assertDoesNotThrow(() -> boardTest.checkPositionContainsTile(positionOfNoTile), "Does not throw exception for positions without Tiles\n");
    }



    @Test
    @DisplayName("Testing validation that Tiles can be placed in a line")
    void testCheckPositionsNotInLine(){

        // Places a tile on the Board at 7,7
        boardTest.placeTile(new Tile('A'), 7,7);

        // Creates 2d arrays of positions that are in a line or form a line with a Tile on the Board
        int[][] verticalPositions = {{8,8}, {9,8}, {10,8}};
        int[][] horizontalPositions = {{8,8}, {8,9}, {8,10}};
        int[][] verticalPositionsWithTiles ={{6,7},{8,7},{9,7}};
        int[][] horizontalPositionsWithTiles = {{7,6},{7,8},{7,9}};

        assertAll("Testing that Players can only place Tiles in a line unless Tile on Board is part of the line\n",

                // Asserts that an exception is not thrown for a line of vertical Tiles
                () -> assertDoesNotThrow(() -> boardTest.checkPositionLine(verticalPositions), "Positions are in a valid line on the board\n"),

                // Asserts that an exception is not thrown for a line of horizontal Tiles
                () -> assertDoesNotThrow(() -> boardTest.checkPositionLine(horizontalPositions), "Positions are in a valid line on the board\n"),

                // Asserts that an exception is not thrown for a line of vertical Tiles due to a Tile on the Board
                () -> assertDoesNotThrow(() -> boardTest.checkPositionLine(verticalPositionsWithTiles), "Positions are in a valid line on the board\n"),

                // Asserts that an exception is not thrown for a line of horizontal Tiles due to a Tile on the Board
                () -> assertDoesNotThrow(() -> boardTest.checkPositionLine(horizontalPositionsWithTiles), "Positions are in a valid line on the board\n")
        );
    }



    @Test
    @DisplayName("Testing validation that Tiles cannot be placed in a non line")
    void testCheckPositionsInALine()
    {
        // Creates arrays of positions not in a line
        int[][] invalidVerticalPosition ={{8,8}, {10,8}, {11,8}};
        int[][] invalidHorizontalPosition = {{8,8}, {8,10}, {8,11}};

        assertAll("Testing that Players can only place Tiles in a line unless Tile on Board is part of the line\n",

                // Asserts that an exception is thrown for positions in a invalid vertical line
                () -> assertThrows(InvalidBoardException.class, () -> boardTest.checkPositionLine(invalidVerticalPosition), "Tiles are not in a line on the board\n"),

                // Asserts that an exception is thrown for positions in a invalid horizontal line
                () -> assertThrows(InvalidBoardException.class, () -> boardTest.checkPositionLine(invalidHorizontalPosition), "Tiles are not in a line on the board\n")
        );
    }



    @Test
    @DisplayName("Testing that a list of tiles can only be placed if it connects to a tile on the board, except the first move")
    void testCheckWordsConnectForEdges()
    {
    }
}