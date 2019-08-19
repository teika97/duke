import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        String[] list = new String[100];

        /**String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        **/

        System.out.println("Hello! I'm Duke\n");
        System.out.println("What can I do for you?");

        String command = input.nextLine();
        // n is the no. of items in the list
        int n = 0;
        while (!(command.equals("bye"))) {
            if (command.equals("list")) {
                printList(list,n);
            }
            // commands that are not "bye" or "list" are added into list
            else {
                String newItem = command;
                list[n] = newItem;
                System.out.println("added: "+newItem);
                n++;
            }
            command = input.nextLine();
        }
        System.out.println("Bye. Hope to see you again soon!");
    }

    //Prints all list items in the string array
    private static void printList(String[] list, int n) {
        for (int i=0; i<n; i++) {
            int listNum = i+1;
            System.out.println(listNum+". "+list[i]);
        }
    }

}
