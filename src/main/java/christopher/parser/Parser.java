package christopher.parser;

import christopher.command.ByeCommand;
import christopher.command.Command;
import christopher.command.DeadlineCommand;
import christopher.command.DeleteCommand;
import christopher.command.EventCommand;
import christopher.command.FindCommand;
import christopher.command.ListCommand;
import christopher.command.MarkCommand;
import christopher.command.SortCommand;
import christopher.command.ToDoCommand;
import christopher.command.UnmarkCommand;
import christopher.storage.Storage;
import christopher.task.Deadline;
import christopher.task.Event;
import christopher.task.Task;
import christopher.task.TaskList;
import christopher.task.ToDo;
import christopher.task.WrongInstructionException;
import christopher.ui.Instruction;

/**
 * Interpret the user's inputs.
 * Returns different types of commands to be executed.
 */
public class Parser {
    private TaskList taskList;
    private Storage storage;

    /**
     * Returns a parser object.
     *
     * @param taskList the chatbot's task list.
     * @param storage the storage that is facilitating the bot.
     */
    public Parser(TaskList taskList, Storage storage) {
        this.taskList = taskList;
        this.storage = storage;
    }

    /**
     * Returns a command after parsing user's input.
     *
     * @param userInput the string command by the user.
     * @return Command one of the appropriate subclasses of Command.
     * @throws WrongInstructionException when the user inputs unsupported commands.
     */
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
        case SORT:
            return this.parseSort(userInput);
        case UNKNOWN:
            throw new WrongInstructionException("Command is unrecognized");
        default:
            throw new WrongInstructionException("You have broken the program, wow!");
        }
    }

    /**
     * Parses user input and returns a ToDoCommand.
     *
     * @param input the user input.
     * @return Command and more specifically a ToDoCommand.
     */
    public ToDoCommand parseToDo(String input) throws WrongInstructionException {
        String toDoInput = input.substring(input.indexOf(" ") + 1).trim();
        return new ToDoCommand(new ToDo(toDoInput), this.taskList);
    }

    /**
     * Parses user input and returns a DeadlineCommand.
     *
     * @param input the user input.
     * @return a DeadlineCommand.
     * @throws ArrayIndexOutOfBoundsException throws when the user did not specify a date and parse is not successful.
     * @throws WrongInstructionException      because super is called in Deadline and Task throws this.
     */
    public DeadlineCommand parseDeadline(String input) throws ArrayIndexOutOfBoundsException,
            WrongInstructionException {
        String[] deadlineInput = input.split(" /by ");
        if (deadlineInput.length > 2) {
            throw new WrongInstructionException("You can only have one deadline, please try again");
        }
        String deadlineName = deadlineInput[0].substring(deadlineInput[0].indexOf(" ") + 1).trim();
        return new DeadlineCommand(new Deadline(deadlineName, deadlineInput[1]), this.taskList);
    }

    /**
     * Parses user input and returns an EventCommand.
     *
     * @param input user input.
     * @return EventCommand object.
     * @throws ArrayIndexOutOfBoundsException throws when user did not fill in all parts.
     * @throws WrongInstructionException because super is called in Deadline and Task throws this.
     */
    public EventCommand parseEvent(String input) throws ArrayIndexOutOfBoundsException, WrongInstructionException {
        String[] eventInput = input.split("/event | /from | /to ");
        if (eventInput.length > 3) {
            throw new WrongInstructionException("You can only have one start and one end, please try again");
        }
        String eventName = eventInput[0].substring(eventInput[0].indexOf(" ") + 1).trim();
        return new EventCommand(new Event(eventName, eventInput[1], eventInput[2]), this.taskList);
    }

    /**
     * Parses a mark command input and returns the handler for such a command.
     *
     * @param input user's input.
     * @return MarkCommand object.
     */
    public MarkCommand parseMark(String input) throws WrongInstructionException {
        int index = Integer.parseInt(input.split(" ")[1]) - 1;
        if (index < 0) {
            throw new WrongInstructionException("You can't mark a task that doesn't exist, index starts from 1");
        }
        if (index >= this.taskList.getTotalTask()) {
            throw new WrongInstructionException("Your index is more than there exists tasks");
        }
        return new MarkCommand(index, this.taskList);
    }

    /**
     * Parses an unmark command input and returns the handler for the unmark command.
     *
     * @param input user's input.
     * @return UnmarkCommand object.
     */
    public UnmarkCommand parseUnmark(String input) throws WrongInstructionException {
        int unmarkIndex = Integer.parseInt(input.split(" ")[1]) - 1;
        // Error handling recommended by AI
        if (unmarkIndex < 0) {
            throw new WrongInstructionException("You can't mark a task that doesn't exist, index starts from 1");
        }
        if (unmarkIndex >= this.taskList.getTotalTask()) {
            throw new WrongInstructionException("Your index is more than there exists tasks");
        }
        return new UnmarkCommand(unmarkIndex, this.taskList);
    }

    /**
     * Parses a list command by the user and returns the handler for this command.
     *
     * @param input confirmed to be a list instruction.
     * @return ListCommand object.
     */
    public ListCommand parseList(String input) {
        return new ListCommand(this.taskList);
    }

    /**
     * Parses a bye command and returns the handler for this command.
     *
     * @param input bye command by the user.
     * @return ByeCommand(the taskList from christopher, the storage from christopher).
     */
    public ByeCommand parseBye(String input) {
        return new ByeCommand(this.taskList, this.storage);
    }

    /**
     * Parses a find command and returns the handler for this command.
     *
     * @param input the user's find command.
     * @return a FindCommand(keywords to be searched, the taskList from christopher).
     */
    public FindCommand parseFind(String input) throws WrongInstructionException {
        if (input.equals("find")) {
            throw new WrongInstructionException("You need to specify what you want to find");
        }
        return new FindCommand(input.split(" "), this.taskList);
    }

    /**
     * Parses a delete command and returns the command object that deals with deleting.
     *
     * @param input user's delete command in string form.
     * @return DeleteCommand(position of task to be deleted, christopher's tasklist, the task to be deleted).
     */
    public DeleteCommand parseDelete(String input) throws WrongInstructionException {
        int deleteIndex = Integer.parseInt(input.split(" ")[1]) - 1;
        if (deleteIndex < 0) {
            throw new WrongInstructionException("You can't mark a task that doesn't exist, index starts from 1");
        }
        if (deleteIndex >= this.taskList.getTotalTask()) {
            throw new WrongInstructionException("Your index is more than there exists tasks");
        }
        Task deleteTask = taskList.getTask(deleteIndex);
        return new DeleteCommand(deleteIndex, this.taskList, deleteTask);
    }

    /**
     * Returns the SortCommand after parsing the input, not much to parse here.
     *
     * @param input user's instruction.
     * @return SortCommand which will sort the list.
     */
    public SortCommand parseSort(String input) {
        return new SortCommand(this.taskList);
    }
}
