package christopher.command;

import christopher.task.Task;
import christopher.task.TaskList;

/**
 * Handles procedures when the user decides to add todo into the taskList
 */
public class ToDoCommand extends Command {
    private Task task;
    private TaskList taskList;

    public ToDoCommand(Task task, TaskList taskList) {
        this.task = task;
        this.taskList = taskList;
    }

    /**
     * Carries out toDo command which is to add this into the taskList
     */
    @Override
    public void execute() {
        this.taskList.add(this.task);
    }

    /**
     * Returns the confirmation and the state of the taskList
     *
     * @return the string to be said by christopher to the user
     */
    @Override
    public String getResponse() {
        return String.format("Got it, I've add this task:\n%s\nNow you have %d tasks", this.task,
                this.taskList.getTotalTask());
    }
}
