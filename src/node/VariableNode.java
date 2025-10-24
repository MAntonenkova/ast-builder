package node;

import java.util.Map;

public class VariableNode extends Node {

  String name;

  public VariableNode(String name) {
    this.name = name;
  }

  public Integer calculate(Map<String, Integer> variables) {
    if (variables.containsKey(name)) {
      return variables.get(name);
    }
    throw new RuntimeException("Variable not found: " + name);
  }

  @Override
  public void printTree(String prefix) {
    System.out.println(prefix + "Variable: " + name);
  }
}
