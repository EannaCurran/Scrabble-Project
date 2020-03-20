# The-Good-The-Bad-and-The-Ugly

**Authors:** Group 9: The Good, The Bad and The Ugly

    John O'Donnell - 18368983
    Éanna Curran - 18311676
    Killian Callaghan - 18332783

### **Scrabble Game**

**How To Play**

The Scrabble game works by inputting commands into a text box on the right side of the screen.
The Board is visualised on the left side of the screen.
The game dialogue and move history are displayed above the text box on the right side of the screen.

Valid commands that can be inputted into the text box:

- HELP: Prints out the list of available commands.
- PASS: Passes the current players turn.
- QUIT: Ends the current game of Scrabble.
- EXCHANGE [Characters]: Exchanges the letters in the frame with random letters in the pool (Eg: EXCHANGE AB).
- BLANK [Characters]: Sets the blank tiles in a players frame to a letter, only functional if a blank tile is in the players frame.
- CHALLENGE [Y/N]: Allows the opponent to challenge a move to be made by the other player, only available after a player has inputted a move.
- RESTART: Launches a new game, can only be called at the end of a game.
- [Grid Reference] [Direction] [Characters] : Places the characters starting at the grid reference and going in the given direction onto the board (Eg: H7 A HELLO).

**Classes**

    Player        - Used to create objects that represents the players in Scrabble
    Frame         - Used to create objects that represents the players' character frames in Scrabble
    Pool          - Used to create an object that represents the pool bag of tiles in Scrabble
    Tile          - Used to create objects that represents the character tiles in Scrabble
    Square        - Used to create objects that represents the squares on the board in Scrabble
    Board         - Used to create object that reprensents the board in Scrabble
    Word          - Used to create objects that store informantion about a Word placed on the Board in Scrabble
    MoveInfo      - Used to create objects that store informantion about a move in Scrabble
    Scrabble      - Used to create object that reprensents an instance of Scrabble game and contains Scabble game logic
    UserInput     - Used to pharse User input for Scrabble game commands
    UserInterface - Used to create the JavaFX for the Scrabble game user interface for the Scabble Game
    
Custom Exceptions
    
     InvalidFrameException       - Custom Exception for Frame Class
     InvalidPlayerNameException  - Custom Exception for name in Player Class
     InvalidPlayerScoreException - Custom Exception for Score in Player Class
     InvalidPoolException        - Custom Exception for Pool Class 
     InvalidTileException        - Custom Exception for Tile Class 
     InvalidSquareException      - Custom Exception for Square Class
     InvalidBoardException       - Custom Exception for Board Class 
     InvaildInputException       - Custom Exception for UserInput Class
     InvalidMoveInfoException    - Custom Exception for MoveIfo Class
     InvalidScrabbleException    - Custom Exception for Scrabble Class
     InvalidWordException        - Custom Exception for Word Class
    
**JUnit Tests**

    PlayerUnitTest - Tests Player Class
    FrameTest      - Tests Frame Class
    PoolTest       - Tests Pool Class
    TileTest       - Tests Tile Class
    SquareTest     - Tests Square Class
    BoardTest      - Tests Board Class
    MoveInfoTest   - Tests MoveInfo Class
    ScrabbleTest   - Tests Scrabble Class
    UserInputTest  - Tests UserInput Class
    WordTest       - Tests Word Class
    
**Project Notes:**

This project is a Gradle project and uses Gradle and groovy as its build tool.
To run the junit test create a Gradle project by running "./gradlew build" in command line in the project folder to generate a Gradle project.
Then navigate in command line to the project folder and run "gradlew test".
The output of the unit test is in a html file at "ProjectFolder"/build/reports/tests/test/index.html
(Where ProjectFolder is the folder where the project is stored).

The executable jar file run the UserInterface Class that runs the Scrabble game.

The Scrabble game can also be rum buy building the Gradle project see above. The running the command "gradlew run"

The documentation is in the Doxygen folder as a pdf.

HTML JavaDoc is in build\docs\javadoc\index

Note: This project was made made on Windows 10 and was tested on Windows 10, other operating systems have not been test. 
Therefore there may be compatibility issues with other operating systems. 
Due to this project using JavaFX for its user interface the executable jar is only compatible on Window 10.
