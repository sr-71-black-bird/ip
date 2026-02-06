package christopher.ui;

/**
 * This class is for generating the standard greetings and goodbyes the bot may say to the user
 */
public class Greeting {
    private String name;

    public Greeting(String name) {
        this.name = name;
    }

    /**
     * Bot uses this once at the start
     * Says Hi to the user
     */
    public void sayHi() {
        System.out.println("____________________________________________________________");
        System.out.println(" Hello! I'm " + this.name);
        System.out.println(" What can I do for you?");
        System.out.println("____________________________________________________________");
    }

    /**
     * Bot users this once at the end when user input 'bye'
     * Uses to say goodbye to the user
     */
    public void sayBye() {
        System.out.println("____________________________________________________________");
        System.out.println(" Bye. Hope to see you again soon!");
        System.out.println("____________________________________________________________");
    }

    /**
     * Regurgitates the command types by the user
     * @param s this is a command by the user
     */
    public void echo(String s) {
        System.out.println("____________________________________________________________");
        System.out.println(" " + s);
        System.out.println("____________________________________________________________");
    }
}
