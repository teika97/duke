import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CommandTest {
    @Test
    public void testIsExit() {
        assertEquals(true,new Command("bye").isExit());
        assertEquals(false,new AddCommand("todo","buy books",null).isExit());
        assertEquals(false,new PrintCommand("list").isExit());
        assertEquals(false,new AddCommand("event","birthday party","08-12-2019 18:00").isExit());
        assertEquals(false,new AddCommand("deadline","project","31-08-2019 23:59").isExit());
        assertEquals(false,new DeleteCommand("delete","2").isExit());
        assertEquals(false,new EditCommand("done","1").isExit());
    }
}
