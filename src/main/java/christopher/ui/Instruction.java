package christopher.ui;

/**
 * This is an enum for all the possible commands chatbot christopher.ui.Christopher could take
 */
public enum Instruction {
    TODO, DEADLINE, EVENT, LIST, BYE, MARK, UNMARK, DELETE, FIND, UNKNOWN;

    /**
     * Takes a command given by the user and match it to one of the enum types
     *
     * @param input specified by user
     * @return one of the enum listed above
     */
    public static Instruction from(String input) {
        String commandWord = input.trim().split(" ")[0].toUpperCase();

        try {
            return Instruction.valueOf(commandWord);
        } catch (IllegalArgumentException e) {
            return UNKNOWN;
        }
    }
}


