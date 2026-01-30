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
        return String.format("[D]%s by: %s", super.toString(), this.deadline);
    }
}
