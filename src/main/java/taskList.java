import java.util.ArrayList;

public class taskList {
    protected ArrayList<Task> list;

    public taskList (ArrayList<Task> loadFile) {

        list = loadFile;
    }
    public int getSize () {
        return list.size();
    }

}
