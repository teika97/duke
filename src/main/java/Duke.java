import java.util.*;
import java.io.*;

public class Duke {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        ArrayList<Task> list = new ArrayList<Task>();

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
        String [] formattedCmd = formatCommand(command, list.size());

        while (!(formattedCmd[0].equals("bye"))) {
            // calls method to print all tasks in list
            if (formattedCmd[0].equals("list")) {
                printList(list);
            }
            // marks tasks as done for the done command
            else if (formattedCmd[0].contains("done")) {
                int itemNo = Integer.parseInt(formattedCmd[1]) - 1;
                list.get(itemNo).isDone = true;
                System.out.println("Nice! I've marked this task as done:");
                // print out completed task with indentation
                System.out.println("   "+list.get(itemNo));
            }
            // delete task from list
            else if (formattedCmd[0].contains("delete")) {
                int itemNo = Integer.parseInt(formattedCmd[1]) - 1;
                System.out.println("Noted. I've removed this task:");
                System.out.println("   "+list.get(itemNo));
                list.remove(itemNo);
                System.out.println("Now you have "+list.size()+" in the list.");
            }
            else {
                boolean hasError = false;
                switch (formattedCmd[0]) {
                    case "todo":
                        list.add(new Todo(formattedCmd[1]));
                        break;
                    case "deadline":
                        list.add(new Deadline(formattedCmd[1],formattedCmd[3]));
                        break;
                    case "event":
                        list.add(new Event(formattedCmd[1],formattedCmd[3]));
                        break;
                    default:
                        hasError = true;
                }
                if (!hasError) {
                    System.out.println("Got it. I've added this task:");
                    System.out.println("   " + list.get(list.size()-1));
                    if (list.size() > 1) {
                        System.out.println("Now you have " + list.size() + " tasks in the list.");
                    } else {
                        System.out.println("Now you have " + list.size() + " task in the list.");
                    }
                }

            }
            try {
                writeToFile(filepath,list);
            } catch (IOException e) {
                System.out.println("Unable to write to file: "+e.getMessage());
            }
            command = input.nextLine();
            formattedCmd = formatCommand(command, list.size());
        }
        System.out.println("Bye. Hope to see you again soon!");
    }

    private static void writeToFile(String filePath, ArrayList<Task> list) throws IOException {
        String [] toBeSaved = new String[100];
        FileWriter fw = new FileWriter(filePath);
        for (int i=0; i<list.size(); i++) {
            Task item = list.get(i);
            toBeSaved[i] = item.getInfo();
        }
        for (int j=0; j<list.size(); j++) {
            fw.write(toBeSaved[j]+System.lineSeparator());
        }
        fw.close();
    }

    // Prints all list items in the string array
    private static void printList(ArrayList<Task> list) {
        System.out.println("Here are the tasks in your list:");
        for (int i=0; i<list.size(); i++) {
            int listNum = i+1;
            System.out.println(listNum+"."+list.get(i));
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
        }*/
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
        boolean isDelete = cmd[0].equals("delete");

        if (!(isTodo||isDeadline||isEvent||isList||isBye||isDone||isDelete)) {
            throw new DukeException("Command type not valid.");
        }
        if (isTodo||isDeadline||isEvent) {
            if (cmd[1]==null) {
                throw new DukeException("The description of a "+cmd[0]+" cannot be empty.");
            }
            if ((isDeadline||isEvent)&&(cmd[3]==null)) {
                throw new DukeException("No date/time input for "+cmd[0]+ "."+
                                        " Note: Input for "+cmd[0]+" must have "+(isDeadline? "/by" : "/at")+" before date/time.");
            }
            if ((isDeadline||isEvent)&&!(cmd[2].equals(isDeadline? "/by" : "/at"))) {
                throw new DukeException("Input for "+cmd[0]+" must have "+(isDeadline? "/by" : "/at")+".");
            }

        }
        if (isDone || isDelete) {
            int itemNo;
            try {
                itemNo = Integer.parseInt(cmd[1]);
            } catch (NumberFormatException exception) {
                throw new DukeException("Unable to format due to improper input.");
            }
            if (itemNo > n) {
                throw new DukeException("Task "+itemNo+" not found in list.");
            }
            if (!(cmd[2]==null)||!(cmd[3]==null)) {
                throw new DukeException("Unable to format due to improper input.");
            }
        }
    }
}

