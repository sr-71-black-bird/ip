package christopher.task;

/**
 * This exception is for when components of a task input by the user are invalid
 */
public class WrongInstructionException extends Exception {
    public WrongInstructionException(String message) {
        super(message);
    }
}
