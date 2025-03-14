package yoshi.task;

import java.io.IOException;
import java.util.ArrayList;
import yoshi.ui.Printer;
import yoshi.exception.YoshiException;
import static yoshi.storage.Storage.updateFile;

public class TaskManager {
    /**
     * Initiialized constants to avoid magic literals
     */
    private static final int MAX_TASKS = 100;
    private static final String ERROR_DEADLINE_MESSAGE = "Invalid command: Please specify the deadline.\n"
            + "     Check that your usage of /by is correct.";
    private static final String MISSING_EVENT_START_MESSAGE = "Invalid command: Please specify the event start time.\n"
            + "     Check that your usage of /from is correct";
    private static final String MISSING_EVENT_END_MESSAGE = "Invalid command: Please specify the event end time.\n"
            + "     Check that your usage of /to is correct";




    private ArrayList<Task> tasks;
    private int numTasks;
    private Printer printer;  // Printer class to be able to help with printing

    /**
     * Constructor for the class TaskManager.
     */
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

    /**
     * Checks if it is okay/valid to add the corresponding task.
     * It checks if task parts are complete and whether or not task list is full.
     * @param taskParts Checks if both parts of tasks are present (task type and task details)
     * @return true or false corresponding to whether the task can be added
     */
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

    /**
     * This function initializes and returns a {@code  Task} type object of either event/deadline/task.
     * @param taskDetails the details of the task to be added (e.g. study for math test).
     * @param taskType the type of task to be added, i.e. one of event/task/deadline.
     * @return a {@code Task} type object which will be added to the lists of tasks, i.e. ArrayList<Task> tasks.
     */
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

    /**
     * This function returns a {@code Deadline} type object and checks for missing deadline times.
     * @param task the corresponding deadline to be initialize and added to the task list
     * @return a {@code Deadline} type object corresponding of the deadline to be added
     * @throws ArrayIndexOutOfBoundsException an exception is thrown when the system expects a deadline time
     * but finds none
     */
    public Deadline initializeDeadline(String task) throws ArrayIndexOutOfBoundsException {
        String[] taskParts = task.split("/by", 2);
        if(taskParts.length < 2) {
            throw new ArrayIndexOutOfBoundsException(ERROR_DEADLINE_MESSAGE);
        }
        String activity = taskParts[0].trim();
        String by = taskParts[1].trim();
        return new Deadline(activity, by);
    }

    /**
     * This function returns a {@code Event} type object and checks for erroneous inputs (missing start/end times)
     * @param task the corresponding event to be initiailized and added to the task list
     * @return a {@code Event} type object corresponding to the event to be added.
     * @throws ArrayIndexOutOfBoundsException an exception is thrown when the system tries to look for start/end times
     * but finds none
     */
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

    /**
     * Adds the corresponding task to the task list, and catches an
     * exception from the initializer helper functions.
     * @param task the corresponding task to be added
     */
    public void addTask(String task) {
        String[] taskParts = task.split(" ", 2);
        if(!isValidTaskAdded(taskParts)) {
            return;
        }
        String taskType = taskParts[0].trim().toLowerCase();
        String taskDetails = taskParts[1].trim();
        try {
            tasks.add(initializeTask(taskDetails, taskType));
            printer.printAddTaskMessage(numTasks, tasks);
            updateFile(tasks);
            numTasks++;
        } catch(ArrayIndexOutOfBoundsException e) {
            printer.printWithSeparator(e.getMessage());
        } catch (IOException e) {
            printer.printWithSeparator("Error: file not found" );
        }
    }

    /**
     * Lists down all the tasks currently in the task list.
     */
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

    /**
     * Prints the task with the corresponding task number
     * @param taskNumber the task number of the task to be printed
     */
    public void printTask(int taskNumber) {
        if(taskNumber < 0 || taskNumber >= MAX_TASKS) {
            YoshiException.invalidTaskNumberException("OUT_OF_BOUNDS");
            return;
        }
        if(taskNumber >= numTasks) {
            YoshiException.taskListException("NO_TASK_HERE");
            return;
        }
        printer.printWithSeparator(" " + tasks.get(taskNumber).toString());
    }

    /**
     * Checks if the task number of the task to be toggled is valid (within bounds, task exists)
     * @param taskNumber the number of the task to be toggled
     * @return true or false depending on whether the number is valid
     */
    public boolean isValidToggleTask(int taskNumber) {
        if(taskNumber < 0 || taskNumber >= MAX_TASKS) {
            YoshiException.invalidTaskNumberException("OUT_OF_BOUNDS");
            return false;
        }
        if(taskNumber >= numTasks) {
            YoshiException.taskListException("NO_TASK_HERE");
            return false;
        }
        return true;
    }

    /**
     * Marks or unmarks the "done status" of the desired task with the corresponding task number
     * @param taskNumber the task number of the task to be marked or unmarked as done
     * @param command this corresponds to "mark" or "unmark", which is to be entered by the user
     */
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

    /**
     * Deletes the desired task with the corresponding task number, and also checks if the task number is valid.
     * @param taskNumber the task number of the task to be deleted.
     * @throws IOException an IOException is thrown in case there are problems with updating the save file.
     */
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

    /**
     * This method takes in a task, and checks if it has a particular keyword in it. If count == 0,
     * then the method prints the message "Here are the matching tasks containing {keyword} in your list"
     * since it is the first one.
     *
     * @param task the task to be checked
     * @param keyword the desired keyword to be found
     * @param count how many tasks currently have the desired keyword in it
     * @param taskNum the corresponding task number
     * @return the updated count (+1 if the keyword is found in the task, not updated otherwise)
     */

    public int checkTaskForWord(Task task, String keyword, int count, int taskNum){
        if (task.getDescription().contains(keyword)) {
            if (count == 0) {
                printer.printWithIndentation("Here are the matching tasks containing \"" + keyword + "\" in your list :");
            }
            printer.printWithIndentation((count+1) + ". " + tasks.get(taskNum-1).toString());
            return count + 1;
        }
        return count;
    }

    /**
     * This method takes in a keyword and lists out all the tasks that have this particular keyword
     * @param keyword The desired keyword
     */
    public void findTask(String keyword) {
        int currTaskNum = 1;
        int count = 0;
        printer.printLine();
        for (Task task : tasks) {
            count = checkTaskForWord(task, keyword, count, currTaskNum);
            currTaskNum++;
        }
        if(count == 0){
            printer.printWithIndentation("Oops, there are no tasks that contain" + " \"" + keyword + "\" in your search......");
        }
        else {
            printer.printWithIndentation("There " + ((count == 1) ? ("is " + count + " task") : ("are " + count + " tasks"))   + " that matches your search");
            // Use the plural form if there are more than 1 tasks matching, else use the singular form.
        }
        printer.printLine();
    }
}