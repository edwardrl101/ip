package yoshi.task;

import java.io.IOException;
import java.util.ArrayList;
import yoshi.ui.Printer;
import yoshi.exception.YoshiException;
import static yoshi.storage.Storage.updateFile;

public class TaskManager {
    private static final int MAX_TASKS = 100;
    private static final int OUT_OF_BOUNDS = -1;
    private static final String ERROR_DEADLINE_MESSAGE = "Invalid command: Please specify the deadline.\n"
            + "     Check that your usage of /by is correct.";
    private static final String MISSING_EVENT_START_MESSAGE = "Invalid command: Please specify the event start time.\n"
            + "     Check that your usage of /from is correct";
    private static final String MISSING_EVENT_END_MESSAGE = "Invalid command: Please specify the event end time.\n"
            + "     Check that your usage of /to is correct";




    private ArrayList<Task> tasks;
    private int numTasks;
    private Printer printer;


    public TaskManager() {
        this.tasks = new ArrayList<>();
        this.numTasks = 0;
        this.printer = new Printer();
    }

    public TaskManager(ArrayList<Task> tasks) {
        this.tasks = tasks;
        this.numTasks = tasks.size();
        this.printer = new Printer();
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }

    public boolean isValidTaskAdded(String[] taskParts) {
        if(taskParts.length < 2) {
            printer.printWithSeparator("Invalid command: Please specify the task details.");
            return false;
        }
        if(numTasks >= MAX_TASKS) {
            YoshiException.taskListException("FULL_LIST");
            return false;
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
            throw new ArrayIndexOutOfBoundsException(ERROR_DEADLINE_MESSAGE);
        }
        String activity = taskParts[0].trim();
        String by = taskParts[1].trim();
        return new Deadline(activity, by);
    }

    public Event initializeEvent(String task) throws ArrayIndexOutOfBoundsException {
        String[] taskParts = task.split("/from", 2);
        if(taskParts.length < 2) {
            throw new ArrayIndexOutOfBoundsException(MISSING_EVENT_START_MESSAGE);
        }
        String activity = taskParts[0].trim();
        String[] dates = taskParts[1].split("/to", 2);
        if(dates.length < 2) {
            throw new ArrayIndexOutOfBoundsException(MISSING_EVENT_END_MESSAGE);
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
        String taskType = taskParts[0].trim().toLowerCase();
        String taskDetails = taskParts[1].trim();
        try {
            tasks.add(initializeTask(taskDetails, taskType));
            printer.printAddTextMessage(numTasks, tasks);
            updateFile(tasks);
            numTasks++;
        } catch(ArrayIndexOutOfBoundsException e) {
            printer.printWithSeparator(e.getMessage());
        } catch (IOException e) {
            printer.printWithSeparator("Error: file not found" );
        }
    }

    public void listTask() {
        if(numTasks == 0) {
            YoshiException.taskListException("EMPTY_LIST");
            return;
        }
        printer.printLine();
        printer.printWithIndentation(" Here are the tasks in your list:");
        for (int i = 1; i <= numTasks; i++) {
            printer.printWithIndentation( " " + i +"." + tasks.get(i-1));
        }
        printer.printLine();
    }

    public void printTask(int taskNumber) {
        if(taskNumber < 0 || taskNumber > numTasks) {
           YoshiException.invalidTaskNumberException("OUT_OF_BOUNDS");
            return;
        }
        printer.printWithSeparator(tasks.get(taskNumber).toString());
    }

    public boolean isValidToggleTask(int taskNumber) {
        if(taskNumber < 0 || taskNumber >= MAX_TASKS) {
            YoshiException.invalidTaskNumberException("OUT_OF_BOUNDS");
            return false;
        }
        if(tasks.get(taskNumber) == null) {
            YoshiException.taskListException("NO_TASK_HERE");
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
            (tasks.get(taskNumber)).markAsDone();
            printer.printWithIndentation(" Great job! I've marked this task as done:");
        } else {
            (tasks.get(taskNumber)).unmarkAsDone();;
            printer.printWithIndentation( " Sure! I've marked this task as not done yet:");
        }
        printer.printWithIndentation("  " + tasks.get(taskNumber));
        printer.printLine();
    }

    public void deleteTask(int taskNumber) throws IOException {
        if(taskNumber >= numTasks || taskNumber < 0) {
            YoshiException.taskListException("NO_TASK_HERE");
        } else {
            printer.printDeleteTaskMessage(numTasks, tasks, taskNumber);
            tasks.remove(taskNumber);
            numTasks--;
            updateFile(tasks);
        }
    }


    public int checkTaskForWord(Task task, String keyword, int count, int taskNum){
        if (task.getDescription().contains(keyword)) {
            if (count == 0) {
                printer.printWithIndentation("Here are the task(s) that matches your search \"" + keyword + "\" :");
            }
            printer.printWithIndentation((count+1) + ". " + tasks.get(taskNum-1).toString());
            return count + 1;
        }
        return count;
    }

    public void findTask(String keyword) {
        int currTaskNum = 1;
        int count = 0;
        printer.printLine();
        for (Task task : tasks) {
            count = checkTaskForWord(task, keyword, count, currTaskNum);
            currTaskNum++;
        }
        if(count == 0){
            printer.printWithIndentation("Oops, there are no tasks that match your search......");
        }
        else {
            printer.printWithIndentation("There are " + count + " tasks that matches your search");
        }
        printer.printLine();
    }
}
