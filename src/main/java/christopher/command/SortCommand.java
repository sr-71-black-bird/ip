package christopher.command;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;

import christopher.task.Task;
import christopher.task.TaskList;

/**
 * Handles procedures when user wants to sort tasks by "deadline."
 */
public class SortCommand extends Command {
    private TaskList taskList;
    private Comparator<Task> byDeadline;

    /**
     * Returns a SortCommand object.
     *
     * @param taskList the taskList to be sorted.
     */
    public SortCommand(TaskList taskList) {
        this.taskList = new TaskList(new ArrayList<>(taskList.getList()));
        this.byDeadline = Comparator.comparing(t -> t.getDeadline().orElse(LocalDateTime.MAX));
    }

    /**
     * Returns the sorted task list.
     */
    @Override
    public void execute() {
        this.taskList.getList().sort(byDeadline);
    }

    /**
     * Prints the newly sorted taskList.
     *
     * @return taskList.string() for the newly sorted list.
     */
    @Override
    public String getResponse() {
        return String.format("Alright, here are tasks sorted by deadline\n%s", this.taskList);
    }

}


