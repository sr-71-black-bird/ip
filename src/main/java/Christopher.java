import java.util.Scanner;

public class Christopher {
    public static void main(String[] args) {
        Greeting greeting = new Greeting("Christopher");
        TaskList taskList = new TaskList();
        Scanner scanner = new Scanner(System.in);

        greeting.sayHi();
        while (true) {
            String input = scanner.nextLine();
            String[] tmp = input.split(" ");
            String firstWord = tmp[0];
            if (firstWord.equals("bye")) {
                greeting.sayBye();
                break;
            } else if (firstWord.equals("list")) {
                System.out.println(taskList);
            } else if (firstWord.equals("todo")) {
                String toDoInput = input.substring(input.indexOf(" ") + 1).trim();
                ToDo tmpToDo = new ToDo(toDoInput);
                System.out.println("Got it. I've added this task:");
                System.out.println(tmpToDo);
                taskList.add(tmpToDo);
                System.out.println("Now you have " + taskList.getTotalTask() + " tasks");
            } else if (firstWord.equals("deadline")) {
                String[] deadlineInput = input.split(" /by ");
                String deadlineName = deadlineInput[0].substring(deadlineInput[0].indexOf(" ") + 1).trim();
                Deadline tmpDeadline = new Deadline(deadlineName, deadlineInput[1]);
                System.out.println("Got it. I've added this task:");
                System.out.println(tmpDeadline);
                taskList.add(tmpDeadline);
                System.out.println("Now you have " + taskList.getTotalTask() + " tasks");
            } else if (firstWord.equals("event")) {
                String[] eventInput = input.split("/event | /from | /to ");
                String eventName = eventInput[0].substring(eventInput[0].indexOf(" ") + 1).trim();
                Event tmpEvent = new Event(eventName, eventInput[1], eventInput[2]);
                System.out.println("Got it. I've added this task:");
                System.out.println(tmpEvent);
                taskList.add(tmpEvent);
                System.out.println("Now you have " + taskList.getTotalTask() + " tasks");
            } else if (firstWord.equals("mark")) {
                System.out.println("Nice! I've marked this task as done:");
                int index = Integer.parseInt(tmp[1]) - 1;
                taskList.complete(index);
                Task tmpTask = taskList.getTask(index);
                System.out.println(tmpTask);
            } else if (firstWord.equals("unmark")) {
                System.out.println("Ok, I've marked this task as not done yet: ");
                int index = Integer.parseInt(tmp[1]) - 1;
                taskList.undoComplete(index);
                Task tmpTask = taskList.getTask(index);
                System.out.println(tmpTask);
            }
        }

    }
}
