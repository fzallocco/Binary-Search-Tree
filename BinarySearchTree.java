/*************************************************************************
*  Pace University
*  Spring 2023
*  Algorithms and Computing Theory
*
*  2/27/2023
*  Course: CS 608
*  Team members: Filippo Zallocco, Ananthula Saivyshnav, Lokeshwar Anchuri, Sakshi Singh
*  Other collaborators:
*
*  References: Software Testing Help. https://www.softwaretestinghelp.com/binary-search-tree-in-java/, 2/09/2023
*  Natraj, Lalitha. https://youtu.be/ovWqEgYYAEQ, 4/02/2019
*
*  Visible data fields: int Key, Node Left, Node Right
*
* Problem 1.
*The asymptotic running time to search for a random integer in skewed and balanced binary search trees can differ significantly.
*In a balanced BST, the tree's height is typically expressed as a logarithmic function based on the number of nodes that make up the tree, or O(log n), where n is the number of nodes in the tree. This function simplifies the algorithm's time complexity by eliminating half of the remaining nodes in each step of the search.
*In a skewed BST, the tree's height can reach a large number, often defined as n - 1, where n is the number of nodes in the tree. In a worst-case scenario, we may have to traverse the entire path from the root to the leaf node that contains the search key, resulting in a time complexity of O(n). To see why this is the case, we consider a BST where every node has only one child, and the children are inserted on the right side of the tree. Such a tree would thus be right-skewed. If we want to *search for a key greater than the root, we must traverse the entire tree, a process that can be time-consuming.
*In the binary search tree class, the operations used to search a random integer are findMin and findMax. Both operations are implemented in a while loop traversing the tree from its root to the leaf node containing the minimum or maximum key. The time complexity of each of these operations is O(h), where h is the tree's height. Therefore, the overall running time of searching in a skewed tree is proportional to the size of the tree. In a balanced tree, on the other hand, the height is given by *the exponent of the power of two. Since no x exponent makes two negative, the time complexity is a relatively smaller number than the height of a skewed tree.
*
* Problem 2.
*Case-1: n=10
*Time taken by skewed binary tree: 6758 nanoseconds
*Time taken by Balanced binary tree: 1900 nanoseconds
*Case-2: n=100
*Time taken by skewed binary tree: 57014 nanoseconds
*Time taken by Balanced binary tree: 5799 nanoseconds
*Case-3: n=1000
*Time taken by skewed binary tree: 408080 nanoseconds
*Time taken by Balanced binary tree: 15048 nanoseconds
*Case-4: n=10000
*Time taken by skewed binary tree: 2509879 nanoseconds
*Time taken by Balanced binary tree: 5028 nanoseconds
*Case-5: n=30000
*Time taken by skewed binary tree: 3106875 nanoseconds
*Time taken by Balanced binary tree: 8569 nanoseconds

* Problem 3.
*From the output, we observe that there is a noticeable difference in search speed between the skewed tree and the balanced tree.
*Upon entering ten as input, we learn that the search algorithm takes five times as long in the skewed tree to find a random integer as it does in the balanced tree.
*Increasing the input size to 100, we appreciate that the speed gap between the two trees goes up by a factor of ten.
*Similarly, when we try 1000 as the input size, we understand that the search time variation between the skewed and balanced trees approaches a factor of 400,000,
*or an increment close to 80 times that of the first speed difference recorded.
*This trend continues as we use larger input sizes, such as 10,000 or 30,000.
*In fact, the terminal returns a search time difference of 2.5M nanoseconds and 3.01M nanoseconds, respectively,
*or 500 and 600 time fold increments from the first time variance recorded.
*************************************************************************/

import java.util.*; //We import the class util from the Java environment.
import java.math.*;	//We import the class math from the Java environment.

class BinarySearchTree {
  class Node{

    int Key;  //data types for the base Node.
    Node Left, Right;

    public Node() {}; //Default constructor.
    public Node(int Num){ //user-defined constructor containing an int-based parameter.
      Key=Num;
      Left=null;
      Right=null; //left and right children are non existent until created.
    }
  }

  Node Root; //this the base root of the tree created following the blue print of the class Node.

  BinarySearchTree(){Root=null;} //The algorithm's constructor assumes root is null and the tree is empty.

  public void add(int Key) { //This method pass an int data type into Root with the second method inset.
    Root = insert(Root, Key);
  }
  Node insert(Node Root, int Key){
       if(Root==null){  //If Root has no value, then Java re-initializes Root with Node's constructor using a new integer.
         Root=new Node(Key);
         return Root; //We then return Root's integer data type.
       }
       if(Key<Root.Key)
         Root.Left=insert(Root.Left, Key);  //Here we test whether the key we intend to append is smaller than the one currently used by node.
       else                                 //If it is smaller then the program appends that integer to the node's left child. Otherwise,
         Root.Right=insert(Root.Right, Key);//it will append the integer data type to the node's right child.
       return Root;// Finally, return the integer data type.
  }

  boolean Search(int Key) { //This search method examines whether an integer data type key exists in the tree.
    Root=Search_Recursive(Root, Key);
    if (Root!=null)//Provided that Root is not null and that the tree is not empty, Java will perform the following instructions:
      return true;  //if the key exists, return true.
    else
      return false; //Otherwise, it will return false.
  }

  Node Search_Recursive(Node node, int Key){  //We pass the key and node inputs from the parameters into the conditions below
    if(node==null || node.Key==Key)   //if the node is empty or the key examined is inside an existing node, then
      return node;                    //the algorithm returns the node at hand
    if (node.Key>Key)                 //if the key at hand is smaller than another node's key,
      return Search_Recursive(node.Left, Key);//we instruct Java to search the left side of the tree
    return Search_Recursive(node.Right, Key);//By default Java will search the keys inside the right side of the tree
  }

  public static void main(String[] args) {

    BinarySearchTree BalancedTree = new BinarySearchTree(); //we create a balanced tree using the constructor BinarySearchTree from the BinarySearchTree class.
    BinarySearchTree SkewedTree = new BinarySearchTree();	//We do the same for the skewed tree.

    System.out.println("Enter tree size as powers of 10: \n");	//We prompt the user to enter an array size based on powers of 10.
    Scanner sc = new Scanner(System.in);	//We use the scanner object to fetch the user input and pass it into an integer data type variable that will define the array size.
    int TreeSize = sc.nextInt();

    System.out.println("Creating balanced tree: \n");	//For every element of the array, create a new integer times infinity and embed it inside balanced tree.
    for(int x=0; x<TreeSize; x++){
      int value=(int) (Math.random()*Integer.MAX_VALUE);
      BalancedTree.add(value);
    }

    System.out.println("\n");
    System.out.println("Creating skewed tree: \n");	//For every element of the array, create a skewed array that goes from index 0 till the end of the array.
    for(int i=0; i<TreeSize; i++){
      SkewedTree.add(i);	//this structure defines the skewed tree.
    }

    int searchedValue = (int) ( Math.random()* Integer.MAX_VALUE );	//we generate a random integer-based number that we use for testing later in the program.

    System.out.println("\n");

    long startS = System.nanoTime();	//We start record the time lapse to find out which tree structure offers the quickest search time for an existing integer/key.
        boolean foundS = SkewedTree.Search(searchedValue);	//we call the Search method on the skewed tree first to find whether a random integer exists in the tree.
        long endS = System.nanoTime();	//we then record the time it tookto perform the above instruction.
        long searchTimeS = endS - startS; //we calculate the time lapse fro start to end and display it below to the user.
        System.out.println("Time to search in SkewedTree: " + searchTimeS + " nanoseconds ");
    long startB = System.nanoTime();	//we start recording again for the time it takes for the algorithm to find a random integer in the balanced tree.
        boolean foundB = BalancedTree.Search(searchedValue);
        long endB = System.nanoTime();
        long searchTimeB = endB - startB;	//After caclutating the time lapse we display it to the user below.
        System.out.println("Time to search in BalancedTree: " + searchTimeB + " nanoseconds ");
        System.out.println(foundS + " " + foundB); //Finally, we inform the user whether or not the random integer was found in either tree.
  }//end of main
}//end of class
