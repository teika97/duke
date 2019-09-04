/**
 * Represents the PrintCommand, an extension of the Command class. A <code>PrintCommand</code>
 * object corresponds to a type represented by a string.
 */
public class PrintCommand extends Command {
    public PrintCommand(String type) {
        super(type);
    }

    /**
     * Prints out all tasks in the list currently.
     * @param tasks Class dealing with arraylist of tasks
     * @param ui Class dealing with User interface
     * @param storage Class dealing with storage of task list
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < tasks.list.size(); i++) {
            int listNum = i + 1;
            System.out.println(listNum + "." + tasks.list.get(i));
        }
    }
}
