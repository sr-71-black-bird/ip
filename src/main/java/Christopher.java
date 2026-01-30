import java.util.Scanner;

public class Christopher {
    public static void main(String[] args) {
        Greeting greeting = new Greeting("Christopher");
        TaskList taskList = new TaskList();
        Scanner scanner = new Scanner(System.in);



        greeting.sayHi();
        outer:
        while (true) {
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
                        greeting.sayBye();
                        break outer;
                    case LIST:
                        greeting.sayBye();
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
                    default:
                        System.out.print("Unknown command, please try again");
                }
            } catch (WrongInstructionException e) {
                System.out.println(e);
            } catch (ArrayIndexOutOfBoundsException e) { //this is for when_todo or deadline has no body
                System.out.println("You instruction is incomplete, please try again");
            }
        }

    }
}
