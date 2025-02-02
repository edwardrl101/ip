public class TaskManager {
    private static final int MAX_TASKS = 100;
    private Task[] tasks;
    private int numTasks;

    public TaskManager() {
        this.tasks = new Task[MAX_TASKS];
        this.numTasks = 0;
    }

    public void addTask(String line) {
        System.out.println("    ____________________________________________________________");
        if(numTasks >= MAX_TASKS) {
            System.out.println("    Oops! Unable to add task: your task list is full!");
        } else {
            tasks[numTasks] = new Task(line);
            System.out.println("     added: " + line);
            numTasks++;
        }
        System.out.println("    ____________________________________________________________");
    }

    public void listTask() {
        System.out.println("    ____________________________________________________________");
        System.out.println("     Here are the tasks in your list:");
        for (int i = 1; i <= numTasks; i++) {
            System.out.println("     " + i +".[" + tasks[i-1].getStatusIcon() +"] " + tasks[i-1].getDescription());
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
            System.out.println("      [" + tasks[taskNumber].getStatusIcon() + "] " + tasks[taskNumber].getDescription());
        } else {
            tasks[taskNumber].unmarkAsDone();;
            System.out.println("     Sure! I've marked this task as not done yet:");
            System.out.println("      [" + tasks[taskNumber].getStatusIcon() + "] " + tasks[taskNumber].getDescription());
        }
        System.out.println("    ____________________________________________________________");
    }
}
