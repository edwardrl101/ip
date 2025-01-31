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
        System.out.println("    How may I assist you today?");
        System.out.println("    ____________________________________________________________");

        Scanner in = new Scanner(System.in);

        while(true) {
            String line = in.nextLine();
            if(line.equals("bye")) {
                break;
            }
            System.out.println("    ____________________________________________________________");
            System.out.println("    " + line);
            System.out.println("    ____________________________________________________________");
        }

        System.out.println("    ____________________________________________________________");
        System.out.println("    Bye! Hope to see you again soon! Keep working hard!");
        System.out.println("    ____________________________________________________________");
    }
}
