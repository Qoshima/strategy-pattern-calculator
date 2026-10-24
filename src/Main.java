public class Main {
    public static void main(String[] args) {
        int a = 3;
        int b = 2;
        Calculator calculator = new Calculator(new AddStrategy());
        System.out.println(a + "+" + b + "=" + calculator.calculate(a, b));
        calculator.setStrategy(new MultiplyStrategy());
        System.out.println(a + "*" + b + "=" + calculator.calculate(a, b));
        calculator.setStrategy(new SubstractStrategy());
        System.out.println(a + "-" + b + "=" + calculator.calculate(a, b));
    }
}
