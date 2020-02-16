package scrabble;

import scrabble.exceptions.InvalidTileException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;


class TileTest {

    // Variables for testing
    private ArrayList<Tile> testTiles = new ArrayList<>();
    private char[] validChars = {'B','G', 'X', ' '};

    @BeforeEach
    void setUp() throws InvalidTileException{
        // Clears the Tiles from previous tests
        testTiles.clear();

        // Refills the array of test Tiles
        for (char validChar : validChars) {
            testTiles.add(new Tile(validChar));
        }
    }


    @Test
    @DisplayName("Tile Test for Invalid Chars passed as Arguments for Tile Constructor")
    void tileTestInvalidCharsConstructor(){
        // Variables for argument testing
        char numberChar = '5', symbolChar = '!';

        // assertAll so that all assertions are run and reported together
        assertAll("Testing that the constructor throw  InvalidTileException\n",
                // Assert that if a digit is passed as an argument to the tile constructor an InvalidTileException is thrown
                () -> assertThrows(InvalidTileException.class, () -> new Tile(numberChar), "The Tile constructor didn't throw an InvalidTileException when a digit was passed as the char argument.\n"),
                // Assert that if a symbol is passed as an argument to the tile constructor an InvalidTileException is thrown
                () -> assertThrows(InvalidTileException.class, () -> new Tile(symbolChar), "The Tile constructor didn't throw an InvalidTileException when a symbol was passed as the char argument.\n")
        );

    }


    @Test
    @DisplayName("Tile Test for Tile Values Returned by getValue")
    void tileTestValues(){

        // assertAll so that all assertions are run and reported together
        assertAll("Testing that the getValue returns the correct value for a Tile\n",
                // Assert that testTiles for index 0 which has a character of B will have the value of 3
                () -> assertEquals(3, testTiles.get(0).getValue(), "The tile constructed with the char 'B' did not return the value 3\n"),
                // Assert that testTiles for index 1 which has a character of G will have the value of 2
                () -> assertEquals(2, testTiles.get(1).getValue(), "The tile constructed with the char 'G' did not return the value 2\n"),
                // Assert that testTiles for index 2 which has a character of X will have the value of 8
                () -> assertEquals(8, testTiles.get(2).getValue(), "The tile constructed with the char 'x' did not return the value 8\n")
        );
    }


    @Test
    @DisplayName("Tile Test for Valid Chars passed as Arguments for Tile Constructor")
    void tileTestValidCharsConstructor(){

        // assertAll so that all assertions are run and reported together
        assertAll("Testing the constructor with valid characters\n",
                // Assert that if validChars[0] is passed as an argument to the tile constructor no exception is thrown
                () -> assertDoesNotThrow(() -> new Tile(validChars[0]), "The Tile constructor throw an Exception when " + validChars[0] + " was passed as the char argument.\n"),
                // Assert that if validChars[1] is passed as an argument to the tile constructor no exception is thrown
                () -> assertDoesNotThrow(() -> new Tile(validChars[1]), "The Tile constructor throw an Exception when " + validChars[1] + " was passed as the char argument.\n"),
                // Assert that if validChars[2] is passed as an argument to the tile constructor no exception is thrown
                () -> assertDoesNotThrow(() -> new Tile(validChars[2]), "The Tile constructor throw an Exception when " + validChars[2] + " was passed as the char argument.\n"),
                // Assert that if validChars[3] is passed as an argument to the tile constructor no exception is thrown
                () -> assertDoesNotThrow(() -> new Tile(validChars[3]), "The Tile constructor throw an Exception when " + validChars[3] + " was passed as the char argument.\n")
        );
    }


    @Test
    @DisplayName("Tile Test for getCharacter")
    void tileTestGetCharacter(){

        // assertAll so that all assertions are run and reported together
        assertAll("Testing that the getCharacter returns the correct char for a Tile\n",
                // Assert that testTiles 0 has a character of validChars[0]
                () -> assertEquals(validChars[0], testTiles.get(0).getCharacter(), "The tile constructed with the char '" + validChars[0] + "' did not return the same char.\n"),
                // Assert that testTiles of index 1 has the same character of validChars[1]
                () -> assertEquals(validChars[1], testTiles.get(1).getCharacter(), "The tile constructed with the char '" + validChars[1] + "' did not return the same char.\n"),
                // Assert that testTiles of index 2 has the same character of validChars[2]
                () -> assertEquals(validChars[2], testTiles.get(2).getCharacter(), "The tile constructed with the char '" + validChars[2] + "' did not return the same char.\n"),
                // Assert that testTiles of index 3 has the same character of validChars[3]
                () -> assertEquals(validChars[3], testTiles.get(3).getCharacter(), "The tile constructed with the char '" + validChars[3] + "' did not return the same char.\n")
        );
    }


    @Test
    @DisplayName("Tile Test for Tile toString Method")
    void tileTestToString(){

        // assertAll so that all assertions are run and reported together
        assertAll("Testing that the toString returns the correct string for a Tile\n",
                // Assert that testTiles for index 0 returns the correct string
                () -> assertEquals("Tile: Char: B Value: 3", testTiles.get(0).toString(), "The tile toString returned the wrong string.\n"),
                // Assert that testTiles for index 3 returns the correct string
                () -> assertEquals("Tile: Char:   Value: 0", testTiles.get(3).toString(), "The tile toString returned the wrong string.\n")
        );
    }


    @Test
    @DisplayName("Tile Test for setCharacter on a blank tile")
    void tileTestSetCharacter(){

        // Use setCharacter on blank Tile
        testTiles.get(3).setCharacter('P');

        // assertAll so that all assertions are run and reported together
        assertAll("Testing that the setCharacter returns the correct value for blank Tile\n",
                // Assert that testTiles for index 0 returns the correct string
                () -> assertEquals('P', testTiles.get(3).getCharacter(), "The tile did not change to the correct character.\n"),
                // Assert that testTiles for index 3 returns the correct string
                () -> assertEquals(0, testTiles.get(3).getValue(), "The tile value did not stay equal to zero.\n")
        );
    }


    @Test
    @DisplayName("Tile Test to see if setCharacter throws InvalidTileException with a non blank Tile")
    void tileTestSetCharacterNonBlankTile(){

        // Assert that if a non blank Tile uses setCharacter an InvalidTileException is thrown
        assertThrows(InvalidTileException.class, () -> testTiles.get(2).setCharacter('P'), "The Tile constructor didn't throw an InvalidTileException when a digit was passed as the char argument.\n");

    }

    @Test
    @DisplayName("Tile Test for compareTo on Tile")
    void tileTestCompareTo(){

        Tile G = new Tile('G');

        // assertAll so that all assertions are run and reported together
        assertAll("Testing that the compareTo returns the correct value\n",
                //Assert that compareTo will return 1 when B is compared to testTiles 0
                ()-> assertEquals(1, G.compareTo(testTiles.get(0)),"The Tile compareTo did not return the correct value\n"),
                //Assert that compareTo will return 0 when G is compared to testTiles 1
                ()-> assertEquals(0, G.compareTo(testTiles.get(1)),"The Tile compareTo did not return the correct value\n"),
                //Assert that compareTo will return -1 when G is compared to testTiles 2
                ()-> assertEquals(-1, G.compareTo(testTiles.get(2)),"The Tile compareTo did not return the correct value\n"),
                //Assert that compareTo will return 1 when G is compared to testTiles 3
                ()-> assertEquals(1, G.compareTo(testTiles.get(3)),"The Tile compareTo did not return the correct value\n")
        );
    }

    @Test
    @DisplayName("Tile Test for compareTo on Tile with same Character, different Value")
    void tileTestCompareToSameChar(){
        Tile G = new Tile('G');

        //Setting Blank Tile to 'G'
        testTiles.get(3).setCharacter('G');

        // assertAll so that all assertions are run and reported together
        assertAll("Testing that the compareTo returns the correct value for the same Character\n",
                //Assert that compareTo will return 1 when G is compared to testTiles 3
                ()-> assertEquals(1, G.compareTo(testTiles.get(3)),"The Tile compareTo did not return the correct value\n"),
                //Assert that compareTo will return -1 when testTiles 3 is compared to G
                ()-> assertEquals(-1, testTiles.get(3).compareTo(G),"The Tile compareTo did not return the correct value\n")
        );
    }

    @Test
    @DisplayName("Tile Test for equals on Tile with same Character, different Value")
    void tileTestEqualsSameChar(){
        Tile G = new Tile('G');

        //Setting Blank Tile to 'G'
        testTiles.get(3).setCharacter('G');

        // assertAll so that all assertions are run and reported together
        assertAll("Testing that the equals returns the correct value\n",
                //Assert that equal will return False
                ()-> assertFalse(G.equals(testTiles.get(3)),"The Tile equals returned True\n"),
                //Assert that equal will return False
                ()-> assertFalse(testTiles.get(3).equals(G),"The Tile equals returned True\n")
        );

    }

    @Test
    @DisplayName("Tile Test for equals on Tile ")
    void tileTestEquals(){
        Tile G = new Tile('G');

        // assertAll so that all assertions are run and reported together
        assertAll("Testing that the equals returns the correct value\n",
                //Assert that equal will return True
                ()-> assertTrue(G.equals(testTiles.get(1)),"The Tile equals returned False\n"),
                //Assert that equal will return False
                ()-> assertFalse(G.equals(testTiles.get(0)),"The Tile equals returned True\n")
        );
    }

    @Test
    @DisplayName("Tile Test for setNull on Blank Tile")
    void tileTestSetNull(){

        //Setting Blank Tile to not null
        testTiles.get(3).setCharacter('B');

        //Setting Blank Tile to null
        testTiles.get(3).setNull();

        //Assert the Blank tile was set back to null
        assertEquals(' ', testTiles.get(3).getCharacter(), "The Tile setNull did not set the Character of Blank Tile Back to Null\n");

    }

    @Test
    @DisplayName("Tile Test for setNull on non Blank")
    void tileTestSetNullNonBlank(){

        //Assert that setNull on a non Blank Tile throws InvalidTileException
        assertThrows( InvalidTileException.class, ()-> testTiles.get(0).setNull(), "The Tile setNull did not throw InvalidTileException for a non Blank Tile\n");

    }
}