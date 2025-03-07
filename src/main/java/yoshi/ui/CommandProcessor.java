package yoshi.ui;
import yoshi.task.TaskManager;
import yoshi.task.Task;

import java.io.IOException;
import java.util.ArrayList;
import yoshi.ui.Printer;
import yoshi.ui.Parser;

public class CommandProcessor {
    private Printer printer = new Printer();
    private Parser parser = new Parser();
    private static final String INVALID_COMMAND_MESSAGE = "Sorry, I am still too underdeveloped to understand that :(\n" +
            "    Please give me a valid command.";

    public void processCommand(String input, TaskManager taskManager) throws IOException {
        String command = parser.extractCommand(input).toLowerCase();
        int taskNumber;
        switch(command) {
        case "list":
            taskManager.listTask();
            break;
        case "mark":
        case "unmark":
            taskNumber = parser.extractInteger(command)-1;
            taskManager.toggleTask(taskNumber, command);
            break;
        case "event":
        case "deadline":
        case "todo":
            taskManager.addTask(input);
            break;
        case "delete":
            taskNumber = parser.extractInteger(input) - 1;
            taskManager.deleteTask(taskNumber);
            break;
        case "print":
            taskNumber = parser.extractInteger(input) - 1;
            taskManager.printTask(taskNumber);
            break;
        default:
            printer.printWithSeparator(INVALID_COMMAND_MESSAGE);
        }
    }
}
