/**
 * Represents EditCommand, an extension of the Command class. A <code>EditCommand</code>
 * object corresponds to type and itemNo represented by two strings.
 */
public class EditCommand extends Command{
    protected String itemNo;

    public EditCommand (String type, String itemNo) {
        super(type);
        this.itemNo = itemNo;
    }

    /**
     * Changes status of a task to done in list based on the index given.
     * @param tasks
     * @param ui
     * @param storage
     */
    @Override
    public void execute(taskList tasks, Ui ui, Storage storage) {
        int item = Integer.parseInt(itemNo) - 1;
        tasks.list.get(item).isDone = true;
        System.out.println("Nice! I've marked this task as done:");
        // print out completed task with indentation
        System.out.println("   " + tasks.list.get(item));
    }
}
