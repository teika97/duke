import org.junit.jupiter.api.Test;
import java.text.ParseException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class ParserTest {
    private Command cmdTest;
    @Test
    public void testSetDate() throws ParseException {
        // Test for correct input
        assertEquals("29 August, 2019, 06:00 PM",new Parser().setDate("29-08-2019 18:00"));
        // Test for exception thrown
        try {
            assertEquals("Unparseable date: 31 Aug 2019 11:59pm",new Parser().setDate("31 Aug 2019 11:59pm"));
            fail();
        } catch (ParseException e) {
            assertEquals("Unparseable date: \"31 Aug 2019 11:59pm\"",e.getMessage());
        }

    }

}
