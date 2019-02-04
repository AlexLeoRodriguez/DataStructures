import java.util.PriorityQueue;
public class HuffTree {
	
  private Node root; // Node that will direct to the rest of the nodes
  private PriorityQueue<Node> minHeap; // heap that will help plug in the nodes to the Tree

  public HuffTree(int[] keys) { //constructor
    createTree(keys);
  }
  
  public Node getRoot() {
	    return root;
	  }

  //Creates and organizes the tree ([1] Referenced website in commentary)
  private void createTree(int[] keys) { //size of keys will always be constant(256) in this assignment
	  int count = 0;
	  for (int i = 0; i < keys.length; i++){ // constant because the length
		 if  (keys[i] > 0){
			 count++;
		 }
	  }
	//size of keys will always be constant in this assignment, but changed for future use
	  minHeap = new PriorityQueue<Node>(count, new HuffmanComparator()); 
    for(int i = 0; i < keys.length; i++){ 
      if (keys[i] > 0){ // if there is a leaf, add it
        Node n = new Node();
        n.setData(keys[i]);
        n.setChar((char)i);
        minHeap.add(n); // add to priority queue
      }
    }
	while(minHeap.size() > 1) { // When there is only 1 node left, Tree is done
		Node x = minHeap.peek();
		minHeap.poll();
		Node y = minHeap.peek();
		minHeap.poll();
		
		Node n = new Node();//create internal node
		
		//set values
		n.setData(x.getData() + y.getData());
		n.setLeft(x);
		n.setRight(y);
		
		//make root the largest node in the heap
		this.root = n; 
		minHeap.add(n);
    }
  }
}
