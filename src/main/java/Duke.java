import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        Task[] list = new Task[100];

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
            // calls method to print all tasks in list
            if (command.equals("list")) {
                printList(list,n);
            }
            // marks tasks as done for the done command
            else if (command.contains("done")) {
                String [] cmdBreakDown = command.split(" ");
                int itemNo = Integer.parseInt(cmdBreakDown[1]) - 1;
                list[itemNo].isDone = true;
                System.out.println("Nice! I've marked this task as done:");
                // print out completed task with indentation
                System.out.println("   "+list[itemNo]);
            }
            // all other commands will be added into the list as a new task
            else {
                Task newItem = new Task(command);
                list[n] = newItem;
                System.out.println("added: "+newItem.description);
                n++;
            }
            command = input.nextLine();
        }
        System.out.println("Bye. Hope to see you again soon!");
    }

    //Prints all list items in the string array
    private static void printList(Task[] list, int n) {
        System.out.println("Here are the tasks in your list:");
        for (int i=0; i<n; i++) {
            int listNum = i+1;
            System.out.println(listNum+". "+"["+list[i].getStatusIcon()+"] "+list[i].description);
        }
    }

}

