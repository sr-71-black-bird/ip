package christopher.parser;

import christopher.command.DeadlineCommand;
import christopher.command.EventCommand;
import christopher.command.ToDoCommand;
import christopher.task.*;

/**
 * This class is primarily used to interpret the user's inputs
 */
public class Parser {
    private TaskList taskList;

    public Parser(TaskList taskList) {
        this.taskList = taskList;
    }

    /**
     * Parses user input and returns a ToDoCommand
     *
     * @param input the user input
     * @return Command and more specifically a ToDoCommand
     */
    public ToDoCommand parseToDo(String input) throws WrongInstructionException {
        String toDoInput = input.substring(input.indexOf(" ") + 1).trim();
        return new ToDoCommand(new ToDo(toDoInput), this.taskList);
    }

    /**
     * Parses user input and returns a DeadlineCommand
     *
     * @param input the user input
     * @return a DeadlineCommand
     * @throws ArrayIndexOutOfBoundsException throws when the user did not specify a date and parse is not successful
     * @throws WrongInstructionException      because super is called in Deadline and Task throws this
     */
    public DeadlineCommand parseDeadline(String input) throws ArrayIndexOutOfBoundsException, WrongInstructionException {
        String[] deadlineInput = input.split(" /by ");
        String deadlineName = deadlineInput[0].substring(deadlineInput[0].indexOf(" ") + 1).trim();
        return new DeadlineCommand(new Deadline(deadlineName, deadlineInput[1]), this.taskList);
    }

    /**
     * Parses user input and returns an EventCommand
     * @param input user input
     * @return EventCommand object
     * @throws ArrayIndexOutOfBoundsException throws when user did not fill in all parts
     * @throws WrongInstructionException because super is called in Deadline and Task throws this
     */
    public EventCommand parseEvent(String input) throws ArrayIndexOutOfBoundsException, WrongInstructionException {
        String[] eventInput = input.split("/event | /from | /to ");
        String eventName = eventInput[0].substring(eventInput[0].indexOf(" ") + 1).trim();
        return new EventCommand(new Event(eventName, eventInput[1], eventInput[2]), this.taskList);
    }


}
