import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class CheckItTest {
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private final PrintStream originalErr = System.err;

    @Before
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));
    }
    @After
    public void restoreStreams() {
        System.setOut(originalOut);
        System.setErr(originalErr);
    }
    @Test public void testPredicateCoverageTrue(){
        CheckIt.checkIt(true, true, false);
        org.junit.Assert.assertEquals("P is true\r\n", outContent.toString());
    }

    @Test public void testPredicateCoverageFalse(){
        CheckIt.checkIt(false, true, false);
        org.junit.Assert.assertEquals("P isn't true\r\n", outContent.toString());
    }

    @Test public void testClauseCoverageTrue(){
        CheckIt.checkIt(true, true, true);
        org.junit.Assert.assertEquals("P is true\r\n", outContent.toString());
    }

    @Test public void testClauseCoverageFalse(){
        CheckIt.checkIt(false, false, false);
        org.junit.Assert.assertEquals("P isn't true\r\n", outContent.toString());
    }

    @Test public void testCACCoverageTrue(){
        //a chosen as major clause
        CheckIt.checkIt(true, true, true);
        org.junit.Assert.assertEquals("P is true\r\n", outContent.toString());
    }

    @Test public void testCACCoverageFalse(){
        //a chosen as major clause
        CheckIt.checkIt(false, false, true);
        org.junit.Assert.assertEquals("P isn't true\r\n", outContent.toString());
    }
    @Test public void testRACCoverageTrue(){
        //a chosen as major clause
        CheckIt.checkIt(true, false, true);
        org.junit.Assert.assertEquals("P is true\r\n", outContent.toString());
    }

    @Test public void testRACCoverageFalse(){
        //a chosen as major clause
        CheckIt.checkIt(false, false, true);
        org.junit.Assert.assertEquals("P isn't true\r\n", outContent.toString());
    }
}
