package christopher.ui;

import java.io.IOException;
import java.time.format.DateTimeParseException;
import java.util.Scanner;
import java.util.ArrayList;

import christopher.storage.Storage;
import christopher.task.Deadline;
import christopher.task.Event;
import christopher.task.Task;
import christopher.task.TaskList;
import christopher.task.ToDo;
import christopher.task.WrongInstructionException;


/**
 * This is the chatbot, christopher.ui.Christopher, he will greet the user, and take commands until a bye was said
 */
public class Christopher {
    /*
    private Greeting greeting = new Greeting("christopher.ui.Christopher");
    private Storage storage = new Storage();
    private TaskList taskList = new TaskList(storage.load());
     */
    private final TaskList taskList;
    private final Storage storage;

    public Christopher() throws IOException, WrongInstructionException {
        this.storage = new Storage();
        this.taskList = new TaskList(this.storage.load());
    }

    public static void main(String[] args) throws IOException, WrongInstructionException {
        /*
        Greeting greeting = new Greeting("christopher.ui.Christopher");
        Storage storage = new Storage();
        TaskList taskList = new TaskList(storage.load());
         */

        Scanner scanner = new Scanner(System.in);
        //this.greeting.sayHi();
        /*
        outer:
        while (true) {
            /*
            String input = scanner.nextLine();
            String[] tmp = input.split(" ");
            Command command = Command.from(input);
            try {
                switch (command) {
                case TODO:
                    String toDoInput = input.substring(input.indexOf(" ") + 1).trim();
                    ToDo tmpToDo = new ToDo(toDoInput);
                    System.out.println("Got it. I've added this task:");
                    System.out.println(tmpToDo);
                    taskList.add(tmpToDo);
                    break;
                case DEADLINE:
                    String[] deadlineInput = input.split(" /by ");
                    String deadlineName = deadlineInput[0].substring(deadlineInput[0].indexOf(" ") + 1).trim();
                    Deadline tmpDeadline = new Deadline(deadlineName, deadlineInput[1]);
                    System.out.println("Got it. I've added this task:");
                    System.out.println(tmpDeadline);
                    taskList.add(tmpDeadline);
                    System.out.println("Now you have " + taskList.getTotalTask() + " tasks");
                    break;
                case BYE:
                    storage.save(taskList);
                    greeting.sayBye();
                    break outer;
                case LIST:
                    System.out.println(taskList.toString());
                    break;
                case EVENT:
                    String[] eventInput = input.split("/event | /from | /to ");
                    String eventName = eventInput[0].substring(eventInput[0].indexOf(" ") + 1).trim();
                    Event tmpEvent = new Event(eventName, eventInput[1], eventInput[2]);
                    System.out.println("Got it. I've added this task:");
                    System.out.println(tmpEvent);
                    taskList.add(tmpEvent);
                    System.out.println("Now you have " + taskList.getTotalTask() + " tasks");
                    break;
                case MARK:
                    System.out.println("Nice! I've marked this task as done:");
                    int index = Integer.parseInt(tmp[1]) - 1;
                    taskList.complete(index);
                    Task tmpTask = taskList.getTask(index);
                    System.out.println(tmpTask);
                    break;
                case UNMARK:
                    System.out.println("Ok, I've marked this task as not done yet: ");
                    int unmarkIndex = Integer.parseInt(tmp[1]) - 1;
                    taskList.undoComplete(unmarkIndex);
                    Task unmarkTask = taskList.getTask(unmarkIndex);
                    System.out.println(unmarkTask);
                    break;
                case DELETE:
                    System.out.println("Noted. I've removed this task:");
                    int deleteIndex = Integer.parseInt(tmp[1]) - 1;
                    Task deleteTask = taskList.getTask(deleteIndex);
                    taskList.delete(deleteIndex);
                    System.out.println(deleteTask);
                    System.out.println(String.format("Now you have %d tasks in the list.", taskList.getTotalTask()));
                    break;
                case FIND:
                    System.out.println("Here are the matching tasks in your list");
                    System.out.println(taskList.find(tmp));
                    break;
                default:
                    System.out.print("Unknown command, please try again\n");
                    break;
                }
            } catch (WrongInstructionException e) {
                System.out.println(e);
            } catch (ArrayIndexOutOfBoundsException e) { //this is for when_todo or deadline has no-body
                System.out.println("You instruction is incomplete, please try again");
            } catch (DateTimeParseException e) {
                System.out.println(e.getMessage());
                System.out.println("Ensure that you input your date, time in the format yyyy-MM-dd HH:mm");
            }
             */
        }


    /**
     * Generates a response for the user's chat message
     */
    public String getResponse(String input) {
        String[] tmp = input.split(" ");
        Command command = Command.from(input);
        String result;
        try {
            switch (command) {
                case TODO:
                    String toDoInput = input.substring(input.indexOf(" ") + 1).trim();
                    ToDo tmpToDo = new ToDo(toDoInput);
                    //System.out.println("Got it. I've added this task:");
                    //System.out.println(tmpToDo);
                    taskList.add(tmpToDo);
                    return String.format("Got it. I've added this task:\n%s", tmpToDo);
                case DEADLINE:
                    String[] deadlineInput = input.split(" /by ");
                    String deadlineName = deadlineInput[0].substring(deadlineInput[0].indexOf(" ") + 1).trim();
                    Deadline tmpDeadline = new Deadline(deadlineName, deadlineInput[1]);
                    //System.out.println("Got it. I've added this task:");
                    //System.out.println(tmpDeadline);
                    taskList.add(tmpDeadline);
                    //System.out.println("Now you have " + taskList.getTotalTask() + " tasks");
                    return String.format("Got it. I've added this task:\n%s\nNow you have %d tasks", tmpDeadline,
                            taskList.getTotalTask());
                case BYE:
                    //storage.save(taskList);
                    //greeting.sayBye();
                    return "Bye bye";
                    //break outer;
                case LIST:
                    System.out.println(taskList.toString());
                    return "test";
                case EVENT:
                    String[] eventInput = input.split("/event | /from | /to ");
                    String eventName = eventInput[0].substring(eventInput[0].indexOf(" ") + 1).trim();
                    Event tmpEvent = new Event(eventName, eventInput[1], eventInput[2]);
                    System.out.println("Got it. I've added this task:");
                    System.out.println(tmpEvent);
                    taskList.add(tmpEvent);
                    System.out.println("Now you have " + taskList.getTotalTask() + " tasks");
                    break;
                case MARK:
                    System.out.println("Nice! I've marked this task as done:");
                    int index = Integer.parseInt(tmp[1]) - 1;
                    taskList.complete(index);
                    Task tmpTask = taskList.getTask(index);
                    System.out.println(tmpTask);
                    break;
                case UNMARK:
                    System.out.println("Ok, I've marked this task as not done yet: ");
                    int unmarkIndex = Integer.parseInt(tmp[1]) - 1;
                    taskList.undoComplete(unmarkIndex);
                    Task unmarkTask = taskList.getTask(unmarkIndex);
                    System.out.println(unmarkTask);
                    break;
                case DELETE:
                    System.out.println("Noted. I've removed this task:");
                    int deleteIndex = Integer.parseInt(tmp[1]) - 1;
                    Task deleteTask = taskList.getTask(deleteIndex);
                    taskList.delete(deleteIndex);
                    System.out.println(deleteTask);
                    System.out.println(String.format("Now you have %d tasks in the list.", taskList.getTotalTask()));
                    break;
                case FIND:
                    System.out.println("Here are the matching tasks in your list");
                    System.out.println(taskList.find(tmp));
                    break;
                default:
                    System.out.print("Unknown command, please try again\n");
                    break;
            }
        } catch (WrongInstructionException e) {
            System.out.println(e);
        } catch (ArrayIndexOutOfBoundsException e) { //this is for when_todo or deadline has no-body
            System.out.println("You instruction is incomplete, please try again");
        } catch (DateTimeParseException e) {
            System.out.println(e.getMessage());
            System.out.println("Ensure that you input your date, time in the format yyyy-MM-dd HH:mm");
        }
        return "hello";
    }
}
