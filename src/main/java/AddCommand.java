/**
 * Represents an AddCommand, an extension of the Command. A <code>AddCommand</code>
 * object corresponds to a type, description, date represented by three strings.
 */
public class AddCommand extends Command{
    protected String description;
    protected String dateTime;

    public AddCommand (String type, String description, String dateTime) {
        super(type);
        this.description = description;
        this.dateTime = dateTime;
    }

    /**
     * Adds the new task into the list.
     * @param tasks
     * @param ui
     * @param storage
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        switch(type) {
        case "todo":
            tasks.list.add(new Todo(description));
            break;
        case "deadline":
            tasks.list.add(new Deadline(description,dateTime));
            break;
        case "event":
            tasks.list.add(new Event(description,dateTime));
            break;
        }

        // Print standard output to console
        System.out.println("Got it. I've added this task:");
        System.out.println("   " + tasks.list.get(tasks.list.size() - 1));
        if (tasks.list.size() > 1) {
            System.out.println("Now you have " + tasks.list.size() + " tasks in the list.");
        } else {
            System.out.println("Now you have " + tasks.list.size() + " task in the list.");
        }
    }
}
