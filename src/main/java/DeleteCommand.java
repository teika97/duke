public class DeleteCommand extends Command{
    protected String itemNo;

    public DeleteCommand (String type, String itemNo) {
        super(type);
        this.itemNo = itemNo;
    }

    @Override
    // delete task from list
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        int item = Integer.parseInt(itemNo) - 1;
        System.out.println("Noted. I've removed this task:");
        System.out.println("   " + tasks.list.get(item));

        tasks.list.remove(item);

        System.out.println("Now you have " + tasks.list.size() + " in the list.");
    }
}
