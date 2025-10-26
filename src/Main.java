import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import node.InvalidInputException;
import node.Node;

public class Main {

  private static final String EXIT_COMMAND = "exit";
  private static final String CALCULATE_COMMAND = "calc";
  private static final String PRINT_AST_TREE_COMMAND = "print";
  private static final String VARIABLE_UPDATE_PATTERN = "^[a-zA-Z_][a-zA-Z0-9_]*\\s*=.*";
  private static final String VARIABLE_NAME_PATTERN = "[a-zA-Z][a-zA-Z0-9]*";
  private static final int MAX_RANDOM_VALUE = 100;
  private static final Scanner scanner = new Scanner(System.in);
  private static boolean isRunning = true;
  private static boolean isValidException = false;
  private static Node root = null;
  private static Map<String, Integer> variableToValue = new HashMap<>();

  public static void main(String[] args) {

    Integer result;
    while (!isValidException) {
      try {
        System.out.println("Please enter an expression (example: (12 + x) * 23 + y):");
        String expression = scanner.nextLine();
        Set<String> variables = findVariables(expression);
        variableToValue = setRandomValues(variables);

        Parser parser = new Parser(expression);
        root = parser.parse();
        isValidException = true;

        calculateAndPrintResult();
      } catch (InvalidInputException ex) {
        System.out.println(ex.getMessage());
      }
    }

    while (isRunning) {
      try {
        System.out.println("Press \"calc\" to recalculate, \"print\" to build AST tree, \"exit\" to exit" //2
          + " or enter a new value for a variable (example: x=20)");
        String command = scanner.nextLine().trim();

        if (command.matches(VARIABLE_UPDATE_PATTERN)) {
          updateVariableIfExists(command);
        } else if (EXIT_COMMAND.equals(command)) {
          isRunning = false;
        } else if (CALCULATE_COMMAND.equals(command)) {
          result = root.calculate(variableToValue);
          printVariableValues();
          System.out.println("Calculation result: " + result);
        } else if (PRINT_AST_TREE_COMMAND.equals(command)) {
          System.out.println("AST tree:");
          root.printTree("");
        } else {
          System.out.println("Unknown command");
        }
      } catch (InvalidInputException ex) {
        System.out.println(ex.getMessage());
      } catch (Exception ex) {
        System.out.println("Unexpected error: " + ex.getMessage());
      }
    }
  }

  private static void calculateAndPrintResult() {
    int result = root.calculate(variableToValue);
    printVariableValues();
    System.out.println("Calculation result: " + result);
  }

  private static void updateVariableIfExists(String command) {
    String[] parts = command.split("=", 2);
    String variableName = parts[0].trim();
    String variableValue = parts[1].trim();

    try {
      int value = Integer.parseInt(variableValue);
      if (variableToValue.containsKey(variableName)) {
        variableToValue.put(variableName, value);
        System.out.println("Set " + variableName + " = " + value);
      } else {
        System.out.println("Variable " + variableName + " not exists ");
      }
    } catch (NumberFormatException e) {
      System.out.println("Invalid value for variable: " + variableValue);
    }
  }

  private static void printVariableValues() {
    System.out.println("Values of variables:");
    for (Entry<String, Integer> e : variableToValue.entrySet()) {
      System.out.println(e.getKey() + " = " + e.getValue());
    }
  }

  private static Set<String> findVariables(String expression) {
    Set<String> variables = new HashSet<>();
    Matcher matcher = Pattern.compile(VARIABLE_NAME_PATTERN).matcher(expression);
    while (matcher.find()) {
      variables.add(matcher.group());
    }
    return variables;
  }

  private static Map<String, Integer> setRandomValues(Set<String> variables) {
    Map<String, Integer> variableToValue = new HashMap<>();
    Random rand = new Random();
    for (String var : variables) {
      int value = rand.nextInt(MAX_RANDOM_VALUE) + 1;
      variableToValue.put(var, value);
    }
    return variableToValue;
  }
}