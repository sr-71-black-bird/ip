import java.util.Scanner;

public class Christopher {
    public static void main(String[] args) {
        Greeting greeting = new Greeting("Christopher");
        TaskList taskList = new TaskList();
        Scanner scanner = new Scanner(System.in);

        greeting.sayHi();
        while (true) {
            String input = scanner.nextLine();
            if (input.equals("bye")) {
                greeting.sayBye();
                break;
            } else if (input.equals("list")) {
                System.out.println(taskList);
            } else {
                String[] newInput = input.split(" ");
                if (newInput[0].equals("mark")) {
                    System.out.println("Nice! I've marked this task as done:");
                    int index = Integer.parseInt(newInput[1]) - 1;
                    taskList.complete(index);
                    Task tmp = taskList.getTask(index);
                    System.out.println(tmp);
                } else if (newInput[0].equals("unmark")) {
                    System.out.println("Ok, I've marked this task as not done yet: ");
                    int index = Integer.parseInt(newInput[1]) - 1;
                    taskList.undoComplete(index);
                    Task tmp = taskList.getTask(index);
                    System.out.println(tmp);
                } else {
                    taskList.add(new Task(input));
                }
            }
        }

    }
}
