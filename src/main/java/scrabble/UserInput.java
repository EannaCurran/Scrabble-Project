package scrabble;


import scrabble.exceptions.InvalidInputException;

public class UserInput {

    //An enum type that stores the type of input that the user inputs
    private UserInputType inputType;
    //Stores the word that the user wants to place on the Board or the Tiles the user wants to exchange
    private char[] word = null;
    //Stores the coordinates of the first letter of that word that is to be placed on the board
    private int[] startPosition = null;
    //A enum type that stores the direction that word is to be placed
    private Direction wordDirection = null;


    //Constructors
    /**
     * Constructor for cases with one token such as 'HELP' or 'PASS'
     * @param type The UserInputType
     */
    public UserInput(UserInputType type){
        inputType = type;
    }

    /**
     * Constructor for when user is swapping a Tile(s)
     * @param type The UserInputType
     * @param tileExchange An array of characters that are to be swapped
     */
    public UserInput(UserInputType type, char[] tileExchange){
        inputType = type;
        //Checks if Tiles are capital alphabetical letters and there is between 1 to 7 Tiles (frame size is 7)
        if(new String(tileExchange).matches("^[A-Z]{1,7}+$")) {
            //Tiles that to be exchanged is stored in the variable word
            word = tileExchange;
        }
        else{
           throw new InvalidInputException("Invalid characters for tile exchange");
        }
    }

    /**
     * Constructor for when the user wants to place a word on the Board
     * @param type The UserInputType
     * @param w  The word that the user wants to place on the Board
     * @param position The position of the coordinate of the first Tile of the word
     * @param d The direction of the word
     */
    public UserInput(UserInputType type, char[] w, int[] position, Direction d){
        inputType = type;
        word = w;
        startPosition = position;
        wordDirection = d;
    }

    //Accessors
    /**
     * Accessor method for getting the type of input
     * @return Returns the input type
     */
    public UserInputType getInputType() {
        return inputType;
    }

    /**
     * Accessor method for getting the word when placing a Tile.
     * Also used for accessing the Tiles a user wants to swap.
     * @return Returns an array of characters which contains either the word the user wants to place or the Tiles the user wants to swap
     */
    public char[] getWord() {
        return word;
    }

    /**
     * Accessor method for getting the coordinates of the first Tile of the word the user wants to place on the board
     * @return Returns the coordinates of the first Tile of the word the user wants to place on the board
     */
    public int[] getStartPosition() {
        return startPosition;
    }

    /**
     * Accessor method for getting the direction of the word that is to be placed on the Board
     * @return Returns the direction of the word that is to be placed on the Board
     */
    public Direction getWordDirection() {
        return wordDirection;
    }

    /**
     * direction is an enum type for the types directions a word can be placed on a Board
     */
    enum Direction{
        VERTICAL,
        HORIZONTAL
    }

    /**
     * UserInputType is an enum type for the types of possible expected user inputs
     */
    public enum UserInputType {
        QUIT,
        HELP,
        PASS,
        EXCHANGE,
        PLACE_TILE,
        ERROR,
        BLANK
    }

    /**
     * @param input The string that the User inputted into the FX console which will be broken down and parsed
     * @return Returns an object UserInput which contains the user's input which has been broken down and parsed
     */
    public static UserInput parseInput(String input) {

        //Declaring a Direction enum type to store the direction the user inputs
        Direction directionInput;
        //An array to store the coordinates of the first letter of the word to be placed on the board
        int[] position = new int[2];
        // A UserInput object to store all the data
        UserInput inputData = null;

        //Splits up the user's input into tokens in a String array
        String[] tokens = input.split("[\\w]+");

        //Checks if the input is empty
        if(input.isEmpty())
        {
            //Sets input type to ERROR as there is no input
            inputData = new UserInput(UserInputType.ERROR);

        }
        else {
            //Switch statement that checks the data of the first token of the tokenised input String
                switch (tokens[0])
                {
                    //QUIT case sets the input type to QUIT
                    case "QUIT":
                    case "Quit":
                    case "quit":
                        inputData = new UserInput(UserInputType.QUIT);
                        break;
                    //HELP case sets the input type to HELP
                    case "HELP":
                    case "Help":
                    case "help":
                        inputData = new UserInput(UserInputType.HELP);
                        break;
                    //PASS case sets the input type to PASS
                    case "PASS":
                    case "Pass":
                    case "pass":
                        inputData = new UserInput(UserInputType.PASS);
                        break;
                    //BLANK case sets the input type to BLANK to set a blank Tile to a letter
                    case "BLANK":
                    case "Blank":
                    case "blank":
                        //A try catch for if the constructor fails
                        try {
                            //The information of the blank tile change is stored in the inputData
                            inputData = new UserInput(UserInputType.BLANK, tokens[1].toCharArray());
                        } catch (Exception e) {
                            //Sets input type to ERROR due to the constructor failing
                            inputData = new UserInput(UserInputType.ERROR);
                        }
                        break;
                        //EXCHANGE case sets the input type to EXCHANGE
                    case "Exchange":
                    case "EXCHANGE":
                    case "exchange":
                        //A try catch for if the constructor fails
                        try {
                            //The information of the Tile exchange is stored in the inputData
                            inputData = new UserInput(UserInputType.EXCHANGE, tokens[1].toCharArray());
                        } catch (Exception e) {
                            //Sets input type to ERROR due to the constructor failing
                            inputData = new UserInput(UserInputType.ERROR);
                        }
                        break;
                    //Default case deals with if the user wants to place a tile
                    default:
                        //Checks if the first token is a row coordinate (A to O) and a column coordinate (a number)
                        if (tokens[0].matches("^[A-O][\\d]$"))
                        {
                            //A try catch to catch any invalid inputs in user input for placing a word on the Board
                            try {


                                char[] temp = tokens[0].toCharArray();
                                position[0] = temp[0] - 'A';
                                position[1] = Integer.parseInt(tokens[0].substring(1));

                                directionInput = null;

                                if (tokens.length == 3) {

                                    switch (tokens[1]) {
                                        case "A":
                                        case "a":
                                            directionInput = Direction.HORIZONTAL;
                                            break;

                                        case "D":
                                        case "d":
                                            directionInput = Direction.VERTICAL;
                                            break;

                                        default:


                                    }


                                    if (directionInput != null) {
                                        inputData = new UserInput(UserInputType.PLACE_TILE, tokens[2].toCharArray(), position, directionInput);
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

}
