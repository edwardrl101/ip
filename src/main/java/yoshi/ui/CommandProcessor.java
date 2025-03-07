package yoshi.ui;
import yoshi.task.TaskManager;
import yoshi.exception.YoshiException;
import java.io.IOException;

public class CommandProcessor {
    private Parser parser = new Parser();


    public void processCommand(String input, TaskManager taskManager) throws IOException {
        String command = parser.extractCommand(input).toLowerCase();
        int taskNumber;
        switch(command) {
        case "list":
            taskManager.listTask();
            break;
        case "mark":
        case "unmark":
            taskNumber = parser.extractInteger(input)-1;
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
        case "find":
            String keyword = parser.extractKeyword(input);
            taskManager.findTask(keyword);
            break;
        default:
            YoshiException.invalidCommandException();
            break;
        }
    }
}
