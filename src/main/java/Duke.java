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
        // n is the no. of items in the list
        int n = 0;
        String command = input.nextLine();
        String [] formattedCmd = formatCommand(command, n);

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
                boolean hasError = false;
                switch (formattedCmd[0]) {
                    case "todo":
                        list[n] = new Todo(formattedCmd[1]);
                        break;
                    case "deadline":
                        list[n] = new Deadline(formattedCmd[1],formattedCmd[3]);
                        break;
                    case "event":
                        list[n] = new Event(formattedCmd[1],formattedCmd[3]);
                        break;
                    default:
                        hasError = true;
                }
                if (!hasError) {
                    System.out.println("Got it. I've added this task:");
                    System.out.println("   " + list[n]);
                    n++;
                    if (n > 1) {
                        System.out.println("Now you have " + n + " tasks in the list.");
                    } else {
                        System.out.println("Now you have " + n + " task in the list.");
                    }
                }
            }
            command = input.nextLine();
            formattedCmd = formatCommand(command, n);
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
    private static String[] formatCommand (String cmd, int n) {
        String [] cmdBreakdown = cmd.split(" ");
        // Reformatted command only has command type, description, /by or /at and time
        String [] cmdFormatted = new String[4];
        int i = 0;
        boolean continuing = false;
        // Directly input command type into first element of cmdFormatted
        cmdFormatted[i] = cmdBreakdown[i];
        i++;
        for (int j=1; j<cmdBreakdown.length; j++) {
            if (cmdBreakdown[j].contains("/")) {
                i++;
                cmdFormatted[i] = cmdBreakdown[j];
                i++;
                continuing = false;
            }
            else {
                if (!continuing) {
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

        try {
            validateCmd(cmdFormatted,n);
        } catch(Exception m) {cmdFormatted[0] = "Error";
                              System.out.println("Exception occurred.");}

        return cmdFormatted;
    }

    public static void validateCmd(String [] cmd, int n) throws DukeException {
        // validate user input command for following requirements
        boolean isTodo = cmd[0].equals("todo");
        boolean isDeadline = cmd[0].equals("deadline");
        boolean isEvent = cmd[0].equals("event");
        boolean isList = cmd[0].equals("list");
        boolean isBye = cmd[0].equals("bye");
        boolean isDone = cmd[0].equals("done");

        if (!(isTodo||isDeadline||isEvent||isList||isBye||isDone)) {
            throw new DukeException("Command type not valid.");
        }
        if (isTodo||isDeadline||isEvent) {
            if (cmd[1]==null) {
                throw new DukeException("The description of a "+cmd[0]+" cannot be empty.");
            }
            if ((cmd[0].equals("deadline")||cmd[0].equals("event"))&&(cmd[3]==null)) {
                throw new DukeException("No date/time input for "+cmd[0]+ "."+
                                        " Note: Input for "+cmd[0]+" must have "+(isDeadline? "/by" : "/at."));
            }
            if ((isDeadline||isEvent)&&!(cmd[2].equals(isDeadline? "/by" : "/at"))) {
                throw new DukeException("Input for "+cmd[0]+" must have "+(isDeadline? "/by" : "/at."));
            }

        }
        if (cmd[0].equals("done")) {
            int itemNo = Integer.parseInt(cmd[1]);
            if (itemNo > n) {
                throw new DukeException("Task "+itemNo+" not found in list.");
            }
        }
    }
}

