package christopher.command;

import christopher.task.Event;
import christopher.task.Task;
import christopher.task.TaskList;

/**
 * Handles the procedures when the user wants to insert an event object into the taskList.
 */
public class EventCommand extends Command {
    private Task task;
    private TaskList taskList;

    /**
     * Returns an eventCommand object.
     *
     * @param task the new task to be added.
     * @param taskList the list to which the new task is added.
     */
    public EventCommand(Task task, TaskList taskList) {
        this.task = task;
        this.taskList = taskList;
    }

    /**
     * Adds this event task into the taskList to be passed in my Christopher during construction.
     */
    @Override
    public void execute() {
        assert (this.task instanceof Event) : "Assumed to be an event task";
        this.taskList.add(this.task);
    }

    /**
     * Gives the String to be printed for the user to see.
     *
     * @return the string to be said to the user
     */
    @Override
    public String getResponse() {
        return String.format("Got it, I've add this task:\n%s\nNow you have %d tasks", this.task,
                this.taskList.getTotalTask());
    }
}
