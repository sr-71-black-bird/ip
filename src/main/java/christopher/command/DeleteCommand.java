package christopher.command;

import christopher.task.Task;
import christopher.task.TaskList;

/**
 * Handles the procedures to be done when user command is confirmed to be a delete command.
 */
public class DeleteCommand extends Command {
    private int index;
    private TaskList taskList;
    private Task task;

    /**
     * Returns a DeleteCommand object.
     *
     * @param index the position of the to be deleted task
     * @param taskList the task list from which to delete
     * @param task the deleted task
     */
    public DeleteCommand(int index, TaskList taskList, Task task) {
        this.index = index;
        this.taskList = taskList;
        this.task = task;
    }

    /**
     * Deletes the specified task.
     */
    @Override
    public void execute() {
        taskList.delete(index);
    }

    /**
     * Confirms task has been deleted.
     * Notifies user of the current state of taskList.
     *
     * @return String containing information above
     */
    @Override
    public String getResponse() {
        return String.format("Noted, I've removed this task:\n%s\nNow you have %d in tasks in the list",
                this.task,
                this.taskList.getTotalTask());
    }
}
