/**
 * Represents a command in Duke.
 */
public class Command {
    protected String type;

    public Command(String type) {
        this.type = type;
    }

    /**
     * Returns whether the command is an exit command.
     * @return True or False
     */
    public boolean isExit() {
        if (this.type.equals("bye")) {
            return true;
        }
        return false;
    }

    /**
     * Empty method to be overridden based on command type.
     * @param tasks Class dealing with arraylist of tasks
     * @param ui Class dealing with User interface
     * @param storage Class dealing with storage of task list
     */
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return "";
    }
}
