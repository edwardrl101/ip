package yoshi.storage;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException;

import yoshi.task.Deadline;
import yoshi.task.Event;
import yoshi.task.Task;
import yoshi.task.Todo;
import yoshi.ui.Printer;

public class Storage {
    private static String filePath;

    static Printer printer = new Printer();
    static String FILE_PATH = "yoshi.txt";
    private static final String SUCCESSFUL_UPDATE_MESSAGE = "Yay! Your save file has been updated successfully :D";

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Updates the save file whenever a new task is toggled as done/not done, added, or deleted.
     * @param tasks the task list
     * @throws FileNotFoundException Exception to be thrown in case file is not found.
     * @throws IOException Exception to be thrown in case there are issues with updating the file.
     */
    public static void updateFile(ArrayList<Task> tasks) throws FileNotFoundException, IOException {
        try {
            FileWriter f = new FileWriter(filePath);
            for (Task task : tasks) {
                String status = task.getDoneStatus() ? "1" : "0";
                if (task instanceof Todo) {
                    f.write("T | " + status + " | " + task.getDescription() + "\n");
                } else if (task instanceof Deadline) {
                    f.write("D | " + status + " | " + task.getDescription() + " | " + ((Deadline) task).getBy() + "\n");
                } else if (task instanceof Event) {
                    f.write("E | " + status + " | " + task.getDescription() + " | " + ((Event) task).getFrom() + " | " + ((Event) task).getTo() + "\n");
                }
            }
            f.close();
            printer.printWithIndentation(SUCCESSFUL_UPDATE_MESSAGE);
        } catch (FileNotFoundException e) {
            System.out.print("");
        } catch (IOException e) {
            printer.printWithSeparator("Error updating file: " + e.getMessage());
        }
    }

    /**
     * Copies the tasks from the save file to the task list.
     * @param file the file to be read.
     * @param tasks the task list.
     * @throws FileNotFoundException Exception to be thrown in case file is not found.
     * @throws IOException Exception to be thrown in case there are issues with reading the file.
     */
    public static void copyTasks(File file, ArrayList<Task> tasks) throws FileNotFoundException, IOException {
        Scanner s = new Scanner(file);
        while (s.hasNext()) {
            String foundTask = s.nextLine();
            String[] parts = foundTask.split("\\|");

            Task task;
            switch (parts[0].trim()) {
            case "T":
                task = new Todo(parts[2].trim());
                break;
            case "D":
                task = new Deadline(parts[2].trim(), parts[3].trim());
                break;
            case "E":
                task = new Event(parts[2].trim(), parts[3].trim(), parts[4].trim());
                break;
            default:
                System.out.println("Sorry! I don't know what this is: " + parts[0]);
                continue;
            }

            if ("1".equals(parts[1].trim())) {
                task.markAsDone();
            } else {
                task.unmarkAsDone();
            }

            tasks.add(task);
        }
    }

    /**
     * Loads the tasks to the CLI.
     * @return the task list along with the tasks loaded from the save file.
     * @throws IOException Exception to be thrown in case there are issues with reading the file.
     */
    public static ArrayList<Task> loadTasks() throws IOException {
        File f = new File(filePath);
        ArrayList<Task> tasks = new ArrayList<>();
        if (f.exists() && !f.isDirectory()) {
            File file = new File(filePath); // create a File for the given file path
            copyTasks(file, tasks);
            return tasks;
        } else {
            try {
                File file = new File(filePath);
                file.createNewFile();
            } catch (IOException e) {
                printer.printWithSeparator("Oh no! I failed to create a file here: " + filePath);
            }
        }
        return tasks;
    }
}
