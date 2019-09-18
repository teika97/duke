import java.util.ArrayList;
import java.util.Collections;

public class SortCommand extends Command{
    ArrayList<Task> todoList;
    ArrayList<Task> eventList;
    ArrayList<Task> deadlineList;
    ArrayList<Task> newList;

    /**
     * Constructor.
     * @param type type of command
     */
    public SortCommand(String type) {
        super(type);
        todoList = new ArrayList<>();
        eventList = new ArrayList<>();
        deadlineList = new ArrayList<>();
        newList = new ArrayList<>();
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        sortByTaskType(tasks);
        return "Your list has been sorted!";
    }

    public void sortByTaskType(TaskList tasks) {
        for (int i = 0; i < tasks.list.size(); i++) {
            String type = tasks.list.get(i).getType();
            final String TODO = "T";
            final String EVENT = "E";
            final String DEADLINE = "D";

            switch (type) {
            case TODO:
                todoList.add(tasks.list.get(i));
                break;
            case EVENT:
                eventList.add(tasks.list.get(i));
                break;
            case DEADLINE:
                deadlineList.add(tasks.list.get(i));
                break;
            default:
                assert false : type;
                break;
            }
        }
        Collections.sort(todoList);
        Collections.sort(eventList);
        Collections.sort(deadlineList);
        newList.addAll(todoList);
        newList.addAll(eventList);
        newList.addAll(deadlineList);

        tasks.list = newList;
    }
}
