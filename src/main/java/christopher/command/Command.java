package christopher.command;

import christopher.task.TaskList;

/**
 * The is the abstract class Command and it has the
 */
public abstract class Command {
    public abstract void execute();
    public abstract String getResponse();
}
