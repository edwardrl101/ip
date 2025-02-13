package yoshi.ui;

import yoshi.task.TaskManager;

import java.util.Scanner;

public class Yoshi {
    public static void main(String[] args) {
        Printer printer = new Printer();
        printer.printWelcomeMessage();

        Scanner in = new Scanner(System.in);
        TaskManager userTaskManager = new TaskManager();
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
            default:
                printer.printWithSeparator("Sorry, I am still too underdeveloped to understand that :(\n" +
                        "    Please give me a valid command.");
            }
        }
    }
}
