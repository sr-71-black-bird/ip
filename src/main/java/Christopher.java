import java.util.Scanner;

public class Christopher {
    public static void main(String[] args) {
        Greeting greeting = new Greeting("Christopher");
        List list = new List();
        Scanner scanner = new Scanner(System.in);

        greeting.sayHi();
        while (true) {
            String input = scanner.nextLine();
            if (input.equals("bye")) {
                greeting.sayBye();
                break;
            } else if (input.equals("list")) {
                System.out.println(list);
            } else {
                list.add(input);
            }
        }

    }
}
