package christopher.task;

import java.util.ArrayList;

/**
 * This is a taskList, it will maintain an array containing tasks on the list
 */
public class TaskList {
    private ArrayList<Task> list;

    public TaskList(ArrayList<Task> list) {
        this.list = list;
    }

    /**
     * This method takes in a task and add it to our taskList
     * @param task The task to be added into the list
     */
    public void add(Task task) {
        this.list.add(task);
    }

    /**
     * This method marks the task at the specified position to be "complete"
     * @param index the position of the desired task - 1 (e.g task3 has index 2)
     */
    public void complete(int index) {
        this.list.get(index).complete();
    }

    /**
     * This method marks the task at the specified position to be "incomplete"
     * @param index the position of the desired task - 1 (e.g task3 has index 2)
     */
    public void undoComplete(int index) {
        this.list.get(index).undoComplete();
    }

    /**
     * This method returns the task at the specified position
     * @param index index is one less than position (e.g task3 has index 2)
     * @return the object of the desired task
     */
    public Task getTask(int index) {
        return this.list.get(index);
    }

    /**
     * This method returns the number of tasks in the list
     * @return the number of tasks in the list
     */
    public int getTotalTask() {
        return this.list.size();
    }

    /**
     * This method removes the specified task from the list
     * @param index the position of the task to be removed - 1 (e.g task3 has index 2)
     */
    public void delete(int index) {
        this.list.remove(index);
    }

    @Override
    public String toString() {
        String output = "";
        for (int i = 0; i < this.list.size() - 1; i++) {
            output = output + (i + 1) + ". " + this.list.get(i) + "\n";
        }
        if (!this.list.isEmpty()) {
            output += this.list.size() + ". " + this.list.getLast();
        }
        return output;
    }
}
