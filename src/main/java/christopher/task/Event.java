package christopher.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * This is an "event", a type of task
 * It contains the start time and end time and a body
 * Start time indicated by /from
 * End time indicated by /by
 */
public class Event extends Task {
    private LocalDateTime start;
    private LocalDateTime end;

    /**
     * creates an event object
     * @param name what the task is
     * @param start date and time where it starts
     * @param end date and time where it ends
     * @throws WrongInstructionException missing information or wrong format
     * @throws DateTimeParseException wrong format for date and time
     */
    public Event(String name, String start, String end) throws WrongInstructionException, DateTimeParseException {
        super(name);
        DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        if (start.equals("")) {
            throw new WrongInstructionException("Your start date invalid");
        }
        if (end.equals("")) {
            throw new WrongInstructionException("Your end date invalid");
        }
        this.start = LocalDateTime.parse(start, inputFormatter);
        this.end = LocalDateTime.parse(end, inputFormatter);
    }

    public LocalDateTime getStart() {
        return this.start;
    }

    public LocalDateTime getEnd() {
        return this.end;
    }

    @Override
    public String toString() {
        DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("MMM dd yyyy, h:mm a");
        return String.format("E | %s | %s | %s)",
                super.toString(),
                this.start.format(outputFormatter),
                this.end.format(outputFormatter));
    }
}
