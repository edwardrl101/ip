public class TaskManager {
    private static final int MAX_TASKS = 100;
    private Task[] tasks;
    private int numTasks;

    public TaskManager() {
        this.tasks = new Task[MAX_TASKS];
        this.numTasks = 0;
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

    public void addTask(String line) {
        System.out.println("    ____________________________________________________________");
        if(numTasks >= MAX_TASKS) {
            System.out.println("    Oops! Unable to add task: your task list is full!");
        } else {
            String[] taskParts = line.split(" ", 2);
            String taskType = taskParts[0].trim();
            String taskDetails = taskParts[1].trim();
            switch(taskType) {
            case "todo":
                tasks[numTasks] = initializeTodo(taskDetails);
                break;
            case "deadline":
                tasks[numTasks] = initializeDeadline(taskDetails);
                break;
            case "event":
                tasks[numTasks] = initializeEvent(taskDetails);
                break;
            default:
                tasks[numTasks] = new Task(line);
                break;
            }
            System.out.println("    Sure! I've added this task:");
            System.out.println("     " + tasks[numTasks]);
            numTasks++;
            System.out.println("    You now have " + numTasks + " tasks in your list!");
        }
        System.out.println("    ____________________________________________________________");
    }

    public void listTask() {
        System.out.println("    ____________________________________________________________");
        System.out.println("     Here are the tasks in your list:");
        for (int i = 1; i <= numTasks; i++) {
            System.out.println("     " + i +"." + tasks[i-1]);
        }
        System.out.println("    ____________________________________________________________");
    }

    public void toggleTask(int taskNumber, String command) {
        System.out.println("    ____________________________________________________________");
        if(taskNumber >= MAX_TASKS || taskNumber < 0) {
            System.out.println("    Invalid task number! Please enter another task number.");
        } else if(tasks[taskNumber] == null) {
            System.out.println("    Invalid command! You have no task here.");
        } else if (command.equals("mark")) {
            tasks[taskNumber].markAsDone();
            System.out.println("     Great job! I've marked this task as done:");
            System.out.println("      " + tasks[taskNumber]);
        } else {
            tasks[taskNumber].unmarkAsDone();;
            System.out.println("     Sure! I've marked this task as not done yet:");
            System.out.println("      " + tasks[taskNumber]);
        }
        System.out.println("    ____________________________________________________________");
    }
}
