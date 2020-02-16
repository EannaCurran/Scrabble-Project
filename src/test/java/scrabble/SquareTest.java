package scrabble;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import scrabble.exceptions.InvalidSquareException;

import java.util.ArrayList;


public class SquareTest {

    private ArrayList<Square> testSquare;

    @BeforeEach
    void setUp(){
        testSquare = new ArrayList<>();

        testSquare.add(new Square(Square.SquareType.NORMAL));
        testSquare.add(new Square(Square.SquareType.TRIPLE_WORD));
        testSquare.add(new Square(Square.SquareType.TRIPLE_LETTER));
        testSquare.add(new Square(Square.SquareType.DOUBLE_WORD));
        testSquare.add(new Square(Square.SquareType.DOUBLE_LETTER));
        testSquare.add(new Square(Square.SquareType.START));
        testSquare.add(new Square(Square.SquareType.NORMAL));
        testSquare.get(testSquare.size()-1).setTile(new Tile('A'));
        testSquare.add(new Square(Square.SquareType.NORMAL));
        testSquare.get(testSquare.size()-1).setTile(new Tile('Q'));

    }

    @Test
    @DisplayName("Test Square toString Method")
    void testToString(){

        // assertAll so that all assertions are run and reported together
        assertAll("Testing the toString method on different square types\n",
                //Assert that if a NORMAL square is printed it returns the correct string
                () -> assertEquals("    ", testSquare.get(0).toString(),"The Square toString method did not return the correct string for a NORMAL Square.\n"),
                //Assert that if a TRIPLE_WORD square is printed it returns the correct string
                () -> assertEquals(" 3W ", testSquare.get(1).toString(),"The Square toString method did not return the correct string for a TRIPLE_WORD Square.\n"),
                //Assert that if a TRIPLE_LETTER square is printed it returns the correct string
                () -> assertEquals(" 3L ", testSquare.get(2).toString(),"The Square toString method did not return the correct string for a TRIPLE_LETTER Square.\n"),
                //Assert that if a DOUBLE_WORD square is printed it returns the correct string
                () -> assertEquals(" 2W ", testSquare.get(3).toString(),"The Square toString method did not return the correct string for a DOUBLE_WORD Square.\n"),
                //Assert that if a DOUBLE_LETTER square is printed it returns the correct string
                () -> assertEquals(" 2L ", testSquare.get(4).toString(),"The Square toString method did not return the correct string for a DOUBLE_LETTER Square.\n"),
                //Assert that if a START square is printed it returns the correct string
                () -> assertEquals(" ** ", testSquare.get(5).toString(),"The Square toString method did not return the correct string for a START Square.\n"),
                //Assert that if a NORMAL square with Tile A is printed it returns the correct string
                () -> assertEquals("A  1", testSquare.get(6).toString(),"The Square toString method did not return the correct string for a NORMAL Square with Tile A.\n"),
                //Assert that if a NORMAL square with Tile Q is printed it returns the correct string
                () -> assertEquals("Q 10", testSquare.get(7).toString(),"The Square toString method did not return the correct string for a NORMAL Square with Tile Q.\n")
        );
    }


    @Test
    @DisplayName("Test Square setTile Method")
    void testSetTile(){

        //Tiles for Testing
        Tile A = new Tile('A');
        Tile Q = new Tile('Q');

        testSquare.get(0).setTile(A);
        testSquare.get(1).setTile(Q);

        // assertAll so that all assertions are run and reported together
        assertAll("Testing the setTile method \n",
                //Assert that a NORMAL square with setTile A getType returns the correct Tile
                () -> assertEquals(A, testSquare.get(0).getTile(), "The Square setTile method did not set the correct Tile.\n"),
                //Assert that a square with setTile Q getType returns the correct Tile
                () -> assertEquals(Q, testSquare.get(1).getTile(), "The Square getTile method did not return the correct Tile.\n")
        );
    }

    @Test
    @DisplayName("Test Square setTile Method on full Square")
    void testSetTileFullSquare(){
        //Tile for Testing
        Tile A = new Tile('A');

        //Assert that setTile throws InvalidSquareException when Square is full
        assertThrows(InvalidSquareException.class, ()-> testSquare.get(6).setTile(A), "The Square setTile method did not throw an InvalidSquareException when used on full Square\n");
    }

    @Test
    @DisplayName("Test Square setTile Method with null Tile")
    void testSetTileNullTile(){
        //Tile for Testing
        Tile nullTile = new Tile(' ');

        //Assert that setTile throws InvalidSquareException when null Tile is inputted
        assertThrows(InvalidSquareException.class, ()-> testSquare.get(0).setTile(nullTile), "The Square setTile method did not throw an InvalidSquareException when null Tile was inputted\n");
    }

    @Test
    @DisplayName("Test Square getTile Method")
    void testGetTile(){

        //Tiles for Testing
        Tile A = new Tile('A');
        Tile Q = new Tile('Q');

        // assertAll so that all assertions are run and reported together
        assertAll("Testing the getTile method\n",
                //Assert that a NORMAL square with Tile A getType returns the correct Tile
                () -> assertTrue(A.equals(testSquare.get(6).getTile()),"The Square getTile method did not return the correct Tile for a NORMAL Square with Tile A.\n"),
                //Assert that a NORMAL square with Tile Q getType returns the correct Tile
                () -> assertTrue(Q.equals(testSquare.get(7).getTile()),"The Square getTile method did not return the correct Tile for a NORMAL Square with Tile Q.\n")
        );

    }

    @Test
    @DisplayName("Test Square setNormal Method")
    void testSetNormal(){

        //setNormal on testSquares
        for (int i = 0; i < 6; i++) {
            testSquare.get(i).setNormal();
        }

        // assertAll so that all assertions are run and reported together
        assertAll("Testing the setNormal method on different square types\n",
                //Assert that a NORMAL square getType returns NORMAL
                () -> assertEquals(Square.SquareType.NORMAL, testSquare.get(0).getType(),"The Square setNormal method did not set Type to NORMAL SquareType.\n"),
                //Assert that a TRIPLE_WORD square getType returns NORMAL
                () -> assertEquals(Square.SquareType.NORMAL, testSquare.get(1).getType(),"The Square setNormal method did not set Type to NORMAL SquareType.\n"),
                //Assert that a TRIPLE_LETTER square getType returns NORMAL
                () -> assertEquals(Square.SquareType.NORMAL, testSquare.get(2).getType(),"The Square setNormal method did not set Type to NORMAL SquareType.\n"),
                //Assert that a DOUBLE_WORD square getType returns NORMAL
                () -> assertEquals(Square.SquareType.NORMAL, testSquare.get(3).getType(),"The Square setNormal method did not set Type to NORMAL SquareType.\n"),
                //Assert that a DOUBLE_LETTER square getType returns NORMAL
                () -> assertEquals(Square.SquareType.NORMAL, testSquare.get(4).getType(),"The Square setNormal method did not set Type to NORMAL SquareType.\n"),
                //Assert that a START square getType returns NORMAL
                () -> assertEquals(Square.SquareType.NORMAL, testSquare.get(5).getType(),"The Square setNormal method did not set Type to NORMAL SquareType.\n")
        );

    }

    @Test
    @DisplayName("Test Square setNormal Method returns correct SquareType")
    void testSetNormalReturn(){

        // assertAll so that all assertions are run and reported together
        assertAll("Testing the setNormal method return value on different square types\n",
                //Assert that a NORMAL square getType returns NORMAL
                () -> assertEquals(Square.SquareType.NORMAL, testSquare.get(0).setNormal(),"The Square setNormal method did not return the expected SquareType.\n"),
                //Assert that a TRIPLE_WORD square setNormal returns TRIPLE_WORD
                () -> assertEquals(Square.SquareType.TRIPLE_WORD, testSquare.get(1).setNormal(),"The Square setNormal method did not return the expected SquareType.\n"),
                //Assert that a TRIPLE_LETTER square setNormal returns TRIPLE_LETTER
                () -> assertEquals(Square.SquareType.TRIPLE_LETTER, testSquare.get(2).setNormal(),"The Square setNormal method did not return the expected SquareType.\n"),
                //Assert that a DOUBLE_WORD square setNormal returns DOUBLE_WORD
                () -> assertEquals(Square.SquareType.DOUBLE_WORD, testSquare.get(3).setNormal(),"The Square setNormal method did not return the expected SquareType.\n"),
                //Assert that a DOUBLE_LETTER square setNormal returns DOUBLE_LETTER
                () -> assertEquals(Square.SquareType.DOUBLE_LETTER, testSquare.get(4).setNormal(),"The Square setNormal method did not return the expected SquareType.\n"),
                //Assert that a START square setNormal returns START
                () -> assertEquals(Square.SquareType.START, testSquare.get(5).setNormal(),"The Square setNormal method did not return the expected SquareType.\n")
        );

    }

    @Test
    @DisplayName("Test Square getType Method")
    void testGetType(){

        // assertAll so that all assertions are run and reported together
        assertAll("Testing the getType method on different square types\n",
                //Assert that a NORMAL square getType returns the correct SquareType
                () -> assertEquals(Square.SquareType.NORMAL, testSquare.get(0).getType(),"The Square getType method did not return the correct SquareType for a NORMAL Square.\n"),
                //Assert that a TRIPLE_WORD square getType returns the correct SquareType
                () -> assertEquals(Square.SquareType.TRIPLE_WORD, testSquare.get(1).getType(),"The Square getType method did not return the correct SquareType for a TRIPLE_WORD Square.\n"),
                //Assert that a TRIPLE_LETTER square getType returns the correct SquareType
                () -> assertEquals(Square.SquareType.TRIPLE_LETTER, testSquare.get(2).getType(),"The Square getType method did not return the correct SquareType for a TRIPLE_LETTER Square.\n"),
                //Assert that a DOUBLE_WORD square getType returns the correct SquareType
                () -> assertEquals(Square.SquareType.DOUBLE_WORD, testSquare.get(3).getType(),"The Square getType method did not return the correct SquareType for a DOUBLE_WORD Square.\n"),
                //Assert that a DOUBLE_LETTER square getType returns the correct SquareType
                () -> assertEquals(Square.SquareType.DOUBLE_LETTER, testSquare.get(4).getType(),"The Square getType method did not return the correct SquareType for a DOUBLE_LETTER Square.\n"),
                //Assert that a START square getType returns the correct SquareType
                () -> assertEquals(Square.SquareType.START, testSquare.get(5).getType(),"The Square getType method did not return the correct SquareType for a START Square.\n"),
                //Assert that a NORMAL square with Tile A getType returns the correct SquareType
                () -> assertEquals(Square.SquareType.NORMAL, testSquare.get(6).getType(),"The Square getType method did not return the correct SquareType for a NORMAL Square with Tile A.\n"),
                //Assert that a NORMAL square with Tile Q getType returns the correct SquareType
                () -> assertEquals(Square.SquareType.NORMAL, testSquare.get(7).getType(),"The Square getType method did not return the correct SquareType for a NORMAL Square with Tile Q.\n")
        );

    }

}
