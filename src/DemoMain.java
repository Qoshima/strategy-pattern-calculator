import java.util.*;

public class DemoMain {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        Map<String, CalculationStrategy> strategy = Map.of(
                "+", new AddStrategy(),
                "-", new SubstractStrategy(),
                "*", new MultiplyStrategy()
        );
        System.out.print("Enter first number: ");
        int a = input.nextInt();
        System.out.print("Enter second number: ");
        int b = input.nextInt();
        Calculator calculator = new Calculator(new AddStrategy());

        for (int i = 0; i <= 2; i++) {
            System.out.print("Enter operation (+, -, *): ");
            String operation = input.next();

            calculator.setStrategy(
                    Objects.requireNonNull(strategy.get(operation), "Invalid operation")
            );
            System.out.println(a + operation + b + "=" + calculator.calculate(a, b));
        }
        input.close();
    }
}
