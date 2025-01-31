import java.util.Scanner;

public class Yoshi {

    public static void addTask(String[] tasks, int numTasks, String line) {
        tasks[numTasks] = line;
        System.out.println("    ____________________________________________________________");
        System.out.println("     added: " + line);
        System.out.println("    ____________________________________________________________");
    }

    public static void listTasks(String[] tasks, int numTasks) {
        System.out.println("    ____________________________________________________________");
        for (int i = 1; i <= numTasks; i++) {
            System.out.println("    " + i +". " + tasks[i-1]);
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
        System.out.println("    How may I assist you today?");
        System.out.println("    ____________________________________________________________");

        Scanner in = new Scanner(System.in);
        String[] tasks = new String[100];
        int numTasks = 0;

        while(numTasks < 100) {
            String line = in.nextLine();
            if(line.equals("bye")) {
                break;
            } else if(line.equals("list")) {
                listTasks(tasks, numTasks);
            } else {
                addTask(tasks, numTasks, line);
                numTasks++;
            }
        }

        System.out.println("    ____________________________________________________________");
        System.out.println("    Bye! Hope to see you again soon! Keep working hard!");
        System.out.println("    ____________________________________________________________");
    }
}
