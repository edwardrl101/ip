package yoshi.task;

import yoshi.ui.Printer;

public class TaskManager {
    private static final int MAX_TASKS = 100;
    private Task[] tasks;
    private int numTasks;
    private Printer printer;

    public TaskManager() {
        this.tasks = new Task[MAX_TASKS];
        this.numTasks = 0;
        this.printer = new Printer();
    }

    public boolean isValidTaskAdded(String[] taskParts) {
        if(taskParts.length < 2) {
            printer.printWithSeparator("Invalid command: Please specify the task details.");
            return false;
        }
        if(numTasks >= MAX_TASKS) {
            printer.printWithSeparator("Unable to add task: your task list is full!.");
        }
        return true;
    }

    public Task initializeTask(String taskDetails, String taskType) {
        Task addedTask;
        switch (taskType) {
        case "deadline":
            addedTask = initializeDeadline(taskDetails);
            break;
        case "event":
            addedTask = initializeEvent(taskDetails);
            break;
        default:
            addedTask = initializeTodo(taskDetails);
            break;
        }
        return addedTask;
    }

    public Todo initializeTodo(String task) {
        return new Todo(task);
    }

    public Deadline initializeDeadline(String task) throws ArrayIndexOutOfBoundsException {
        String[] taskParts = task.split("/by", 2);
        if(taskParts.length < 2) {
            throw new ArrayIndexOutOfBoundsException("Invalid command: Please specify the deadline.\n"
            + "     Check that your usage of /by is correct.");
        }
        String activity = taskParts[0].trim();
        String by = taskParts[1].trim();
        return new Deadline(activity, by);
    }

    public Event initializeEvent(String task) throws ArrayIndexOutOfBoundsException {
        String[] taskParts = task.split("/from", 2);
        if(taskParts.length < 2) {
            throw new ArrayIndexOutOfBoundsException("Invalid command: Please specify the event start time.\n"
            + "     Check that your usage of /from is correct");
        }
        String activity = taskParts[0].trim();
        String[] dates = taskParts[1].split("/to", 2);
        if(dates.length < 2) {
            throw new ArrayIndexOutOfBoundsException("Invalid command: Please specify the event end time.\n"
            + "     Check that your usage of /to is correct");
        }
        String from = dates[0].trim();
        String to = dates[1].trim();
        return new Event(activity, from, to);
    }

    public void addTask(String task) {
        String[] taskParts = task.split(" ", 2);
        if(!isValidTaskAdded(taskParts)) {
            return;
        }
        String taskType = taskParts[0].trim();
        String taskDetails = taskParts[1].trim();
        try {
            tasks[numTasks] = initializeTask(taskDetails, taskType);
            printer.printAddTextMessage(numTasks, tasks);
            numTasks++;
        } catch(ArrayIndexOutOfBoundsException e) {
            printer.printWithSeparator(e.getMessage());
        }
    }

    public void listTask() {
        if(numTasks == 0) {
            printer.printWithSeparator("Oh no! You have no tasks in your list!");
            return;
        }
        printer.printLine();
        printer.printWithIndentation(" Here are the tasks in your list:");
        for (int i = 1; i <= numTasks; i++) {
            printer.printWithIndentation( " " + i +"." + tasks[i-1]);
        }
        printer.printLine();
    }

    public boolean isValidToggleTask(int taskNumber) {
        if(taskNumber < 0 || taskNumber >= MAX_TASKS) {
            printer.printWithSeparator("Invalid task number: Please enter a valid task number (1-100).");
            return false;
        }
        if(tasks[taskNumber] == null) {
            printer.printWithSeparator("Invalid command: You have no task here.");
            return false;
        }
        return true;
    }

    public void toggleTask(int taskNumber, String command) {
        if(!isValidToggleTask(taskNumber)) {
            return;
        }
        printer.printLine();
        if (command.equals("mark")) {
            tasks[taskNumber].markAsDone();
            printer.printWithIndentation(" Great job! I've marked this task as done:");
        } else {
            tasks[taskNumber].unmarkAsDone();;
            printer.printWithIndentation( " Sure! I've marked this task as not done yet:");
        }
        printer.printWithIndentation("  " + tasks[taskNumber]);
        printer.printLine();
    }
}
