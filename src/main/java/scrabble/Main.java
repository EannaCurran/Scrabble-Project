package scrabble;

public class Main {


    //Main Function to execute the test suite in executable Jar
    public static void main(String[] args) {

        //Print the Tile message
        System.out.println("Scrabble Game:\nGroup 9 - The Good, The Bad and The Ugly\n");

        //Create the pool and print the initial state
        Pool pool = new Pool();
        System.out.println("Initialized Pool:\n" + pool);

        //Create the players and print there initial state
        Player player1 = new Player("Killian", pool);
        Player player2 = new Player("Eanna", pool);
        System.out.println("Initialized Player 1:\n" + player1 + "\n\nInitialized Player 1:\n" + player2);

        //Print the pools state after the player are made
        System.out.println("Pool after Players are initialized:\n" + pool);

        Board board = new Board();
        System.out.println("Initalised board:\n"+ board.toString());

        //User inputting the desired letter to place
        //and the coordinates of where they want to place it on the board
        char playerCharInput = 'E';
        int playerRowInput = 7;
        int playerColumnInput = 7;
        if(player1.charUserInputCheck(playerCharInput))
        {
            Tile tileToBePlaced = player1.getPlayerFrame().getChar(playerCharInput);
            board.getSquare(playerRowInput,playerColumnInput).setTile(tileToBePlaced);

        }

        //board.getSquare(5,7).setTile(new Tile('Q'));
        System.out.println("Placed a tile on the board:\n"+ board.toString());


    }


}
