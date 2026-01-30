public class TaskList {
    private Task[] list;
    private int index;

    public TaskList() {
        this.list = new Task[100];
        this.index = 0;
    }

    //this adds something into the list
    public void add(Task task) {
        this.list[index] = task;
        this.index++;
        System.out.println("added: " + task);
    }

    //this completes the task at given index
    public void complete(int index) {
        this.list[index].complete();
    }

    //this uncompletes the task at given index
    public void undoComplete(int index) {
        this.list[index].undoComplete();
    }

    public Task getTask(int index) {
        return this.list[index];
    }

    @Override
    public String toString() {
        String output = "";
        for (int i = 0; i < this.index - 1; i++) {
            output = output + (i + 1) + ". " + list[i] + "\n";
        }
        output += (this.index) + ". " + list[index - 1];
        return output;
    }
}
