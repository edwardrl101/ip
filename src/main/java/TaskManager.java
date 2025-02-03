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

    public Todo initializeTodo(String line) {
        return new Todo(line);
    }

    public Deadline initializeDeadline(String line) {
        String[] taskParts = line.split("/by", 2);
        String activity = taskParts[0].trim();
        String by = taskParts[1].trim();
        return new Deadline(activity, by);
    }

    public Event initializeEvent(String line) {
        String[] taskParts = line.split("/from", 2);
        String activity = taskParts[0].trim();
        String[] dates = taskParts[1].split("/to", 2);
        String from = dates[0].trim();
        String to = dates[1].trim();
        return new Event(activity, from, to);
    }

    public boolean isValidAddTask(String[] taskParts) {
        if(!taskParts[0].equals("todo") && !taskParts[0].equals("deadline") && !taskParts[0].equals("event")) {
            printer.printWithSeparator("Invalid command: Please enter a valid task type.");
            return false;
        }
        if(taskParts.length < 2) {
            printer.printWithSeparator("Invalid command: Please specify the task details.");
            return false;
        }
        if(numTasks >= MAX_TASKS) {
            printer.printWithSeparator("Unable to add task: your task list is full!.");
        }
        return true;
    }


    public void addTask(String line) {
        String[] taskParts = line.split(" ", 2);
        if(!isValidAddTask(taskParts)) {
            return;
        }
        String taskType = taskParts[0].trim();
        String taskDetails = taskParts[1].trim();
        switch(taskType) {
        case "deadline":
            tasks[numTasks] = initializeDeadline(taskDetails);
            break;
        case "event":
            tasks[numTasks] = initializeEvent(taskDetails);
            break;
        default:
            tasks[numTasks] = initializeTodo(taskDetails);
            break;
        }
        printer.printAddTextMessage(numTasks, tasks);
        numTasks++;
    }

    public void listTask() {
        printer.printLine();
        printer.printWithIndentation(" Here are the tasks in your list:");
        for (int i = 1; i <= numTasks; i++) {
            printer.printWithIndentation( " " + i +"." + tasks[i-1]);
        }
        printer.printLine();
    }

    public boolean isValidToggleTask(int taskNumber) {
        if(taskNumber < 0 || taskNumber >= MAX_TASKS) {
            printer.printWithSeparator("Invalid task number: Please enter a valid task number.");
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
