/**
 * Represents a task inputted by the user. A <code>Task</code> object
 * corresponds to a description represented by a String and its status
 * represented by a boolean
 */
public class Task implements Comparable <Task> {
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
            return "1" + "--" + this.description;
        }
        return "0" + "--" + this.description;
    }

    /**
     * Returns the status icon and description of task.
     * @return Status icon and description.
     */
    public String toString() {
        return "[" + this.getStatusIcon() + "]" + " " + this.description;
    }

    /**
     * Returns task type.
     * @return Task type
     */
    public String getType() { return "Task";}

    /**
     * Returns task description.
     * @return task description
     */
    public String getDesc() {
        return this.description;
    }

    /**
     * Returns date if applicable.
     * @return date
     */
    public String getDate() {
        return "No date";
    }

    @Override
    /**
     * Compares task descriptions to sort in lexicographical order.
     */
    public int compareTo(Task task) {
        String desc1 = this.getDesc();
        String desc2 = task.getDesc();

        return desc1.compareTo(desc2);
    }



}

