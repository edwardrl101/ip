package yoshi.ui;

import yoshi.exception.YoshiException;

public class Parser {

    private static final int BAD_INPUT = -1;
    private static final String NO_KEYWORD = "-1";

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

    public String extractKeyword(String input) throws ArrayIndexOutOfBoundsException{
        String[] arguments = input.split(" ", 2);
        if(arguments.length < 2) {
            YoshiException.invalidTaskNumberException("NO_NUM_PROVIDED");
            return NO_KEYWORD;
        }
        return arguments[1];
    }
}
