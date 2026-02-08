package christopher.task;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

public class TaskListTest {

    private TaskList taskList;
    private Task t1;

    @BeforeEach
    public void initialize() throws WrongInstructionException {
        taskList = new TaskList(new ArrayList<>());
        t1 = new ToDo("say hi");
    }

    @Test
    public void addTask_sizeIncreases() {
        taskList.add(t1);
        assertEquals(1, taskList.getTotalTask());
        assertEquals(t1, taskList.getTask(0));
    }

    @Test
    public void complete_taskMarkedComplete() {
        taskList.add(t1);
        taskList.complete(0);
        assertTrue(taskList.getTask(0).isDone());
    }
}
