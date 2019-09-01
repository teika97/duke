import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.util.Date;

public class Parser {
    boolean isTodo, isDeadline, isEvent, isList,isBye,isDone,isDelete;
    String [] cmdUnits;

    // Reformats command line based on command type i.e. todo, deadline, event
    public Command parseCommand(String cmd, int n) throws DukeException{
        // Split all words in the command string into cmdUnits
        cmdUnits = cmd.split(" ");

        // Initialize variables used for reformatting command
        // Command has only command type, description, "/by" or "/at" and time
        String [] finalCmdUnits = new String[4];
        Command finalCmd = null;
        int i = 0;
        boolean continuing = false;

        // First element of cmdUnits is Command type
        // Input into first element of finalCmdUnits
        finalCmdUnits[i] = this.cmdUnits[i];

        // Initialize all boolean variables based on command type
        isTodo = finalCmdUnits[0].equals("todo");
        isDeadline = finalCmdUnits[0].equals("deadline");
        isEvent = finalCmdUnits[0].equals("event");
        isList = finalCmdUnits[0].equals("list");
        isBye = finalCmdUnits[0].equals("bye");
        isDone = finalCmdUnits[0].equals("done");
        isDelete = finalCmdUnits[0].equals("delete");

        i++;
        for (int j = 1; j < cmdUnits.length; j++) {
            // If not separated by /by or /at
            // Words in cmdUnits concatenated into same finalCmdUnit
            // /by or /at saved as a separate variable, finalCmdUnits[2]
            if (cmdUnits[j].contains("/")) {
                i++;
                finalCmdUnits[i] = cmdUnits[j];
                i++;
                continuing = false;
            } else {
                if (!continuing) {
                    finalCmdUnits[i] = cmdUnits[j];
                } else {
                    finalCmdUnits[i] = finalCmdUnits[i].concat(" "+ cmdUnits[j]);
                }
                continuing = true;
            }
        }

        // Check command does not violate any input format restrictions
        validateCmd(finalCmdUnits, n);

        // Create new command variable based on command type
        if (isBye) {
            finalCmd = new Command(finalCmdUnits[0]);
        }
        if (isList) {
            finalCmd = new PrintCommand(finalCmdUnits[0]);
        }
        if (isTodo) {
            finalCmd = new AddCommand(finalCmdUnits[0], finalCmdUnits[1], null);
        }
        if (isDeadline || isEvent) {
            String convertedDate;
            try {
                convertedDate = setDate(finalCmdUnits[3]);
            } catch (ParseException e) {
                System.out.println("Warning - Unable to format date/time input.");
                convertedDate = finalCmdUnits[3];
            }
            finalCmd = new AddCommand(finalCmdUnits[0], finalCmdUnits[1], convertedDate);
        }
        if (isDone) {
            finalCmd = new EditCommand(finalCmdUnits[0], finalCmdUnits[1]);
        }
        if (isDelete) {
            finalCmd = new DeleteCommand(finalCmdUnits[0], finalCmdUnits[1]);
        }

        return finalCmd;
    }

    // Reformat date/time input into (dd MMMM, yyyy hh a) format e.g. 12 December, 2019, 06pm
    // Adapted from https://stackoverflow.com/questions/10308720/java-change-date-format-from-custom-date-to-mm-dd-yyyy
    public static String setDate(String input) throws ParseException {
        SimpleDateFormat parser = new SimpleDateFormat("dd-MM-yyyy HH:mm");
        SimpleDateFormat formatter = new SimpleDateFormat("dd MMMM, yyyy, hh:mm a");
        Date convertedDate = null;
        convertedDate = parser.parse(input);
        return formatter.format(convertedDate);
    }

    public void validateCmd(String [] cmd, int n) throws DukeException {
        // Throw DukeException based on which requirement it violates
        if (!(isTodo || isDeadline || isEvent || isList || isBye || isDone || isDelete)) {
            throw new DukeException("Command type not valid.");
        }
        if (isTodo || isDeadline || isEvent) {
            if (cmd[1] == null) {
                throw new DukeException("The description of a " + cmd[0] + " cannot be empty.");
            }
            if ((isDeadline || isEvent) && (cmd[3] == null)) {
                throw new DukeException("No date/time input for " + cmd[0] + "." + " Note: Input for "
                        + cmd[0] + " must have " + (isDeadline? "/by" : "/at") + " before date/time.");
            }
            if ((isDeadline || isEvent) && !(cmd[2].equals(isDeadline? "/by" : "/at"))) {
                throw new DukeException("Input for " + cmd[0] + " must have " + (isDeadline? "/by" : "/at") + ".");
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
                throw new DukeException("Task " + itemNo + " not found in list.");
            }
            if (!(cmd[2] == null) || !(cmd[3] == null)) {
                throw new DukeException("Unable to format due to improper input.");
            }
        }
    }

}
