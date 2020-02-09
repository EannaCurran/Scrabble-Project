package scrabble;

import scrabble.exceptions.InvalidPoolException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PoolTest {

    // Declaring variables used in testing
    private Pool pool;
    private Tile tile;


    @BeforeEach
    void setUp() {

        // Setting up of variables used in testing
        pool = new Pool();
        tile = new Tile('A');
    }


    @Test
    @DisplayName("Testing that when a Pool is initialised it contains 100 tiles")
    void initialSizeOfPool() {

        // Asserts that the Pool has an initial size of 100
        assertEquals(100, pool.tilesInPool(), "Pool is not initialed it contains 100 tiles\n");
    }


    @Test
    @DisplayName("Testing that removeTile removes a tile from the Pool")
    void sizeOfPoolAfterRemoval() {

        // Removes a Tile from the Pool;
        pool.removeTile();

        // Asserts that the Pool has 99 titles left
        assertEquals(99, pool.tilesInPool(), "After calling removeTile on pool the tile was not removed from the pool\n");
    }


    @Test
    @DisplayName("Testing removeTile fails for an empty Pool")
    void removeFromEmptyPool() {

        // Empties the Pool of Tiles
        for (int i = 0; i < 100; i++) {
            pool.removeTile();
        }

        // Asserts that removing a Tile from an empty Pool gives a InvalidPoolException
        assertThrows(InvalidPoolException.class, () -> pool.removeTile(), "The pool did not throw an InvalidPoolException when removing a title from an empty list\n");
    }


    @Test
    @DisplayName("Testing that receiveTile adds a Tile to the Pool")
    void addTileToPool(){

        // Attempts to add a Tile to the full Pool
        pool.receiveTile(new Tile('Y'));

        // Assert that the number of tiles has increased
        assertEquals(101, pool.tilesInPool(), "The number of tiles has not increased by the expected amount\n");
    }

    @Test
    @DisplayName("Testing isEmpty returns false for a Pool that has Tiles")
    void poolIsEmpty(){

        // Asserts that isEmpty returns false for a full Pool
        assertFalse(pool.isEmpty(),"isEmpty returned true for a full Pool.\n");
    }

    @Test
    @DisplayName("Testing isEmpty returns true for a Pool that has zero Tiles")
    void poolIsEmptyWhenEmpty(){

        // Empties the Pool of Tiles
        for (int i = 0; i < 100; i++) {
            pool.removeTile();
        }

        // Asserts that isEmpty returns true for a Pool that is empty
        assertTrue(pool.isEmpty(),"isEmpty returned false for a Pool that is empty.\n");
    }

    @Test
    @DisplayName("Testing toString returns the correct output for an empty Pool")
    void testToStringEmptyPool(){

        // Empties the Pool of Tiles
        for (int i = 0; i < 100; i++) {
            pool.removeTile();
        }

        // Asserts toString returns the correct format for an empty Pool
        assertEquals("Pool: Size: 0\n", pool.toString(), "toString returns the incorrect format for an empty Pool.\n");
    }


    @Test
    @DisplayName("Testing receiveTile passes the Tile into the Pool")
    void testReceiveTile(){

        // Empties the Pool of Tiles
        for (int i = 0; i < 100; i++) {
            pool.removeTile();
        }

        // Passes a Tile into the Pool
        pool.receiveTile(tile);

        // Checks that the Tile passed in is the only Tile in the Pool
        assertEquals(tile, pool.removeTile(),"The Tile passed in was not the only Tile in the Pool.\n");
    }


    @Test
    @DisplayName("Testing that poolFill fills the pool with every initial tile")
    void testPoolFill(){

        // Empties the Pool of Tiles
        for (int i = 0; i < 100; i++) {
            pool.removeTile();
        }

        // Refills the empty Pool
        pool.poolFill();

        // Asserts the refilled Pool is equal to a new Pool
        Pool tempPool = new Pool();
        assertEquals(tempPool.toString(), pool.toString());
    }



    @Test
    @DisplayName("Testing toString")
    void testToString(){

        // Asserts toString returns the correct format for a Pool
        assertEquals("Pool: Size: 100\n" +
                "Tile: Char: A Value: 1\n" +
                "Tile: Char: A Value: 1\n" +
                "Tile: Char: A Value: 1\n" +
                "Tile: Char: A Value: 1\n" +
                "Tile: Char: A Value: 1\n" +
                "Tile: Char: A Value: 1\n" +
                "Tile: Char: A Value: 1\n" +
                "Tile: Char: A Value: 1\n" +
                "Tile: Char: A Value: 1\n" +
                "Tile: Char: B Value: 3\n" +
                "Tile: Char: B Value: 3\n" +
                "Tile: Char: C Value: 3\n" +
                "Tile: Char: C Value: 3\n" +
                "Tile: Char: D Value: 2\n" +
                "Tile: Char: D Value: 2\n" +
                "Tile: Char: D Value: 2\n" +
                "Tile: Char: D Value: 2\n" +
                "Tile: Char: E Value: 1\n" +
                "Tile: Char: E Value: 1\n" +
                "Tile: Char: E Value: 1\n" +
                "Tile: Char: E Value: 1\n" +
                "Tile: Char: E Value: 1\n" +
                "Tile: Char: E Value: 1\n" +
                "Tile: Char: E Value: 1\n" +
                "Tile: Char: E Value: 1\n" +
                "Tile: Char: E Value: 1\n" +
                "Tile: Char: E Value: 1\n" +
                "Tile: Char: E Value: 1\n" +
                "Tile: Char: E Value: 1\n" +
                "Tile: Char: F Value: 4\n" +
                "Tile: Char: F Value: 4\n" +
                "Tile: Char: G Value: 2\n" +
                "Tile: Char: G Value: 2\n" +
                "Tile: Char: G Value: 2\n" +
                "Tile: Char: H Value: 4\n" +
                "Tile: Char: H Value: 4\n" +
                "Tile: Char: I Value: 1\n" +
                "Tile: Char: I Value: 1\n" +
                "Tile: Char: I Value: 1\n" +
                "Tile: Char: I Value: 1\n" +
                "Tile: Char: I Value: 1\n" +
                "Tile: Char: I Value: 1\n" +
                "Tile: Char: I Value: 1\n" +
                "Tile: Char: I Value: 1\n" +
                "Tile: Char: I Value: 1\n" +
                "Tile: Char: J Value: 8\n" +
                "Tile: Char: K Value: 5\n" +
                "Tile: Char: L Value: 1\n" +
                "Tile: Char: L Value: 1\n" +
                "Tile: Char: L Value: 1\n" +
                "Tile: Char: L Value: 1\n" +
                "Tile: Char: M Value: 3\n" +
                "Tile: Char: M Value: 3\n" +
                "Tile: Char: N Value: 1\n" +
                "Tile: Char: N Value: 1\n" +
                "Tile: Char: N Value: 1\n" +
                "Tile: Char: N Value: 1\n" +
                "Tile: Char: N Value: 1\n" +
                "Tile: Char: N Value: 1\n" +
                "Tile: Char: O Value: 1\n" +
                "Tile: Char: O Value: 1\n" +
                "Tile: Char: O Value: 1\n" +
                "Tile: Char: O Value: 1\n" +
                "Tile: Char: O Value: 1\n" +
                "Tile: Char: O Value: 1\n" +
                "Tile: Char: O Value: 1\n" +
                "Tile: Char: O Value: 1\n" +
                "Tile: Char: P Value: 3\n" +
                "Tile: Char: P Value: 3\n" +
                "Tile: Char: Q Value: 10\n" +
                "Tile: Char: R Value: 1\n" +
                "Tile: Char: R Value: 1\n" +
                "Tile: Char: R Value: 1\n" +
                "Tile: Char: R Value: 1\n" +
                "Tile: Char: R Value: 1\n" +
                "Tile: Char: R Value: 1\n" +
                "Tile: Char: S Value: 1\n" +
                "Tile: Char: S Value: 1\n" +
                "Tile: Char: S Value: 1\n" +
                "Tile: Char: S Value: 1\n" +
                "Tile: Char: T Value: 1\n" +
                "Tile: Char: T Value: 1\n" +
                "Tile: Char: T Value: 1\n" +
                "Tile: Char: T Value: 1\n" +
                "Tile: Char: T Value: 1\n" +
                "Tile: Char: T Value: 1\n" +
                "Tile: Char: U Value: 1\n" +
                "Tile: Char: U Value: 1\n" +
                "Tile: Char: U Value: 1\n" +
                "Tile: Char: U Value: 1\n" +
                "Tile: Char: V Value: 4\n" +
                "Tile: Char: V Value: 4\n" +
                "Tile: Char: W Value: 4\n" +
                "Tile: Char: W Value: 4\n" +
                "Tile: Char: X Value: 8\n" +
                "Tile: Char: Y Value: 4\n" +
                "Tile: Char: Y Value: 4\n" +
                "Tile: Char: Z Value: 10\n" +
                "Tile: Char:   Value: 0\n" +
                "Tile: Char:   Value: 0\n", pool.toString(),"The toString of pool did not match the expected string\n");
    }
}
