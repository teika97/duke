import javax.swing.*;
import java.text.ParseException;
import java.util.*;
import java.io.*;
import java.text.SimpleDateFormat;

public class Duke {
    private Storage storage;
    private taskList tasks;
    private Ui ui;
    private Parser parser;

    public Duke (String filePath) {
        ui = new Ui();
        parser = new Parser();
        storage = new Storage(filePath);
        try {
            tasks = new taskList(storage.loadFile());
        } catch (FileNotFoundException e) {
            ui.showLoadingError();
            tasks = new taskList(new ArrayList<Task>());
        }
    }

    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine();
                Command c = parser.parseCommand(fullCommand,tasks.getSize());
                c.execute(tasks,ui,storage);
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
        new Duke("/Users/Kai/Documents/GitHub/duke/src/main/data/data.txt").run();
    }


}

