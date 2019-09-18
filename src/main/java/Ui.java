import java.util.Scanner;

/**
 * Represents all methods related to user interface.
 */
public class Ui {
    /**
     * Prints loadingError if unable to find file to load content.
     */
    public String showLoadingError() {
        DukeException loadingError = new DukeException("That file doesn't exist.");
        return loadingError.getMessage();
    }

    /**
     * Prints savingError if unable to find file to write content.
     */
    public String showSavingError() {
        DukeException savingError = new DukeException("Something went wrong when saving the file.");
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

        return "Hey! Duke speaking. \nHow may I help you today?";
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
        return "Glad to be able to help. See you again soon!";
    }

    /**
     * Print details after adding new task into list.
     * @param num Number of tasks in list after adding
     * @param newTask New task added
     * @return print output
     */
    public String printAddedCommand(int num, Task newTask) {
        String line1 = "Got it. I've added this task:\n" + "   " + newTask;
        String line2 = "";
        if (num > 1) {
            line2 = "Now you have " + num + " tasks in the list.";
        } else {
            line2 = "Now you have " + num + " task in the list.";
        }
        return line1 + "\n" + line2;
    }

    /**
     * Print details after removing task from list.
     * @param num Number of tasks in list after removing task.
     * @param removedTask Old task removed
     * @return print output
     */
    public String printRemovalCommand(int num, Task removedTask) {
        String line1 = "Noted. I've removed this task:\n" + "   " + removedTask;
        String line2 = "Now you have " + num + " in the list.";

        return line1 + "\n" + line2;
    }

    /**
     * Prints line.
     */
    public String showLine() {
        return "_________________________________________";
    }
}
