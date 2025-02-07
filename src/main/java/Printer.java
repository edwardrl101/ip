public class Printer {
    private static final String INDENT = "    ";
    private static final String LINE = INDENT + "____________________________________________________________";

    public void printLine() {
        System.out.println(LINE);
    }

    public void printWithSeparator(String text) {
        System.out.println(LINE);
        System.out.println(INDENT + text);
        System.out.println(LINE);
    }

    public void printWelcomeMessage() {
        printLine();
        System.out.println("    Hello! I'm Yoshi!");
        System.out.println("    __   __  _______  _______  __   __  ___ \n" +
                "   |  | |  ||       ||       ||  | |  ||   |\n" +
                "   |  |_|  ||   _   ||  _____||  |_|  ||   |\n" +
                "   |       ||  | |  || |_____ |       ||   |\n" +
                "   |_     _||  |_|  ||_____  ||       ||   |\n" +
                "     |   |  |       | _____| ||   _   ||   |\n" +
                "     |___|  |_______||_______||__| |__||___|");
        System.out.println("    How may I assist you today? :D");
        printLine();
    }

    public void printGoodbyeMessage() {
        printWithSeparator("Bye! Hope to see you again soon! Keep working hard! :-)");
    }

    public void printWithIndentation(String text) {
        System.out.println(INDENT + text);
    }

    public void printAddTextMessage(int numTasks, Task[] tasks) {
        printLine();
        printWithIndentation("Sure! I've added this task:");
        printWithIndentation(" " + tasks[numTasks]);
        printWithIndentation("You now have " + (numTasks + 1) +
                " " + ((numTasks + 1) == 1 ? "task" : "tasks") + " in your list!");

        printLine();
    }
}
