package scrabble;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

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
        testSquare.get(testSquare.size()-1).setTile(new Tile('X'));

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
                //Assert that if a NORMAL square with Tile X is printed it returns the correct string
                () -> assertEquals("X 10", testSquare.get(7).toString(),"The Square toString method did not return the correct string for a NORMAL Square with Tile X.\n")
        );
    }


    @Test
    @DisplayName("Test Square setTile Method")
    void testSetTile(){


    }

    @Test
    @DisplayName("Test Square getTile Method")
    void testGetTile(){


    }

    @Test
    @DisplayName("Test Square setNormal Method")
    void testSetNormal(){


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
                //Assert that a NORMAL square with Tile X getType returns the correct SquareType
                () -> assertEquals(Square.SquareType.NORMAL, testSquare.get(7).getType(),"The Square getType method did not return the correct SquareType for a NORMAL Square with Tile X.\n")
        );

    }

}
