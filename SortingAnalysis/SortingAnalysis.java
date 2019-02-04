/*
 Alexander Rodriguez
 Sorting Analysis
 ID:81135872
 */


import java.util.Scanner;
import java.io.*;

public class SortingAnalysis {
	static void readFile(int[] arr, String filename){
		try {
			File file = new File(filename);
			Scanner read = new Scanner(file);
			int i = 0;
			while (read.hasNextLine()){
				arr[i] = read.nextInt();
				i++;
			}
			read.close();
		} 
		catch (FileNotFoundException s) {
			System.out.println("File does Not Exist Please Try Again");
		}
	}
	//Tested
	static void bubbleSort(int[] arr) { // [2] Used reference from online (citation available on commentary)
		boolean swap = false;
		  for (int i = 0; i < arr.length-1; i++) {
		      for (int j = 0; j < arr.length-i-1; j++){ //keep j+1 from going out of bounds
		        if (arr[j] > arr[j+1]) {
		        	swap = true;
		            // swap
		            int temp = arr[j];
		            arr[j] = arr[j+1];
		            arr[j+1] = temp;
		        }
		      }
		      // added this part
		      if (swap) { // If no swap occurs in the array, everything is organized
		    	  swap = false;
		      }
		      else { // exit the loop
		    	  i = arr.length - 1;
		      }
		    }
		}
	// TESTED
	static void merge(int[] arr, int l, int m, int r) { // [1] Used reference online (cited in commentary)
	    // Find sizes of two smaller arrays to merge
	    int n1 = m - l + 1;
	    int n2 = r - m;
	
	    // Create temporary  arrays
	    int[] left = new int [n1];
	    int[] right = new int [n2];
	
	    //Copy data into previous arrays
	    for (int i=0; i<n1; ++i) {
	      left[i] = arr[l + i];
	    }
	
	    for (int j=0; j<n2; ++j) {
	      right[j] = arr[m + 1+ j];
	    }
	
	    // Initial indexes of first and second subarrays
	    int i = 0;
	    int j = 0;
	    int k = l; // initial index of merge subarray
	    while (i < n1 && j < n2)
	    {
	        if (left[i] <= right[j]){
	            arr[k] = left[i];
	            i++;
	        }
	        else {
	            arr[k] = right[j];
	            j++;
	        }
	        k++;
	    }
	    //Copy elements in left and right to the arr
	    while (i < n1) {
	        arr[k] = left[i];
	        i++;
	        k++;
	    }
	    while (j < n2) {
	        arr[k] = right[j];
	        j++;
	        k++;
	    }
	}
	// TESTED
	static void mergeSort(int[] arr, int l, int r) { // [1] Used reference online (cited in commentary)
	    if (l < r) {
	        // Find the middle point
	        int m = (l+r)/2;
	
	        // Sort first and second halves (Recursion)
	        mergeSort(arr, l, m);
	        mergeSort(arr , m+1, r);
	
	        // Merge the sorted halves
	        merge(arr, l, m, r);
	    }
	}

	public static void main(String[] args) {
		int[] arr1 = new int[5000];
		int[] arr2 = new int[10000];
		int[] arr3 = new int[20000];
		int[] arr4 = new int[50000];
		long startTime;
		long endTime;
		long finalTime;
		//long startTime = System.nanoTime();
		
		System.out.println("Best Case:");
		
		//Start of 5000
		readFile(arr1, "/Users/AlexanderR/Desktop/Test files/BestCase5000.txt");
		startTime = System.nanoTime();
		bubbleSort(arr1);
		endTime = System.nanoTime();
		finalTime = endTime - startTime;
		
		System.out.println("Bubble Sort 5000: " + finalTime);
		
		startTime = System.nanoTime();
		mergeSort(arr1, 0, arr1.length - 1);
		endTime = System.nanoTime();
		finalTime = endTime - startTime;
		
		System.out.println("Merge Sort 5000: " + finalTime);
		// end of 5000
		
		//start of 10000
		readFile(arr2, "/Users/AlexanderR/Desktop/Test files/BestCase10000.txt");
		startTime = System.nanoTime();
		bubbleSort(arr2);
		endTime = System.nanoTime();
		finalTime = endTime - startTime;
		
		System.out.println("Bubble Sort 10000: " + finalTime);
		
		startTime = System.nanoTime();
		mergeSort(arr2, 0, arr2.length - 1);
		endTime = System.nanoTime();
		finalTime = endTime - startTime;
		
		System.out.println("Merge Sort 10000: " + finalTime);
		// end of 10000
		
		//start of 20000
		readFile(arr3, "/Users/AlexanderR/Desktop/Test files/BestCase20000.txt");
		startTime = System.nanoTime();
		bubbleSort(arr3);
		endTime = System.nanoTime();
		finalTime = endTime - startTime;
		
		System.out.println("Bubble Sort 20000: " + finalTime);
		
		startTime = System.nanoTime();
		mergeSort(arr3, 0, arr3.length - 1);
		endTime = System.nanoTime();
		finalTime = endTime - startTime;
		
		System.out.println("Merge Sort 20000: " + finalTime);
		// end of 20000
		
		//start of 50000
		readFile(arr4, "/Users/AlexanderR/Desktop/Test files/BestCase50000.txt");
		startTime = System.nanoTime();
		bubbleSort(arr4);
		endTime = System.nanoTime();
		finalTime = endTime - startTime;
		
		System.out.println("Bubble Sort 50000: " + finalTime);
		
		startTime = System.nanoTime();
		mergeSort(arr4, 0, arr4.length - 1);
		endTime = System.nanoTime();
		finalTime = endTime - startTime;
		
		System.out.println("Merge Sort 50000: " + finalTime + "\n");
		// end of 50000
		
		// Average case
		System.out.println("Average Case:");
		
		//Start of 5000
		readFile(arr1, "/Users/AlexanderR/Desktop/Test files/AverageCase5000.txt");
		startTime = System.nanoTime();
		bubbleSort(arr1);
		endTime = System.nanoTime();
		finalTime = endTime - startTime;
		
		System.out.println("Bubble Sort 5000: " + finalTime);
		
		startTime = System.nanoTime();
		mergeSort(arr1, 0, arr1.length - 1);
		endTime = System.nanoTime();
		finalTime = endTime - startTime;
		
		System.out.println("Merge Sort 5000: " + finalTime);
		// end of 5000
		
		//start of 10000
		readFile(arr2, "/Users/AlexanderR/Desktop/Test files/AverageCase10000.txt");
		startTime = System.nanoTime();
		bubbleSort(arr2);
		endTime = System.nanoTime();
		finalTime = endTime - startTime;
		
		System.out.println("Bubble Sort 10000: " + finalTime);
		
		startTime = System.nanoTime();
		mergeSort(arr2, 0, arr2.length - 1);
		endTime = System.nanoTime();
		finalTime = endTime - startTime;
		
		System.out.println("Merge Sort 10000: " + finalTime);
		// end of 10000
		
		//start of 20000
		readFile(arr3, "/Users/AlexanderR/Desktop/Test files/AverageCase20000.txt");
		startTime = System.nanoTime();
		bubbleSort(arr3);
		endTime = System.nanoTime();
		finalTime = endTime - startTime;
		
		System.out.println("Bubble Sort 20000: " + finalTime);
		
		startTime = System.nanoTime();
		mergeSort(arr3, 0, arr3.length - 1);
		endTime = System.nanoTime();
		finalTime = endTime - startTime;
		
		System.out.println("Merge Sort 20000: " + finalTime);
		// end of 20000
		
		//start of 50000
		readFile(arr4, "/Users/AlexanderR/Desktop/Test files/AverageCase50000.txt");
		startTime = System.nanoTime();
		bubbleSort(arr4);
		endTime = System.nanoTime();
		finalTime = endTime - startTime;
		
		System.out.println("Bubble Sort 50000: " + finalTime);
		
		startTime = System.nanoTime();
		mergeSort(arr4, 0, arr4.length - 1);
		endTime = System.nanoTime();
		finalTime = endTime - startTime;
		
		System.out.println("Merge Sort 50000: " + finalTime + "\n");
		// end of 50000
		
		// Worst case
		System.out.println("Worst Case:");
		
		//Start of 5000
		readFile(arr1, "/Users/AlexanderR/Desktop/Test files/WorstCase5000.txt");
		startTime = System.nanoTime();
		bubbleSort(arr1);
		endTime = System.nanoTime();
		finalTime = endTime - startTime;
		
		System.out.println("Bubble Sort 5000: " + finalTime);
		
		startTime = System.nanoTime();
		mergeSort(arr1, 0, arr1.length - 1);
		endTime = System.nanoTime();
		finalTime = endTime - startTime;
		
		System.out.println("Merge Sort 5000: " + finalTime);
		// end of 5000
		
		//start of 10000
		readFile(arr2, "/Users/AlexanderR/Desktop/Test files/WorstCase10000.txt");
		startTime = System.nanoTime();
		bubbleSort(arr2);
		endTime = System.nanoTime();
		finalTime = endTime - startTime;
		
		System.out.println("Bubble Sort 10000: " + finalTime);
		
		startTime = System.nanoTime();
		mergeSort(arr2, 0, arr2.length - 1);
		endTime = System.nanoTime();
		finalTime = endTime - startTime;
		
		System.out.println("Merge Sort 10000: " + finalTime);
		// end of 10000
		
		//start of 20000
		readFile(arr3, "/Users/AlexanderR/Desktop/Test files/WorstCase20000.txt");
		startTime = System.nanoTime();
		bubbleSort(arr3);
		endTime = System.nanoTime();
		finalTime = endTime - startTime;
		
		System.out.println("Bubble Sort 20000: " + finalTime);
		
		startTime = System.nanoTime();
		mergeSort(arr3, 0, arr3.length - 1);
		endTime = System.nanoTime();
		finalTime = endTime - startTime;
		
		System.out.println("Merge Sort 20000: " + finalTime);
		// end of 20000
		
		//start of 50000
		readFile(arr4, "/Users/AlexanderR/Desktop/Test files/WorstCase50000.txt");
		startTime = System.nanoTime();
		bubbleSort(arr4);
		endTime = System.nanoTime();
		finalTime = endTime - startTime;
		
		System.out.println("Bubble Sort 50000: " + finalTime);
		
		startTime = System.nanoTime();
		mergeSort(arr4, 0, arr4.length - 1);
		endTime = System.nanoTime();
		finalTime = endTime - startTime;
		
		System.out.println("Merge Sort 50000: " + finalTime + "\n");
		// end of 50000
	}
}
