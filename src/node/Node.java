package node;

import java.util.Map;

abstract public class Node {

  public abstract Integer calculate(Map<String, Integer> variables);

  public void printTree(String prefix) {
  }
}
