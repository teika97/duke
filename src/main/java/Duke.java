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

    /**
     * Runs the Duke program.
     */
    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine();
                Command c = parser.parseCommand(fullCommand,tasks.getSize());
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (DukeException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showLine();
            }
        }
        try {
            storage.writeToFile(tasks.list);
        } catch (IOException e) {
            ui.showSavingError();
        }

    }

    public static void main(String[] args) {
        new Duke("/Users/Kai/Documents/GitHub/duke/data/data.txt").run();
    }
}

