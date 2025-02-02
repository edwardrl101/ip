import java.util.Scanner;

public class Yoshi {

    public static final int MAX_TASKS = 100;

    public static void addTask(Task[] tasks, int numTasks, String line) {
        System.out.println("    ____________________________________________________________");
        if(numTasks >= MAX_TASKS) {
            System.out.println("    Oops! Unable to add task: your task list is full!");
        } else {
            tasks[numTasks] = new Task(line);
            System.out.println("     added: " + line);
        }
        System.out.println("    ____________________________________________________________");
    }

    public static void listTask(Task[] tasks, int numTasks) {
        System.out.println("    ____________________________________________________________");
        System.out.println("     Here are the tasks in your list:");
        for (int i = 1; i <= numTasks; i++) {
            System.out.println("     " + i +".[" + tasks[i-1].getStatusIcon() +"] " + tasks[i-1].getDescription());
        }
        System.out.println("    ____________________________________________________________");
    }

    public static void toggleTask(Task[] tasks, int taskNumber, String command) {
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

    public static void main(String[] args) {
        System.out.println("    ____________________________________________________________");
        System.out.println("    Hello! I'm Yoshi!");
        System.out.println("    __   __  _______  _______  __   __  ___ \n" +
                "   |  | |  ||       ||       ||  | |  ||   |\n" +
                "   |  |_|  ||   _   ||  _____||  |_|  ||   |\n" +
                "   |       ||  | |  || |_____ |       ||   |\n" +
                "   |_     _||  |_|  ||_____  ||       ||   |\n" +
                "     |   |  |       | _____| ||   _   ||   |\n" +
                "     |___|  |_______||_______||__| |__||___|");
        System.out.println("    How may I assist you today? :D");
        System.out.println("    ____________________________________________________________");

        Scanner in = new Scanner(System.in);
        Task[] tasks = new Task[MAX_TASKS];
        int numTasks = 0;
        boolean USER_IS_CHATTING = true;

        while(USER_IS_CHATTING) {
            String line = in.nextLine();
            String[] words = line.split(" ");
            switch(words[0]) {
            case "bye":
                USER_IS_CHATTING = false;
                System.out.println("    ____________________________________________________________");
                System.out.println("    Bye! Hope to see you again soon! Keep working hard! :-)");
                System.out.println("    ____________________________________________________________");
                break;
            case "list":
                listTask(tasks, numTasks);
                break;
            case "mark":
            case "unmark":
                if(words.length < 2) {
                    System.out.println("    ____________________________________________________________");
                    System.out.println("    Please enter the task number for the command!");
                    System.out.println("    ____________________________________________________________");
                } else {
                    int taskNumber = Integer.parseInt(words[1]) - 1;
                    toggleTask(tasks, taskNumber, words[0]);
                }
                break;
            default:
                addTask(tasks, numTasks, line);
                numTasks++;
                break;
            }
        }
    }
}
