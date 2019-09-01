import java.util.ArrayList;

public class TaskList {
    protected ArrayList<Task> list;

    public TaskList(ArrayList<Task> loadFile) {
        list = loadFile;
    }

    public int getSize () {
        return list.size();
    }

}
