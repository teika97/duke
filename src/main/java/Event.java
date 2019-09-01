public class Event extends Task{
    protected String at;
    protected String type;

    public Event (String description, String at) {
        super(description);
        this.at = at;
        this.type = "E";
    }

    @Override
    public String getInfo() {
        return this.type + "-" + super.getInfo() + "-" + this.at;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at + ")";
    }
}