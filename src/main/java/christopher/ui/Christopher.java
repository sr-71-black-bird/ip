package christopher.ui;

import java.io.IOException;
import java.time.format.DateTimeParseException;

import christopher.command.Command;
import christopher.parser.Parser;
import christopher.storage.Storage;
import christopher.task.Deadline;
import christopher.task.Event;
import christopher.task.Task;
import christopher.task.TaskList;
import christopher.task.ToDo;
import christopher.task.WrongInstructionException;


/**
 * This is the chatbot, christopher.ui.Christopher, he will greet the user, and take commands until a bye was said
 */
public class Christopher {
    private final TaskList taskList;
    private final Storage storage;
    private final Parser parser;

    public Christopher() throws IOException, WrongInstructionException {
        this.storage = new Storage();
        this.taskList = new TaskList(this.storage.load());
        this.parser = new Parser(this.taskList, this.storage);
    }

    public static void main(String[] args) throws IOException, WrongInstructionException {
        System.out.println("Hello");
    }

    /**
     * Generates a response for the user's chat message
     */
    public String getResponse(String input) {
        try {
            Command command = this.parser.parse(input);
            command.execute();
            return command.getResponse();
        } catch (WrongInstructionException e) {
            return e.getMessage();
        } catch (ArrayIndexOutOfBoundsException e) {
            return String.format("Your command has missing parts");
        } catch (DateTimeParseException e) {
            return String.format("Your input date, time is in the wrong format");
        }
    }
}
