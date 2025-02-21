package yoshi.ui;

import yoshi.task.Task;

import java.io.FileNotFoundException;
import java.util.ArrayList;

import static yoshi.ui.Storage.copyContents;

public class Printer {
    private static final String INDENT = "    ";
    private static final String LINE = INDENT + "____________________________________________________________";

    public void printTasks(ArrayList<Task> tasks) {
        printLine();
        for (Task task : tasks) {
            System.out.println(INDENT + task);
        }
        printLine();
    }
    public void printLine() {
        System.out.println(LINE);
    }

    public void printWithSeparator(String text) {
        System.out.println(LINE);
        System.out.println(INDENT + text);
        System.out.println(LINE);
    }

    public void printWelcomeMessage(ArrayList<Task> tasks) {
        printLine();
        System.out.println("    Hello! I'm Yoshi!");
        System.out.println("    __   __  _______  _______  __   __  ___ \n" +
                "   |  | |  ||       ||       ||  | |  ||   |\n" +
                "   |  |_|  ||   _   ||  _____||  |_|  ||   |\n" +
                "   |       ||  | |  || |_____ |       ||   |\n" +
                "   |_     _||  |_|  ||_____  ||       ||   |\n" +
                "     |   |  |       | _____| ||   _   ||   |\n" +
                "     |___|  |_______||_______||__| |__||___|");
        System.out.println("Your current list of tasks:");
        try {
            copyContents(tasks);
            printTasks(tasks);
        } catch (FileNotFoundException e) {
            System.out.println("File not found, please check your filepath");
        }
        System.out.println("\n    How may I assist you today? :D");
        printLine();
    }

    public void printGoodbyeMessage() {
        printWithSeparator("Bye! Hope to see you again soon! Keep working hard! :-)");
    }

    public void printWithIndentation(String text) {
        System.out.println(INDENT + text);
    }

    public void printAddTextMessage(int numTasks, ArrayList<Task> tasks) {
        printLine();
        printWithIndentation("Sure! I've added this task:");
        printWithIndentation(" " + tasks.get(numTasks));
        printWithIndentation("You now have " + (numTasks + 1) +
                " " + ((numTasks + 1) == 1 ? "task" : "tasks") + " in your list!");

        printLine();
    }

    public void printDeleteTaskMessage(int numTasks, ArrayList<Task> tasks, int taskNumber) {
        printLine();
        printWithIndentation("Sure! I've deleted this task:");
        printWithIndentation(" " + tasks.get(taskNumber));
        printWithIndentation("You now have " + (numTasks - 1) +
                " " + ((numTasks - 1) == 1 ? "task" : "tasks") + " in your list!");

        printLine();
        // commit
    }
}
