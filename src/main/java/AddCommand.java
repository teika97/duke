/**
 * Represents an AddCommand, an extension of the Command. A <code>AddCommand</code>
 * object corresponds to a type, description, date represented by three strings.
 */
public class AddCommand extends Command {
    protected String description;
    protected String dateTime;

    /**
     * Constructor.
     * @param type type of command
     * @param description task description
     * @param dateTime deadline or time of event
     */
    public AddCommand(String type, String description, String dateTime) {
        super(type);
        this.description = description;
        this.dateTime = dateTime;
    }

    /**
     * Adds the new task into the list.
     * @param tasks Class dealing with arraylist of tasks
     * @param ui Class dealing with User interface
     * @param storage Class dealing with storage of task list
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        switch (type) {
        case "todo":
            tasks.list.add(new Todo(description));
            break;
        case "deadline":
            tasks.list.add(new Deadline(description,dateTime));
            break;
        case "event":
            tasks.list.add(new Event(description,dateTime));
            break;
        default:
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
