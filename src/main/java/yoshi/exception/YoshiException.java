package yoshi.exception;
import yoshi.ui.Printer;

public class YoshiException extends Exception {

    private static Printer printer = new Printer();
    private static final int NO_NUM_PROVIDED = -1;
    private static final int INVALID_NUM_PROVIDED = -2;

    private static final String INVALID_COMMAND_MESSAGE = "Sorry, I am still too underdeveloped to understand that :(\n" +
            "    Please give me a valid command.";
    private static final String NO_NUM_MESSAGE = "Please key in the corresponding task number.";
    private static final String INVALID_NUM_MESSAGE = "Invalid number received. Check that you provided a number and not a string.";
    private static final String OUT_OF_BOUNDS_MESSAGE = "Please key in a valid task number (1-100).";
    private static final String EMPTY_LIST_MESSAGE = "Oh no! Your task list is empty!";
    private static final String FULL_LIST_MESSAGE = "Unable to add task. Your task list is full!";
    private static final String NO_TASK_MESSAGE = "Hmm, I can't seem to find a task here...";

    public YoshiException(String message) {
        super(message);
    }

    public static void invalidCommandException() {
        printer.printWithSeparator(INVALID_COMMAND_MESSAGE);
    }

    public static void invalidTaskNumberException(String errorType) {
        switch (errorType) {
        case "NO_NUM_PROVIDED":
            printer.printWithSeparator(NO_NUM_MESSAGE);
            break;
        case "INVALID_NUM_PROVIDED":
            printer.printWithSeparator(INVALID_NUM_MESSAGE);
            break;
        case "OUT_OF_BOUNDS":
            printer.printWithSeparator(OUT_OF_BOUNDS_MESSAGE);
            break;
        default:
            break;
        }
    }

    public static void taskListException(String errorType) {
        switch (errorType) {
        case "EMPTY_LIST":
            printer.printWithSeparator(EMPTY_LIST_MESSAGE);
            break;
        case "FULL_LIST":
            printer.printWithSeparator(FULL_LIST_MESSAGE);
            break;
        case "NO_TASK_HERE":
            printer.printWithSeparator(NO_TASK_MESSAGE);
            break;
        default:
            break;
        }
    }


}
