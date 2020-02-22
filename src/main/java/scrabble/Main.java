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
        //char playerCharInput = 'E';
        //int playerRowInput = 0;
        //int playerColumnInput = 0;
        System.out.println(player1.toString());


        //PLAYER ONE TURN ONE
        char[] player1Tiles = new char[7];
        int[][] player1Moves  = new int[7][2];
        for(int i=0;i<7;i++)
        {
            player1Tiles[i] = player1.getPlayerFrame().getTile(i).getCharacter();
            player1Moves[i][1] = 7 + i;
            player1Moves[i][0] = 7;
        }

        //player1Tiles = new char[6];
        //player1Tiles[1] = player1.getPlayerFrame().getTile(1).getCharacter();
        //player1Moves = new int[7][2];
        //player1Moves[0][0] = 7;
        //player1Moves[0][1] = 8;

        board.placeTiles(player1, player1Tiles, player1Moves);

        System.out.println("Turn 1: Player 1 placed a word on the board:\n"+ board.toString());
        System.out.println(player1.toString());
        System.out.println(player1Tiles);


        //PLAYER 2 TURN ONE
        char[] player2Tiles = new char[7];
        int[][] player2Moves  = new int[7][2];

        for(int i=0;i<7;i++)
        {
            player2Tiles[i] = player2.getPlayerFrame().getTile(i).getCharacter();
            player2Moves[i][1] = 7;
            player2Moves[i][0] = 8+i;
        }

        board.placeTiles(player2, player2Tiles, player2Moves);
        System.out.println("Turn 1: Player 1 placed a word on the board:\n"+ board.toString());

        System.out.println(player2.toString());
        System.out.println(player2Tiles);

        //PLAYER 1 TURN 2
        player1Tiles = new char[3];
        player1Moves  = new int[3][2];

        player1Moves[0][1] = 12;
        player1Moves[1][1] = 12;
        player1Moves[2][1] = 12;

        player1Moves[0][0] = 6;
        player1Moves[1][0] = 8;
        player1Moves[2][0] = 9;

        for(int i=0;i<3;i++)
        {
            player1Tiles[i] = player1.getPlayerFrame().getTile(i).getCharacter();
        }
        board.placeTiles(player1, player1Tiles, player1Moves);

        System.out.println("Turn 2: Player 1 placed a word on the board:\n"+ board.toString());
        System.out.println(player1.toString());
        System.out.println(player1Tiles);


        /*
        if(player1.charUserInputCheck(playerCharInput))
        {
            Tile tileToBePlaced = player1.getPlayerFrame().getChar(playerCharInput);
            board.getSquare(playerRowInput,playerColumnInput).setTile(tileToBePlaced);
        }
         */

        //board.getSquare(5,7).setTile(new Tile('Q'));


    }


}
