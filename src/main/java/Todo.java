public class Todo extends Task{
    protected String type;

    public Todo (String description) {
        super(description);
        this.type = "T";
    }
    @Override
    public String getInfo() {
        return this.type+"-"+super.getInfo();
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
