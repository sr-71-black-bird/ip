package christopher.command;

import christopher.parser.Parser;
import christopher.task.Task;
import christopher.task.TaskList;
import christopher.task.WrongInstructionException;

public class DeadlineCommand extends Command {
    private String input;
    private Task task;
    private TaskList taskList;
    private Parser parser = new Parser();

    public DeadlineCommand(String input, TaskList taskList) throws WrongInstructionException {
        this.input = input;
        this.taskList = taskList;
        this.task = this.parser.parseDeadline(input);
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
