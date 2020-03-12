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
        System.out.println("Initialized Player 1:\n" + player1 + "\n\nInitialized Player 2:\n" + player2);

        //Print the pools state after the player are made
        System.out.println("Pool after Players are initialized:\n" + pool);

        //Creating a board
        Board board = new Board();
        System.out.println("Initalised board:\n"+ board.toString());
        System.out.println(player1.toString());


        //PLAYER ONE TURN ONE

        //player1Tiles and player1Moves simulate user input
        char[] player1Tiles = new char[7];
        int[][] player1Moves  = new int[7][2];

        //Loops through player1's frame and adds it to player1Tiles
        //also adds input for row and column coordinates for each tile
        for(int i=0;i<7;i++)
        {
            player1Tiles[i] = player1.getPlayerFrame().getTile(i).getCharacter();
            player1Moves[i][1] = 7 + i;
            player1Moves[i][0] = 7;

            //If there is a blank tile, it is set to the letter A
            if (player1Tiles[i] == ' '){
                player1Tiles[i] = 'A';
                player2.getPlayerFrame().getTile(i).setCharacter(player1Tiles[i]);
            }
        }

        //The tiles are placed on the board
        board.placeTiles(player1, player1Tiles, player1Moves);

        //Prints the board, placed tiles and player 1's data
        System.out.println("\nTurn 1: Player 1 placed a word on the board:\n"+ board + "\nThe tiles placed: ");
        System.out.println(player1Tiles);
        System.out.println(player1.toString());



        //PLAYER 2 TURN ONE

        //player2Tiles and player2Moves simulate user input
        char[] player2Tiles = new char[7];
        int[][] player2Moves  = new int[7][2];

        //Loops through player2's frame and adds it to player2Tiles
        //also adds input for row and column coordinates for each tile
        for(int i=0;i<7;i++)
        {
            player2Tiles[i] = player2.getPlayerFrame().getTile(i).getCharacter();
            player2Moves[i][1] = 7;
            player2Moves[i][0] = 8+i;

            //If there is a blank tile, it is set to the letter A
            if (player2Tiles[i] == ' '){
                player2Tiles[i] = 'A';
                player2.getPlayerFrame().getTile(i).setCharacter(player2Tiles[i]);
            }
        }

        //The tiles are placed on the board
        board.placeTiles(player2, player2Tiles, player2Moves);

        //Prints the board, placed tiles and player 2's data
        System.out.println("\nTurn 1: Player 2 placed a word on the board:\n"+ board.toString() + "\nThe tiles placed: ");
        System.out.println(player2Tiles);
        System.out.println(player2.toString());


        //PLAYER 1 TURN 2

        //Player tiles and moves are set to new arrays
        player1Tiles = new char[3];
        player1Moves  = new int[3][2];

        //Moves are set
        player1Moves[0][1] = 12;
        player1Moves[1][1] = 12;
        player1Moves[2][1] = 12;

        player1Moves[0][0] = 6;
        player1Moves[1][0] = 8;
        player1Moves[2][0] = 9;

        //Loop to go through player's frame
        for(int i=0;i<3;i++)
        {
            player1Tiles[i] = player1.getPlayerFrame().getTile(i).getCharacter();

            //If there is a blank tile, it is set to the letter A
            if (player1Tiles[i] == ' '){
                player1Tiles[i] = 'A';
                player1.getPlayerFrame().getTile(i).setCharacter(player1Tiles[i]);
            }
        }
        //Places tiles on board
        board.placeTiles(player1, player1Tiles, player1Moves);

        //Prints the board, placed tiles and player 1's data
        System.out.println("\nTurn 2: Player 1 placed a word on the board:\n"+ board.toString() + "\nThe tiles placed: ");
        System.out.println(player1Tiles);
        System.out.println(player1.toString());


       //TURN 2 PLAYER 2
        //Player tiles and moves are set to new arrays
        player2Tiles = new char[3];
        player2Moves  = new int[3][2];

        //Moves are set
        player2Moves[0][0] = 14;
        player2Moves[1][0] = 14;
        player2Moves[2][0] = 14;

        player2Moves[0][1] = 6;
        player2Moves[1][1] = 8;
        player2Moves[2][1] = 9;

        //Loop to go through player's frame
        for(int i=0;i<3;i++)
        {
            player2Tiles[i] = player2.getPlayerFrame().getTile(i).getCharacter();

            //If there is a blank tile, it is set to the letter A
            if (player2Tiles[i] == ' '){
                player2Tiles[i] = 'A';
                player2.getPlayerFrame().getTile(i).setCharacter(player2Tiles[i]);
            }
        }

        //Tiles are placed on the board
        board.placeTiles(player2, player2Tiles, player2Moves);

        //Prints the board, placed tiles and player 2's data
        System.out.println("\nTurn 2: Player 2 placed a word on the board:\n"+ board.toString() + "\nThe tiles placed: ");
        System.out.println(player2Tiles);
        System.out.println(player2.toString());


    }


}
