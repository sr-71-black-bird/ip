package christopher.parser;

import christopher.command.*;
import christopher.storage.Storage;
import christopher.task.*;
import christopher.ui.Instruction;

/**
 * This class is primarily used to interpret the user's inputs
 */
public class Parser {
    private TaskList taskList;
    private Storage storage;

    public Parser(TaskList taskList, Storage storage) {
        this.taskList = taskList;
        this.storage = storage;
    }

    public Command parse(String userInput) throws WrongInstructionException {
        Instruction instruction = Instruction.from(userInput);
        switch (instruction) {
        case TODO:
            return this.parseToDo(userInput);
        case DEADLINE:
            return this.parseDeadline(userInput);
        case EVENT:
            return this.parseEvent(userInput);
        case LIST:
            return this.parseList(userInput);
        case BYE:
            return this.parseBye(userInput);
        case MARK:
            return this.parseMark(userInput);
        case UNMARK:
            return this.parseUnmark(userInput);
        case DELETE:
            return this.parseDelete(userInput);
        case FIND:
            return this.parseFind(userInput);
        case UNKNOWN:
            throw new WrongInstructionException("Command is unrecognized");
        default:
            throw new WrongInstructionException("You have broken the program, wow!");
        }
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
     *
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

    /**
     * Parses a mark command input and returns the handler for such a command
     *
     * @param input user's input
     * @return MarkCommand object
     */
    public MarkCommand parseMark(String input) {
        int index = Integer.parseInt(input.split(" ")[1]) - 1;
        return new MarkCommand(index, this.taskList);
    }

    /**
     * Parses an unmark command input and returns the handler for the unmark command
     *
     * @param input user's input
     * @return UnmarkCommand object
     */
    public UnmarkCommand parseUnmark(String input) {
        int unmarkIndex = Integer.parseInt(input.split(" ")[1]) - 1;
        return new UnmarkCommand(unmarkIndex, this.taskList);
    }

    /**
     * Parses a list command by the user and returns the handler for this command
     *
     * @param input confirmed to be a list instruction
     * @return ListCommand object
     */
    public ListCommand parseList(String input) {
        return new ListCommand(this.taskList);
    }

    /**
     * Parses a bye command and returns the handler for this command
     *
     * @param input bye command by the user
     * @return ByeCommand(the taskList from christopher, the storage from christopher)
     */
    public ByeCommand parseBye(String input) {
        return new ByeCommand(this.taskList, this.storage);
    }

    /**
     * Parses a find command and returns the handler for this command
     *
     * @param input the user's find command
     * @return a FindCommand(keywords to be searched, the taskList from christopher)
     */
    public FindCommand parseFind(String input) {
        System.out.println("Here are the matching tasks in your list");
        return new FindCommand(input.split(" "), this.taskList);
    }

    /**
     * Parses a delete command and returns the command object that deals with deleting
     *
     * @param input user's delete command in string form
     * @return DeleteCommand(position of task to be deleted, christopher's tasklist, the task to be deleted)
     */
    public DeleteCommand parseDelete(String input) {
        int deleteIndex = Integer.parseInt(input.split(" ")[1]) - 1;
        Task deleteTask = taskList.getTask(deleteIndex);
        return new DeleteCommand(deleteIndex, this.taskList, deleteTask);
    }
}


