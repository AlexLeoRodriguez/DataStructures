import java.util.LinkedList;
import java.util.Stack;
import java.lang.Math;

public class SparseMatrix implements SparseInterface{
	 public class Node {
		  private int row;
		  private int col;
		  private int data;
	
		  public Node(int row, int col, int data) {
		    this.row = row;
		    this.col = col;
		    this.data = data;
		  }
	
		  public int getData(){
		    return this.data;
		  }
	
		  public int getCol(){
		    return this.col;
		  }
	
		  public int getRow(){
		    return this.row;
		  }
	
		  public void setData(int n){
		    this.data = n;
		  }
	
		  public void setCol(int n){
		    this.col = n;
		  }
	
		  public void setRow(int n){
		    this.row = n;
		  }
		}
	 
  private int size = 0;
  private int listSize = 0;
  private int result = 0;

  private LinkedList<Node> List = new LinkedList<Node>();

  private boolean errorCheck(int row, int col) {
    if (row >= this.size || row < 0 || col >= this.size || col < 0) { //test if out of bounds
      return true;
    }
    return false;
  }
  private int getListSize() { // returns the size of the list
	  return listSize;
  }

     public void clear(){
       this.List.clear(); // supposed to clear linked list
       this.listSize = 0;
     }

     public void setSize(int size){ // COME BACK TO LATER
    	 if (size <= 0){
    		 throw new RuntimeException("Invalid Matrix size");
    	 }
       clear();
       this.size = size;
     }

     public void addElement(int row, int col, int data){ // Always checks rows first
       if (errorCheck(row,col) != false) {
    	   throw new RuntimeException("Invalid bounds");
       }
       else if (data == 0) { //test if out of bounds
           removeElement(row,col);
       }
       else if(this.listSize == 0){ // if there are no values in the list, add it immediately
         Node newNode = new Node(row,col,data);
         this.List.push(newNode);
         this.listSize++;
       }
       else {
    	   boolean done = false;
    	   for (int i = 0; i < this.listSize; i++){ // Test if row and col value already exist
    		   if (this.List.get(i).getRow() == row && this.List.get(i).getCol() == col){
				   done = true;
				   this.List.get(i).setData(data);
				   i = getListSize(); // found value so skip to the end of the loop
    		   }
    	   }
    	   if (!(done))  { // row and col value do not already exist
    		   boolean below = false; // node rows below input row
    	         boolean above = false; // node rows above input row
    	         boolean equal = false; // node rows equal to input row
    	         for(int i = 0; i < this.listSize; i++){ // finds test cases to organize list
    	            if (this.List.get(i).getRow() == row){
    	              equal = true; // found a row value equal
    	            }
    	            if (this.List.get(i).getRow() < row) {
    	              below = true;// found a row value less than
    	            }
    	            if(this.List.get(i).getRow() > row) {
    	              above = true; // found a row value greater than
    	            }
    	         }
    	         Stack<Node> S = new Stack<Node>(); // stack to help organize DELETE
    	         int index = -1; // to reference how many values need to be placed on Stack
    	         if(below && !(equal) && !(above)) { // below only: add to the end
    	           Node newNode = new Node(row, col, data);
    	           this.List.addLast(newNode);
    	           this.listSize++;
    	         }
    	         else if(above && !(equal) && !(below)) { // above only: add to the start
    	           Node newNode = new Node(row, col, data);
    	           this.List.addFirst(newNode);
    	           this.listSize++;
    	         }
    	         else if(!(above) && equal && !(below)){ // equal only
    	           index = -1;
    	           for(int i = 0; i < this.listSize; i++) {
    	             if (this.List.get(i).getCol() < col) { // oldRow < newRow
    	               index = i+1;
    	             }
    	           }
    	           if(index == -1) { // if still -1, all oldRow > newRow, place at start
    	             Node newNode = new Node(row, col, data);
    	             this.List.addFirst(newNode);
    	             this.listSize++;
    	           }
    	           else { // use index to place to stack
    	             for (int i = 0; i < index; i++) { // remove every leading value
    	               S.push(this.List.get(0));
    	               this.List.remove(0);
    	             }
    	             Node newNode = new Node(row, col, data); // add new value
    	             this.List.addFirst(newNode);
    	             this.listSize++;
    	             while (!(S.empty())) { //Put old values back
    	               this.List.add(0,S.pop());
    	             }
    	           }
    	         }
               // 2 or more test cases
    	         else if(above && equal && !(below)) {
    	           for(int i = 0; i < this.listSize; i++) {
    	             if (this.List.get(i).getRow() > row){
    	               i = this.listSize;
    	             }
    	             else if (this.List.get(i).getCol() < col) {
    	               index = i+1;
    	             }
    	           }
    	           if(index == -1) {
    	             Node newNode = new Node(row, col, data);
    	             this.List.addFirst(newNode);
    	             this.listSize++;
    	           }
    	           else {
    	             for (int i = 0; i < index; i++) { // remove every leading value
    	               S.push(this.List.get(0));
    	               this.List.remove(0);
    	             }
    	             Node newNode = new Node(row, col, data); // add new value
    	             this.List.addFirst(newNode);
    	             this.listSize++;
    	             while (!(S.empty())) { //Put old values back
    	               this.List.add(0,S.pop());
    	             }
    	           }
    	         }
    	         else if(below && equal && !(above)) {
    	           int start = -1;
    	           for(int i = 0; i < this.listSize; i++) {
    	             if(this.List.get(i).getRow() == row){
    	               start = i;
    	               i = this.listSize;
    	             }
    	           }
    	           for(int i = start;i < this.listSize; i++) {
    	             if((this.List.get(i).getCol() > col)) {
    	               index = i;
    	             }
    	           }
    	           if(index == -1) {
    	             for (int i = 0; i < start; i++) { // remove every leading value
    	               S.push(this.List.get(0));
    	               this.List.remove(0);
    	             }
    	             Node newNode = new Node(row, col, data); // add new value
    	             this.List.addFirst(newNode);
    	             this.listSize++;
    	             while (!(S.empty())) { //Put old values back
    	               this.List.add(0,S.pop());
    	             }
    	           }
    	           else {
    		             for (int i = 0; i < index; i++) { // remove every leading value
    		               S.push(this.List.get(0));
    		               this.List.remove(0);
    		             }
    		             Node newNode = new Node(row, col, data); // add new value
    		             this.List.addFirst(newNode);
    		             this.listSize++;
    		             while (!(S.empty())) { //Put old values back
    		               this.List.add(0,S.pop());
    		             }
    		           }
    	           }
    	         else if (above && below && !(equal)){
    	      	   for(int i = 0; i < this.listSize; i++) {
    	      		   if(this.List.get(i).getRow() > row) {
    	      			   index = i;
    	      			   i = getListSize();
    	      		   }
    	      	   }
    	      	   if(index == -1) {
    	      		 throw new RuntimeException("Something went wrong in above and below");
    	      	   }
    	      	   else {
    	      		 for (int i = 0; i < index; i++) { // remove every leading value
    	               S.push(this.List.get(0));
    	               this.List.remove(0);
    	             }
    	             Node newNode = new Node(row, col, data); // add new value
    	             this.List.addFirst(newNode);
    	             this.listSize++;
    	             while (!(S.empty())) { //Put old values back
    	               this.List.add(0,S.pop());
    	             }
    	      	   }
    	         }
               // All 3 cases
               else if(above && equal && below) {
                 int start = -1;
                 for(int i = 0; i < this.listSize; i++) {
                   if(this.List.get(i).getRow() == row){
                     start = i;
                     i = this.listSize;
                   }
                 }
                 for(int i = start; i < this.listSize; i++) {
                   if (this.List.get(i).getRow() > row){
                     i = this.listSize;
                   }
                   else if (this.List.get(i).getCol() < col) {
                     index = i+1;
                   }
                 }
                 if(index == -1) {
                   for (int i = 0; i < start; i++) { // remove every leading value
                     S.push(this.List.get(0));
                     this.List.remove(0);
                   }
                   Node newNode = new Node(row, col, data); // add new value
                   this.List.addFirst(newNode);
                   this.listSize++;
                   while (!(S.empty())) { //Put old values back
                     this.List.add(0,S.pop());
                   }
                 }
                 else {
                   for (int i = 0; i < index; i++) { // remove every leading value
                     S.push(this.List.get(0));
                     this.List.remove(0);
                   }
                   Node newNode = new Node(row, col, data); // add new value
                   this.List.addFirst(newNode);
                   this.listSize++;
                   while (!(S.empty())) { //Put old values back
                     this.List.add(0,S.pop());
                   }
                 }
               }
    	         else {
    	           throw new RuntimeException("Something went wrong.");
    	         }
    	   }
        }
       }

     public void removeElement(int row, int col){
       if (errorCheck(row,col) != false) { //test if out of bounds
    	   throw new RuntimeException("Invalid bounds");
       }
       else {
         int index = -1;
         for(int i = 0; i < getListSize(); i++){
           if (this.List.get(i).getRow() == row && this.List.get(i).getCol() == col){ // find row and col
               index = i;
               i = getListSize();
           }
         }
         if (index == -1){
        	 // Do nothing (there was nothing in the list anyway)
         }
         else {
           this.List.remove(index);
           this.listSize--;
         }
       }
     }

     public int getElement(int row, int col){
      if (errorCheck(row,col) != false) { //test if out of bounds
    	  throw new RuntimeException("Invalid bounds");
      }
      int index = -1;
      for(int i = 0; i < getListSize(); i++){ // GO THROUGH LIST
        if (this.List.get(i).getRow() == row && this.List.get(i).getCol() == col){
	    	index = i;
	    	i = getListSize();
        }
      }
      if (index == -1){ //if not in list, value = 0
        return 0;
      }
      else { //else, value found
        return this.List.get(index).getData();
      }
     }

     public int determinant(){ //THIS IS RECURSIVE]
       if (getSize() == 1){ // Needs base case (SIZE = 1)
         if(getListSize() < 1){
           return 0;
         }
         else {
           return this.List.get(0).getData();

         }
       }
       double exp;
       int data;
       for(int loop = 0; loop < getSize(); loop++) {
         data = 0;
         exp = loop + (getSize() - 1);
         for(int i = 0; i < getListSize(); i++){
	    	 if (this.List.get(i).getRow() == loop && this.List.get(i).getCol() == (getSize() - 1)) {
               data = this.List.get(i).getData();
	    	 }
	    	 if (this.List.get(i).getRow() > loop) {
	    		 i = getListSize();
	    	 }
         }
         this.result += (int)Math.pow(-1,exp) * data * minor(loop,(getSize() - 1)).determinant();
       }
       int answer = this.result;
       this.result = 0;
       return answer;
     }

     public SparseInterface minor(int row, int col){
         if (errorCheck(row,col) != false) { //test if out of bounds
      	   throw new RuntimeException("Invalid bounds");
         }
         SparseMatrix minor = new SparseMatrix();
         minor.setSize(getSize() - 1);
         for(int i = 0; i < this.listSize; i++) {
        	 if((this.List.get(i).getRow() > row) && (this.List.get(i).getCol() > col)) {
        		 minor.addElement((this.List.get(i).getRow() - 1), (this.List.get(i).getCol() - 1), this.List.get(i).getData()); //save
        	 }
        	 else if((this.List.get(i).getRow() < row) && (this.List.get(i).getCol() > col)) {
        		 minor.addElement(this.List.get(i).getRow(), (this.List.get(i).getCol() - 1), this.List.get(i).getData()); //save
        	 }
        	 else if((this.List.get(i).getRow() > row) && (this.List.get(i).getCol() < col)) {
        		 minor.addElement((this.List.get(i).getRow() - 1), this.List.get(i).getCol(), this.List.get(i).getData());
        	 }
        	 else if((this.List.get(i).getRow() < row) && (this.List.get(i).getCol() < col)) {
        		 minor.addElement(this.List.get(i).getRow(), this.List.get(i).getCol(), this.List.get(i).getData());
        	 }
         }
       return minor;
     }

     public String toString(){ //save values inside the LinkedList to string
    	 //Assume list is already in order
    	 String answer = "";
    	 for(int i = 0; i < this.listSize; i++){
             answer += (this.List.get(i).getRow() + " " + this.List.get(i).getCol() + " " + this.List.get(i).getData() + "\n");
           }
    	 return answer;
     }

     public int getSize(){  //returns the size of the n x n matrix
      return this.size;
     }

     public SparseMatrix(){ // Constructor
      this.size = 5;
     }
}