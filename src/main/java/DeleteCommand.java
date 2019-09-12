/**
 * Represents a DeleteCommand, an extension of the Command class. A <code>DeleteCommand</code>
 * object corresponds to a type and itemNo represented by two strings.
 */
public class DeleteCommand extends Command {
    protected String itemNo;

    public DeleteCommand(String type, String itemNo) {
        super(type);
        this.itemNo = itemNo;
    }

    /**
     * Removes task from list based on index given.
     * @param tasks Class dealing with arraylist of tasks
     * @param ui Class dealing with User interface
     * @param storage Class dealing with storage of task list
     * @return Output message to console.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        int itemIdx = Integer.parseInt(itemNo) - 1;
        int num = tasks.list.size();
        assert itemIdx < num;
        Task removedTask = tasks.list.get(itemIdx);

        tasks.list.remove(itemIdx);
        assert tasks.list.size() == num - 1;
        num = tasks.list.size();

        return ui.printRemovalCommand(num, removedTask);

    }
}
