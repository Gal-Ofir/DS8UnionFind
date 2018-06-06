package HW8;


/** 
 * Implementation of the Union-Find ADT. 
 */ 
public class UnionFind { 
 
   private int up[]; 
   private int weight[]; 
   int numSets;

 
   /** 
    * Constructor - initializes up and weight arrays.
    *
    * Student - Since our elements are number 1 to numElements,
    * it makes sense to start the arrays from 1, thus, we'll ignore up[0] and weight[0]
    * This has little to no effect in the long run (both in time and space complexity)
    * @param numElements initial number of singleton sets. 
    */ 
   public UnionFind (int numElements) { 
		//your code comes here
       this.up = new int[numElements + 1];
       this.weight = new int[numElements + 1];
       this.numSets = numElements;

       for(int i = 1; i <= numElements; i++) {
           up[i] = -1;
           weight[i] = 1;
       }
   } 
 
   /** 
    * Unites two sets using weighted union.
    *
    * Lecture pseudo code implemented as Java
    *
    * @param i representative of first set. 
    * @param j representative of second set. 
    */ 
   public void union (int i, int j) {
       //your code comes here

       // this code assumes that i and j are indeed the roots (representatives of their respective sets)
       if (i != j) {

           if (weight[i] < weight[j]) {
               up[i] = j;
               weight[j] += weight[i];
           } else {
               up[j] = i;
               weight[i] += weight[j];
           }
           numSets--;
       }
   }
 
   /** 
    * Finds the set representative, and applies path compression. 
    * 
    * @param i the element to search. 
    * @return the representative of the group that contains i. 
    */ 
   public int find (int i) { 
		//your code comes here

       //find the root, and save it
       if (i == 0) return 0;
       int root = i;
       while(up[root] != -1) {
          root = up[root];
       }
       // traverse again, making each node in the path point to the root
       while (i != root) {
           up[i] = root;
           i = up[i];
       }
       return root;
   } 
 
   /** 
    * Find the current number of sets. 
    * 
    * @return the number of set. 
    */ 
   public int getNumSets() { 
		//your code comes here
       int count = 0;
       for (int i : up) {
           if (i == -1) {
               count++;
           }
       }
		return count;
   } 
 
   /** 
    * Prints the contents of the up array. 
    */ 
   private void debugPrintUp() { 
 
      System.out.print ("up:     "); 
      for (int i = 1; i < up.length; i++)
         System.out.print (up[i] + " "); 
      System.out.println (""); 
   } 
 
   /** 
    * Prints the results of running find on all elements. 
    */ 
   private void debugPrintFind() { 
 
      System.out.print ("find:   ");
      for (int i = 1; i < up.length; i++)
         System.out.print (find (i) + " "); 
      System.out.println (""); 
   } 
 
   /** 
    * Prints the contents of the weight array. 
    */ 
   private void debugPrintWeight() { 
 
      System.out.print ("weight: "); 
      for (int i = 1; i < weight.length; i++)
         System.out.print (weight[i] + " "); 
      System.out.println (""); 
   } 
 
   /** 
    * Various tests for the Union-Find functionality. 
    * 
    * @param args command line arguments - not used. 
    */ 
   public static void main (String[] args) { 
 
      UnionFind uf = new UnionFind (10); 
 
      uf.debugPrintUp(); 
      uf.debugPrintFind(); 
      uf.debugPrintWeight(); 
      System.out.println ("Number of sets: " + uf.getNumSets()); 
      System.out.println (""); 
 
      uf.union (2, 1); 
      uf.union (3, 2); 
      uf.union (4, 2); 
      uf.union (5, 2); 
 
      uf.debugPrintUp(); 
      uf.debugPrintFind(); 
      uf.debugPrintWeight(); 
      System.out.println ("Number of sets: " + uf.getNumSets()); 
      System.out.println(); 
 
      uf.union (6, 7); 
      uf.union (8, 9); 
      uf.union (6, 8); 
 
      uf.debugPrintUp(); 
      uf.debugPrintFind(); 
      uf.debugPrintWeight(); 
      System.out.println ("Number of sets: " + uf.getNumSets()); 
      System.out.println(); 
 
      uf.find (8); 
 
      uf.debugPrintUp(); 
      uf.debugPrintFind(); 
      uf.debugPrintWeight(); 
      System.out.println ("Number of sets: " + uf.getNumSets()); 
      System.out.println(); 
   } 
}