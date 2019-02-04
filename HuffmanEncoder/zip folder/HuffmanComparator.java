import java.util.Comparator;

// Class made to compare the values in the node and decide where they go in the Priority Queue
public class HuffmanComparator implements Comparator<Node> { //[1] Referenced on website in commentary
    public int compare(Node x, Node y) {
        return x.getData() - y.getData();
    }
}
