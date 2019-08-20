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
        String [] formattedCmd = formatCommand(command);
        // n is the no. of items in the list
        int n = 0;
        while (!(formattedCmd[0].equals("bye"))) {
            // calls method to print all tasks in list
            if (formattedCmd[0].equals("list")) {
                printList(list,n);
            }
            // marks tasks as done for the done command
            else if (formattedCmd[0].contains("done")) {
                int itemNo = Integer.parseInt(formattedCmd[1]) - 1;
                list[itemNo].isDone = true;
                System.out.println("Nice! I've marked this task as done:");
                // print out completed task with indentation
                System.out.println("   "+list[itemNo]);
            }
            // all other commands will be added into the list as a new task
            else {
                switch (formattedCmd[0]) {
                    case "todo":
                        list[n] = new Todo(formattedCmd[1]);
                        break;
                    case "deadline":
                        list[n] = new Deadline(formattedCmd[1],formattedCmd[2]);
                        break;
                    case "event":
                        list[n] = new Event(formattedCmd[1],formattedCmd[2]);
                        break;
                }
                System.out.println("Got it. I've added this task:");
                System.out.println("   "+list[n]);
                n++;
                if (n>1) {
                    System.out.println("Now you have " + n + " tasks in the list.");
                }
                else {
                    System.out.println("Now you have " + n + " task in the list.");
                }
            }
            command = input.nextLine();
            formattedCmd = formatCommand(command);
        }
        System.out.println("Bye. Hope to see you again soon!");
    }

    // Prints all list items in the string array
    private static void printList(Task[] list, int n) {
        System.out.println("Here are the tasks in your list:");
        for (int i=0; i<n; i++) {
            int listNum = i+1;
            System.out.println(listNum+"."+list[i]);
        }
    }

    // Reformats command line based on command type i.e. todo, deadline, event
    private static String[] formatCommand (String cmd) {
        String [] cmdBreakdown = cmd.split(" ");
        // Reformatted command only has command type, description, time
        String [] cmdFormatted = new String[3];
        int i = 0;
        boolean continuing = false;
        // Directly input command type into first element of cmdFormatted
        cmdFormatted[i] = cmdBreakdown[i];
        i++;
        for (int j=1; j<cmdBreakdown.length; j++) {
            if (cmdBreakdown[j].contains("/")) {
                i++;
                continuing = false;
            }
            else {
                if (continuing==false) {
                    cmdFormatted[i] = cmdBreakdown[j];
                }
                else {
                    cmdFormatted[i] = cmdFormatted[i].concat(" "+cmdBreakdown[j]);
                }
                continuing = true;
            }
        }
        // For checking
        /**for (int k=0; k<3; k++) {
            System.out.println(k+". "+cmdFormatted[k]);
        }**/
        return cmdFormatted;
    }
}

