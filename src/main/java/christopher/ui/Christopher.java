package christopher.ui;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.format.DateTimeParseException;

import christopher.command.Command;
import christopher.parser.Parser;
import christopher.storage.Storage;
import christopher.task.TaskList;
import christopher.task.WrongInstructionException;


/**
 * Accomplishes various task related commands by the user.
 */
public class Christopher {
    String userHome = System.getProperty("user.home");
    Path folderPath = Paths.get(userHome, "christopher_data");
    String fullPath = folderPath.resolve("tasks.txt").toString();

    private final TaskList taskList;
    private final Storage storage;
    private final Parser parser;

    /**
     * Returns a Christopher object.
     *
     * @throws IOException when storage fails.
     * @throws WrongInstructionException to accomodate new task creation during loading from memory.
     */
    public Christopher() throws IOException, WrongInstructionException {
        this.storage = new Storage(fullPath);
        this.taskList = new TaskList(this.storage.load());
        this.parser = new Parser(this.taskList, this.storage);
    }

    public static void main(String[] args) {
        System.out.println("Hello");
    }

    public Storage getStorage() {
        return this.storage;
    }

    public TaskList getTaskList() {
        return this.taskList;
    }

    /**
     * Generates a response for the user's chat message.
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
