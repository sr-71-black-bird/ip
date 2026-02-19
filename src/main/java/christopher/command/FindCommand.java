package christopher.command;

import java.util.ArrayList;

import christopher.task.TaskList;
import christopher.task.WrongInstructionException;

/**
 * Executes the find command when the user is confirmed to be choosing such command.
 */
public class FindCommand extends Command {
    private TaskList taskList;
    private TaskList result;
    private ArrayList<String> keywords = new ArrayList<>();

    /**
     * Returns a FindCommand object.
     *
     * @param keywords a list of words to use to find the tasks.
     * @param taskList the task list from which to search.
     * @throws WrongInstructionException throws when there is no tasks.
     */
    public FindCommand(String[] keywords, TaskList taskList) throws WrongInstructionException {
        this.taskList = taskList;
        for (int i = 1; i < keywords.length; i++) {
            this.keywords.add(keywords[i]);
        }
        if (this.keywords.size() <= 0) {
            throw new WrongInstructionException("You actually need some keywords to find your tasks");
        }
    }

    /**
     * Finds the matching tasks given a set of keywords.
     */
    @Override
    public void execute() {
        assert (keywords.size() > 0) : "Assumed there's actually some keywords";
        this.result = this.taskList.find(keywords.toArray(new String[0]));
    }

    /**
     * Informs the user which tasks match the ones they want to find.
     *
     * @return the TaskList.toString() of the tasks they are interested in.
     */
    @Override
    public String getResponse() {
        return String.format("Here are matching tasks in your list:\n%s", this.result);
    }
}
