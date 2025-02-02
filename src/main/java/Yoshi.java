import java.util.Scanner;

public class Yoshi {
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
        TaskManager userTaskManager = new TaskManager();
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
                userTaskManager.listTask();
                break;
            case "mark":
            case "unmark":
                if(words.length < 2) {
                    System.out.println("    ____________________________________________________________");
                    System.out.println("    Please enter the task number for the command!");
                    System.out.println("    ____________________________________________________________");
                } else {
                    int taskNumber = Integer.parseInt(words[1]) - 1;
                    userTaskManager.toggleTask(taskNumber, words[0]);
                }
                break;
            default:
                userTaskManager.addTask(line);
                break;
            }
        }
    }
}
