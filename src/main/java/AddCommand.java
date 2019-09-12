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
     * Adds command to list of tasks.
     * @param tasks Class dealing with arraylist of tasks
     * @param ui Class dealing with User interface
     * @param storage Class dealing with storage of task list
     * @return Output message to console
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
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
            assert false : type;
            break;
        }

        int num = tasks.list.size();
        int idx = num - 1;
        Task newTask = tasks.list.get(idx);

        return ui.printAddedCommand(num, newTask);

    }
}
