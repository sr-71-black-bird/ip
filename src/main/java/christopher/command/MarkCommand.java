package christopher.command;

import christopher.task.TaskList;

/**
 * Handles procedures when the user inserted mark command.
 */
public class MarkCommand extends Command {
    private TaskList taskList;
    private int index;

    /**
     * Returns a MarkCommand object.
     *
     * @param index the position of the task to be marked.
     * @param taskList task list from which we want to mark our task.
     */
    public MarkCommand(int index, TaskList taskList) {
        this.index = index; //bug found by AI (line missing)
        this.taskList = taskList;
    }

    /**
     * Completes the specified task.
     */
    @Override
    public void execute() {
        taskList.complete(index);
    }

    /**
     * Returns the string to be said to the user.
     *
     * @return the string shown to user.
     */
    @Override
    public String getResponse() {
        return String.format("Nice! I've marked this task as done:\n%s", this.taskList.getTask(this.index));
    }
}
