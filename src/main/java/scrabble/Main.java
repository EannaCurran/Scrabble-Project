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

    }


}
