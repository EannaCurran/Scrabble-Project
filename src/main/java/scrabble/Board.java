package scrabble;

import scrabble.exceptions.InvalidBoardException;
import scrabble.exceptions.InvalidMoveInfoException;

import java.util.Arrays;


/**
 * The Board Class represents the Board for the Scrabble Game as an object
 */
public class Board {

    /**
     * Constant value for Board size
     */
    public final static int BOARD_SIZE = 15;

    /**
     * Bingo Score Bonus
     */
    public final static int BINGO = 50;

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
                boardSquares[j][BOARD_SIZE - 1 - i] = new Square(type);
                boardSquares[BOARD_SIZE - 1 - i][BOARD_SIZE - 1 - j] = new Square(type);
                boardSquares[BOARD_SIZE - 1 - j][i] = new Square(type);
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
     *
     * @param moveInfo The move to validate
     * @return true if valid move
     * @throws InvalidMoveInfoException If the Move is Invalid
     */
    protected boolean checkValidMove(MoveInfo moveInfo){

        boolean validMove = false;

        //If the word is at least length 2 and the start and end positions are valid
        if (moveInfo.getPrimaryWord().getWord().length >= 2 && checkValidPosition(moveInfo.getPrimaryWord().getStartPosition()) && checkValidPosition(moveInfo.getPrimaryWord().getDirection() == UserInput.Direction.VERTICAL ? new int[]{moveInfo.getPrimaryWord().getStartPosition()[0] + moveInfo.getPrimaryWord().getWord().length, moveInfo.getPrimaryWord().getStartPosition()[1]} : new int[]{moveInfo.getPrimaryWord().getStartPosition()[0], moveInfo.getPrimaryWord().getStartPosition()[1] + moveInfo.getPrimaryWord().getWord().length})){

            //The word os the whole Word on the Board
            if (wholeWord(moveInfo.getPrimaryWord())) {
                getRequiredTiles(moveInfo);
                findAuxiliaryWords(moveInfo);

                // Checks that the player has each of the Tiles in their Frame
                if (checkPlayerHasTiles(moveInfo.getPlayer(), moveInfo.getRequiredTiles())) {

                    //If the the Word connects to another Word or Start Square
                    if (checkWordConnects(moveInfo)){
                        validMove = true;
                    }
                    else {
                        throw new InvalidMoveInfoException("The Word did not connect to a Word or Start Square.\n");
                    }

                } else {
                    throw new InvalidMoveInfoException("The Player does not have the required Tiles for this move.\n");
                }
            }
            else {
                throw new InvalidMoveInfoException("The Word is not the whole Word on the Board.\n");
            }
        }
        else{
            throw new InvalidMoveInfoException("The Word does not fit on the Board or is less than 2 letters long.\n");
        }

        return validMove;
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
     *
     * @param moveInfo The move to place on the Board
     */
    public void placeTiles(MoveInfo moveInfo){

        if (checkValidMove(moveInfo)){

            //calculateScore(moveInfo);

            moveInfo.getPlayer().increaseScore(moveInfo.getMoveScore());

            // Loops through each move and places the Tile on the Board playerTurn = (playerTurn + 1) % 2;
            for(int i = 0; i < moveInfo.getRequiredTiles().length; i++){

                placeTile(moveInfo.getPlayer().getPlayerFrame().getTile(moveInfo.getRequiredTiles()[i]), moveInfo.getRequiredTilesPositions()[i][0], moveInfo.getRequiredTilesPositions()[i][1]);
            }
        }
        else {
            throw new InvalidMoveInfoException("The move is invalid.\n");
        }
    }



    /**
     * Method to validate that a position passed in is on the board
     *
     * @param position Position to check if its on the board
     * @return True if the position is valid
     */
    protected static Boolean checkValidPosition(int[] position){
        if (position.length == 2) {
            return position[0] >= 0 && position[0] < BOARD_SIZE && position[1] >= 0 && position[1] < BOARD_SIZE;
        }
        else{
            throw new InvalidBoardException("Positions have a i and j component.\n");
        }
    }



    /**
     * Method to validate if the Player placing a list of Tiles has each Tile in his Frame
     *
     *  @param player Player to check if they have the necessary Tiles
     * @param word List of Tiles to check
     * @return True if the player has the tiles
     */
    protected boolean checkPlayerHasTiles(Player player, char[] word){
        // Checks if the player has every Tile in their Frame
        return (player.getPlayerFrame().checkTiles(word));
    }



    /**
     * Method to check the Word connects on the Board
     * @param moveInfo Move to check
     *
     * @return True if valid
     */
    private Boolean checkWordConnects(MoveInfo moveInfo) {

        // Boolean to store if a connecting tile has been found
        boolean connectCheck = false;

        // Check if a the first tile of the game has been placed, since it doesn't need to have a connecting tile
        if (boardSquares[7][7].isEmpty()) {

            // If the first tile of the game hasn't been placed and the position [7][7] is not passed in
            if (moveInfo.getPrimaryWord().getDirection() == UserInput.Direction.VERTICAL ? (moveInfo.getPrimaryWord().getStartPosition()[1] == 7 && moveInfo.getPrimaryWord().getStartPosition()[0] <= 7 && moveInfo.getPrimaryWord().getStartPosition()[0] + moveInfo.getPrimaryWord().getWord().length >= 7) : (moveInfo.getPrimaryWord().getStartPosition()[0] == 7 && moveInfo.getPrimaryWord().getStartPosition()[1] <= 7 && moveInfo.getPrimaryWord().getStartPosition()[1] + moveInfo.getPrimaryWord().getWord().length  >= 7)) {
                connectCheck = true;
            }
        }
        //Else if the word made auxiliary Words or the required Tiles is less than the total chars in the Word
        else if(moveInfo.getAuxiliaryWords().size() > 0 || moveInfo.getRequiredTiles().length < moveInfo.getPrimaryWord().getWord().length){
                connectCheck = true;
        }

        return connectCheck;
    }


    /**
     * Method to find the required Tiles that the Player needs to place
     *
     * @param moveInfo The move to get required Tiles
     * @throws InvalidMoveInfoException The Word does not match current Tiles on the Board
     */
    public void getRequiredTiles(MoveInfo moveInfo){

        int[][] tilePositions = new  int[moveInfo.getPrimaryWord().getWord().length][2];
        char[] tiles = new char[moveInfo.getPrimaryWord().getWord().length];

        int numTiles = 0;

        //For loop to run through each char in the word and find if a tile needs to be placed
        for (int i = 0; i < moveInfo.getPrimaryWord().getWord().length; i++){

            //Current position changes which axis is increased
            int[] currentPosition = moveInfo.getPrimaryWord().getDirection() == UserInput.Direction.VERTICAL? new int[]{moveInfo.getPrimaryWord().getStartPosition()[0] + i, moveInfo.getPrimaryWord().getStartPosition()[1]}: new int[]{moveInfo.getPrimaryWord().getStartPosition()[0], moveInfo.getPrimaryWord().getStartPosition()[1] + i};

            Square currentSquare = this.getSquare(currentPosition[0], currentPosition[1]);

            //If the Square is empty then a  tile is required
            if (currentSquare.isEmpty()){
                tilePositions[numTiles] = currentPosition;
                tiles[numTiles] = moveInfo.getPrimaryWord().getWord()[i];
                numTiles++;
            }
            //Else check the tiles on the board match the word
            else if (moveInfo.getPrimaryWord().getWord()[i] != currentSquare.getTile().getCharacter()){
                throw new InvalidMoveInfoException("Word does not match tiles on the board.\n");
            }
        }

        //Set the required Tiles. Trim the array size to match numTiles
        moveInfo.setRequiredTiles(Arrays.copyOfRange(tiles, 0, numTiles), Arrays.copyOfRange(tilePositions, 0, numTiles));
    }

    /**
     * Method to check the word is the whole word
     *
     * @param word Word to check
     * @return True if whole word
     */
    private boolean wholeWord(Word word){

        boolean result;

        //If Word is vertical
        if (word.getDirection() == UserInput.Direction.VERTICAL){
            //The start tile is the top edge of the board or the previous square is empty and the last tile is the bottom edge or the next square is empty
            result = (word.getStartPosition()[0] == 0 || this.getSquare(word.getStartPosition()[0] - 1, word.getStartPosition()[1]).isEmpty()) && (word.getStartPosition()[0] + word.getWord().length== BOARD_SIZE - 1 || this.getSquare(word.getStartPosition()[0] + word.getWord().length + 1, word.getStartPosition()[1]).isEmpty());
        }
        //Else horizontal
        else {
            //The start tile is the left edge of the board or the previous square is empty and the last tile is the right edge or the next square is empty
            result = (word.getStartPosition()[1] == 0 || this.getSquare(word.getStartPosition()[0], word.getStartPosition()[1] - 1).isEmpty()) && (word.getStartPosition()[1] + word.getWord().length == BOARD_SIZE - 1 || this.getSquare(word.getStartPosition()[0], word.getStartPosition()[1] + word.getWord().length + 1).isEmpty());
        }

        return result;
    }


    /**
     * Method to find all the auxiliary Words of a move
     *
     * @param moveInfo The move
     */
    private void findAuxiliaryWords(MoveInfo moveInfo){

        //If the primaryWord is Vertical
        if(moveInfo.getPrimaryWord().getDirection() == UserInput.Direction.VERTICAL) {

            //For loop to go through each of requiredTiles
            for (int i = 0; i < moveInfo.getRequiredTiles().length; i++) {

                int[] currentPosition = moveInfo.getRequiredTilesPositions()[i];

                //If the previous tile Horizontal is valid
                if (checkValidPosition(new int[]{currentPosition[0], currentPosition[1] - 1}) && !getSquare(currentPosition[0], currentPosition[1] - 1).isEmpty()) {
                    //Find and add the newly modified Word to the auxiliaryWords
                    moveInfo.addAuxiliaryWord(findWord(new int[]{moveInfo.getRequiredTilesPositions()[i][0], moveInfo.getRequiredTilesPositions()[i][1] - 1}, UserInput.Direction.HORIZONTAL));
                }
                //Else if the next tile Horizontal is valid
                else if (checkValidPosition(new int[]{currentPosition[0], currentPosition[1] + 1}) && !getSquare(currentPosition[0], currentPosition[1] + 1).isEmpty()) {
                    //Find and add the newly modified Word to the auxiliaryWords
                    moveInfo.addAuxiliaryWord(findWord(new int[]{moveInfo.getRequiredTilesPositions()[i][0], moveInfo.getRequiredTilesPositions()[i][1] + 1}, UserInput.Direction.HORIZONTAL));
                }

            }
        }
        //Horizontal
        else {
            for (int i = 0; i < moveInfo.getRequiredTiles().length; i++) {

                int[] currentPosition = moveInfo.getRequiredTilesPositions()[i];

                //If the previous tile Vertical is valid
                if (checkValidPosition(new int[]{currentPosition[0] - 1 , currentPosition[1]}) && !getSquare(currentPosition[0] - 1, currentPosition[1]).isEmpty()) {
                    //Find and add the newly modified Word to the auxiliaryWords
                    moveInfo.addAuxiliaryWord(findWord(new int[]{moveInfo.getRequiredTilesPositions()[i][0] - 1, moveInfo.getRequiredTilesPositions()[i][1]}, UserInput.Direction.VERTICAL));
                }
                //Else if the next tile Vertical is valid
                else if (checkValidPosition(new int[]{currentPosition[0] + 1 , currentPosition[1]}) && !getSquare(currentPosition[0] + 1, currentPosition[1]).isEmpty()) {
                    //Find and add the newly modified Word to the auxiliaryWords
                    moveInfo.addAuxiliaryWord(findWord(new int[]{moveInfo.getRequiredTilesPositions()[i][0] + 1, moveInfo.getRequiredTilesPositions()[i][1]}, UserInput.Direction.VERTICAL));
                }
            }
        }
    }

    /**
     * Method to find the Word given a position on the Board and Direction
     *
     * @param position Position in the Word
     * @param direction Direction of the Word
     * @return The Word
     * @throws InvalidBoardException The position is invalid
     */
    private Word findWord(int[] position, UserInput.Direction direction){

        if (position.length == 2 && checkValidPosition(position)){

            char[] word = new char[BOARD_SIZE];

            int[] currentPosition = position;
            Square currentSquare;

            //Direction of the position to update
            int dirIndex = direction == UserInput.Direction.VERTICAL? 0:1;

            //While loop till board edge or empty Square
            while(checkValidPosition(currentPosition) && getSquare(currentPosition[0], currentPosition[1]).isEmpty()){
                currentPosition[dirIndex]--;
            }

            currentPosition[dirIndex]++;
            position = currentPosition;

            int i;

            //For loop to get the word chars
            for (i = 0; i < word.length && checkValidPosition(currentPosition) && (currentSquare = getSquare(currentPosition[0], currentPosition[1])).isEmpty(); i++) {

                word[i] = currentSquare.getTile().getCharacter();
                currentPosition[dirIndex]++;
            }

            //Return the Word with the array shorted to just the chars
            return new Word(position, direction, Arrays.copyOfRange(word, 0, i));
        }
        else{
            throw new InvalidBoardException("Position invalid position inputted.\n");
        }
    }

    /**
     * Method to calculate the score of a move
     *
     * @param moveInfo Move to calculate the score
     */
    protected void calculateScore(MoveInfo moveInfo){

        //Calculate the score of the primary Word
        int result = calculateScoreWord(moveInfo.getPrimaryWord());

        //For loop to calculate the score of all the auxiliary Words
        for (int i = 0; i < moveInfo.getAuxiliaryWords().size(); i++) {
            result += calculateScoreWord(moveInfo.getAuxiliaryWords().get(i));
        }

        //If the move is a Bingo
        if (moveInfo.getRequiredTiles().length == Frame.FRAME_SIZE){
            result += BINGO;
        }

        moveInfo.setScore(result);
    }

    /**
     *Method to calculate the score of a Word
     *
     * @param word The word to calculate the score
     * @return THe score of the Word
     */
    private int calculateScoreWord(Word word){

        int wordFactor = 1, result = 0;
        Square currentSquare;

        //For loop to run through each Square in the Word
        for (int i = 0; i < word.getWord().length; i++) {

            //Get the current Square based on Word Direction
            currentSquare = word.getDirection() == UserInput.Direction.VERTICAL? getSquare(word.getStartPosition()[0] + i,word.getStartPosition()[1] ): getSquare(word.getStartPosition()[0] ,word.getStartPosition()[1] + i);

            //Switch statement to calculate based on SquareType
            switch (currentSquare.getType()){

                case TRIPLE_WORD:
                    wordFactor *= 3;
                    result += currentSquare.getTile().getValue();
                    break;
                case DOUBLE_WORD:
                case START:
                    wordFactor *= 2;
                    result += currentSquare.getTile().getValue();
                    break;
                case TRIPLE_LETTER:
                    result += 3 * currentSquare.getTile().getValue();
                    break;
                case DOUBLE_LETTER:
                    result += 2 * currentSquare.getTile().getValue();
                    break;
                default:
                    result += currentSquare.getTile().getValue();
            }
        }
        return result * wordFactor;
    }

    /**
     * Method to set all Squares under a word to Normal
     *
     * @param word The Word
     */
    protected void setWordSquaresNormal(Word word){
        Square currentSquare;

        //For loop to set all tile under the word to Normal
        for (int i = 0; i < word.getWord().length; i++) {
            //Get the current Square based on Word Direction
            currentSquare = word.getDirection() == UserInput.Direction.VERTICAL? getSquare(word.getStartPosition()[0] + i,word.getStartPosition()[1] ): getSquare(word.getStartPosition()[0] ,word.getStartPosition()[1] + i);

            currentSquare.setNormal();
        }
    }

    /**
     * Method to remove the Tiles place in a move
     *
     * @param moveInfo The move to remove
     */
    public void removeMove(MoveInfo moveInfo){

        //For loop to set the squares back to empty
        for (int i = 0; i < moveInfo.getRequiredTiles().length; i++) {

            getSquare(moveInfo.getRequiredTilesPositions()[i][0], moveInfo.getRequiredTilesPositions()[i][1]).setEmpty();
        }
    }

}
