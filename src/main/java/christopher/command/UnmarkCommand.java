package christopher.command;

import christopher.task.TaskList;

/**
 * Handles when the user wants an unmarkCommand.
 */
public class UnmarkCommand extends Command {
    private int index;
    private TaskList taskList;

    public UnmarkCommand(int index, TaskList taskList) {
        this.index = index;
        this.taskList = taskList;
    }

    /**
     * Unmarks the specified task at the index position.
     */
    @Override
    public void execute() {
        this.taskList.undoComplete(index);
    }

    /**
     * Returns verifying the unmarking of the specified task.
     *
     * @return String to be shown to user.
     */
    @Override
    public String getResponse() {
        return String.format("Ok, I've marked this task as not done yet:\n%s", this.taskList.getTask(index));
    }
}
