package christopher.command;

/**
 * Handles commands after interpreting user inputs.
 */
public abstract class Command {
    public abstract void execute();
    public abstract String getResponse();
}
