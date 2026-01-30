import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> list;
    private int index;

    public TaskList() {
        this.list = new ArrayList<>();
        this.index = 0;
    }

    //this adds something into the list
    public void add(Task task) {
        this.list.add(task);
        this.index++;
    }

    //this completes the task at given index
    public void complete(int index) {
        this.list.get(index).complete();
    }

    //this uncompletes the task at given index
    public void undoComplete(int index) {
        this.list.get(index).undoComplete();
    }

    public Task getTask(int index) {
        return this.list.get(index);
    }

    //this method returns the total number of tasks
    public int getTotalTask() {
        return this.list.size();
    }

    //this method is for deleting a task
    public void delete(int index) {
        System.out.println("Noted. I've removed this task:");
        Task task = this.list.get(index);
        System.out.println(task);
        this.list.remove(index);
        System.out.println(String.format("Now you have %d tasks in the list.", this.list.size()));
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
