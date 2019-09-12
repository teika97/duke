/**
 * Represents a todo task. A <code>Todo</code> object
 * corresponds to a task type represented by a string.
 */
public class Todo extends Task {
    protected String type;

    public Todo(String description) {
        super(description);
        this.type = "T";
    }

    @Override
    /**
     * Returns task type.
     * @return Task type
     */
    public String getType() {
        return this.type;
    }

    /**
     * Returns task type, status, task description separated by a dash.
     * @return Task information.
     */
    @Override
    public String getInfo() {
        return this.type + "--" + super.getInfo();
    }

    /**
     * Returns task type, status, task description.
     * @return Task information.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
