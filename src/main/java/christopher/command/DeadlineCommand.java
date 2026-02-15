package christopher.command;

import christopher.task.Task;
import christopher.task.TaskList;

/**
 * This class handles the things to be done when the user decides to add a deadline task to the list
 */
public class DeadlineCommand extends Command {
    private Task task;
    private TaskList taskList;

    public DeadlineCommand(Task task, TaskList taskList) {
        this.task = task;
        this.taskList = taskList;
    }

    /**
     * This carries out and add this deadline task to the taskList passed
     */
    @Override
    public void execute() {
        taskList.add(this.task);
    }

    /**
     * @return the string to be said to the user
     */
    public String getResponse() {
        return String.format("Got it. I've added this task:\n%s\nNow you have %d tasks", this.task,
                taskList.getTotalTask());
    }
}
