import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.ArrayList;

public class AddCommandTest {
    private TaskList tasksTest = new TaskList(new ArrayList<Task>());
    private Ui uiTest = new Ui();
    private Storage storageTest = new Storage("stuff");
    @Test
    public void execute() {
        // Test AddCommand works for correct input - todo command type
        new AddCommand("todo","buy books",null).execute(tasksTest,uiTest,storageTest);
        assertEquals(1,tasksTest.list.size());

        // Test AddCommand works for correct input - event command type
        new AddCommand("event","birthday party","08-12-2019 18:00").execute(tasksTest,uiTest,storageTest);
        assertEquals(2,tasksTest.list.size());

        // Test AddCommand works for correct input - deadline command type
        new AddCommand("deadline","report","11-12-2019 23:59").execute(tasksTest,uiTest,storageTest);
        assertEquals(3,tasksTest.list.size());
    }
}
