package christopher.command;

import christopher.task.Task;
import christopher.task.TaskList;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;

/**
 * Handles procedures when user wants to sort tasks by "deadline"
 */
public class SortCommand extends Command {
    private TaskList taskList;
    private Comparator<Task> byDeadline;

    public SortCommand(TaskList taskList) {
        this.taskList = new TaskList(new ArrayList<>(taskList.getList()));
        this.byDeadline = Comparator.comparing(t -> t.getDeadline().orElse(LocalDateTime.MAX));
    }

    /**
     * the new taskList that is held by this object is sorted according to deadline
     */
    @Override
    public void execute() {
        this.taskList.getList().sort(byDeadline);
    }

    /**
     * Gives the newly sorted taskList
     *
     * @return taskList.string() for the newly sorted list
     */
    @Override
    public String getResponse() {
        return String.format("Alright, here are tasks sorted by Deadline\n%s", this.taskList);
    }

}


