import java.util.Scanner;

public class Yoshi {

    public static void addTask(Task[] tasks, int numTasks, String line) {
        tasks[numTasks] = new Task(line);
        System.out.println("    ____________________________________________________________");
        System.out.println("     added: " + line);
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
        if (command.equals("mark")) {
            tasks[taskNumber].markAsDone();
            System.out.println("     Great job! I've marked this task as done:");
        } else {
            tasks[taskNumber].unmarkAsDone();;
            System.out.println("     Sure! I've marked this task as not done yet:");
        }
        System.out.println("      [" + tasks[taskNumber].getStatusIcon() + "] " + tasks[taskNumber].getDescription());
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
        Task[] tasks = new Task[100];
        int numTasks = 0;

        while(numTasks < 100) {
            String line = in.nextLine();
            String[] words = line.split(" ");
            switch(words[0]) {
            case "bye":
                System.out.println("    ____________________________________________________________");
                System.out.println("    Bye! Hope to see you again soon! Keep working hard! :-)");
                System.out.println("    ____________________________________________________________");
                return;
            case "list":
                listTask(tasks, numTasks);
                break;
            case "mark":
            case "unmark":
                int taskNumber = Integer.parseInt(words[1]) - 1;
                toggleTask(tasks, taskNumber, words[0]);
                break;
            default:
                addTask(tasks, numTasks, line);
                numTasks++;
                break;
            }
        }
    }
}
