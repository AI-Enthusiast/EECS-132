/** A class to demonstrate insertion sort on a linked list */
public class InsertionSortTest {

  /**
   * Launches the demo.  Expects two or 3 inputs:
   *   1) the number of values to sort
   *   2) how often should there be feedback printed
   *   3) an optional -print if you want the list printed at the end
   * For example, java InsertionSortTest 1000 100 will sort 1000 elements 
   * and print a message after each 100 are sorted
   * @param args  the command line arguments
   */
  public static void main(String[] args) {
    int numToSort = Integer.parseInt(args[0]);
    int printRate = Integer.parseInt(args[1]);
    
    // sort the data
    LinkedList<Integer> list = new LinkedList<Integer>();
    for (int i = 0; i < numToSort; i++) {
      LinkedList.addInOrder((int)(Math.random() * numToSort), list);
      if ((i + 1) % printRate == 0)
        System.out.println((i+1) + " numbers sorted");
    }
    System.out.println("Done Sorting!");
    
    // if the user wants to see the sorted list, print it out
    if (args.length > 2 && args[2].equals("-print"))
      LinkedList.printList(list);
  }
}
