/**
 * Represents Exceptions that are specific to the Duke app.
 */
public class DukeException extends Exception {
    protected String err;

    public DukeException(String s) {
        this.err = s;
    }

    /**
     * Returns the basic error message for Duke.
     * @return Duke error message.
     */
    public String getMessage() {
        return err + "\nPlease try again.";
    }
}
