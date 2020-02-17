package scrabble;

import scrabble.exceptions.InvalidBoardException;
import scrabble.exceptions.InvalidFrameException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;


/**
 * The Board Class represents the Board for the Scrabble Game as an object
 */
public class Board {

    /**
     * The Array of Square that holds the 15x15 Squares of the Board
     */
    private Square[][] boardSquares;

    /**
     * Board Constructor
     */
    public Board(){

        //Set boardSquare
        boardSquares = new Square[15][15];

        //Fill the boardSquares
        newBoard();
    }

    /**
     * Method to fill the board full of blank Square
     */
    private void newBoard() {

        //Centre Square is Start
        boardSquares[7][7] = new Square(Square.SquareType.START);

        //Stores the SquareType
        Square.SquareType type;

        //The Board is mirrored on quadrants
        //For loop to run down the quadrants
        for (int i = 0; i < 7; i++) {
            //For loop to run across the quadrants
            for (int j = 0; j < 8; j++) {

                //Get the SquareType for the spot in the quadrant
                type = boardSquareType(i + "" + j);

                //Create the Square in each of the quadrants
                boardSquares[i][j] = new Square(type);
                boardSquares[j][14 - i] = new Square(type);
                boardSquares[14 - i][14 - j] = new Square(type);
                boardSquares[14 - j][i] = new Square(type);
            }
        }
    }

    /**
     * Method to find the SquareType of a Square in a quadrant of the Board
     *
     * @param ij The i j components in String form
     * @return The SquareType of the Square at the position in the quadrant
     */
    private Square.SquareType boardSquareType(String ij){
        Square.SquareType result;

        //Switch statement to find the SquareType for ij
        switch (ij){
            //TRIPLE_WORD
            case "00":
            case "07":
                result = Square.SquareType.TRIPLE_WORD;
                break;

            //DOUBLE_WORD
            case "11":
            case "22":
            case "33":
            case "44":
                result = Square.SquareType.DOUBLE_WORD;
                break;

            //TRIPLE_LETTER
            case "15":
            case "55":
            case "51":
                result = Square.SquareType.TRIPLE_LETTER;
                break;

            //DOUBLE_LETTER
            case "03":
            case "30":
            case "62":
            case "26":
            case "37":
            case "66":
                result = Square.SquareType.DOUBLE_LETTER;
                break;

            //Default is NORMAL
            default:
                result = Square.SquareType.NORMAL;
        }

        return result;
    }

    // Killian

    public void resetBoard(){

        //Set boardSquare
        boardSquares = new Square[15][15];

        //Fill the boardSquares
        newBoard();

    }

    /**
     *
     * @param i row coordinate
     * @param j column coordinate
     * @return True if inside Board, false if outside the Board
     */
    public boolean CoordinateValidationCheck(int i, int j)
    {
        return i < 15 && i >= 0 && j < 15 && j >= 0;
    }

    /**
     *
     * @param i row coordinate
     * @param j column coordinate
     * @return The Square at i and j
     * @throws InvalidBoardException Coordinates are not inside the Board
     */
    public Square getSquare(int i, int j)
    {
        if (CoordinateValidationCheck(i, j)){
            return this.boardSquares[i][j];
        }
        else {
            throw new InvalidBoardException("Coordinates are not inside the board.");
        }
    }

    /**
     * toString method that prints the board
     *
     * @return Returns the board as a string
     */
    @Override
    public String toString(){
                        //The board's title
        String result = "\t\t\t\t\t\t\t\tScrabble Board\n  |";


        //for loop creates the number columns
        for(int n=0; n<15; n++)
        {
            if(n<9)
            {
                result = result + " " + (n+1) + "  |";
            }
            else
            {
                result = result + " " + (n+1) + " |";
            }
        }

        //For loop for the row index
        for(int i=0;i<15;i++)
        {
            result = result + "\n______________________________________________________________________________\n";

            //If statement checks if row number is not a double digit
            if(i<9)
            {
                //Gives single digit numbers the same spacing as double digits
                result = result + " ";
            }
            result = result + (i+1) + "|";

            //Loop for the column index
            for(int j=0; j<15; j++)
            {
                result = result + boardSquares[i][j] + "|";
            }
        }

        return result;
    }


    /**
     * Method to check that a move from the player is valid
     * @param player: Player making the move
     * @param word: List of tiles requested to make the move
     * @param positions: Positions entered to place each Tile
     */
    public void checkValidMove(Player player, ArrayList<Tile> word, int[][] positions){

        // Checks that the word length is greater than 0
        checkWordLength(word);

        // Checks that the player has each of the Tiles in their Frame
        checkPlayerHasTiles(player, word);

        // Checks that all co-ordinates are on the board
        checkValidPosition(positions);


        // Checks that none of the entered positions don't already contain Tiles
        checkPositionContainsTile(positions);

        // Checks that all the positions are in a line
        checkPositionDirection(positions);
    }


    /**
     * Method to place a Tile on the board
     *
     * @param tile: Tile to be placed on the board
     * @param position_i: I position on the Board to place the Tile
     * @param position_j J position on the Board to place the Tile
     */
    private void placeTile(Tile tile, int position_i, int position_j){

        // Places the tile passed in onto the ij position on the board
        boardSquares[position_i][position_j].setTile(tile);
    }


    /**
     * Method to validate that a position passed in is on the board
     *
     * @param position: Position to check if its on the board
     */
    private void checkValidPosition(int[][] position){

        // Checks if the position is in the range of the board, if not exception is thrown
        for(int[] ints : position){
            if(ints[0] < 0 || ints[0] > 14 || ints[1] < 0 || ints[1] > 14){
                throw new InvalidBoardException("position not on board");
            }
        }
    }


    /**
     * Method to validate if the Player placing a list of Tiles has each Tile in his Frame
     *
     * @param player: Player to check if they have the necessary Tiles
     * @param word: List of Tiles to check
     */
    private void checkPlayerHasTiles(Player player, ArrayList<Tile> word){

        // Checks if the player has every Tile in their Frame, if not exception is thrown
        if(!(player.getPlayerFrame().checkTiles(word))){
            throw new InvalidFrameException("Player doesn't have the necessary tiles");
        }
    }


    /**
     * Method to check that words to be placed on the board are longer than 0 tiles
     * @param word: List of Tiles to check
     */
    private void checkWordLength(ArrayList<Tile> word){

        // Checks that the word contains a Tile, if not exception is thrown
        if(word.size() == 0){
            throw new InvalidBoardException("Word must be longer than 0 tiles");
        }
    }


    /**
     * Method to check if a position on the Board already has a Tile in it
     * @param position: Co-ordinates to check if a Tile in already in it
     */
    private void checkPositionContainsTile(int[][] position){

        // Checks that the position has a tile in it, if it does exception is thrown
        for(int[] ints : position){
            if(!(boardSquares[ints[0]][ints[1]].isEmpty())){
                throw new InvalidBoardException("Position already contains a tile");
            }
        }
    }


    /**
     * Method to check that a list of positions to place a word is in a line
     * @param position: List of co-ordinates to check they are part of a line of Tiles
     */
    private void checkPositionDirection(int[][] position){

        // Storing which orientation the list of positions are in
        boolean tempVertical = true;

        // Loops to check which direct the list of positions are not in the vertical direction
        for(int j = 0; j < position.length - 1;j++){

            if(position[j][0] != position[j+1][0]){

                tempVertical = false;
                break;
            }
        }

        // Check that tile tiles in the vertical direction
        if(tempVertical){

            // Sorts the values in position based on the j direction
            Arrays.sort(position, Comparator.comparingInt(a -> a[1]));

            // Checks that all there is no gap from the co-ordinates in position, if there is exception is thrown
            for(int j = 0; j < position.length - 1;j++){

                if(!(position[j][1]+1 == position[j+1][1] || !boardSquares[position[j][0]][position[j][1]+1].isEmpty())){

                    throw new InvalidBoardException("Tiles are not in a line on the board");
                }
            }

            return;
        }

        // Sorts the values in position based on the i direction
        Arrays.sort(position, Comparator.comparingInt(a -> a[0]));

        // Checks that all there is no gap from the co-ordinates in position, if there is exception is thrown
        for(int j = 0; j < position.length - 1;j++){

            if(!(position[j][0]+1 == position[j+1][0] || !boardSquares[position[j][0]+1][position[j][1]].isEmpty())){

                throw new InvalidBoardException("Tiles are not in a line on the board");
            }
        }
    }


    public static void main(String[] args) {
    }
}
