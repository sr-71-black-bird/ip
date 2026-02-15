package christopher.command;

import christopher.parser.Parser;
import christopher.task.Task;
import christopher.task.TaskList;
import christopher.task.WrongInstructionException;

public class ToDoCommand extends Command{
    private String input;
    private Task task;
    private TaskList taskList;
    private Parser parser = new Parser();

    public ToDoCommand(String input, TaskList taskList) throws WrongInstructionException {
        this.input = input;
        this.taskList = taskList;
        this.task = this.parser.parseToDo(input);
    }

    /**
     * Carries out toDo command which is to add this into the taskList
     */
    @Override
    public void execute() {
        this.taskList.add(this.task);
    }

    /**
     * Returns the string to be said by christopher to the user
     * @return the string to be said by christopher to the user
     */
    @Override
    public String getResponse() {
        return String.format("Got it, I've add this task:\n%s\nNow you have %d tasks", this.task,
                this.taskList.getTotalTask());
    }
}
