public class Greeting {
    private String name;

    public Greeting(String name) {
        this.name = name;
    }

    //This method is to say Hi
    public void sayHi() {
        System.out.println("____________________________________________________________");
        System.out.println(" Hello! I'm " + this.name);
        System.out.println(" What can I do for you?");
        System.out.println("____________________________________________________________");
    }

    //This method is to say goodbye
    public void sayBye() {
        System.out.println(" Bye. Hope to see you again soon!");
        System.out.println("____________________________________________________________");
    }
}
