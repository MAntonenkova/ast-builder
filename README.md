# AST Builder

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

1. Enter a mathematical expression, for example: (12 + x) * 23 + y
    Constraints:
    - values must be positive integers up to 16 bits
    - variables can consist of digits and Latin letters, but must start with a letter
    - supported operations: addition (+), subtraction (-), multiplication (*), division (/)
2. After entering the expression, the program will:
    - display the abstract syntax tree (AST)
    - assign random values to the variables
    - calculate and show the result of expression

3. Then you can:
   Set variable value, for example: x=20
   Recalculate expression: calc
   Show AST: print
   Exit: exit