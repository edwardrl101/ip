package yoshi.ui;
import yoshi.task.TaskManager;
import yoshi.exception.YoshiException;
import java.io.IOException;
import java.util.Objects;

public class CommandProcessor {
    private Parser parser = new Parser(); // Parser to assist with extracting commands/integers out of inputs
    private static final int BAD_INPUT = -1;
    private static final String NO_KEYWORD = null;

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
            taskNumber = parser.extractInteger(input);
            if(taskNumber == BAD_INPUT) {
                break;
            }
            taskManager.toggleTask(taskNumber - 1, command);
            break;
        case "event":
        case "deadline":
        case "todo":
            taskManager.addTask(input);
            break;
        case "delete":
            taskNumber = parser.extractInteger(input);
            if(taskNumber == BAD_INPUT) {
                break;
            }
            taskManager.deleteTask(taskNumber - 1);
            break;
        case "print":
            taskNumber = parser.extractInteger(input);
            if(taskNumber == BAD_INPUT) {
                break;
            }
            taskManager.printTask(taskNumber - 1);
            break;
        case "find":
            String keyword = parser.extractKeyword(input);
            if(Objects.equals(keyword, NO_KEYWORD)) {
                break;
            }
            taskManager.findTask(keyword);
            break;
        default:
            YoshiException.invalidCommandException();
            break;
        }
    }
}