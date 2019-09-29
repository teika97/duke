import java.util.ArrayList;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private Parser parser;
    private String root = "";
    private String fileDir = "data";
    private String fileName = "data.txt";
    private String dataFilePath = "";
    private String dataDirPath = "";

    /**
     * Constructor.
     */
    public Duke() throws DukeException {
        ui = new Ui();

        try {
            // Checks for whether a data directory and a data file exists within that directory
            // New data directory or data file is created if necessary
            // Solution below adapted from https://github.com/nus-cs2103-AY1920S1/duke/pull/37/commits/060a6e36a706715f663c335ddb0b7d615fd4af81
            root = System.getProperty("user.dir");
            dataFilePath = root + File.separator + fileDir + File.separator + fileName;
            dataDirPath = root + File.separator + fileDir;
            File dataFile = new File(dataFilePath);
            File dataDir = new File(dataDirPath);
            if (dataDir.mkdir()) {
                dataFile.createNewFile();
                System.out.println("New directory and file created.");
            } else {
                if (dataFile.mkdir()) {
                    System.out.println("New file created.");
                }
                else {
                    System.out.println("File already exists.");
                }
            }
        } catch (IOException e) {
            throw new DukeException("There is an error with setting up data file requirements.");
        }

        parser = new Parser();
        storage = new Storage(dataFilePath);
        try {
            tasks = new TaskList(storage.loadFile());
        } catch (FileNotFoundException e) {
            ui.showLoadingError();
            tasks = new TaskList(new ArrayList<Task>());
        }
    }

    /**
     * Prints out welcome message.
     * @return welcome message
     */
    public String showWelcome() {
        return ui.showWelcome();
    }

    /**
     * Prints out goodbye message.
     * @return goodbye message
     */
    public String sayGoodbye() { return ui.sayGoodbye();}

    /**
     * Runs duke program to get response for user command input.
     * @param input user command
     * @return program response
     */
    public String getResponse(String input) {
        String response = "";
        boolean isExit = false;
        try {
            Command c = parser.parseCommand(input,tasks.getSize());
            response = c.execute(tasks,ui,storage);
            isExit = c.isExit();
        } catch (DukeException e) {
            response = ui.showError(e.getMessage());
        }
        if (isExit) {
            response = ui.sayGoodbye();
            try {
                storage.writeToFile(tasks.list);
            } catch (IOException e) {
                response = ui.showSavingError();
            }
        }
        return response;
    }
}

