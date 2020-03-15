package scrabble;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import scrabble.exceptions.InvalidWordException;

import static org.junit.jupiter.api.Assertions.*;

public class WordTest {


    @Test
    @DisplayName("Word Test Constructor")
    void wordTestConstructor(){

        assertAll("Test valid input for Word Constructor",
                //Assert that valid input dose not throw an exception
                ()-> assertDoesNotThrow(()-> new Word(new int[]{7,7}, UserInput.Direction.VERTICAL, new char[]{'C', 'A', 'T'}), "The Constructor throw exception for a valid input\n"),
                //Assert that valid input dose not throw an exception
                ()-> assertDoesNotThrow(()-> new Word(new int[]{0,0}, UserInput.Direction.HORIZONTAL, new char[]{'D', 'O', 'G'}), "The Constructor throw exception for a valid input\n")
        );
    }

    @Test
    @DisplayName("Word Test Constructor Null Direction")
    void wordTestConstructorNullDir(){

        //Assert that invalid Direction throws an InvalidWordException
        assertThrows(InvalidWordException.class, ()-> new Word(new int[]{7,7}, null, new char[]{'C', 'A', 'T'}), "The Constructor did not throw InvalidWordException for a invalid Direction\n");
    }

    @Test
    @DisplayName("Word Test Constructor Invalid Start Position")
    void wordTestConstructorInvalidPos(){

        assertAll("Test invalid position for Word Constructor",
                //Assert that invalid position throws an InvalidWordException
                ()-> assertThrows(InvalidWordException.class,()-> new Word(new int[]{-1,0}, UserInput.Direction.VERTICAL, new char[]{'C', 'A', 'T'}), "The Constructor did not throw InvalidWordException for a invalid position\n"),
                //Assert that invalid position throws an InvalidWordException
                ()-> assertThrows(InvalidWordException.class,()-> new Word(new int[]{0,-1}, UserInput.Direction.HORIZONTAL, new char[]{'D', 'O', 'G'}), "The Constructor did not throw InvalidWordException for a invalid position\n"),
                //Assert that invalid position throws an InvalidWordException
                ()-> assertThrows(InvalidWordException.class,()-> new Word(new int[]{15,0}, UserInput.Direction.VERTICAL, new char[]{'C', 'A', 'T'}), "The Constructor did not throw InvalidWordException for a invalid position\n"),
                //Assert that invalid position throws an InvalidWordException
                ()-> assertThrows(InvalidWordException.class,()-> new Word(new int[]{0,15}, UserInput.Direction.HORIZONTAL, new char[]{'D', 'O', 'G'}), "The Constructor did not throw InvalidWordException for a invalid position\n")
        );
    }

    @Test
    @DisplayName("Word Test Constructor Invalid Word")
    void wordTestConstructorInvalidWord(){

        assertAll("Test invalid Word for Word Constructor",
                //Assert that invalid Word throws an InvalidWordException
                ()-> assertThrows(InvalidWordException.class,()-> new Word(new int[]{0,0}, UserInput.Direction.VERTICAL, new char[]{}), "The Constructor did not throw InvalidWordException for a null Word\n"),
                //Assert that invalid Word throws an InvalidWordException
                ()-> assertThrows(InvalidWordException.class,()-> new Word(new int[]{0,0}, UserInput.Direction.HORIZONTAL, new char[]{'d', 'o', 'g'}), "The Constructor did not throw InvalidWordException for a invalid Word containing lower case letters\n"),
                //Assert that invalid Word throws an InvalidWordException
                ()-> assertThrows(InvalidWordException.class,()-> new Word(new int[]{0,0}, UserInput.Direction.VERTICAL, new char[]{'5', 'A', 'T'}), "The Constructor did not throw InvalidWordException for a invalid Word containing non letter\n"),
                //Assert that invalid Word throws an InvalidWordException
                ()-> assertThrows(InvalidWordException.class,()-> new Word(new int[]{0,0}, UserInput.Direction.VERTICAL, new char[]{' ', 'A', 'T'}), "The Constructor did not throw InvalidWordException for a invalid Word containing Blank\n"),
                //Assert that invalid Word throws an InvalidWordException
                ()-> assertThrows(InvalidWordException.class,()-> new Word(new int[]{0,0}, UserInput.Direction.HORIZONTAL, new char[]{'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P'}), "The Constructor did not throw InvalidWordException for a invalid Word of length 16\n")
        );
    }

}
