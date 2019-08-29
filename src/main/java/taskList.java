import java.util.ArrayList;

/**
 * Represents the list of all tasks.
 */
public class taskList {
    protected ArrayList<Task> list;

    public taskList (ArrayList<Task> loadFile) {
        list = loadFile;
    }

    /**
     * Returns total no. of tasks in the list.
     * @return Total no. of tasks.
     */
    public int getSize () {
        return list.size();
    }

}
