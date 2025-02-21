package yoshi.ui;
import yoshi.ui.Printer;

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

public class Storage {
    static Printer printer = new Printer();
    static String filePath = "src/main/java/yoshi/data/yoshi.txt";

    public static void updateFile(ArrayList<Task> tasks) throws FileNotFoundException, IOException {
        try { //overwriting the file
            FileWriter f = new FileWriter(filePath); // create a File for the given file path
            int numTasks = 0;
            for (Task task : tasks) {
                if (task.getDoneStatus()) {
                    if (task.getClass() == Todo.class) {
                        f.write("T|1|"+task.getDescription()+"\n");
                    } else if (task.getClass() == Deadline.class) {
                        f.write("D|1|"+task.getDescription()+"|"+((Deadline) task).getBy()+"\n");
                    } else {
                        f.write("E|1|"+task.getDescription()+"|"+((Event) task).getFrom()+"|"+((Event) task).getTo()+"\n");
                    }
                }
                else {
                    if (task.getClass() == Todo.class) {
                        f.write("T|0|"+task.getDescription()+"\n");
                    } else if (task.getClass() == Deadline.class) {
                        f.write("D|0|"+task.getDescription()+"|"+((Deadline) task).getBy()+"\n");
                    } else {
                        f.write("E|0|"+task.getDescription()+"|"+((Event) task).getFrom()+"|"+((Event) task).getTo()+"\n");
                    }
                }
                numTasks++;
            }
            f.close();
            printer.printWithSeparator("File has been updated successfully!");
        } catch (FileNotFoundException e) {
            printer.printWithSeparator("File not found: " + filePath);
        } catch (IOException e) {
            printer.printWithSeparator("Error updating file: " + e.getMessage());
        }
    }

    public static void printFileContents() throws FileNotFoundException {
        try {
            File f = new File(filePath); // create a File for the given file path
            Scanner s = new Scanner(f); // create a Scanner using the File as the source
            while (s.hasNext()) {
                System.out.println(s.nextLine());
            }
        } catch (FileNotFoundException e) {
            printer.printWithSeparator("File not found: " + filePath);
        }
    }

    public static void copyContents(ArrayList<Task> tasks) throws FileNotFoundException {
        try {
            File f = new File(filePath); // create a File for the given file path
            Scanner s = new Scanner(f); // create a Scanner using the File as the source
            while (s.hasNext()) {
                String taskFromFile = s.nextLine();
                String[] words = taskFromFile.split("\\|");
                //[0] is type, [1] is unmarked/marked, [2] is description
                //System.out.println(words[0] + ", " + words[1] + ", " + words[2]);

                //System.out.println(words.length);
                if (words.length < 3) {
                    System.out.println("Invalid task: " + taskFromFile);
                    continue; //move on to the next task
                }

                if (words[0].equals("T")) {
                    tasks.add(new Todo(words[2]));
                    if (words[1].equals("1")) {
                        tasks.get(tasks.size() - 1).markAsDone();
                    } else {
                        tasks.get(tasks.size() - 1).unmarkAsDone();
                    }
                } else if (words[0].equals("D")) {
                    tasks.add(new Deadline(words[2], words[3]));
                    if (words[1].equals("1")) {
                        tasks.get(tasks.size() - 1).markAsDone();
                    } else {
                        tasks.get(tasks.size() - 1).unmarkAsDone();
                    }
                } else {
                    tasks.add(new Event(words[2], words[3], words[4]));
                    if (words[1].equals("1")) {
                        tasks.get(tasks.size() - 1).markAsDone();
                    } else {
                        tasks.get(tasks.size() - 1).unmarkAsDone();
                    }
                }
            }
        } catch (FileNotFoundException e) {
           printer.printWithSeparator("File not found: " + filePath);
        }
    }
}
