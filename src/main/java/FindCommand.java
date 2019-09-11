import java.util.ArrayList;

public class FindCommand extends Command {
    protected String keyWord;
    protected boolean inList;
    protected ArrayList<Task> foundTasks = new ArrayList<Task>();

    /**
     * Constructor.
     * @param type type of command
     * @param keyWord word to be matched to all tasks
     */
    public FindCommand(String type, String keyWord) {
        super(type);
        this.keyWord = keyWord;
        inList = false;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        // Traverse TaskList to find all matching tasks and add into a list
        for (int i = 0; i < tasks.getSize(); i++) {
            String desc = tasks.list.get(i).description;
            Task foundTask = tasks.list.get(i);
            boolean hasKeyWord = desc.contains(keyWord);
            if (hasKeyWord) {
                inList = true;
                foundTasks.add(foundTask);
            }
        }
        String output = "";
        // Print out list of matching tasks
        if (inList) {
            assert foundTasks.size() > 0;
            output = "Here are the matching tasks in your list:";
            for (int j = 0; j < foundTasks.size(); j++) {
                String itemNo = Integer.toString(j+1);
                output = output + "\n" + itemNo + "." + foundTasks.get(j);
            }
        } else {
            assert foundTasks.size() == 0;
            output = "No matching tasks found.";
        }
        return output;
    }
}
