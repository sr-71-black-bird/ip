/**
 * This is an enum for all the possible commands chatbot Christopher could take
 */
public enum Command {
    TODO, DEADLINE, EVENT, LIST, BYE, MARK, UNMARK, DELETE, UNKNOWN;

    public static Command from(String input) {
        String commandWord = input.trim().split(" ")[0].toUpperCase();

        try {
            return Command.valueOf(commandWord);
        } catch (IllegalArgumentException e) {
            return UNKNOWN;
        }
    }
}


