package christopher.task;

import java.time.LocalDateTime;
import java.util.Optional;

/**
 * Parents for various types of subclasses.
 * Has a task name, which every task should have.
 */
public class Task {
    private String name;
    private boolean isDone;

    /**
     * Creates a Task object.
     *
     * @param name task to do.
     * @throws WrongInstructionException wrongly formatted or incomplete info.
     */
    public Task(String name) throws WrongInstructionException {
        if (name.equals("")) {
            throw new WrongInstructionException("You entered an empty task!");
        }
        this.name = name;
        this.isDone = false;
    }

    public String getName() {
        return this.name;
    }

    /**
     * Marks the specific task instance to be complete.
     */
    public void complete() {
        this.isDone = true;
    }

    /**
     * Marks the specific task instance to be incomplete.
     */
    public void undoComplete() {
        this.isDone = false;
    }

    /**
     * Checks if it is done.
     *
     * @return the isDone status of said task.
     */
    public boolean isDone() {
        return this.isDone;
    }

    /**
     * Returns the deadline as an optional as some types of tasks may not have a deadline.
     *
     * @return Optional which contains a local date and time.
     */
    public Optional<LocalDateTime> getDeadline() {
        return Optional.empty();
    }

    @Override
    public String toString() {
        if (this.isDone == false) {
            return "[ ] " + this.name;
        }
        return "[X] " + this.name;
    }
}
