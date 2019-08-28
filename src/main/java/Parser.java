import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Parser {
    boolean isTodo, isDeadline, isEvent, isList,isBye,isDone,isDelete;
    String [] cmdBreakdown;

    // Reformats command line based on command type i.e. todo, deadline, event
    public Command parseCommand(String cmd, int n) throws DukeException{
        cmdBreakdown = cmd.split(" ");
        // Reformatted command only has command type, description, /by or /at and time
        String [] cmdFormatted = new String[4];
        Command finalCmd = null;
        int i = 0;
        boolean continuing = false;

        // Directly input command type into first element of cmdFormatted
        cmdFormatted[i] = this.cmdBreakdown[i];
        isTodo = cmdFormatted[0].equals("todo");
        isDeadline = cmdFormatted[0].equals("deadline");
        isEvent = cmdFormatted[0].equals("event");
        isList = cmdFormatted[0].equals("list");
        isBye = cmdFormatted[0].equals("bye");
        isDone = cmdFormatted[0].equals("done");
        isDelete = cmdFormatted[0].equals("delete");
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
        validateCmd(cmdFormatted, n);
        if (isBye) {
            finalCmd = new Command(cmdFormatted[0]);
        }
        if (isList) {
            finalCmd = new PrintCommand(cmdFormatted[0]);
        }
        if (isTodo) {
            finalCmd = new AddCommand(cmdFormatted[0],cmdFormatted[1],null);
        }
        if (isDeadline || isEvent) {
            String convertedDate;
            try {
                convertedDate = setDate(cmdFormatted[3]);
            } catch (ParseException e) {
                System.out.println("Warning - Unable to format date/time input: "+cmdFormatted[3]);
                convertedDate = cmdFormatted[3];
            }
            finalCmd = new AddCommand(cmdFormatted[0],cmdFormatted[1],convertedDate);
        }
        if (isDone) {
            finalCmd = new EditCommand(cmdFormatted[0], cmdFormatted[1]);
        }
        if (isDelete) {
            finalCmd = new DeleteCommand(cmdFormatted[0], cmdFormatted[1]);
        }

        return finalCmd;
    }

    // Reformat date/time input into (dd MMMM, yyyy hh a) format e.g. 12 December, 2019, 06pm
    // Adapted from https://stackoverflow.com/questions/10308720/java-change-date-format-from-custom-date-to-mm-dd-yyyy
    private static String setDate(String input) throws ParseException {
        SimpleDateFormat parser = new SimpleDateFormat("dd-MM-yyyy HH:mm");
        SimpleDateFormat formatter = new SimpleDateFormat("dd MMMM, yyyy, hh:mm a");
        Date convertedDate = null;
        convertedDate = parser.parse(input);
        return formatter.format(convertedDate);
    }

    public void validateCmd(String [] cmd, int n) throws DukeException {
        // validate user input command for following requirements

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
