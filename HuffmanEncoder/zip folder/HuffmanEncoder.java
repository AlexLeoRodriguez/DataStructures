import java.io.*;
import java.util.*;
public class HuffmanEncoder implements HuffmanCoding {
	private String hash[] = new String[256];
	
	public HuffmanEncoder(){}
	
	//take a file as input and create a table with characters and frequencies
	//print the characters and frequencies
	public String getFrequencies(File inputFile) throws FileNotFoundException {
		Scanner s;
		int counter[] = new int[256]; // 256 is the size that uses all the ascii values
		try{
			s = new Scanner(inputFile); 	
		}
		catch (FileNotFoundException e) {
			throw new FileNotFoundException("File not found."); // file doesn't exist
		}
		while(s.hasNextLine()){
			String n = s.nextLine();
			for(int i = 0; i < n.length(); i++){ //An int[] makes all indexes 0 when created.
				counter[n.charAt(i)]++;
			}
		}
		s.close();
		String end = ""; //needs to start empty
		for(int i = 0; i < 256; i++){ //if a value in my array(hash) > 0, its in the file
			if (counter[i] > 0){
				//(char)i return the ascii character
				end += (char)i + " " + counter[i] + "\n"; 
			}
		}
		return end;
	}

	//take a file as input and create a Huffman Tree
	public HuffTree buildTree(File inputFile) throws FileNotFoundException, Exception {
		Scanner s;
		int counter[] = new int[256];
		boolean multiChar = false;
		try{
			s = new Scanner(inputFile); 	
		}
		catch (FileNotFoundException e){
			throw new FileNotFoundException("File not found."); // file doesn't exist
		}
		int test = -1;
		int previous = -1;
		while(s.hasNextLine()){
			String n = s.nextLine();
			for(int i = 0; i < n.length(); i++){ //An int[] makes all indexes 0 when created.
				//(char)i return the ascii character
				test = (int)n.charAt(i);
				if (previous != -1 && test != previous) {
					multiChar = true;
				}
				counter[n.charAt(i)]++;
				previous = (int)n.charAt(i);
			}
		}
		s.close();
		if(multiChar) { // Restricts Huffman Tree to x > 1 characters.
			HuffTree tree = new HuffTree(counter);
			return tree;
		}
		else { //  x(# of nodes) < 2  => does not make a tree
			throw new Exception("ERROR: There are either 0 or 1 character types. Tree cannot be made.");
		}
	}



	//take a file and a HuffTree and encode the file.
	//output a string of 1's and 0's representing the file
	public String encodeFile(File inputFile, HuffTree huffTree) throws FileNotFoundException{
		String code = "";
		Scanner s;
		try{
			s = new Scanner(inputFile); // read
		}
		catch (FileNotFoundException e){
			throw new FileNotFoundException("File not found.");
		}
		clearHash(); 
		getCodes(huffTree.getRoot(), ""); // set codes to universal array
		while(s.hasNextLine()){
			String n = s.nextLine();
			for(int i = 0; i < n.length(); i++) {
				// code is the collection of binary values from the HuffTree
				code += this.hash[n.charAt(i)];
			}
		}
		s.close();
		clearHash();
		return code;
	}

	//take a String and HuffTree and output the decoded words
	public String decodeFile(String code, HuffTree huffTree) throws Exception {
		if (huffTree.getRoot() == null) {
			throw new Exception("ERROR: huffTree is empty");
		}
		Node node = huffTree.getRoot();
		String words = "";
		for(int i = 0; i < code.length(); i++) {
			if(code.charAt(i) == '0') {
				node = node.getLeft();
			}
			else if(code.charAt(i) == '1') {
				node = node.getRight();
			}
			else {
				throw new Exception("ERROR: Input value is not Binary.");
			}

			if (node.getLeft() == null && node.getRight() == null) {
				words += node.getChar();
				node = huffTree.getRoot();
			}
		}
		return words;
	}

	//print the characters and their codes
	public String traverseHuffmanTree(HuffTree huffTree) throws Exception{ 
		if (huffTree.getRoot() == null) {
			throw new Exception("ERROR: huffTree is empty");
		}
		String end = "";
		clearHash();
		getCodes(huffTree.getRoot(), ""); //set codes to universal array
		
		for(int i = 0; i < this.hash.length; i++) { // constant because of predefined size
			if(this.hash[i].length() > 0) {
				end += (char)i + " " + this.hash[i] + "\n";
			}
		}
		
		clearHash();
		return end;
	}
	
	// Recursive algorithm that gets all the character's binary codes
	private void getCodes(Node x, String binary) { 
		if (x.getLeft() == null && x.getRight() == null) { // Base case (child)
			this.hash[x.getChar()] = binary;
			return;
		}
		else {
			getCodes(x.getLeft(), binary + "0"); // move left
			getCodes(x.getRight(), binary + "1"); // move right
		}
	}
	
	//Makes empty strings in the string array 
	private void clearHash() {
		for(int i = 0; i < this.hash.length; i++){
			this.hash[i] = "";
		}
	}
}
