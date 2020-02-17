package scrabble;

import scrabble.exceptions.InvalidBoardException;

import java.lang.management.PlatformLoggingMXBean;
import java.lang.reflect.Array;
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
                //PLACEHOLDER
                result = result + "    |";
                //result = result + boardSquares[i][j] + "|";
            }
        }

        return result;
    }

    // Eanna

    private void placeTile(Tile tile, int position_i, int position_j){
            boardSquares[position_i][position_j].setTile(tile);
    }

    public void checkValidMove(Player player, ArrayList<Tile> word, int[][] positions){

        boolean Vertical;

        checkWordLength(word);
        checkPlayerHasTiles(player, word);

        for(int i = 0; i < positions.length; i++){
            for(int j = 0; j < 2; j++){
                checkValidPosition(positions[i][j]);
            }

            checkPositionContainsTile(positions[i]);
        }

        Vertical = checkPositionDirection(positions);

    }

    private void checkValidPosition(int position){
        if(position < 0 ||  position > 14){
            throw new InvalidBoardException("position not on board");
        }
    }

    private void checkPlayerHasTiles(Player player, ArrayList<Tile> word){
        if(!(player.getPlayerFrame().checkTiles(word)) ){
            throw new InvalidBoardException("Player doesn't have the necessary tiles");
        }
    }

    private void checkWordLength(ArrayList<Tile> word){
        if(word.size() == 0){
            throw new InvalidBoardException("Word must be longer than 0 tiles");
        }
    }

    private void checkPositionContainsTile(int[] position){
        if(!(boardSquares[position[0]][position[1]].isEmpty())){
            throw new InvalidBoardException("Position already contains a tile");
        }

    }

    private boolean checkPositionDirection(int[][] position){

        boolean tempVertical = false;
        for(int j = 0; j < position.length - 1;j++){
            if(position[j][0] == position[j+1][0]){

                tempVertical = true;
            }
            else{
                tempVertical = false;
                break;
            }
        }

        if(tempVertical == true){
            Arrays.sort(position, Comparator.comparingInt(a -> a[1]));
            for(int j = 0; j < position.length - 1;j++){
                if(!(position[j][1]+1 == position[j+1][1] || !boardSquares[position[j][0]][position[j][1]+1].isEmpty())){

                    throw new InvalidBoardException("Tiles are not in a line on the board");
                }
            }
            return true;
        }

        for(int i = 0; i < position.length - 1; i++){

            if(position[i][1] == position[i+1][1]){

                tempVertical = false;
            }
            else {
                throw new InvalidBoardException("tiles in line aok");

            }

        }

        if(tempVertical == false){
            Arrays.sort(position, Comparator.comparingInt(a -> a[0]));
            System.out.println(Arrays.deepToString(position));
            for(int j = 0; j < position.length - 1;j++){
                System.out.println(position[j+1][0]);
                if(!(position[j][0]+1 == position[j+1][0] || !boardSquares[position[j][0]+1][position[j][1]].isEmpty())){

                    throw new InvalidBoardException("Tiles are not in a line on the board");
                }
            }
        }

        return false;

    }

    public static void main(String[] args) {

    }

}
