package christopher.command;

import christopher.task.TaskList;

/**
 * Executes the find command when the user is confirmed to be choosing such command
 */
public class FindCommand extends Command {
    private TaskList taskList;
    private TaskList result;
    private String[] keywords;

    public FindCommand(String[] keywords, TaskList taskList) {
        this.taskList = taskList;
    }

    /**
     * Finds the matching tasks given a set of keywords
     */
    @Override
    public void execute() {
        assert (keywords.length > 0) : "Assumed there's actually some keywords";
        this.result = this.taskList.find(keywords);
    }

    /**
     * Informs the user which tasks match the ones they want to find
     *
     * @return the TaskList.toString() of the tasks they are interested in
     */
    @Override
    public String getResponse() {
        return String.format("Here are matching tasks in your list:\n$s", this.result);
    }
}
