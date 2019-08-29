/**
 * Represents an Event task. A <code>Event</code> object
 * corresponds to a time and a task type represented by two
 * strings.
 */
public class Event extends Task{
    protected String at;
    protected String type;

    public Event (String description, String at) {
        super(description);
        this.at = at;
        this.type = "E";
    }

    /**
     * Returns task type, status, description, time separated by a dash.
     * @return Task information.
     */
    @Override
    public String getInfo() {
        return this.type+"-"+super.getInfo()+"-"+this.at;
    }

    /**
     * Returns task type, status, description, time.
     * @return Task information.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at + ")";
    }
}