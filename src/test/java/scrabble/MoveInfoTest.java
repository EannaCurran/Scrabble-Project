package scrabble;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import scrabble.exceptions.InvalidMoveInfoException;
import scrabble.exceptions.InvalidWordException;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class MoveInfoTest {

    private Player player;
    private Pool pool;
    private MoveInfo moveInfo;

    @BeforeEach
    void setUp(){


        pool = new Pool();
        player = new Player("Bot", pool);

        moveInfo = new MoveInfo(player, new int[]{7,7}, UserInput.Direction.VERTICAL, new char[]{'C', 'A', 'T'});
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
        assertThrows(InvalidMoveInfoException.class, ()-> new MoveInfo(null, new int[]{7,7}, UserInput.Direction.VERTICAL, new char[]{'C', 'A', 'T'}), "The Constructor did not throw InvalidMoveInfoException for a invalid Player\n");
    }

    @Test
    @DisplayName("MoveInfo Test setScore")
    void moveInfoTestSetScore(){
        // assertAll so that all assertions are run and reported together
        assertAll("Test valid input for MoveInfo setScore",
                //Assert that valid input dose not throw an exception
                ()-> assertDoesNotThrow(()-> moveInfo.setScore(100),  "setScore throw exception for a valid input\n"),
                //Assert that the move score is 100
                ()-> assertEquals(100, moveInfo.getMoveScore(), "The setScore set the incorrect score value\n")
        );
    }

    @Test
    @DisplayName("MoveInfo Test setScore Invalid")
    void moveInfoTestSetScoreInvalid(){

        // assertAll so that all assertions are run and reported together
        assertAll("Test invalid input for MoveInfo setScore",
                //Assert that invalid Word throws an InvalidMoveInfoException
                ()-> assertThrows(InvalidMoveInfoException.class,()-> moveInfo.setScore(-100), "The setScore did not throw InvalidMoveInfoException for -100\n"),
                //Assert that invalid Word throws an InvalidMoveInfoException
                ()-> assertThrows(InvalidMoveInfoException.class,()-> moveInfo.setScore(-1), "The setScore did not throw InvalidMoveInfoException for -1\n")
        );
    }

    @Test
    @DisplayName("MoveInfo Test addAuxiliaryWord")
    void moveInfoTestAddAuxiliaryWord(){
        Word word = new Word(new int[]{0,0}, UserInput.Direction.HORIZONTAL, new char[]{'D', 'O', 'G'});

        // assertAll so that all assertions are run and reported together
        assertAll("Test valid input for MoveInfo addAuxiliaryWord",
                //Assert that valid input dose not throw an exception
                ()-> assertDoesNotThrow(()-> moveInfo.addAuxiliaryWord(word),  "addAuxiliaryWord throw exception for a valid input\n"),
                //Assert that the move auxiliary word is word
                ()-> assertEquals(word, moveInfo.getAuxiliaryWords().get(0), "The addAuxiliaryWord set the incorrect Word\n")
        );
    }

    @Test
    @DisplayName("MoveInfo Test setRequiredTiles")
    void moveInfoTestSetRequiredTiles(){
        char[] wordReq = new char[]{'D', 'O', 'G'};
        int[][] wordReqPos = new int[][]{{7,7}, {7,8}, {7,9}};

        // assertAll so that all assertions are run and reported together
        assertAll("Test valid input for MoveInfo setRequiredTiles",
                //Assert that valid input dose not throw an exception
                ()-> assertDoesNotThrow(()-> moveInfo.setRequiredTiles(wordReq, wordReqPos),  "setRequiredTiles throw exception for a valid input\n"),
                //Assert that the move auxiliary word is word
                ()-> assertTrue(Arrays.equals(wordReq, moveInfo.getRequiredTiles()), "The setRequiredTiles set the incorrect required Tiles\n"),
                //Assert that the move auxiliary word is word
                ()-> assertTrue(Arrays.equals(wordReqPos, moveInfo.getRequiredTilesPositions()), "The setRequiredTiles set the incorrect required Tiles positions\n")
        );
    }

    @Test
    @DisplayName("MoveInfo Test setRequiredTiles Invalid")
    void moveInfoTestSetRequiredTilesInvalid(){

        // assertAll so that all assertions are run and reported together
        assertAll("Test invalid input for MoveInfo setRequiredTiles",
                //Assert that invalid Word throws an InvalidMoveInfoException
                ()-> assertThrows(InvalidMoveInfoException.class,()-> moveInfo.setRequiredTiles(new char[]{'D', 'O', 'G'}, new int[][]{{7,7}, {7,8}}), "The setRequiredTiles did not throw InvalidMoveInfoException for positions less than Tiles\n"),
                //Assert that invalid Word throws an InvalidMoveInfoException
                ()-> assertThrows(InvalidMoveInfoException.class,()-> moveInfo.setRequiredTiles(new char[]{'D', 'O'}, new int[][]{{7,7}, {7,8}, {7,9}}), "The setRequiredTiles did not throw InvalidMoveInfoException for Tiles less than positions\n"),
                //Assert that invalid Word throws an InvalidMoveInfoException
                ()-> assertThrows(InvalidMoveInfoException.class,()-> moveInfo.setRequiredTiles(new char[]{'D', 'O', 'G', 'S', 'C', 'A', 'T', 'S'}, new int[][]{{7,3},{7,4}, {7,5}, {7,6},{7,7}, {7,8}, {7,9}, {7,10}}), "The setRequiredTiles did not throw InvalidMoveInfoException for longer than Frame Size\n"),
                //Assert that invalid Word throws an InvalidMoveInfoException
                ()-> assertThrows(InvalidMoveInfoException.class,()-> moveInfo.setRequiredTiles(new char[]{}, new int[][]{}), "The setRequiredTiles did not throw InvalidMoveInfoException for empty\n")
        );
    }
}
