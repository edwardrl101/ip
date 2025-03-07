package yoshi.ui;
import yoshi.task.TaskManager;
import yoshi.exception.YoshiException;
import java.io.IOException;

public class CommandProcessor {
    private Parser parser = new Parser(); // Parser to assist with extracting commands/integers out of inputs

    /**
     * Process the command entered by the user, and calls the taskManager methods to assist with
     * executing the command.
     * @param input the exact input entered by the user.
     * @param taskManager the taskManager of the chatbot.
     * @throws IOException IOException thrown in case there is an issue with updating the save file properly.
     */
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
        default:
            YoshiException.invalidCommandException();
        }
    }
}
