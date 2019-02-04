//Self explanatory 
public class Node {
  private int data;
  private char c;
  private Node left;
  private Node right;

  public Node() {
    this.left = null;
    this.right = null;
  }
  
  public void setData(int x) {
    this.data = x;
  }

  public void setChar(char x){
    this.c = x;
  }

  public void setLeft(Node x) {
    this.left = x;
  }

  public void setRight(Node x) {
    this.right = x;
  }

  public int getData() {
    return this.data;
  }

  public char getChar() {
    return this.c;
  }

  public Node getLeft() {
    return this.left;
  }

  public Node getRight() {
    return this.right;
  }
}
