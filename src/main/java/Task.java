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
            throw new WrongInstructionException("Task name invalid");
        }
        this.name = name;
        this.isDone = false;
    }

    //This is to complete the task
    public void complete() {
        this.isDone = true;
    }

    //This method reverses the done status
    public void undoComplete() {
        this.isDone = false;
    }

    @Override
    public String toString() {
        if (this.isDone == false) {
            return "[ ] " + this.name;
        }
        return "[X] " + this.name;
    }
}
