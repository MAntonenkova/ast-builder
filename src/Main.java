import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import node.Node;

public class Main {

  private static final String EXIT_COMMAND = "exit";
  private static final String CALCULATE_COMMAND = "calc";
  private static final String PRINT_AST_TREE_COMMAND = "print";

  public static void main(String[] args) {

    Scanner scanner = new Scanner(System.in);
    System.out.println("Please enter expression (example: (12 + x) * 23 + y):");
    String expression = scanner.nextLine();

    Set<String> variables = findVariables(expression);
    Map<String, Integer> variableToValue = setRandomValues(variables);

    Parser parser = new Parser(expression);
    Node root = parser.parse();

    System.out.println("AST tree:");
    root.printTree("");

    Integer result = root.calculate(variableToValue);

    System.out.println("Values of variables:");
    for (Map.Entry<String, Integer> e : variableToValue.entrySet()) {
      System.out.println(e.getKey() + " = " + e.getValue());
    }

    System.out.println("Calculation result: " + result);

    while (true) {
      System.out.println("Press \"calc\" to recalculate, \"print\" to build AST tree or \"exit\" to exit");
      scanner = new Scanner(System.in);
      String command = scanner.nextLine();

      if (EXIT_COMMAND.equals(command)) {
        break;
      }
      if (CALCULATE_COMMAND.equals(command)) {
         result = root.calculate(variableToValue);
        System.out.println("Calculation result: " + result);
      }
      if (PRINT_AST_TREE_COMMAND.equals(command)) {
        root.printTree("");
      }
      // TODO - распарсить команду, если она на присвоение новых значений переменным
    }
  }

  private static Set<String> findVariables(String expression) {
    Set<String> variables = new HashSet<>();
    // TODO - поменять парсинг переменных на более сложный - вида xmstf423
    Matcher matcher = Pattern.compile("[a-zA-Z][a-zA-Z0-9]*").matcher(expression);
    while (matcher.find()) {
      variables.add(matcher.group());
    }
    return variables;
  }

  private static Map<String, Integer> setRandomValues(Set<String> variables) {
    Map<String, Integer> variableToValue = new HashMap<>();
    Random rand = new Random();
    for (String var : variables) {
      int value = rand.nextInt(100) + 1;
      variableToValue.put(var, value);
    }
    return variableToValue;
  }
}