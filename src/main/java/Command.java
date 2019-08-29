/**
 * Represents a command in Duke.
 */
public class Command {
    protected String type;
    public Command (String type) {
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
     * @param tasks
     * @param ui
     * @param storage
     */
    public void execute(taskList tasks, Ui ui, Storage storage) {
    }
}
