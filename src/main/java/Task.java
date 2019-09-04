/**
 * Represents a task inputted by the user. A <code>Task</code> object
 * corresponds to a description represented by a String and its status
 * represented by a boolean
 */
public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns icon based on its status.
     * @return Status Icon.
     */
    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or x symbols
    }

    /**
     * Returns status and description of task separated by a dash.
     * @return Task description
     */
    public String getInfo() {
        if (this.isDone) {
            return "1" + "-" + this.description;
        }
        return "0" + "-" + this.description;
    }

    /**
     * Returns the status icon and description of task.
     * @return Status icon and description.
     */
    public String toString() {
        return "[" + this.getStatusIcon() + "]" + " " + this.description;
    }
}

