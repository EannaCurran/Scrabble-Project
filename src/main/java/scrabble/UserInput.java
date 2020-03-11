package scrabble;

public class UserInput {

    public final static int BOARD_SIZE = 15;

    //Stores user input information after user input is broken down and parsed
    private int userWordLength;
    private String userWord;
    private direction directionInput;
    private userInputType InputType;

    /**
     * direction is an enum type for the types directions a word can be placed on a Board
     */
    enum direction{
        VERTICAL,
        HORIZONTAL
    }

    /**
     * userInputType is an enum type for the types of possible expected user inputs
     */
    enum userInputType{
        QUIT,
        HELP,
        PASS,
        EXCHANGE,
        PLACE_TILE,
        ERROR
    }

    /**
     *
     * @param input The string that the User inputted into the FX console which will be broken down and parsed
     * @return returns false if a user did an invalid input or inputted "HELP". Returns true for a valid input
     */
    public boolean parseInput(String input)
    {

        if(input == "QUIT" || input == "quit" || input == "Quit")
        {
            InputType = userInputType.QUIT;
            //Endgame method
        }

        if(input == "HELP" || input == "help" || input == "Help")
        {
            InputType = userInputType.HELP;

            System.out.println("<grid ref> <across/down> <word> (where <grid ref> is\n" +
                    "the position for the first letter in terms of rows and columns (i and j),\n " +
                    "<across/down> is the direction of word placement and <word>\n" +
                    "is the word to be placed), e.g. “1 1 A HELLO”; “EXCHANGE <letters>");
            return false;
        }

        if(input == "PASS" || input == "pass" || input == "Pass")
        {
            //Passes turn
            InputType = userInputType.PASS;
            return true;
        }

        String[] tokens = input.split(" ");

        //Checks row coordinate is a number
        try {
            int rowCoordinate = Integer.parseInt(tokens[0]);
        } catch (NumberFormatException e) {
            System.out.println(tokens[0] + " is not a valid row coordinate, please try again.");
            InputType = userInputType.ERROR;
            return false;
        }
        int rowCoordinate = Integer.parseInt(tokens[0]);

        //Checks column coordinate is a number
        try {
            int columnCoordinate = Integer.parseInt((tokens[1]));
        } catch (NumberFormatException e) {
            System.out.println(tokens[0] + " is not a valid column coordinate, please try again.");
            InputType = userInputType.ERROR;
            return false;
        }
        int columnCoordinate = Integer.parseInt((tokens[1]));


        if(CoordinateValidationCheck(rowCoordinate, columnCoordinate)) {
        }
        else {
            System.out.println("Invalid coordinates were given, please give numbers between 1-15 and try again.");
            InputType = userInputType.ERROR;
            return false;
        }


        if(tokens[3] == "A" || tokens[3] == "a")
        {
            directionInput = direction.HORIZONTAL;
        }
        else if(tokens[3] == "D" || tokens[3] == "d")
        {
            directionInput = direction.VERTICAL;
        }
        else{
            //Invalid input
            return false;
        }

        userWordLength = tokens[4].length();
        userWord = tokens[4];

        InputType = userInputType.PLACE_TILE;
        //TODO place word method/return parsed stuff
        return true;
    }


    private boolean CoordinateValidationCheck(int rowCoordinate, int columnCoordinate) {
        return rowCoordinate < Board.BOARD_SIZE && rowCoordinate >= 0 && columnCoordinate < BOARD_SIZE && columnCoordinate >= 0;
    }


}
