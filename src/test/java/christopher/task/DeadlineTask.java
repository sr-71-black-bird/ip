package christopher.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * This is a class containing tests for the Deadline class
 */
public class DeadlineTask {
    private Deadline t1;
    private LocalDateTime deadline;

    /**
     * This is to load up t1 and deadline before each test is run
     * @throws WrongInstructionException deadline constructor throws this error
     */
    @BeforeEach
    public void initialize() throws WrongInstructionException {
        t1 = new Deadline("Say hi", "2026-02-19 19:30");
        deadline = LocalDateTime.of(2026, 2, 19, 19, 30);
    }

    @Test
    public void checkDate_success() {
        assertEquals(deadline, t1.getDeadline());
    }

    public Deadline getDeadlineObject() {
        return this.t1;
    }

    public LocalDateTime getDeadlineTime() {
        return this.deadline;
    }
}
