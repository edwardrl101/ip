package yoshi.ui;

import yoshi.exception.YoshiException;

public class Parser {

    private static final int BAD_INPUT = -1;

    /**
     *
     * @param input the input entered by the user, whereby the first word should be a command, e.g. delete/list.
     * @return a {@code String} type object, the command entered by the user.
     */
    public String extractCommand (String input) {
        String[] arguments = input.split(" ", 2);
        if(arguments[0] == null) {
            System.out.println("Please key in something so I can help you :)");
            return null;
        }
        return arguments[0];
    }

    /**
     * This function will be called when the user keys in specific commands, e.g. delete/mark.
     * @param input the input entered by the user, whereby the second part should be the task number for the command.
     * @return an {@code integer} type object which is the task number.
     */
    public int extractInteger (String input) {
        String[] arguments = input.split(" ", 2);
        if(arguments.length < 2) {
            YoshiException.invalidTaskNumberException("NO_NUM_PROVIDED");
            return BAD_INPUT;
        }
        try {
            return Integer.parseInt(arguments[1]);
        } catch (NumberFormatException e) {
            YoshiException.invalidTaskNumberException("INVALID_NUM_PROVIDED");
            return BAD_INPUT;
        }
    }
}
