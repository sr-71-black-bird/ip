package christopher.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Optional;

/**
 * This is a form of task, specifically a task with a deadline attached
 * christopher.task.Deadline is specified by the "/by" indication during user input
 */
public class Deadline extends Task {
    private LocalDateTime deadline;

    /**
     * Creates a deadline object
     *
     * @param name the name or the task body
     * @param deadline date and time of the deadline
     * @throws WrongInstructionException when the user doesn't input enough info
     * @throws DateTimeParseException when the wrong format is given for date and time
     */
    public Deadline(String name, String deadline) throws WrongInstructionException, DateTimeParseException {
        super(name);
        DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        this.deadline = LocalDateTime.parse(deadline, inputFormatter);
    }

    /**
     * Returns the deadline date as an optional
     *
     * @return as above
     */
    public Optional<LocalDateTime> getDeadline() {
        return Optional.of(this.deadline);
    }

    @Override
    public String toString() {
        DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("MMM dd yyyy, h:mm a");
        return String.format("D | %s | %s", super.toString(), this.deadline.format(outputFormatter));
    }
}
