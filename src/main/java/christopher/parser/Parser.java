package christopher.parser;

import christopher.task.*;
import christopher.ui.Command;

import static christopher.ui.Command.UNKNOWN;

/**
 * This class is primarily used to interpret the user's inputs
 */
public class Parser {

    /**
     * This parses user input and returns a ToDo, used by the ToDoCommand
     * @param input the user input
     * @return ToDo which is a result of parsing
     */
    public Task parseToDo(String input) throws WrongInstructionException {
        String toDoInput = input.substring(input.indexOf(" ") + 1).trim();
        return new ToDo(toDoInput);
    }

    /**
     * This parses user input and returns a Deadline, used by the Deadline Command
     * @param input the user input
     * @return a deadline object
     * @throws ArrayIndexOutOfBoundsException throws when the user did not specify a date and parse is not successful
     * @throws WrongInstructionException because super is called in Deadline and Task throws this
     */
    public Task parseDeadline(String input) throws ArrayIndexOutOfBoundsException, WrongInstructionException {
        String[] deadlineInput = input.split(" /by ");
        String deadlineName = deadlineInput[0].substring(deadlineInput[0].indexOf(" ") + 1).trim();
        return new Deadline(deadlineName, deadlineInput[1]);
    }

    /**
     * This parses user input and returns an Event, used by the Event Command
     * @param input
     * @return Event object
     * @throws ArrayIndexOutOfBoundsException throws when user did not fill in all parts
     * @throws WrongInstructionException because super is called in Deadline and Task throws this
     */
    public Task parseEvent(String input) throws ArrayIndexOutOfBoundsException, WrongInstructionException {
        String[] eventInput = input.split("/event | /from | /to ");
        String eventName = eventInput[0].substring(eventInput[0].indexOf(" ") + 1).trim();
        return new Event(eventName, eventInput[1], eventInput[2]);
    }
}
