package christopher.task;

import java.util.ArrayList;

/**
 * Maintains information regarding a list of currently held tasks.
 */
public class TaskList {
    private ArrayList<Task> list;

    public TaskList(ArrayList<Task> list) {
        this.list = list;
    }

    /**
     * Takes in a task and add it to our taskList.
     *
     * @param task The task to be added into the list.
     */
    public void add(Task task) {
        this.list.add(task);
    }

    /**
     * Marks the task at the specified position to be "complete."
     *
     * @param index the position of the desired task - 1 (e.g task3 has index 2).
     */
    public void complete(int index) {
        this.list.get(index).complete();
    }

    /**
     * Marks the task at the specified position to be "incomplete."
     *
     * @param index the position of the desired task - 1 (e.g task3 has index 2).
     */
    public void undoComplete(int index) {
        this.list.get(index).undoComplete();
    }

    /**
     * Returns the task at the specified position.
     *
     * @param index index is one less than position (e.g task3 has index 2).
     * @return the object of the desired task.
     */
    public Task getTask(int index) {
        return this.list.get(index);
    }

    /**
     * Returns the number of tasks in the list.
     *
     * @return the number of tasks in the list.
     */
    public int getTotalTask() {
        return this.list.size();
    }

    /**
     * Removes the specified task from the list.
     *
     * @param index the position of the task to be removed - 1 (e.g task3 has index 2).
     */
    public void delete(int index) {
        this.list.remove(index);
    }

    public ArrayList<Task> getList() {
        return this.list;
    }

    /**
     * Finds the desired tasks based on keywords in an array form.
     *
     * @param keywords the specified keywords to look out for.
     * @return TaskList containing the list of desired task.
     */
    public TaskList find(String[] keywords) {
        TaskList result = new TaskList(new ArrayList<>());
        for (int i = 0; i < this.list.size(); i++) {
            Task tmp = this.getTask(i);
            String name = tmp.toString().toLowerCase();

            boolean matches = false;

            for (String keyword: keywords) {
                if (keyword.isBlank()) {
                    continue;
                }
                if (name.contains(keyword.toLowerCase())) {
                    matches = true;
                    break;
                }
            }

            if (matches) {
                result.add(tmp);
            }
        }
        return result;
    }

    @Override
    public String toString() {
        String output = "";
        for (int i = 0; i < this.list.size() - 1; i++) {
            output = output + (i + 1) + ". " + this.list.get(i) + "\n";
        }
        if (!this.list.isEmpty()) {
            output += this.list.size() + ". " + this.list.get(this.list.size() - 1);
        }
        return output;
    }
}
