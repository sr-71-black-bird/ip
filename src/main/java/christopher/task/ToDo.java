package christopher.task;

/**
 * This is a specific type of task
 * It just contains a task body
 */
public class ToDo extends Task {
    public ToDo(String name) throws WrongInstructionException {
        super(name);
    }

    @Override
    public String toString() {
        return "T | " + super.toString();
    }
}
