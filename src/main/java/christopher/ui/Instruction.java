package christopher.ui;

/**
 * Checks the command type and prepares for error handling when command is unrecognized.
 */
public enum Instruction {
    TODO, DEADLINE, EVENT, LIST, BYE, MARK, UNMARK, DELETE, FIND, SORT, UNKNOWN;

    /**
     * Takes a command given by the user and match it to one of the enum types.
     *
     * @param input specified by user.
     * @return one of the enum listed above.
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


