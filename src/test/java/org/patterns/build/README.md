# Builder Pattern

## Overview
The Builder Pattern is a creational design pattern that allows for the step-by-step construction of complex objects. It separates the construction of a complex object from its representation, allowing the same construction process to create different representations.

## Implementation in this Package
This package contains a simple implementation of the Builder Pattern:

- `Person`: The product class that is being built
- `Person.PersonBuilder`: A static nested builder class that constructs the Person object
- `BuilderPatternDemo`: A demonstration of how to use the builder pattern

## UML Diagram
A UML diagram of this implementation is provided in the `BuilderPatternUML.puml` file. This is a PlantUML file that can be rendered into a visual diagram.

### How to View the UML Diagram
There are several ways to view the PlantUML diagram:

1. **Online PlantUML Server**:
   - Visit [PlantUML Server](https://www.plantuml.com/plantuml/uml/)
   - Copy and paste the content of the `BuilderPatternUML.puml` file

2. **IDE Plugins**:
   - Many IDEs (IntelliJ IDEA, Eclipse, VS Code) have PlantUML plugins
   - Install the appropriate plugin for your IDE
   - Open the `BuilderPatternUML.puml` file in your IDE

3. **Command Line**:
   - Install PlantUML (requires Java and Graphviz)
   - Run: `java -jar plantuml.jar BuilderPatternUML.puml`

## Benefits of the Builder Pattern
- Allows you to create different representations of an object using the same construction code
- Isolates complex construction code from the business logic
- Provides better control over the construction process
- Makes the code more readable when creating objects with many parameters

## When to Use
- When the process of constructing an object is complex
- When an object needs to be created with many optional parameters
- When you want to create different representations of an object
- When you need immutable objects with a large number of fields