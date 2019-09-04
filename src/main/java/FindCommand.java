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
    public void execute(TaskList tasks, Ui ui, Storage storage) {
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

        // Print out list of matching tasks
        if (inList) {
            System.out.println("Here are the matching tasks in your list:");
            for (int j = 0; j < foundTasks.size(); j++) {
                System.out.println((j + 1) + "." + foundTasks.get(j));
            }
        } else {
            System.out.println("No matching tasks found.");
        }
    }
}
