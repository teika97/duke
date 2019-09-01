import java.util.Scanner;

public class Ui {
    public void showLoadingError() {
        new DukeException("Unable to find File!");
    }

    public void showSavingError() {
        new DukeException("Unable to save File!");
    }

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

    public String readCommand() {
        Scanner input = new Scanner(System.in);
        return input.nextLine();
    }

    public void showError(String err) {
        System.out.println(err);
    }

    public void sayGoodbye() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    public void showLine() {
        System.out.println("_________________________________________");
    }
}
