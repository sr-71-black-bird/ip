import java.util.Scanner;

public class Christopher {
    public static void main(String[] args) {
        Greeting greeting = new Greeting("Christopher");
        Scanner scanner = new Scanner(System.in);

        greeting.sayHi();
        while (true) {
            String input = scanner.nextLine();
            if (input.equals("bye")) {
                greeting.sayBye();
                break;
            }
            greeting.echo(input);
        }

    }
}
