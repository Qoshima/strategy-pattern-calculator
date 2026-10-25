# Assignment 5 — Strategy Pattern

Course: Software Design Patterns  
Student: Alimbekov Yernur  
Group: SE-2524

## 1. Introduction

This project demonstrates the Strategy design pattern using a simple Java calculator. The family of interchangeable algorithms consists of addition, subtraction, and multiplication. Each algorithm is implemented in a separate class that follows the `CalculationStrategy` interface.

The `Calculator` class acts as the Context: it stores a reference to a strategy, delegates the calculation, and allows the strategy to be changed at runtime. The project has two clients:

- `Main` demonstrates direct switching through `setStrategy()`.
- `DemoMain` accepts user input and selects a strategy using a `Map<String, CalculationStrategy>`.

This design avoids using `if` or `switch` to select the mathematical algorithm.

## 2. UML Class Diagram

![UML class diagram for the Strategy Pattern calculator](https://raw.githubusercontent.com/Qoshima/strategy-pattern-calculator/main/diagram.png)

The diagram shows both clients, the `Calculator` Context, the `CalculationStrategy` interface, and three concrete strategies. The concrete strategies realize the interface, while `Calculator` has an association with the interface because it stores a strategy reference. Each client depends on the Context.

## 3. Clean Code Principles

### 3.1. Replacing conditional selection with polymorphism

Annotated code (`DemoMain`):

```java
Map<String, CalculationStrategy> strategies = Map.of(
    "+", new AddStrategy(),
    "-", new SubstractStrategy(),
    "*", new MultiplyStrategy()
);

CalculationStrategy strategy = strategies.get(operation);
calculator.setStrategy(strategy);
```

Justification: A symbol is matched to a strategy through the map, not through an `if-else` or `switch` chain. The selected object's implementation of `calculate()` determines the operation through polymorphism. The `if` statements in `DemoMain` only handle `exit` and invalid input; they do not select an algorithm.

### 3.2. Meaningful and intention-revealing names

Annotated code:

```java
public class AddStrategy implements CalculationStrategy {
    @Override
    public int calculate(int a, int b) {
        return a + b;
    }
}
```

```java
public class MultiplyStrategy implements CalculationStrategy {
    @Override
    public int calculate(int a, int b) {
        return a * b;
    }
}
```

Justification: Names such as `AddStrategy`, `MultiplyStrategy`, `CalculationStrategy`, and `Calculator` describe each class's purpose. A reader can understand the design without inspecting every method body.

### 3.3. Single Responsibility Principle (SRP)

Annotated code (`AddStrategy`):

```java
public class AddStrategy implements CalculationStrategy {
    @Override
    public int calculate(int a, int b) {
        return a + b;
    }
}
```

Justification: Each concrete strategy performs one mathematical algorithm and returns a result. It does not read console input or print messages. Input and output belong to the client classes, while the `Calculator` delegates calculations. Thus, an output-format change does not require modifying any strategy.

### 3.4. Context depends on an abstraction

Annotated code (`Calculator`):

```java
private CalculationStrategy strategy;

public Calculator(CalculationStrategy strategy) {
    this.strategy = strategy;
}

public void setStrategy(CalculationStrategy strategy) {
    this.strategy = strategy;
}

public int calculate(int a, int b) {
    return strategy.calculate(a, b);
}
```

Justification: The Context stores the `CalculationStrategy` interface rather than a concrete `AddStrategy`, `SubstractStrategy`, or `MultiplyStrategy`. It does not contain the algorithms or inspect strategy types. This reduces coupling and makes runtime replacement possible.

### 3.5. Open/Closed Principle and extensibility

Proposed extension (not yet implemented):

```java
public class MaxStrategy implements CalculationStrategy {
    @Override
    public int calculate(int a, int b) {
        return Math.max(a, b);
    }
}
```

Justification: A new algorithm can be introduced by adding another class that implements `CalculationStrategy`. There is no need to change `Calculator` or any existing concrete strategy. To make the new algorithm available in the interactive client, its registration must be added to the `DemoMain` map. This changes client configuration, not the Context or existing algorithms.

## 4. Strategy vs. State

Strategy and State both use composition and interchangeable implementations behind an interface, but their intentions differ.

Strategy allows a client to choose which algorithm to use. In this calculator, `Main` or `DemoMain` explicitly selects addition, subtraction, or multiplication, and the Context delegates the operation.

State is used when an object's behavior depends on its current state, often with transitions between states during its lifecycle. This calculator has no state-driven transitions, so Strategy is the more suitable pattern.

## 5. Conclusion

The Strategy pattern makes the calculator flexible by separating mathematical algorithms from their usage. Both clients can switch operations at runtime while the Context continues to depend only on one interface. This design adds several small classes compared with a single calculator containing conditional branches, but it improves separation of responsibilities, readability, and extensibility. The `Map` implementation also demonstrates conditional-free algorithm selection from user input.

## 6. GitHub Repository

https://github.com/Qoshima/strategy-pattern-calculator
