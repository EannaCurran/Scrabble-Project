package scrabble;

import scrabble.exceptions.InvalidBoardException;

import java.util.Arrays;
import java.util.Comparator;


/**
 * The Board Class represents the Board for the Scrabble Game as an object
 */
public class Board {



    /**
     * Constant value for Board size
     */
    public final static int BOARD_SIZE = 15;



    /**
     * The Array of Square that holds the 15x15 Squares of the Board
     */
    private Square[][] boardSquares;





    /**
     * Board Constructor
     */
    public Board(){

        //Set boardSquare
        boardSquares = new Square[BOARD_SIZE][BOARD_SIZE];

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
     * Method to return the current Board
     * @return the current Board
     */
    public Square[][] getBoardSquares(){
        return this.boardSquares;
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



    /**
     * Method to reset the Board
     */
    public void resetBoard(){

        //Set boardSquare
        boardSquares = new Square[BOARD_SIZE][BOARD_SIZE];

        //Fill the boardSquares
        newBoard();
    }



    /**
     * Method checks if coordinates are valid
     *
     * @param i row coordinate
     * @param j column coordinate
     * @return True if inside Board, false if outside the Board
     */
    private boolean CoordinateValidationCheck(int i, int j)
    {
        return i < BOARD_SIZE && i >= 0 && j < BOARD_SIZE && j >= 0;
    }



    /**
     * Method that returns a specific board square
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
     * toString method that prints the Board
     *
     * @return Returns the board as a string
     */
    @Override
    public String toString(){
                        //The board's title
        StringBuilder result = new StringBuilder("\t\t\t\t\t\t\t\tScrabble Board\n  |");


        //for loop creates the number columns
        for(int n=0; n < BOARD_SIZE; n++)
        {
            if(n<9)
            {
                result.append(" ").append(n + 1).append("  |");
            }
            else
            {
                result.append(" ").append(n + 1).append(" |");
            }
        }

        //For loop for the row index
        for(int i=0; i < BOARD_SIZE;i++)
        {
            result.append("\n______________________________________________________________________________\n");

            //If statement checks if row number is not a double digit
            if(i<9)
            {
                //Gives single digit numbers the same spacing as double digits
                result.append(" ");
            }
            result.append(i + 1).append("|");

            //Loop for the column index
            for(int j=0; j < BOARD_SIZE; j++)
            {
                result.append(boardSquares[i][j]).append("|");
            }
        }

        return result.toString();
    }



    /**
     * Method to check that a move from the player is valid
     * @param player: Player making the move
     * @param word: List of tiles requested to make the move
     * @param positions: Positions entered to place each Tile
     */
    protected void checkValidMove(Player player, char[] word, int[][] positions){

        // Checks that the word length is greater than 0
        checkWordLength(word);

        // Checks that the player has each of the Tiles in their Frame
        checkPlayerHasTiles(player, word);

        // Checks that all co-ordinates are on the board
        //TODO checkValidPosition(positions[);

        // Checks that none of the entered positions don't already contain Tiles
        checkPositionContainsTile(positions);

        // Checks that all the positions are in a line
        checkPositionLine(positions);

        // Checks that the inputted positions connect to a Tile already on the board
        checkWordConnects(positions);
    }



    /**
     * Method to place a Tile on the board
     *
     * @param tile: Tile to be placed on the Board
     * @param position_i: I position on the Board to place the Tile
     * @param position_j J position on the Board to place the Tile
     */
     protected void placeTile(Tile tile, int position_i, int position_j){

        // Places the tile passed in onto the ij position on the board
        boardSquares[position_i][position_j].setTile(tile);
    }



    /**
     * Method for a Player to place a list of Tiles on the Board
     * @param player: Person to place Tiles
     * @param word: Characters to place on the Board
     * @param positions: List of positions to place Tiles on the Board
     */
    public void placeTiles(Player player, char[] word, int[][] positions){

        // Validates the players move
        checkValidMove(player, word, positions);

        // Loops through each move and places the Tile on the Board
        for(int i = 0; i < word.length; i++){

            placeTile(player.getPlayerFrame().getTile(word[i]), positions[i][0], positions[i][1]);
            player.getPlayerFrame().removeTile(word[i]);
        }

        player.getPlayerFrame().fillFrame();
    }



    /**
     * Method to validate that a position passed in is on the board
     *
     * @param position Position to check if its on the board
     * @return True if the position is valid
     */
    protected Boolean checkValidPosition(int[] position){

       return position[0] >= 0 && position[0] < 15 && position[1] >= 0 && position[1] < 15;

    }



    /**
     * Method to validate if the Player placing a list of Tiles has each Tile in his Frame
     *
     * @param player: Player to check if they have the necessary Tiles
     * @param word: List of Tiles to check
     */
    protected void checkPlayerHasTiles(Player player, char[] word){

        // Checks if the player has every Tile in their Frame, if not exception is thrown
        if (!(player.getPlayerFrame().checkTiles(word))){

            throw new InvalidBoardException("Player doesn't have the necessary Tiles\n");
        }
    }



    /**
     * Method to check that words to be placed on the board are valid lengths
     * @param word: List of Tiles to check
     */
    protected void checkWordLength(char[] word){

        // Checks that the word contains a Tile, if not exception is thrown
        if(word.length == 0){

            throw new InvalidBoardException("Word must be longer than 0 Tiles\n");
        }

        if(word.length > 7){

            throw new InvalidBoardException("Cannot place more than 7 Tiles\n");
        }
    }



    /**
     * Method to check if a position on the Board already has a Tile in it
     * @param position: Co-ordinates to check if a Tile in already in it
     */
    protected void checkPositionContainsTile(int[][] position){

        // Checks that the position has a tile in it, if it does exception is thrown
        for(int[] ints : position){

            if(!(boardSquares[ints[0]][ints[1]].isEmpty())){

                throw new InvalidBoardException("Position already contains a Tile\n");
            }
        }
    }



    /**
     * Method to check that a list of positions to place a word is in a line
     * @param position: List of co-ordinates to check they are part of a line of Tiles
     */
    protected void checkPositionLine(int[][] position){

        // Storing which orientation the list of positions are in
        boolean tempVertical = true;

        // Loops to check which direct the list of positions are not in the vertical direction
        for(int j = 0; j < position.length - 1;j++){

            if(position[j][0] != position[j+1][0]){

                tempVertical = false;
                break;
            }
        }

        //Loops to check that the horizontal list of positions are in on the same
        if(!tempVertical){

            for(int j = 0; j < position.length - 1;j++){

                if(position[j][1] != position[j+1][1]){

                    throw new InvalidBoardException("Tiles are not in a line on the Board\n");
                }
            }
        }

        // Check that tile tiles in the vertical direction
        if(tempVertical){

            // Sorts the values in position based on the j direction
            Arrays.sort(position, Comparator.comparingInt(a -> a[1]));

            // Checks that all there is no gap from the co-ordinates in position and co-ordinates are not diagonal, if there is exception is thrown
            for(int j = 0; j < position.length - 1;j++){

                if((!((position[j][1]+1 == position[j+1][1] && position[j][0] == position[j+1][0]) || !boardSquares[position[j][0]][position[j][1]+1].isEmpty()))){

                    throw new InvalidBoardException("Tiles are not in a line on the Board\n");
                }
            }

            return;
        }

        // Sorts the values in position based on the i direction
        Arrays.sort(position, Comparator.comparingInt(a -> a[0]));

        // Checks that all there is no gap from the co-ordinates in position and co-ordinates are not diagonal, if there is exception is thrown
        for(int j = 0; j < position.length - 1;j++){

            if((!((position[j][0]+1 == position[j+1][0] && position[j][1] == position[j + 1][1] || !boardSquares[position[j][0]+1][position[j][1]].isEmpty())))){

                throw new InvalidBoardException("Tiles are not in a line on the Board\n");
            }
        }
    }



    /**
     * Method to check if a list of positions connect with a tile already on the board
     * @param position: List of positions to check if any of them would connect with a tile on the board
     */
    protected void checkWordConnects(int[][] position){

        // Boolean to store if a connecting tile has been found
        boolean connectCheck = false;

        // Check if a the first tile of the game has been placed, since it doesn't need to have a connecting tile
        if(boardSquares[7][7].isEmpty() ){

            // If the first tile of the game hasn't been placed and the position [7][7] is not passed in, exception is thrown
            for (int[] ints : position) {

                if (ints[0] == 7 && ints[1] == 7) {

                    return;
                }
            }

            throw new InvalidBoardException("First word in game must be placed within [7][7]\n");
        }

        // Loops through each co-ordinate in position, checks if any of the surrounding positions contain tiles, if it does
        // connectCheck is set to true and loop is broken, edge guarding included for any positions on the edge of the board;
        for (int[] ints : position) {

            if (checkValidPosition(new int[]{ints[0] + 1, ints[1]})){

            }

        }
        // If none of the positions connect to a tile on the board, InvalidException is thrown
        if(!connectCheck){
            throw new InvalidBoardException("Placed Tiles not connected to any Tiles\n");
        }
    }



}
