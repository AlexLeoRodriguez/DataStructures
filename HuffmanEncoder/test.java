import java.io.*;
import java.util.*;

public class test {
	
	public static void main(String args[]) throws Exception{
		HuffmanEncoder d = new HuffmanEncoder();
		String w = "";
		String tree = "";
		String code = "";
		String decode = "";
		File file;
		try{
			file = new File("/Users/AlexanderR/Desktop/HuffmanEncoder/shorterCode.txt");
			w = d.getFrequencies(file);
			HuffTree x = d.buildTree(file);
			if (x.getRoot() == null) {
				System.out.println("null");
			}
			tree = d.traverseHuffmanTree(x);
			code = d.encodeFile(file, x);
			decode = d.decodeFile(code, x);
			
		}
		catch (FileNotFoundException e){
			throw new FileNotFoundException();
		}
		System.out.println(w);
		System.out.println(tree);
		System.out.println(code);
		System.out.println(decode);
	}

}
