package node;

import java.util.Map;

public class OperationNode extends Node {

   String operation;
  Node left, right;
  private static final String OFFSET = "    ";

  public OperationNode(String operation, Node left, Node right) {
    this.operation = operation;
    this.left = left;
    this.right = right;
  }

  public Integer calculate(Map<String, Integer> variables) {
    int left = this.left.calculate(variables);
    int right = this.right.calculate(variables);
    switch (operation) {
      case "+":
        return left + right;
      case "-":
        return left - right;
      case "*":
        return left * right;
      case "/":
        return left / right;
      default:
        throw new RuntimeException("Unsupported operation: " + operation);
    }
  }

  @Override
  public void printTree(String prefix) {
    System.out.println(prefix + "Operation: " + operation);
    // Выводим левый и правый узлы с увеличенным отступом
    if (left != null) {
      System.out.println(prefix + "├──");
      left.printTree(prefix + "│   ");
    }
    if (right != null) {
      System.out.println(prefix + "└──");
      right.printTree(prefix + OFFSET);
    }
  }
}
