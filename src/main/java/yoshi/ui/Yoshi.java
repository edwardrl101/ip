package yoshi.ui;

import yoshi.task.TaskManager;

import java.io.IOException;
import java.util.Scanner;

public class Yoshi {
    public static void main(String[] args) throws IOException {
        Printer printer = new Printer();
        TaskManager userTaskManager = new TaskManager();
        printer.printWelcomeMessage(userTaskManager.getTasks());

        Scanner in = new Scanner(System.in);
        int numTasks = 0;
        boolean USER_IS_CHATTING = true;

        while(USER_IS_CHATTING) {
            if (!in.hasNextLine()) {
                break;
            }
            String line = in.nextLine();
            String[] words = line.split(" ");
            switch(words[0].toLowerCase()) {
            case "bye":
                USER_IS_CHATTING = false;
                printer.printGoodbyeMessage();
                in.close();
                break;
            case "list":
                userTaskManager.listTask();
                break;
            case "mark":
            case "unmark":
                if(words.length < 2) {
                    printer.printWithSeparator("Invalid command: Please enter the task number for this command.");
                } else {
                    int taskNumber = Integer.parseInt(words[1]) - 1;
                    userTaskManager.toggleTask(taskNumber, words[0].toLowerCase());
                }
                break;
            case "event":
            case "deadline":
            case "todo":
                userTaskManager.addTask(line);
                break;
            case "delete":
                if(words.length < 2) {
                    printer.printWithSeparator("Invalid command: Please enter the index of the task to be deleted.");
                } else {
                    int taskNumber = Integer.parseInt(words[1]) - 1;
                    userTaskManager.deleteTask(taskNumber);
                }
                break;
            default:
                printer.printWithSeparator("Sorry, I am still too underdeveloped to understand that :(\n" +
                        "    Please give me a valid command.");
            }
        }
    }
}
