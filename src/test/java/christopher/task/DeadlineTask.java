package christopher.task;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class DeadlineTask {
    Deadline t1;
    LocalDateTime deadline;

    @BeforeEach
    public void initialize() throws WrongInstructionException {
        t1 = new Deadline("Say hi", "2026-02-19 19:30");
        deadline = LocalDateTime.of(2026, 2, 19, 19, 30);
    }

    @Test
    public void checkDate_success() {
        assertEquals(deadline, t1.getDeadline());
    }
}
