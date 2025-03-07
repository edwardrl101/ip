package yoshi.ui;

public class Parser {

    private static final int SINGLE_ARG_COMMAND = -1;
    private static final int NO_NUM_PROVIDED = -2;

    public String extractCommand (String input) {
        String[] arguments = input.split(" ", 2);
        if(arguments[0] == null) {
            System.out.println("Please key in something so I can help you :)");
        }
        return arguments[0];
    }

    public int extractInteger (String input) {
        String[] arguments = input.split(" ", 2);
        if(arguments.length < 2) {
            return SINGLE_ARG_COMMAND;
        }
        try {
            return Integer.parseInt(arguments[1]);
        } catch (NumberFormatException e) {
            return NO_NUM_PROVIDED;
        }
    }
}
