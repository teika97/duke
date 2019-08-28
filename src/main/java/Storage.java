import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    protected File f;
    protected String filePath;

    public Storage (String filePath) {
        this.filePath = filePath;
        this.f = new File(filePath);
    }
    public ArrayList<Task> loadFile() throws FileNotFoundException {
        Scanner s = new Scanner(f);
        ArrayList <Task> list = new ArrayList<>();
        while (s.hasNextLine()) {
            String line = s.nextLine();
            String [] item = line.split("-");
            switch (item[0]) {
                case "T":
                    list.add(new Todo(item[2]));
                    if (item[1].equals("1")) {
                        list.get(list.size()-1).isDone = true;
                    }
                    break;
                case "E":
                    list.add(new Event(item[2],item[3]));
                    if (item[1].equals("1")) {
                        list.get(list.size()-1).isDone = true;
                    }
                    break;
                case "D":
                    list.add(new Deadline(item[2],item[3]));
                    if (item[1].equals("1")) {
                        list.get(list.size()-1).isDone = true;
                    }
                    break;
            }
        }
        return list;
    }
    public void writeToFile(ArrayList<Task> list) throws IOException {
        String[] toBeSaved = new String[100];
        FileWriter fw = new FileWriter(this.filePath);
        for (int i = 0; i < list.size(); i++) {
            Task item = list.get(i);
            toBeSaved[i] = item.getInfo();
        }
        for (int j = 0; j < list.size(); j++) {
            fw.write(toBeSaved[j] + System.lineSeparator());
        }
        fw.close();
    }
}
