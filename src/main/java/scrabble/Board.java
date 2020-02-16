package scrabble;

import scrabble.exceptions.InvalidBoardException;

import java.util.ArrayList;


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
     * toString method that prints the Board
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

    // Eanna

    public boolean checkValidMove(ArrayList<Tile> word){
        return true;
    }

    public void placeWord(ArrayList<Tile> word){

    }

    public static void main(String[] args) {
        Board board = new Board();
        System.out.println(board);
    }
}
