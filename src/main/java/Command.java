public class Command {
    protected String type;

    public Command (String type) {
        this.type = type;
    }
    public boolean isExit() {
        if (this.type.equals("bye")) {
            return true;
        }
        return false;
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) {
    }
}
