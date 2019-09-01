import java.util.Scanner;

/**
 * Represents all methods related to user interface.
 */
public class Ui {
    /**
     * Prints loadingError if unable to find file to load content.
     */
    public void showLoadingError() {
        new DukeException("Unable to find File!");
    }

    /**
     * Prints savingError if unable to find file to write content.
     */
    public void showSavingError() {
        new DukeException("Unable to save File!");
    }

    /**
     * Prints welcome message.
     */
    public void showWelcome() {
        /**String logo = " ____        _        \n"
         + "|  _ \\ _   _| | _____ \n"
         + "| | | | | | | |/ / _ \\\n"
         + "| |_| | |_| |   <  __/\n"
         + "|____/ \\__,_|_|\\_\\___|\n";
         System.out.println("Hello from\n" + logo);**/

        System.out.println("Hello! I'm Duke\n");
        System.out.println("What can I do for you?");
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
    public void showError(String err) {
        System.out.println(err);
    }

    /**
     * Prints goodbye message.
     */
    public void sayGoodbye() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    /**
     * Prints line.
     */
    public void showLine() {
        System.out.println("_________________________________________");
    }
}
