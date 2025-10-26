# AST Builder

## Requirements
- Java Development Kit (JDK) 8

## Build and Run

1. **Build the project:**
   ```bash
   javac -d out src/Main.java src/Parser.java src/node/*.java
   cd out
   jar cfe ast-builder.jar Main *.class node/*.class

2. **Run the project:**
 ```bash
   java -jar ast-builder.jar
```

## Usage

1. Enter a mathematical expression, for example: (12 + x) * 23 + y. Constraints:
    - values must be positive numbers up to 16 bits
    - the variable name can consist of Latin letters  and numbers, but must start with a letter
    - supported operations: addition (+), subtraction (-), multiplication (*), division (/)
2. After entering the expression, the program will:
    - assign random values to the variables
    - calculate and show the result of the expression

3. Available additional commands:
    - set a new value to a variable, for example: x=20
    - recalculate an expression: command "calc"
    - print the abstract syntax tree (AST): command "print"
    - exit: command "exit"