package christopher.storage;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.ArrayList;
import java.util.Locale;

import christopher.task.Deadline;
import christopher.task.Event;
import christopher.task.Task;
import christopher.task.TaskList;
import christopher.task.ToDo;
import christopher.task.WrongInstructionException;

/**
 * Loads and saves from a data tasks.txt file.
 * Creates a data tasks.txt file if there isn't one.
 */
public class Storage {
    private final Path filePath;

    public Storage(String relativePath) {
        this.filePath = Paths.get(relativePath);
    }

    /**
     * Saves the tasks in the tasklist into ./data/tasks.txt.
     *
     * @param taskList this is the current taskList object.
     * @throws IOException thrown when the target file has various issues.
     */
    public void save(TaskList taskList) throws IOException {
        Files.createDirectories(filePath.getParent());
        try (BufferedWriter writer = Files.newBufferedWriter(filePath)) {
            writer.write(taskList.toString());
        } catch (IOException e) {
            System.out.println(e.getMessage());
            System.out.println("There is an issue with the file to store our tasks, try again");
        }
    }

    /**
     * Takes in the body of the task written in tasks.txt and see if they are done.
     *
     * @param taskBody only the body of the task description written to be passed in.
     * @return true if done and false if not done.
     */
    public boolean isDoneFromTaskBody(String taskBody) {
        return taskBody.startsWith("[X]");
    }

    /**
     * Reads a line in the save file and return the corresponding task.
     *
     * @param line a line in the save file.
     * @return the task indicated by the line in save file.
     * @throws WrongInstructionException when the lines written in save file doesn't match any existing task format.
     */
    public Task parseTask(String line) throws WrongInstructionException {
        String[] parts = line.substring(line.indexOf(" ") + 1).split(" \\| "); //we don't want the index
        String taskType = parts[0];
        String taskBody = parts[1];
        boolean isDone = isDoneFromTaskBody(taskBody);
        String taskName = taskBody.substring(4); //This takes just the name from "[X] name"
        Task task;
        switch (taskType) {
        case "T":
            task = new ToDo(taskName);
            break;
        case "D":
            String deadline = parts[2];
            task = new Deadline(taskName, parseDateTime(deadline));
            break;
        case "E":
            String start = parts[2];
            String end = parts[3];
            task = new Event(taskName, parseDateTime(start), parseDateTime(end));
            break;
        default:
            throw new WrongInstructionException("Unrecognizable format in save file tasks.txt, please review");
        }
        if (isDone) {
            task.complete();
        }
        return task;
    }

    /**
     * Returns LocalDateTime represented by input after parsing it.
     *
     * @param input a string which represent date and time.
     * @return LocalDateTime variable which contains the date and time represented by input.
     */
    public LocalDateTime parseDateTime(String input) {
        DateTimeFormatter formatter =
                new DateTimeFormatterBuilder()
                        .parseCaseInsensitive()
                        .appendPattern("MMM d yyyy, h:mm a")
                        .toFormatter(Locale.ENGLISH);
        return LocalDateTime.parse(input, formatter);
    }

    /**
     * Loads whatever tasks inside text.txt into the taskList when chatbot starts up.
     *
     * @return Arraylist of tasks for taskList to be initialized with.
     * @throws IOException throws when there is an issue with the file in question.
     */
    public ArrayList<Task> load() throws IOException, WrongInstructionException {
        ArrayList<Task> tasks = new ArrayList<>();
        try {
            if (!Files.exists(filePath)) { //this is when there the file doesn't exits
                return tasks;
            }
            for (String line : Files.readAllLines(filePath)) {
                tasks.add(parseTask(line));
            }
        } catch (IOException e) {
            return new ArrayList<>();
        }
        return tasks;
    }
}
