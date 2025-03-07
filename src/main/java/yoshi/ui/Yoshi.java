package yoshi.ui;

import yoshi.storage.Storage;
import yoshi.task.TaskManager;

import java.io.IOException;
import java.util.Scanner;

public class Yoshi {
    private Printer printer;
    private Storage storage;
    private TaskManager taskManager;
    private Parser parser;
    private CommandProcessor commandProcessor;


    public Yoshi(String filePath) throws IOException {
        printer = new Printer();
        storage = new Storage(filePath);
        taskManager = new TaskManager(storage.loadTasks());
        parser = new Parser();
        commandProcessor = new CommandProcessor();
    }

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

    public static void main(String[] args) throws IOException {
        new Yoshi("yoshi.txt").run();
    }
}
