package scrabble;

import scrabble.exceptions.InvalidPlayerNameException;
import scrabble.exceptions.InvalidPlayerScoreException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

class PlayerUnitTest {

    private Player defaultPlayer;
    private Pool poolTest;


    @BeforeEach
    void setUp() {
        poolTest = new Pool();
        defaultPlayer = new Player("Killian", poolTest);
    }


    // Tests for names that are null
    @Test
    @DisplayName("Testing that name can not be null or empty")
    final void nameNull() {
        assertAll("Testing the constructor for empty names\n",
                () ->assertThrows(InvalidPlayerNameException.class, () -> defaultPlayer.setName(null), "SetName didn't throw a InvalidPlayerNameException.\n"),
                () ->assertThrows(InvalidPlayerNameException.class, () -> defaultPlayer.setName(""), "SetName didn't throw a InvalidPlayerNameException.\n")
        );
    }


    // Test for names that are 24 characters long
    @Test
    @DisplayName("Testing 24 characters for name is valid")
    void NameLengthTest() {
        assertDoesNotThrow(() -> new Player("wwwwwwwwwwwwwwwwwwwwwwww", poolTest), "The Player constructor threw an exception when the length of the name was under 24 characters.");
    }


    // Tests for names longer than 24 characters
    @Test
    @DisplayName("Testing 25 characters for name is invalid")
    void NameTooLong() {
        assertThrows(InvalidPlayerNameException.class, () -> defaultPlayer.setName("wwwwwwwwwwwwwwwwwwwwwwwww"), "SetName didn't throw a InvalidPlayerNameException.\n");
    }


    // Tests for names containing numbers
    @Test
    @DisplayName("Checking if name contains numbers")
    void NameContainsNumbers() {
        assertDoesNotThrow(() -> new Player("1234", poolTest), "setName threw a InvalidPlayerNameException when name contained numbers.\n");
    }


    // Tests for alphabetical letters in the Players name
    @Test
    @DisplayName("Checking for alphabetical letters")
    void NameContainsAlphabet() {
        assertDoesNotThrow(() -> new Player("abcdefg", poolTest), "setName threw a InvalidPlayerNameException when name contained numbers.\n");

    }


    // Tests for white space in the Players name
    @Test
    @DisplayName("Checking for white space")
    void NameContainsSpaces() {
        assertThrows(InvalidPlayerNameException.class, () -> defaultPlayer.setName("White Space"), "SetName didn't throw a InvalidPlayerNameException when there was white space.\n");
    }


    // Tests for non alphabet letters and non numbers in the Players name
    @Test
    @DisplayName("Checking for non alphabet letters and non numbers")
    void NameContainsNonLetters() {
        assertThrows(InvalidPlayerNameException.class, () -> defaultPlayer.setName("#!?"), "SetName didn't throw a InvalidPlayerNameException when there was non alphabet letters and non number characters.\n");
    }


    // Testing increasing the score by a positive number
    @Test
    @DisplayName("Test increasing the score by a positive number.")
    void IncreaseScore() {
        assertDoesNotThrow(() -> defaultPlayer.increaseScore(64)," increaseScore didn't throw a PlayerScoreException when the score increased by a positive number.\n");
    }


    // Tests that the score can't be increased by a negative number
    @Test
    @DisplayName("Checking the increase can't be negative")
    void IncreaseScoreNegative() {
        assertThrows(InvalidPlayerScoreException.class, () -> defaultPlayer.increaseScore(-1), " increaseScore didn't throw an InvalidPlayerScoreException when a negative value was passed.\n");
    }


    // Tests that the score can't be increased by zero
    @Test
    @DisplayName("Checking increase can be zero")
    void IncreaseScoreZero() {
        assertDoesNotThrow(() -> defaultPlayer.decreaseScore(0)," increaseScore threw a PlayerScoreException when the score increase was zero.\n");

    }


    // Tests that the score can be decreased by a positive number
    @Test
    @DisplayName("Checking decrease can be a positive integer")
    void DecreaseScorePositiveNumber() {
        assertDoesNotThrow(() -> defaultPlayer.decreaseScore(64)," decreaseScore didn't throw an InvalidPlayerScoreException when a positive value was passed.\n");
    }


    // Tests that the score can be decreased by a negative number
    @Test
    @DisplayName("Checks if the score decrease value being passed is negative")
    void DecreaseScoreNegative() {
        assertThrows(InvalidPlayerScoreException.class, () -> defaultPlayer.decreaseScore(-1), " decreaseScore didn't throw an InvalidPlayerScoreException when a negative value was passed.\n");
    }


    // Tests that score can be decreased by zero
    @Test
    @DisplayName("Checking decrease can be a zero")
    void DecreaseScoreZero() {
        assertDoesNotThrow(() -> defaultPlayer.decreaseScore(0),"IncreaseScore threw a PlayerScoreException when the score increase was zero.\n");
    }


    // Tests for names that are empty in playerReset
    @Test
    @DisplayName("Reset method Testing that name can not be null or empty")
    void ResetNameNull() {
        assertAll("Testing the constructor for empty names",
                () ->assertThrows(InvalidPlayerNameException.class, () -> defaultPlayer.playerReset(null), "SetName didn't throw a InvalidPlayerNameException.\n"),
                () ->assertThrows(InvalidPlayerNameException.class, () -> defaultPlayer.playerReset(""), "SetName didn't throw a InvalidPlayerNameException.\n")
        );
    }


    // Tests for names that have 24 characters in playerReset
    @Test
    @DisplayName("Reset method Testing 24 characters for name is valid")
    void ResetNameLengthTest() {
        assertDoesNotThrow(() -> defaultPlayer.playerReset("wwwwwwwwwwwwwwwwwwwwwwww"), "The Player constructor threw an exeception when the length of the name was under 24 characters.\n");
    }


    // Tests for names that have more than 24 characters in playerReset
    @Test
    @DisplayName("Reset method Testing 25 characters for name is invalid")
    void ResetNameTooLong() {
        assertThrows(InvalidPlayerNameException.class, () -> defaultPlayer.playerReset("wwwwwwwwwwwwwwwwwwwwwwwww"), "SetName didn't throw a InvalidPlayerNameException.\n");
    }


    // Tests for names containing number in playerReset
    @Test
    @DisplayName("Reset method checking if name contains numbers")
    void ResetNameContainsNumbers() {
        assertDoesNotThrow(() -> defaultPlayer.playerReset("1234"), "setName threw a InvalidPlayerNameException when name contained numbers.\n");
    }


    // Tests for names containing alphabetical letters in playerReset
    @Test
    @DisplayName("Reset method Checking for alphabet letters")
    void ResetNameContainsAlphabet() {
        assertDoesNotThrow(() -> defaultPlayer.playerReset("abcdefg"), "setName threw a InvalidPlayerNameException when name contained numbers.\n");

    }


    // Tests for names containing white space in playerReset
    @Test
    @DisplayName("Reset Method Checking for white space")
    void ResetNameContainsSpaces() {
        assertThrows(InvalidPlayerNameException.class, () -> defaultPlayer.playerReset("White Space"), "SetName didn't throw a InvalidPlayerNameException when there was white space.\n");
    }


    // Tests for non alphabet letters and non numbers names in playerReset
    @Test
    @DisplayName("Reset Checking for non alphabet letters and non numbers")
    void ResetNameContainsNonLetters() {
        assertThrows(InvalidPlayerNameException.class, () -> defaultPlayer.playerReset("#!?"), "SetName didn't throw a InvalidPlayerNameException when there was non alphabet letters and non number characters.\n");
    }
}