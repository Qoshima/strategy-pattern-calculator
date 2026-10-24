import java.util.*;

public class DemoMain {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        Map<String, CalculationStrategy> strategies = Map.of(
                "+", new AddStrategy(),
                "-", new SubstractStrategy(),
                "*", new MultiplyStrategy()
        );
        try{
            System.out.print("Enter first number: ");
            int a = input.nextInt();
            System.out.print("Enter second number: ");
            int b = input.nextInt();
            Calculator calculator = new Calculator(new AddStrategy());

            while(true) {
                System.out.print("Enter operation (+, -, *, exit): ");
                String operation = input.next();

                if(operation.equalsIgnoreCase("exit")) { //this condition controlling only for loop
                    break;
                }

                CalculationStrategy strategy = strategies.get(operation); //choosing an algorithm without conditional branching

                if(strategy == null) { //this condition check for errors
                    System.out.println("Invalid operation, try again");
                    continue;
                }

                calculator.setStrategy(strategy);
                System.out.println(a + operation + b + "=" + calculator.calculate(a, b));
            }
        } catch (InputMismatchException e) {
            System.out.println("Error: enter valid integers");
        }
        input.close();
    }
}
