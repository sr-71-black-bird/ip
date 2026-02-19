package christopher.command;

import christopher.task.TaskList;

/**
 * Handles the procedures when user's input has been confirmed to be a List instruction.
 */
public class ListCommand extends Command {
    private TaskList taskList;

    public ListCommand(TaskList taskList) {
        this.taskList = taskList;
    }

    /**
     * Does nothing since list instruction has nothing to be done.
     */
    @Override
    public void execute() {
        //do nothing
    }

    /**
     * Returns the current state of TaskList in a string form.
     *
     * @return toString() representation of taskList.
     */
    @Override
    public String getResponse() {
        return this.taskList.toString();
    }
}
