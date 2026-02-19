package christopher.task;

/**
 * Throws exception when user generally input the wrong command for various reasons.
 * Displays the specific reason through message.
 */
public class WrongInstructionException extends Exception {
    public WrongInstructionException(String message) {
        super(message);
    }
}
