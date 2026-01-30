public class List {
    private String[] list;
    private int index;

    public List() {
        this.list = new String[100];
        this.index = 0;
    }

    //this adds something into the list
    public void add(String item) {
        this.list[index] = item;
        this.index++;
        System.out.println("added: " + item);
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
