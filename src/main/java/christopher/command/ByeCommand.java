package christopher.command;

import java.io.IOException;

import christopher.storage.Storage;
import christopher.task.TaskList;

/**
 * Handles the procedures when user's input has been confirmed to be a bye instruction.
 */
public class ByeCommand extends Command {
    private Storage storage;
    private TaskList taskList;

    /**
     * Returns a ByeCommand object.
     *
     * @param taskList the tasklist to be passed by christopher.
     * @param storage the same storage that would be passed by christopher.
     */
    public ByeCommand(TaskList taskList, Storage storage) {
        this.taskList = taskList;
        this.storage = storage;
    }

    /**
     * Saves the current taskList to tasks.txt data file.
     */
    @Override
    public void execute() {
        try {
            this.storage.save(this.taskList);
        } catch (IOException e) {
            System.out.println("The storage failed to save");
        }
    }

    /**
     * Says bye to the user.
     *
     * @return a string: "Bye."
     */
    @Override
    public String getResponse() {
        return String.format("Bye");
    }
}
