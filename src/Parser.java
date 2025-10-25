import node.InvalidInputException;
import node.Node;
import node.NumberNode;
import node.OperationNode;
import node.VariableNode;

public class Parser {

  String input;
  int position = 0;
  public static final int MAX_16_BIT_VALUE = (int) Math.pow(2, 16);

  Parser(String input) {
    this.input = input.replaceAll("\\s+", "");
  }

  Node parse() {
    Node expr = parseExpression();
    if (position != input.length()) {
      throw new InvalidInputException(String.format("Invalid input: %s", input));
    }
    return expr;
  }

  Node parseExpression() {
    Node node = parseTerm();
    while (position < input.length() && (peek() == '+' || peek() == '-')) {
      char op = next();
      Node right = parseTerm();
      node = new OperationNode(String.valueOf(op), node, right);
    }
    return node;
  }

  Node parseTerm() {
    Node node = parseFactor();
    while (position < input.length() && (peek() == '*' || peek() == '/')) {
      char op = next();
      Node right = parseFactor();
      node = new OperationNode(String.valueOf(op), node, right);
    }
    return node;
  }

  private Node parseFactor() {
    if (peek() == '(') {
      next();
      Node node = parseExpression();
      if (peek() != ')') {
        throw new InvalidInputException("')' was expected");
      }
      next();
      return node;
    } else if (Character.isLetter(peek())) {
      String varName = parseVariable();
      return new VariableNode(varName);
    } else {
      return new NumberNode(parseNumber());
    }
  }

  private String parseVariable() {
    StringBuilder sb = new StringBuilder();
    while (position < input.length() && (Character.isLetterOrDigit(peek()))) {
      sb.append(next());
    }
    return sb.toString();
  }

  private int parseNumber() {
    StringBuilder sb = new StringBuilder();
    while (position < input.length() && (Character.isDigit(peek()) || peek() == '.')) {
      sb.append(next());
    }
    int number = Integer.parseInt(sb.toString());
    if (number > MAX_16_BIT_VALUE) {
      throw new InvalidInputException(String.format("Number must be less than 16 bits, number = %s", number));
    }
    return number;
  }

  char peek() {
    return input.charAt(position);
  }

  char next() {
    return input.charAt(position++);
  }
}
