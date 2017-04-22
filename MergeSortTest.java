/** Used to test the merge sort algorithm of linked list */
public class MergeSortTest {
  
  /** Runs the sort demo
    * @param args contains an integer which is the number of values to sort and an optional "-print"
    */
  public static void main(String[] args) {
    int numToSort = Integer.parseInt(args[0]);
    
    // fill a linked list with random data
    LinkedList<Integer> list = new LinkedList<Integer>();
    for (int i = 0; i < numToSort; i++) {
      list.addToFront((int)(Math.random() * numToSort));
    }
    
    // sort the data
    LinkedList.mergeSort(list);
    System.out.println("Done Sorting!");
    
    // if the user wants to see the sorted list, print it out
    if (args.length > 1 && args[1].equals("-print"))
      LinkedList.printList(list);
  }
}
