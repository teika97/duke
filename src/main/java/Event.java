import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Represents an Event task. A <code>Event</code> object
 * corresponds to a time and a task type represented by two
 * strings.
 */
public class Event extends Task{
    protected String at;
    protected String type;

    /**
     * Constructor.
     * @param description task description
     * @param at time of event
     */
    public Event(String description, String at) {
        super(description);
        this.at = at;
        this.type = "E";
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
     * Returns date of event if applicable.
     * @return date of event
     */
    public String getDate() {
        return this.at;
    }

    /**
     * Returns task type, status, description, time separated by a dash.
     * @return Task information.
     */
    @Override
    public String getInfo() {
        return this.type + "--" + super.getInfo() + "--" + this.at;
    }

    /**
     * Returns task type, status, description, time.
     * @return Task information.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at + ")";
    }

    /**
     * Compares dates for comparator to sort according to date.
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