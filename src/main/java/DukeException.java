public class DukeException extends Exception {
    protected String err;

    public DukeException(String s) {
        this.err = s;
    }

    public String getMessage() {
        return "DukeException: "+"\u2639"+ " OOPS!!! "+err;
    }
}
