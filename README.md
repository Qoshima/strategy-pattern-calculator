# Calculator Strategy Design Pattern
## 1. Project Overview
This project demonstrates the Strategy Design Pattern in Java through a simple calculator.

The calculator supports three mathematical operations: addition, substraction, and multiplication.

Each operation is implemented as a separate strategy. The ``Calculator`` class acts as the Context and delegates calculations to the selected strategy.

The project includes two client implementations to demonstrate different approaches to runtime strategy switching.

## 2. Project Structure
```
src/
-Main.java (client)
-DemoMain.java (client)
-Calculator.java (context)
-CalculationStrategy.java (strategy interface)
-AddStrategy.java (concrete strategy)
-SubstractStrategy.java (concrete strategy)
-MultipleStrategy.java (concrete strategy)
```
``Main`` demonstrates direct strategy switching

``DemoMain`` allows interactive strategy selection using Map

## 3. Strategy Pattern Implementation
the CalculationStrategy interface declares a common method:

````int calculate(int a, int b);````

Each concrete strategy implements its own mathematical operation.

The ``Calculator`` class holds a reference to ``CalculationStrategy`` and supports runtime switching using:

```
calculator.setStrategy(strategy);
```

Thus allows the calculator to change its behavior without modifying its internal implementation.

## 4. Two Client Implementations
#### ``Main.java`` - Direct Strategy Switching
The first implementation demonstrates manual runtime strategy switching without a selection mechanism.

```
Calculator calculator = new Calculator(new AddStrategy());
calculator.setStrategy(new MultiplyStrategy());
calculator.setStrategy(new SubstractStrategy());
```
The program reads two integers and performs all three operations.

#### Example output
```
Enter first number: 3
Enter second number: 2
3+2=5
3*2=6
3-2=1
```
#### ``DemoMain.java`` Interactive Strategy Selection
The second implementation uses ``Scanner`` and ``Map`` to select strategies based on user input.
```
Map<String, CalculationStrategy> strategies = Map.of(
    "+", new AddStrategy(),
    "-", new SubstractStrategy(),
    "*", new MultiplyStrategy()
);
```
The selected strategy is retrieved without conditional branching:
```
CalculationStrategy strategy = strategies.get(operation);
calculator.setStrategy(strategy);
```
The program also supports invalid-operation handling and the ``exit`` command.
#### Example output
```
Enter first number: 3
Enter second number: 2
Enter operation (+, -, *, exit): +
3+2=5
Enter operation (+, -, *, exit): *
3*2=6
Enter operation (+, -, *, exit): /
Invalid operation, try again
Enter operation (+, -, *, exit): exit
```
## 5. How to Run
### Requirements:
- Java JDK 17 or newer
- Intellij IDEA or a terminal
### Using Intellij IDEA
1. Open the project in IntelliJ IDEA.
2. Configure the Project SDK.
3. Open Main.java or DemoMain.java.
4. Run the selected class using its main() method.
### Using Terminal
From the project root directory, compile the source files:

```javac -d out src/*.java```

Run the direct implementation:

```java -cp out Main```

Or run the interactive implementation:

```java -cp out DemoMain```

## 6. How to Add a New Strategy
The project can be extended without modifying the ``Calculator`` class or existing strategies.

For example, to add a new operation:
1. Create a new class implementing ``CalculationStrategy``.
2. Implement the ``calculate()`` method.
3. Register the new strategy in the client's ``Map`` if it should be available interactively

## 7. Clean Code Principles
The implementation demonstrates:
1. Single Responsibility Principle - Each strategy performs only one mathematical algorithm.
2. Meaningful Naming - Classes describe their responsibilities.
3. Polymorphism - Different algorithms share a common interface.
4. Encapsulation - The Context does not depend on concrete algorithm implementations.
5. Open/Closed Principle - New strategies can be added without modifying the Context or existing strategies.
## 8. Conclusion
This project demonstrates how the Strategy Pattern separates algorithms from the Context and enables runtime behavior changes.

Two client implementations show both direct strategy switching and interactive selection using a ``Map``, without using ``if-else`` or ``switch`` to select mathematical algorithms.

The result is a simple, flexible, and extensible calculator.