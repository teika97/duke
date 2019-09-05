import java.util.Scanner;

/**
 * Represents all methods related to user interface.
 */
public class Ui {
    /**
     * Prints loadingError if unable to find file to load content.
     */
    public String showLoadingError() {
        DukeException loadingError = new DukeException("Unable to find File!");
        return loadingError.getMessage();
    }

    /**
     * Prints savingError if unable to find file to write content.
     */
    public String showSavingError() {
        DukeException savingError = new DukeException("Unable to save File!");
        return savingError.getMessage();
    }

    /**
     * Prints welcome message.
     */
    public String showWelcome() {
        /**String logo = " ____        _        \n"
         + "|  _ \\ _   _| | _____ \n"
         + "| | | | | | | |/ / _ \\\n"
         + "| |_| | |_| |   <  __/\n"
         + "|____/ \\__,_|_|\\_\\___|\n";
         System.out.println("Hello from\n" + logo);**/

        return "Hello! I'm Duke.\nWhat can I do for you?";
    }

    /**
     * Returns command based on user input.
     * @return Command
     */
    public String readCommand() {
        Scanner input = new Scanner(System.in);
        return input.nextLine();
    }

    /**
     * Prints error message.
     * @param err Error.
     */
    public String showError(String err) {
        return err;
    }

    /**
     * Prints goodbye message.
     */
    public String sayGoodbye() {
        return "Bye. Hope to see you again soon!";
    }

    /**
     * Prints line.
     */
    public String showLine() {
        return "_________________________________________";
    }
}
