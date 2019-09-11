/**
 * Represents EditCommand, an extension of the Command class. A <code>EditCommand</code>
 * object corresponds to type and itemNo represented by two strings.
 */
public class EditCommand extends Command {
    protected String itemNo;

    public EditCommand(String type, String itemNo) {
        super(type);
        this.itemNo = itemNo;
    }

    /**
     * Changes status of a task to done in list based on the index given.
     * @param tasks Class dealing with arraylist of tasks
     * @param ui Class dealing with User interface
     * @param storage Class dealing with storage of task list
     */
    @Override
    // marks tasks as done for the done command
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        int item = Integer.parseInt(itemNo) - 1;
        assert item < tasks.list.size();

        tasks.list.get(item).isDone = true;

        return "Nice! I've marked this task as done:\n" + "   " + tasks.list.get(item);
    }
}
