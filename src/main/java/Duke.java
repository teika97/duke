import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        /**String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        **/

        System.out.println("Hello! I'm Duke\n");
        System.out.println("What can I do for you?");
        String command = input.next();

        while (command!="bye") {
            System.out.println(command);
            command = input.next();
        }
        System.out.println("Bye. Hope to see you again soon!");
    }
}
