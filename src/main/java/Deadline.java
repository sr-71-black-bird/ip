import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * This is a form of task, specifically a task with a deadline attached
 * Deadline is specified by the "/by" indication during user input
 */
public class Deadline extends Task {
    private LocalDateTime deadline;

    public Deadline(String name, String deadline) throws WrongInstructionException, DateTimeParseException {
        super(name);
        if (deadline.equals("")) {
            throw new WrongInstructionException("Task deadline invalid");
        }
        DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        this.deadline = LocalDateTime.parse(deadline, inputFormatter);
    }

    @Override
    public String toString() {
        DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("MMM dd yyyy, h:mm a");
        return String.format("D | %s | %s", super.toString(), this.deadline.format(outputFormatter));
    }
}
