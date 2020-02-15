package scrabble;

import java.util.ArrayList;

public class Board {

    // Johns
    public Board(){

    }

    // Killian

    public void resetBoard(){

    }


    /**
     * toString method that prints the board
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

    public boolean checkValidMove(ArrayList<Tile> word){
        return true;
    }

    public void placeWord(ArrayList<Tile> word){

    }
}
