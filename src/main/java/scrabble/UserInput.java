package scrabble;


import scrabble.exceptions.InvalidInputException;

public class UserInput {

    private UserInputType inputType;

    private char[] word = null;

    private int[] startPosition = null;

    private direction wordDirection = null;


    //Constructors

    //For one token for instances such as 'HELP' or 'PASS'
    public UserInput(UserInputType type){
        inputType = type;
    }

    public UserInput(UserInputType type, char[] tileExchange){
        inputType = type;
        if(new String(tileExchange).matches("^[A-Z]{1,7}+$")) {
            word = tileExchange;
        }
        else{
           throw new InvalidInputException("Invalid characters for tile exchange");
        }
    }

    public UserInput(UserInputType type, char[] w, int[] position, direction d){
        inputType = type;

        word = w;

        startPosition = position;

        wordDirection = d;

    }

    //Accessors
    public UserInputType getInputType() {
        return inputType;
    }

    public char[] getWord() {
        return word;
    }

    public int[] getStartPosition() {
        return startPosition;
    }

    public direction getWordDirection() {
        return wordDirection;
    }

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
    enum UserInputType {
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
     * @return returns an oject
     */
    public static UserInput parseInput(String input) {

        direction directionInput = null;


        int[] position = new int[2];

        UserInput inputData = null;

        String[] tokens = input.split("\\s");

        if(input.isEmpty())
        {
            inputData = new UserInput(UserInputType.ERROR);

        }
        else {
                switch (tokens[0])
                {
                    case "QUIT":
                    case "Quit":
                    case "quit":
                        inputData = new UserInput(UserInputType.QUIT);
                        break;
                    case "HELP":
                    case "Help":
                    case "help":
                        inputData = new UserInput(UserInputType.HELP);
                        break;
                    case "PASS":
                    case "Pass":
                    case "pass":
                        inputData = new UserInput(UserInputType.PASS);
                        break;
                    case "Exchange":
                    case "EXCHANGE":
                    case "exchange":
                        try {
                            inputData = new UserInput(UserInputType.EXCHANGE, tokens[1].toCharArray());
                        } catch (Exception e) {
                            inputData = new UserInput(UserInputType.ERROR);
                        }
                        break;
                    default:
                        if (tokens[0].matches("^[A-O][\\d]$"))
                        {
                            try {

                                char[] temp = tokens[0].toCharArray();
                                position[0] = temp[0] - 'A';
                                position[1] = Integer.parseInt(tokens[0].substring(1));


                                directionInput = null;

                                if (tokens.length == 3) {

                                    switch (tokens[1]) {
                                        case "A":
                                        case "a":
                                            directionInput = direction.HORIZONTAL;
                                            break;

                                        case "D":
                                        case "d":
                                            directionInput = direction.VERTICAL;
                                            break;

                                        default:


                                    }


                                    if (directionInput != null) {
                                        inputData = new UserInput(UserInputType.PLACE_TILE, tokens[3].toCharArray(), position, directionInput);
                                    } else {
                                        inputData = new UserInput(UserInputType.ERROR);
                                    }
                                }

                                } catch(Exception e){
                                    inputData = new UserInput(UserInputType.ERROR);
                                }


                        }
                            else{
                                    inputData = new UserInput(UserInputType.ERROR);
                                }

                }
        }
        return inputData;

        }

        /*
        if(input == "QUIT" || input == "quit" || input == "Quit")
        {
            InputType = UserInputType.QUIT;
            //Endgame method
        }

        if(input == "HELP" || input == "help" || input == "Help")
        {
            InputType = UserInputType.HELP;

            System.out.println("<grid ref> <across/down> <word> (where <grid ref> is\n" +
                    "the position for the first letter in terms of rows and columns (i and j),\n " +
                    "<across/down> is the direction of word placement and <word>\n" +
                    "is the word to be placed), e.g. “1 1 A HELLO; EXCHANGE <letters>");
            return false;
        }

        if(input == "PASS" || input == "pass" || input == "Pass")
        {
            //Passes turn
            InputType = UserInputType.PASS;
            return true;
        }


        //Checks row coordinate is a number
        try {
            int rowCoordinate = Integer.parseInt(tokens[0]);
        } catch (NumberFormatException e) {
            System.out.println(tokens[0] + " is not a valid row coordinate, please try again.");
            InputType = UserInputType.ERROR;
            return false;
        }
        int rowCoordinate = Integer.parseInt(tokens[0]);

        //Checks column coordinate is a number
        try {
            int columnCoordinate = Integer.parseInt((tokens[1]));
        } catch (NumberFormatException e) {
            System.out.println(tokens[0] + " is not a valid column coordinate, please try again.");
            InputType = UserInputType.ERROR;
            return false;
        }
        int columnCoordinate = Integer.parseInt((tokens[1]));


        if(CoordinateValidationCheck(rowCoordinate, columnCoordinate)) {
        }
        else {
            System.out.println("Invalid coordinates were given, please give numbers between 1-15 and try again.");
            InputType = UserInputType.ERROR;
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

        InputType = UserInputType.PLACE_TILE;
        return true;


    } */

}
