public class EditCommand extends Command{
    protected String itemNo;

    public EditCommand (String type, String itemNo) {
        super(type);
        this.itemNo = itemNo;
    }

    @Override
    // marks tasks as done for the done command
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        int item = Integer.parseInt(itemNo) - 1;
        tasks.list.get(item).isDone = true;

        System.out.println("Nice! I've marked this task as done:");
        // print out completed task with indentation
        System.out.println("   " + tasks.list.get(item));
    }
}
