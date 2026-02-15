package christopher.command;

import christopher.parser.Parser;
import christopher.task.Task;
import christopher.task.TaskList;
import christopher.task.WrongInstructionException;

public class EventCommand extends Command {
    private String input;
    private Task task;
    private TaskList taskList;
    private Parser parser = new Parser();

    public EventCommand(String input, TaskList taskList) throws WrongInstructionException {
        this.input = input;
        this.taskList = taskList;
        this.task = this.parser.parseEvent(input);
    }

    /**
     * add this event task into the taskList to be passed in my Christopher during construction
     */
    @Override
    public void execute() {
        this.taskList.add(this.task);
    }

    /**
     *
     * @return the string to be said to the user
     */
    @Override
    public String getResponse() {
        return String.format("Got it, I've add this task:\n%s\nNow you have %d tasks", this.task,
                this.taskList.getTotalTask());
    }
}
