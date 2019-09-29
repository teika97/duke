import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.util.Date;

/**
 * Represents all methods related to parsing of commands.
 */
public class Parser {
    boolean isTodo;
    boolean isDeadline;
    boolean isEvent;
    boolean isList;
    boolean isBye;
    boolean isDone;
    boolean isDelete;
    boolean isFind;
    boolean isSort;
    String [] cmdUnits;

    /**
     * Returns command based on the command type e.g. todo, deadline, event, etc.
     * Validates command, if not valid, DukeException is thrown.
     * @param cmd Command string based on user input.
     * @param n Total no. of tasks in list.
     * @return Command.
     * @throws DukeException If command input is not valid.
     */
    public Command parseCommand(String cmd, int n) throws DukeException {
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
        isFind = finalCmdUnits[0].equals("find");
        isSort = finalCmdUnits[0].equals("sort");

        i++;
        assert i == 1;
        for (int j = 1; j < cmdUnits.length; j++) {
            // If not separated by /by or /at
            // Words in cmdUnits concatenated into same finalCmdUnit
            // /by or /at saved as a separate variable, finalCmdUnits[2]
            if (cmdUnits[j].contains("/")) {
                i++;
                assert i == 2;
                finalCmdUnits[i] = cmdUnits[j];
                i++;
                assert i == 3;
                continuing = false;
            } else {
                assert (i == 1 || i == 3);
                if (!continuing) {
                    finalCmdUnits[i] = cmdUnits[j];
                } else {
                    finalCmdUnits[i] = finalCmdUnits[i].concat(" " + cmdUnits[j]);
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
                throw new DukeException("Warning - Unable to format date/time input.");
            }
            finalCmd = new AddCommand(finalCmdUnits[0], finalCmdUnits[1], convertedDate);
        }
        if (isDone) {
            finalCmd = new EditCommand(finalCmdUnits[0], finalCmdUnits[1]);
        }
        if (isDelete) {
            finalCmd = new DeleteCommand(finalCmdUnits[0], finalCmdUnits[1]);
        }
        if (isFind) {
            finalCmd = new FindCommand(finalCmdUnits[0], finalCmdUnits[1]);
        }
        if (isSort) {
            finalCmd = new SortCommand(finalCmdUnits[0]);
        }

        return finalCmd;
    }

    // Adapted from https://stackoverflow.com/questions/10308720/java-change-date-format-from-custom-date-to-mm-dd-yyyy
    /**
     * Returns translated date/time input into (dd MMMM, yyyy, hh:mm a) format e.g. 12 December, 2019, 06:00 PM.
     * @param input Date from user input.
     * @return Translated Date.
     * @throws ParseException If user input is unparseable.
     */
    public static String setDate(String input) throws ParseException {
        SimpleDateFormat parser = new SimpleDateFormat("dd-MM-yyyy HH:mm");
        SimpleDateFormat formatter = new SimpleDateFormat("dd MMMM, yyyy, hh:mm a");
        Date convertedDate = null;
        convertedDate = parser.parse(input);
        return formatter.format(convertedDate);
    }

    /**
     * Checks if command input from user is valid.
     * If not, throws DukeException.
     * @param cmd Command string array reformatted from user input.
     * @param n Total no. of tasks in list.
     * @throws DukeException If command input is not valid.
     */
    public void validateCmd(String [] cmd, int n) throws DukeException {
        // Throw DukeException based on which requirement it violates
        if (!(isTodo || isDeadline || isEvent || isList || isBye || isDone || isDelete || isFind || isSort)) {
            throw new DukeException("That command doesn't make any sense.");
        }
        if (isTodo || isDeadline || isEvent) {
            if (cmd[1] == null) {
                throw new DukeException("You forgot to add the task description.");
            }
            if ((isDeadline || isEvent) && (cmd[3] == null)) {
                throw new DukeException("You forgot to add the date and time for " + cmd[0] + ".\n" + "Don't forget that for a "
                        + cmd[0] + " you need to add\n" + (isDeadline ? "/by" : "/at") + " before the date and time.");
            }
            if ((isDeadline || isEvent) && !(cmd[2].equals(isDeadline ? "/by" : "/at"))) {
                throw new DukeException("Sorry about being so strict, but a " + cmd[0] + " must have a\n " + (isDeadline ? "/by" : "/at")
                        + " before the date and time.");
            }

        }
        if (isDone || isDelete) {
            int itemNo;
            try {
                itemNo = Integer.parseInt(cmd[1]);
            } catch (NumberFormatException exception) {
                throw new DukeException("What you entered doesn't make any sense. Please use the correct format.");
            }
            if (itemNo > n) {
                throw new DukeException("You only have " + n + " tasks in the list currently.");
            }
            if (!(cmd[2] == null) || !(cmd[3] == null)) {
                throw new DukeException("What you entered doesn't make any sense. Please use the correct format.");
            }
        }
        if (isFind) {
            String [] keyWords = cmd[1].split(" ");
            if (keyWords.length > 1) {
                throw new DukeException("We currently can only search using one key word. Sorry for the inconvenience.");
            }
        }
    }
}
