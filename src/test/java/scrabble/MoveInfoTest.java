package scrabble;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import scrabble.exceptions.InvalidWordException;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class MoveInfoTest {

    private Player player;
    private Pool pool;
    private Board board;

    @BeforeEach
    void setUp(){

        board = new Board();
        pool = new Pool();
        player = new Player("Bot", pool);
    }

    @Test
    @DisplayName("MoveInfo Test Constructor")
    void moveInfoTestConstructor(){

        // assertAll so that all assertions are run and reported together
        assertAll("Test valid input for MoveInfo Constructor",
                //Assert that valid input dose not throw an exception
                ()-> assertDoesNotThrow(()-> new MoveInfo(player , new int[] {7,7}, UserInput.Direction.VERTICAL, new char[]{'C', 'A', 'T'}),  "The Constructor throw exception for a valid input\n"),
                //Assert that valid input dose not throw an exception
                ()-> assertDoesNotThrow(()-> new MoveInfo(player ,new int[]{0,0}, UserInput.Direction.HORIZONTAL, new char[]{'D', 'O', 'G'}), "The Constructor throw exception for a valid input\n")
        );
    }

    @Test
    @DisplayName("MoveInfo Test Constructor Null Direction")
    void moveInfoTestConstructorNullDir(){

        //Assert that invalid Direction throws an InvalidWordException
        assertThrows(InvalidWordException.class, ()-> new MoveInfo(player, new int[]{7,7}, null, new char[]{'C', 'A', 'T'}), "The Constructor did not throw InvalidWordException for a invalid Direction\n");
    }

    @Test
    @DisplayName("Word Test Constructor Invalid Start Position")
    void moveInfoTestConstructorInvalidPos(){

        // assertAll so that all assertions are run and reported together
        assertAll("Test invalid position for MoveInfo Constructor",
                //Assert that invalid position throws an InvalidWordException
                ()-> assertThrows(InvalidWordException.class,()-> new MoveInfo(player, new int[]{-1,0}, UserInput.Direction.VERTICAL, new char[]{'C', 'A', 'T'}), "The Constructor did not throw InvalidWordException for a invalid position\n"),
                //Assert that invalid position throws an InvalidWordException
                ()-> assertThrows(InvalidWordException.class,()-> new MoveInfo(player, new int[]{0,-1}, UserInput.Direction.HORIZONTAL, new char[]{'D', 'O', 'G'}), "The Constructor did not throw InvalidWordException for a invalid position\n"),
                //Assert that invalid position throws an InvalidWordException
                ()-> assertThrows(InvalidWordException.class,()-> new MoveInfo(player, new int[]{15,0}, UserInput.Direction.VERTICAL, new char[]{'C', 'A', 'T'}), "The Constructor did not throw InvalidWordException for a invalid position\n"),
                //Assert that invalid position throws an InvalidWordException
                ()-> assertThrows(InvalidWordException.class,()-> new MoveInfo(player, new int[]{0,15}, UserInput.Direction.HORIZONTAL, new char[]{'D', 'O', 'G'}), "The Constructor did not throw InvalidWordException for a invalid position\n")
        );
    }

    @Test
    @DisplayName("MoveInfo Test Constructor Invalid Word")
    void moveInfoTestConstructorInvalidWord(){

        // assertAll so that all assertions are run and reported together
        assertAll("Test invalid Word for MoveInfo Constructor",
                //Assert that invalid Word throws an InvalidWordException
                ()-> assertThrows(InvalidWordException.class,()-> new MoveInfo(player, new int[]{0,0}, UserInput.Direction.VERTICAL, new char[]{}), "The Constructor did not throw InvalidWordException for a null Word\n"),
                //Assert that invalid Word throws an InvalidWordException
                ()-> assertThrows(InvalidWordException.class,()-> new MoveInfo(player, new int[]{0,0}, UserInput.Direction.HORIZONTAL, new char[]{'d', 'o', 'g'}), "The Constructor did not throw InvalidWordException for a invalid Word containing lower case letters\n"),
                //Assert that invalid Word throws an InvalidWordException
                ()-> assertThrows(InvalidWordException.class,()-> new MoveInfo(player, new int[]{0,0}, UserInput.Direction.VERTICAL, new char[]{'5', 'A', 'T'}), "The Constructor did not throw InvalidWordException for a invalid Word containing non letter\n"),
                //Assert that invalid Word throws an InvalidWordException
                ()-> assertThrows(InvalidWordException.class,()-> new MoveInfo(player, new int[]{0,0}, UserInput.Direction.VERTICAL, new char[]{' ', 'A', 'T'}), "The Constructor did not throw InvalidWordException for a invalid Word containing Blank\n"),
                //Assert that invalid Word throws an InvalidWordException
                ()-> assertThrows(InvalidWordException.class,()-> new MoveInfo(player, new int[]{0,0}, UserInput.Direction.HORIZONTAL, new char[]{'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P'}), "The Constructor did not throw InvalidWordException for a invalid Word of length 16\n")
        );
    }

    @Test
    @DisplayName("MoveInfo Test Constructor Null Player")
    void moveInfoTestConstructorNullPlayer(){

        //Assert that invalid Player throws an InvalidWordException
        assertThrows(InvalidWordException.class, ()-> new MoveInfo(null, new int[]{7,7}, null, new char[]{'C', 'A', 'T'}), "The Constructor did not throw InvalidMoveInfoException for a invalid Player\n");
    }

}
