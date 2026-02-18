package christopher.task;

import java.time.LocalDateTime;
import java.util.Optional;

/**
 * This is a specific type of task
 * It just contains a task body
 */
public class ToDo extends Task {
    public ToDo(String name) throws WrongInstructionException {
        super(name);
    }

    /**
     * Returns the empty option because this task has no deadline
     *
     * @return Optional(empty), to be used with orElse (max time)
     */
    public Optional<LocalDateTime> getDeadline() {
        return Optional.empty();
    }

    @Override
    public String toString() {
        return "T | " + super.toString();
    }
}
