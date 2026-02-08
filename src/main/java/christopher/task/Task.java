package christopher.task;

/**
 * This is task class; it will have children of specific task types
 * It has a name, and an indication of whether it is done
 * Can be done or undone
 */
public class Task {
    private String name;
    private boolean isDone;

    public Task(String name) throws WrongInstructionException {
        if (name.equals("")) {
            throw new WrongInstructionException("christopher.task.Task name invalid");
        }
        this.name = name;
        this.isDone = false;
    }

    /**
     * Marks the specific task instance to be complete
     */
    public void complete() {
        this.isDone = true;
    }

    /**
     * Marks the specific task instance to be incomplete
     */
    public void undoComplete() {
        this.isDone = false;
    }

    /**
     * Check if it is done
     * @return the isDone status of said task
     */
    public boolean isDone() {
        return this.isDone;
    }

    @Override
    public String toString() {
        if (this.isDone == false) {
            return "[ ] " + this.name;
        }
        return "[X] " + this.name;
    }
}
