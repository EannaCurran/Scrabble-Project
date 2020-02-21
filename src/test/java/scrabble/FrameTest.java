package scrabble;

import scrabble.exceptions.InvalidFrameException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class FrameTest {

    // Declaring variables used in testing
    private Pool pool;
    private Frame frame;
    private Tile tileOne, tileTwo;
    private ArrayList<Tile> tileList;
    private Frame emptyFrame;


    @BeforeEach
    void setUp() {

        // Setting up of variables used in testing
        pool = new Pool();
        frame = new Frame(pool);
        emptyFrame = new Frame(pool);
        emptyFrame.returnFrame().clear();
        tileOne = new Tile('A');
        tileTwo = new Tile('B');
        tileList = new ArrayList<>();
        tileList.add(tileOne);
        tileList.add(tileTwo);
    }


    @Test
    @DisplayName("Tests the size of the frame returned from returnFrame")
    void returnFrameSize(){

        // Asserts that a full frame has 7 tiles in it
        assertEquals(7, frame.returnFrame().size(), "The Frame should be initialized to be filled with 7 tiles\n");
    }


    @Test
    @DisplayName("Tests that every object in a filled frame is a tile")
    void fillFrameWithTiles(){

        // Asserts that each object in a filled Frame is an object of type Tile
        for(Object t: frame.returnFrame()){
            assertTrue(t instanceof Tile);
        }
    }


    @Test
    @DisplayName("Tests that an empty Frame returns an arrayList with no Tiles")
    void returnEmptyFrame() {

        // Asserts that returnFrame returns an empty ArrayList if no Tiles have been added to the Frame
        assertEquals(new ArrayList<>(), emptyFrame.returnFrame(), "The empty frame list was not empty\n");
    }


    @Test
    @DisplayName("Tests that returnFrame returns a Frame with Tiles that have been added to it")
    void returnFilledFrame(){

        // Adds tileList to the empty Frame
        for(Tile tile: tileList) {
            emptyFrame.addTile(tile);
        }

        // Asserts that the tileList passed into the empty Frame is the same as the Tiles in the Frame
        assertEquals(tileList, emptyFrame.returnFrame(), "The tileList passed into the empty frame is not the same as the tiles in the frame\n");
    }


    @Test
    @DisplayName("Tests that isEmpty returns true when the Frame is empty")
    void isEmptyFrame() {

        // Asserts that isEmpty returns true for an empty frame
        assertTrue(emptyFrame.isEmpty(), "isEmpty returned false for an empty frame\n");
    }


    @Test
    @DisplayName("Tests that removeTiles removes the correct Tiles in Frame")
    void removeTiles() {

        // Adds tilesList to the empty Frame
        for(Tile tile: tileList) {
            emptyFrame.addTile(tile);
        }

        // Removes these tiles from the Frame
        emptyFrame.removeTiles(tileList);

        // Asserts that emptyFrame is empty after the tileList has been removed from the frame
        assertTrue(emptyFrame.isEmpty(), "emptyFrame is not empty after the tileList has been removed from the frame\n");
    }

    @Test
    @DisplayName("Tests that isEmpty returns false for frames that contains Tiles")
    void addTilesNotEmpty() {

        // Asserts that isEmpty returns false for a frame which has tiles in it
        assertFalse(frame.isEmpty(), "isEmpty returned true for a frame which has tiles in it\n");
    }


    @Test
    @DisplayName("Tests that you cannot add more than 7 Tiles to a Frame")
    void addTilesFullFrame(){

        // Asserts that an IllegalArgumentException is thrown when tiles are added to a full frame
        assertThrows(IllegalArgumentException.class, ()-> frame.addTile(tileOne), "Frame can't contain more than 7 tiles\n");
    }


    @Test
    @DisplayName("Tests that addTiles add the correct Tiles to the Frame")
    void addTilesCorrectTiles(){

        // Adds tileList to the empty Frame
        for(Tile tile: tileList) {
            emptyFrame.addTile(tile);
        }

        // Asserts that the list previously passed in is currently the frame of the the initially empty frame
        assertEquals(tileList, emptyFrame.returnFrame(), "The list previously passed in is not currently the frame of the the initially empty frame\n");
    }

    @Test
    @DisplayName("Tests that addTiles add the correct individual Tile to the Frame")
    void addTilesCorrectTileAdded(){

        // Adds tileList to the empty Frame
        for(Tile tile: tileList) {
            emptyFrame.addTile(tile);
        }

        // Asserts that returnFrame returns true for a tile that was in tileList
        assertTrue(emptyFrame.returnFrame().contains(tileOne),"returnFrame returns false for a tile that was in tileList\n");
    }


    @Test
    @DisplayName("Tests that swapTiles adds the correct number of Tiles to the Frame")
    void swapTilesSize() {

        // Removes the first and second Tile from the filled Frame
        frame.removeTile(0);
        frame.removeTile(1);

        // Adds the Tiles in tileList to the Frame
        for(Tile tile: tileList) {
            frame.addTile(tile);
        }

        // Swaps the Tiles passed in from tileList with Tiles in the Pool
        frame.swapTiles(tileList);


        // Asserts that the size of the frame is still 7
        assertEquals(7, frame.returnFrame().size(), "The size of the frame is not still 7\n");
    }


    @Test
    @DisplayName("Tests that checkTiles returns true for a list of Tiles already in the Frame")
    void checkTiles() {

        // Adds tileList to the empty Frame
        for(Tile tile: tileList) {
            emptyFrame.addTile(tile);
        }

        // Asserts that checkTiles returns true when a list of of tiles passed into the method are in the frame
        assertTrue(emptyFrame.checkTiles(tileList), "checkTiles returned false when a list of of tiles passed into the method are in the frame\n");
    }


    @Test
    @DisplayName("Tests that checkTiles returns true when an empty list of tiles is checked")
    void checkTilesEmptyTileList(){

        // Asserts that checkTiles returns true if an empty array list is passed into the method
        assertTrue(frame.checkTiles(new ArrayList<>()), "checkTiles returned false if an empty array list is passed into the method\n");
    }


    @Test
    @DisplayName("Tests that checkTiles returns false when a list of Tiles that are not in the frame is checked")
    void checkTilesNotInFrame(){

        // Asserts that checkTiles returns false if every tile passed in is not in the Frame
        assertFalse(emptyFrame.checkTiles(tileList));
    }


    @Test
    @DisplayName("Tests that toString works for an empty frame")
    void toStringEmptyFrame() {

        // Asserts that toString of emptyFrame outputs the correct string
        assertEquals("Current Frame Contains: ", emptyFrame.toString(), "toString of emptyFrame outputs the incorrect string\n");
    }


    @Test
    @DisplayName("Tests that toString works for a frame with Tiles")
    void toStringFilledFrame(){

        // Adds tileList to emptyFrame
        System.out.println(emptyFrame.toString());
        for(Tile tile: tileList) {
            emptyFrame.addTile(tile);
        }

        // Asserts that toString of emptyFrame with tiles outputs the correct string
        assertEquals("Current Frame Contains: A B ", emptyFrame.toString(), "toString of emptyFrame with tiles outputs the incorrect string\n");
    }


    @Test
    @DisplayName("Test that Tiles cannot be removed from an empty Frame")
    void removeTilesFromEmptyFrame(){

        // Asserts that an InvalidFrameException is thrown if the Tiles are not in the frame
        assertThrows(InvalidFrameException.class, ()-> emptyFrame.removeTiles(tileList), "Tiles not in the frame, therefore tiles cannot be removed\n");
    }

}