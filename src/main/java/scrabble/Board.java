package scrabble;

import java.util.ArrayList;

public class Board {

    // Johns
    public Board(){

    }

    // Killian

    public void resetBoard(){

    }

    @Override
    public String toString(){
        String result = "/t/t Scrabble Board";

        for(int i=0;i<15;i++)
        {
            result = result + "\n________________________________________________\n";

            if(i<9)
            {
                result = result + " ";
            }
        }

        return null;
    }

    // Eanna

    public boolean checkValidMove(ArrayList<Tile> word){
        return true;
    }

    public void placeWord(ArrayList<Tile> word){

    }
}
