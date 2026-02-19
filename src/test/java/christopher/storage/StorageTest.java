package christopher.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import christopher.task.Deadline;
import christopher.task.Event;
import christopher.task.Task;
import christopher.task.TaskList;
import christopher.task.ToDo;
import christopher.task.WrongInstructionException;

public class StorageTest {
    private Storage storage;
    private final Path testPath = Paths.get("./data/test_tasks.txt");
    private TaskList taskList;

    @BeforeEach
    public void setUp() {
        storage = new Storage();
        taskList = new TaskList(new ArrayList<>());
    }

    @AfterEach
    public void tearDown() throws IOException {
        // Clean up test files
        Files.deleteIfExists(testPath);
    }

    @Test
    public void parseTask_validToDoTask_returnsCorrectTask() throws WrongInstructionException {
        Task task = storage.parseTask("1. T | [X] Buy groceries");
        assertTrue(task instanceof ToDo);
        assertEquals("Buy groceries", task.getName());
        assertTrue(task.isDone());
    }

    @Test
    public void parseTask_validDeadlineTask_returnsCorrectTask() throws WrongInstructionException {
        Task task = storage.parseTask("2. D | [ ] Submit report | 2023-05-15");
        assertTrue(task instanceof Deadline);
        assertEquals("Submit report", task.getName());
        assertEquals("2023-05-15", ((Deadline) task).getDeadline());
    }

    @Test
    public void parseTask_validEventTask_returnsCorrectTask() throws WrongInstructionException {
        Task task = storage.parseTask("3. E | [ ] Team meeting | 2023-05-10 14:00 | 2023-05-10 16:00");
        assertTrue(task instanceof Event);
        assertEquals("Team meeting", task.getName());
        assertEquals("2023-05-10 14:00", ((Event) task).getStart());
        assertEquals("2023-05-10 16:00", ((Event) task).getEnd());
    }

    @Test
    public void parseTask_invalidFormat_throwsException() {
        assertThrows(WrongInstructionException.class, () -> {
            storage.parseTask("Invalid format");
        });
    }

    @Test
    public void isDoneFromTaskBody_completedTask_returnsTrue() {
        assertTrue(storage.isDoneFromTaskBody("[X] Task"));
    }

    @Test
    public void isDoneFromTaskBody_incompleteTask_returnsFalse() {
        assertFalse(storage.isDoneFromTaskBody("[ ] Task"));
    }
}