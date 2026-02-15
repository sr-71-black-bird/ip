package christopher.command;

import christopher.task.TaskList;

/**
 * Handles commands after interpretting user inputs
 */
public abstract class Command {
    public abstract void execute();
    public abstract String getResponse();
}
