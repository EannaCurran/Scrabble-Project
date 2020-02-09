package scrabble;

import org.junit.internal.TextListener;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.SuiteDisplayName;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.RunWith;


//Creating JUnit Test Suite using JUnit 5 Platform
@RunWith(JUnitPlatform.class)
@SuiteDisplayName("JUnit Platform Suite Demo")
@SelectClasses({TileTest.class, PoolTest.class, FrameTest.class, PlayerUnitTest.class})
public class PlayerTest {

    //Main Function to execute the test suite in executable Jar
    public static void main(String[] args) {

        JUnitCore junit = new JUnitCore();

        //Lister for the fail message and stack trace of the unit tests
        junit.addListener(new TextListener(System.out));

        //Run the test suite
        Result result = junit.run(PlayerTest.class);

        //Output the results
        resultReport(result);

    }

    //Method to output the results of the unit tests
    public static void resultReport(Result result) {
        System.out.println("Finished. Result: Failures: " +
                result.getFailureCount() + ". Ignored: " +
                result.getIgnoreCount() + ". Tests run: " +
                result.getRunCount() + ". Time: " +
                result.getRunTime() + "ms.");

    }
}