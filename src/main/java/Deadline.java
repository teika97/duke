/**
 * Represents a deadline task. A <code>Deadline</code> object
 * corresponds to a deadline and task type represented by two strings.
 */
public class Deadline extends Task{
    protected String by;
    protected String type;

    public Deadline (String description, String by) {
        super(description);
        this.by = by;
        this.type = "D";
    }

    /**
     * Returns task type, status, task description, deadline separated by dash.
     * @return Task information.
     */
    @Override
    public String getInfo() {
        return this.type + "-" + super.getInfo() + "-" + this.by;
    }

    /**
     * Returns task type, status, task description, deadline.
     * @return Task information.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}
