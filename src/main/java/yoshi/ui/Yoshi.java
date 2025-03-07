package yoshi.ui;

import yoshi.storage.Storage;
import yoshi.task.TaskManager;

import java.io.IOException;
import java.util.Scanner;

public class Yoshi {
    private Printer printer; // Printer class to help with printing
    private Storage storage; // Storage class to help with loading/saving tasks
    private TaskManager taskManager; // TaskManager class to assist with operations on the task list
    private Parser parser; // Parser to help with input parsing
    private CommandProcessor commandProcessor; // CommandProcessor class which will process the commands

    /**
     * Initializes helper classes as well as the file path of the save file.
     * @param filePath
     * @throws IOException
     */
    public Yoshi(String filePath) throws IOException {
        printer = new Printer();
        storage = new Storage(filePath);
        taskManager = new TaskManager(storage.loadTasks());
        parser = new Parser();
        commandProcessor = new CommandProcessor();
    }

    /**
     * The program is run here, accepting user input and processing them until the user says "bye".
     * @throws IOException Exception to be thrown in case there are issues with updating save file.
     */
    public void run() throws IOException {
        printer.printWelcomeMessage();
        Scanner in = new Scanner(System.in);
        boolean USER_IS_CHATTING = true;
        while(USER_IS_CHATTING) {
            if (!in.hasNextLine()) {
                break;
            }
            String input = in.nextLine();
            String command = (parser.extractCommand(input)).toLowerCase();
            if(command.equals("bye")) {
                USER_IS_CHATTING = false;
                printer.printGoodbyeMessage();
                in.close();
                break;
            } else {
                commandProcessor.processCommand(input, taskManager);
            }
        }
    }

    /**
     * Yoshi will start here when the program is run.
     * @param args the input by the user.
     * @throws IOException Exception to be thrown in case there are issues with updating save file.
     */
    public static void main(String[] args) throws IOException {
        new Yoshi("yoshi.txt").run();
    }
}
