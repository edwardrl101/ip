package yoshi.ui;

import yoshi.task.Task;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import static yoshi.storage.Storage.loadTasks;

public class Printer {
    private static final String INDENT = "    ";
    private static final String LINE = INDENT + "____________________________________________________________";
    private static final String WELCOME_MESSAGE = INDENT + "Hello! I'm Yoshi!\n" +
            "    __   __  _______  _______  __   __  ___ \n" +
            "   |  | |  ||       ||       ||  | |  ||   |\n" +
            "   |  |_|  ||   _   ||  _____||  |_|  ||   |\n" +
            "   |       ||  | |  || |_____ |       ||   |\n" +
            "   |_     _||  |_|  ||_____  ||       ||   |\n" +
            "     |   |  |       | _____| ||   _   ||   |\n" +
            "     |___|  |_______||_______||__| |__||___|";
    private static final String GOODBYE_MESSAGE = "Bye! Hope to see you again soon! Keep working hard! :-)";
    private static final String SUCCESSFUL_ADD_TASK_MESSAGE = "Sure! I've added this task:";
    private static final String SUCCESSFUL_DELETE_TASK_MESSAGE = "Sure! I've deleted this task:";
    private static final String FILE_NOT_FOUND_ERROR_MESSAGE = "File not found, please check your filepath";



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

    public void printWelcomeMessage() {
        printLine();
        System.out.println(WELCOME_MESSAGE);
        System.out.println(INDENT + "Your current list of tasks:");
        try {
            ArrayList<Task> tasks = loadTasks();
            printTasks(tasks);
        } catch (FileNotFoundException e) {
            printWithIndentation(FILE_NOT_FOUND_ERROR_MESSAGE);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println("\n    How may I assist you today? :D");
        printLine();
    }

    public void printGoodbyeMessage() {
        printWithSeparator(GOODBYE_MESSAGE);
    }

    public void printWithIndentation(String text) {
        System.out.println(INDENT + text);
    }

    public void printAddTextMessage(int numTasks, ArrayList<Task> tasks) {
        printLine();
        printWithIndentation(SUCCESSFUL_ADD_TASK_MESSAGE);
        printWithIndentation(" " + tasks.get(numTasks));
        printWithIndentation("You now have " + (numTasks + 1) +
                " " + ((numTasks + 1) == 1 ? "task" : "tasks") + " in your list!");

        printLine();
    }

    public void printDeleteTaskMessage(int numTasks, ArrayList<Task> tasks, int taskNumber) {
        printLine();
        printWithIndentation(SUCCESSFUL_DELETE_TASK_MESSAGE);
        printWithIndentation(" " + tasks.get(taskNumber));
        printWithIndentation("You now have " + (numTasks - 1) +
                " " + ((numTasks - 1) == 1 ? "task" : "tasks") + " in your list!");

        printLine();
    }
}
