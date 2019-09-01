/**
 * Represents a DeleteCommand, an extension of the Command class. A <code>DeleteCommand</code>
 * object corresponds to a type and itemNo represented by two strings.
 */
public class DeleteCommand extends Command{
    protected String itemNo;

    public DeleteCommand (String type, String itemNo) {
        super(type);
        this.itemNo = itemNo;
    }

    /**
     * Removes task from list based on index given.
     * @param tasks
     * @param ui
     * @param storage
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        int item = Integer.parseInt(itemNo) - 1;
        System.out.println("Noted. I've removed this task:");
        System.out.println("   " + tasks.list.get(item));

        tasks.list.remove(item);

        System.out.println("Now you have " + tasks.list.size() + " in the list.");
    }
}
