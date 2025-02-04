package dodo.utilities;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TimeStringUtilityTest {
    @Test
    public void stringToLdTest1() {
        try {
            assertEquals(TimeStringUtility.stringToLdt("2002-02-03"),
                    LocalDate.of(2002, 2, 3));
        } catch (DodoException ex) {
        }
    }

    @Test
    public void stringToLdTest2() {
        String errorLog = "bobo";
        try {
            TimeStringUtility.stringToLd("2002-02-0a");
        } catch (DodoException ex) {
            errorLog = ex.getMessage();
        }
        assertEquals(errorLog, "Incorrect formatting of time. Use: yyyy-mm-dd");
    }

    @Test
    public void stringToLdTest3() {
        String errorLog = "bobo";
        try {
            TimeStringUtility.stringToLd("0002-13-32");
        } catch (DodoException ex) {
            errorLog = ex.getMessage();
        }
        assertEquals(errorLog, "Incorrect formatting of time. Use: yyyy-mm-dd");
    }


    @Test
    public void stringToLdtTest1() {
        try {
            assertEquals(TimeStringUtility.stringToLd("2002-02-03 12:55"),
                    LocalDateTime.of(2002, 2, 3, 12, 55));
        } catch (DodoException ex) {
        }
    }

    @Test
    public void stringToLdtTest2() {
        String errorLog = "bobo";
        try {
            TimeStringUtility.stringToLdt("2002-02-0a 12:55");
        } catch (DodoException ex) {
            errorLog = ex.getMessage();
        }
        assertEquals(errorLog, "Incorrect formatting of time. Use: yyyy-mm-dd hh:ss");
    }

    @Test
    public void stringToLdtTest3() {
        String errorLog = "bobo";
        try {
            TimeStringUtility.stringToLdt("0002-13-32 25:61");
        } catch (DodoException ex) {
            errorLog = ex.getMessage();
        }
        assertEquals(errorLog, "Incorrect formatting of time. Use: yyyy-mm-dd hh:ss");
    }
}
