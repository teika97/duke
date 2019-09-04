import java.util.ArrayList;

/**
 * Represents the list of all tasks.
 */
public class TaskList {
    protected ArrayList<Task> list;

    public TaskList(ArrayList<Task> loadFile) {
        list = loadFile;
    }

    /**
     * Returns total no. of tasks in the list.
     * @return Total no. of tasks.
     */
    public int getSize() {
        return list.size();
    }
}
