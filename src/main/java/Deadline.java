/**
 * This is a form of task, specifically a task with a deadline attached
 * Deadline is specified by the "/by" indication during user input
 */
public class Deadline extends Task {
    private String deadline;

    public Deadline(String name, String deadline) throws WrongInstructionException {
        super(name);
        if (deadline.equals("")) {
            throw new WrongInstructionException("Task deadline invalid");
        }
        this.deadline = deadline;
    }

    @Override
    public String toString() {
        return String.format("D | %s | %s", super.toString(), this.deadline);
    }
}
