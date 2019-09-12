import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Represents a deadline task. A <code>Deadline</code> object
 * corresponds to a deadline and task type represented by two strings.
 */
public class Deadline extends Task {
    protected String by;
    protected String type;

    /**
     * Constructor.
     * @param description task description
     * @param by time by which item to be completed
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
        this.type = "D";
    }

    @Override
    /**
     * Returns task type.
     * @return Task type
     */
    public String getType() {
        return this.type;
    }

    @Override
    /**
     * Returns deadline of task if applicable.
     * @return Task deadline
     */
    public String getDate() {
        return this.by;
    }

    /**
     * Returns task type, status, task description, deadline separated by dash.
     * @return Task information.
     */
    @Override
    public String getInfo() {
        return this.type + "--" + super.getInfo() + "--" + this.by;
    }

    /**
     * Returns task type, status, task description, deadline.
     * @return Task information.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }

    /**
     * Compares dates for comparator to sort according to task deadlines.
     */
    @Override
    public int compareTo(Task task) {
        SimpleDateFormat parser = new SimpleDateFormat("dd MMMM, yyyy, hh:mm a");
        Date date1 = null;
        Date date2 = null;
        try {
            date1 = parser.parse(task.getDate());
            date2 = parser.parse(this.getDate());
        } catch(ParseException e) {
            System.out.println("Invalid date.");
        }
        return date2.compareTo(date1);
    }
}
