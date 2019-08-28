public class PrintCommand extends Command {
    public PrintCommand (String type) {
        super(type);
    }
    @Override
    // calls method to print all tasks in list
    public void execute(taskList tasks, Ui ui, Storage storage) {
        System.out.println("Here are the tasks in your list:");
        for (int i=0; i<tasks.list.size(); i++) {
            int listNum = i+1;
            System.out.println(listNum+"."+tasks.list.get(i));
        }
    }
}
