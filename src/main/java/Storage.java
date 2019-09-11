import java.util.ArrayList;
import java.util.Scanner;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Represents all methods related to storage.
 */
public class Storage {
    protected File ldFile;
    protected String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
        this.ldFile = new File(filePath);
    }

    /**
     * Returns list of all tasks based on file content.
     * If file not found, throws FileNotFoundException.
     * @return List of all tasks.
     * @throws FileNotFoundException If file is not found.
     */
    public ArrayList<Task> loadFile() throws FileNotFoundException {
        Scanner s = new Scanner(ldFile);
        ArrayList<Task> list = new ArrayList<>();

        while (s.hasNextLine()) {
            // Split all words in a row from file
            String line = s.nextLine();
            String [] item = line.split("-");
            assert (item[1].equals("1") || item[1].equals("0"));

            // Create and add task object into TaskList based on row data
            switch (item[0]) {
            case "T":
                assert item.length == 3;
                list.add(new Todo(item[2]));
                if (item[1].equals("1")) {
                    list.get(list.size() - 1).isDone = true;
                }
                break;
            case "E":
                assert item.length == 4;
                list.add(new Event(item[2], item[3]));
                if (item[1].equals("1")) {
                    list.get(list.size() - 1).isDone = true;
                }
                break;
            case "D":
                assert item.length == 4;
                list.add(new Deadline(item[2], item[3]));
                if (item[1].equals("1")) {
                    list.get(list.size() - 1).isDone = true;
                }
                break;
            default:
                assert false : item[0];
                break;
            }
        }
        return list;
    }

    /**
     * Overwrites file content with data of all tasks in list.
     * @param list List of all tasks.
     * @throws IOException If file is not found.
     */
    public void writeToFile(ArrayList<Task> list) throws IOException {
        String[] toBeSaved = new String[100];
        FileWriter fw = new FileWriter(this.filePath);

        // Retrieve all tasks from TaskList and convert into single string
        // Save all tasks into a string array
        for (int i = 0; i < list.size(); i++) {
            Task item = list.get(i);
            toBeSaved[i] = item.getInfo();
        }

        // Write string array into file
        for (int j = 0; j < list.size(); j++) {
            fw.write(toBeSaved[j] + System.lineSeparator());
        }

        fw.close();
    }
}
