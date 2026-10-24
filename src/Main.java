import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.print("Enter first number: ");
        int a = input.nextInt();
        System.out.print("Enter second number: ");
        int b = input.nextInt();

        Calculator calculator = new Calculator(new AddStrategy());
        System.out.println(a + "+" + b + "=" + calculator.calculate(a, b));
        calculator.setStrategy(new MultiplyStrategy());
        System.out.println(a + "*" + b + "=" + calculator.calculate(a, b));
        calculator.setStrategy(new SubstractStrategy());
        System.out.println(a + "-" + b + "=" + calculator.calculate(a, b));
    }
}
