package christopher.ui;
/**
 * This is a storage class, it is responsible for loading from a data tasks.txt file
 * It is also responsible for storing whatever tasks remaining into the same file
 */

import christopher.task.Deadline;
import christopher.task.Event;
import christopher.task.Task;
import christopher.task.TaskList;
import christopher.task.ToDo;
import christopher.task.WrongInstructionException;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class Storage {
    private final Path path = Paths.get("./data/tasks.txt");

    /**
     * This saves the tasks in the tasklist into ./data/tasks.txt
     * @param taskList this is the current taskList object
     * @throws IOException thrown when the target file has various issues
     */
    public void save(TaskList taskList) throws IOException {
        Files.createDirectories(path.getParent());
        try (BufferedWriter writer = Files.newBufferedWriter(path)) {
            writer.write(taskList.toString());
        } catch (IOException e) {
            System.out.println(e.getMessage());
            System.out.println("There is an issue with the file to store our tasks, try again");
        }
    }

    /**
     * This method takes in the body of the task written in tasks.txt and see if they are done
     * @param taskBody only the body of the task description written to be passed in
     * @return true if done and false if not done
     */
    public boolean isDoneFromTaskBody(String taskBody) {
        return taskBody.startsWith("[X]");
    }

    /**
     * This is for reading a line in the save file and return the corresponding task
     * @param line a line in the save file
     * @return the task indicated by the line in save file
     * @throws WrongInstructionException when the lines written in save file doesn't match any existing task format
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
            task = new Deadline(taskName, deadline);
            break;
        case "E":
            String start = parts[2];
            String end = parts[3];
            task = new Event(taskName, start, end);
        default:
            throw new WrongInstructionException("Unrecognizable format in save file tasks.txt, please review");
        }
        if (isDone) {
            task.complete();
        }
        return task;
    }

    /**
     * This loads whatever tasks inside text.txt into the taskList when chatbot starts up
     * @return Arraylist of tasks for taskList to be initialized with
     * @throws IOException throws when there is an issue with the file in question
     */
    public ArrayList<Task> load() throws IOException, WrongInstructionException {
        ArrayList<Task> tasks = new ArrayList<>();
        if (!Files.exists(path)) { //this is when there the file doesn't exits
            return tasks;
        }
        for (String line: Files.readAllLines(path)) {
            tasks.add(parseTask(line));
        }
        return tasks;
    }
}
