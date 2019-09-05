import java.util.ArrayList;

import java.io.FileNotFoundException;
import java.io.IOException;

public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private Parser parser;

    /**
     * Constructor.
     * @param filePath location of file
     */
    public Duke(String filePath) {
        ui = new Ui();
        parser = new Parser();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.loadFile());
        } catch (FileNotFoundException e) {
            ui.showLoadingError();
            tasks = new TaskList(new ArrayList<Task>());
        }
    }

    public String showWelcome() {
        return ui.showWelcome();
    }

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

