# The-Good-The-Bad-and-The-Ugly

**Authors:** Group 9: The Good, The Bad and The Ugly

    John O'Donnell - 18368983
    Éanna Curran - 18311676
    Killian Callaghan - 18332783

### **Scrabble Game**

**Classes**

    Player - Used to create objects that represents the players in Scrabble
    Frame - Used to create objects that represents the players' character frames in Scrabble
    Pool - Used to create an object that represents the pool bag of tiles in Scrabble
    Tile - Used to create objects that represents the character tiles in Scrabble
    Square - Used to create objects that represents the squares on the board in Scrabble
    Board - Used to create object that reprensents the board in Scrabble
    Main - Is the class containing the main method ececuted by the jar and creates and prints the pool and players with there frames and tiles
    
Custom Exceptions
    
     InvalidFrameException - Custom Exception for Frame Class
     InvalidPlayerNameException - Custom Exception for name in Player Class
     InvalidPlayerScoreException - Custom Exception for Score in Player Class
     InvalidPoolException - Custom Exception for Pool Class 
     InvalidTileException - Custom Exception for Tile Class 
     InvalidSquareException - Custom Exception for Square Class
     InvalidBoardException - Custom Exception for Board Class 
    
**JUnit Tests**

    PlayerUnitTest - Tests Player Class
    FrameTest - Tests Frame Class
    PoolTest - Tests Pool Class
    TileTest - Tests Tile Class
    SquareTest - Tests Square Class
    BoardTest - Tests Board Class
    
**Project Notes:**

This project is a gradle project and uses gradle and groovy as its build tool.
To run the junit test create a gradle project by running "./gradlew build" in command line in the project folder to generate a gradle project.
Then navigate in command line to the project folder and run "gradlew test".
The output of the unit test is in a html file at "ProjectFolder"/build/reports/tests/test/index.html
(Where ProjectFolder is the folder where the project is stored).

The executable jar file run the Main Class that creates sample objects from Scrabble and prints out there initialized states. 

The documentation is in the Doxygen folder as a pdf.

Note: This project was made made on Windows 10 and was tested on Windows 10, other operating systems have not been test. 
Therefore there may be compatibility issues with other operating systems. 
