package node;

import java.util.Map;

public class NumberNode extends Node {

  int value;

  public NumberNode(int value) {
    this.value = value;
  }

  public Integer calculate(Map<String, Integer> variables) {
    return value;
  }

  @Override
  public void printTree(String prefix) {
    System.out.println(prefix + "Number: " + value);
  }
}
