public class DukeException extends Exception {
    DukeException(String s) {
        super(s);
        System.out.println("DukeException: "+"\u2639"+ " OOPS!!! "+s);
    }
}
