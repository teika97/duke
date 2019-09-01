public class Task {
    protected String description;
    protected boolean isDone;

    public Task (String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone? "\u2713" : "\u2718"); //return tick or x symbols
    }

    public String getInfo() {
        if (this.isDone) {
            return "1" + "-" + this.description;
        }
        return "0" + "-" + this.description;
    }

    public String toString() {
        return "[" + this.getStatusIcon() + "]" + " " + this.description;
    }
}

