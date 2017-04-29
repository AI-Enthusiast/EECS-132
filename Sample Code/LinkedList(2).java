import java.util.NoSuchElementException;
import java.util.Iterator;

/**
 * A class to represent a linked list of nodes.
 */
public class LinkedList<T> implements Iterable<T> {
  /** the first node of the list, or null if the list is empty */
  private LLNode<T> front;
  
  /**
   * Creates an initially empty linked list
   */
  public LinkedList() {
    front = null;
  }
  
  /**
   * Returns the first node.
   */
  protected LLNode<T> getFront() {
    return front;
  }

  /**
   * Changes the first node.
   * @param node  the first node of the new linked list
   */
  protected void setFront(LLNode<T> node) {
    this.front = node;
  }

  /**
   * Add an element to the front of the linked list
   * @param element  the element to add
   */
  public void addToFront(T element) {
    setFront(new LLNode<T>(element, getFront()));
  }
  
  /**
   * Add an element in order into a sorted list
   * @param element the element to add
   * @param list the list to add the element to.  The list is assumed to be sorted.
   */
  public static <E extends Comparable<? super E>> void addInOrder(E element, LinkedList<E> list) {
    // case 1. the list is empty
    if (list.isEmpty())
      list.addToFront(element);
    // case 2. not empty but the first element is larger
    else if (element.compareTo(list.getFront().getElement()) <= 0)
      list.addToFront(element);
    // case 3. not empty and first element is smaller
    else {
      LLNode<E> nodeptr = list.getFront();
      while (nodeptr.getNext() != null &&
             element.compareTo(nodeptr.getNext().getElement()) > 0) {
        nodeptr = nodeptr.getNext();
      }
      // want element to go immediately after nodeptr
      nodeptr.setNext(new LLNode<E>(element, nodeptr.getNext()));
    }
  }
  
  /**
   * Return whether the list is empty
   * @return true if the list is empty
   */
  public boolean isEmpty() {
    return (getFront() == null);
  }
  
  /**
   * Returns the length of the linked list
   * @return the number of nodes in the list
   */
  public int length() {
    int count = 0;                      // counts number of nodes seen
    LLNode<T> nodeptr = getFront();
    while (nodeptr != null) {
      count++;
      nodeptr = nodeptr.getNext();
    }
    return count;
  }
  
  /**
   * Remove and return the element at the front of the list
   * @return the first element of the list
   * @throws NoSuchElementException if there is no such element
   */
  public T removeFromFront() {
    if (isEmpty())
      throw new NoSuchElementException();
    else {
      T save = getFront().getElement();
      setFront(getFront().getNext());
      return save;
    }
  }

  /**
   * Add an element to the very end of the list
   * @param element the element to add to the end of the list
   */
  public void addToEnd(T element) {
    if (isEmpty()) 
      addToFront(element);
    else {
      LLNode<T> nodeptr = getFront();
      // the loop will end with nodeptr looking at the last node in list
      while (nodeptr.getNext() != null)
        nodeptr = nodeptr.getNext();
      nodeptr.setNext(new LLNode<T>(element, null));
    }
  }
  
  /**
   * Required by the Iterable interface. 
   * @return an iterator for the list
   */
  public Iterator<T> iterator() {
    return new LinkedListIterator<T>(getFront());
  }
  
  /** Print the contents of the linked list to the screen
    * @param list  the list to be printed
    */
  public static void printList(Iterable<?> list) {
    // a for-each loop.  To the right of the colon must be an Iterable value
    for (Object element : list)
      System.out.print(element + " ");
    System.out.println();
  }
  
  /**
   * Splits a list into two equal sized lists of nodes by separating the odd and even nodes
   * @param list1 the input list of nodes to split. At the end, list1 will retain only every other node
   * @return a list of the nodes removed from the input list
   */
  private static <T extends Comparable<? super T>> LLNode<T> split(LLNode<T> list1) {
    LLNode<T> list2 = list1.getNext();  // the returned list will start at the 2nd node
    LLNode<T> nodeptr = list2;          // keeps track of where we are in the list
    LLNode<T> prevptr = list1;          // the node before nextptr
    // at each iteration, it sets the next pointer of prevnode to skip the next node of the list
    while (nodeptr != null) {
      prevptr.setNext(nodeptr.getNext());
      prevptr = nodeptr;
      nodeptr = nodeptr.getNext();
    }
    return list2;
  }
  
  /**
   * Merges two sorted lists of nodes into a single sorted list.
   * The input lists are destroyed in the process.
   * @param list1 a sorted list of nodes
   * @param list2 a sorted list of nodes
   * @return a list containing all the nodes of the input lists and in sorted order
   */
  private static <T extends Comparable<? super T>> LLNode<T> merge(LLNode<T> list1, LLNode<T> list2) {
    LLNode<T> list;             // the first node of the output list
    if (list1.getElement().compareTo(list2.getElement()) < 0) {
      list = list1;             // the first node is the smaller of list1 and list2
      list1 = list1.getNext();
    }
    else {
      list = list2;
      list2 = list2.getNext();
    }
    
    LLNode<T> endptr = list;    // keep track of the last node of the output list
    // at each iteration, take the smaller of list1 and list2, place it
    // at the end of the output list, and then increment those pointers
    while (list1 != null && list2 != null) {
      if (list1.getElement().compareTo(list2.getElement()) < 0) {
        endptr.setNext(list1);
        list1 = list1.getNext();
      }
      else {
        endptr.setNext(list2);
        list2 = list2.getNext();
      }
      endptr = endptr.getNext();
    }
    // one of list1 or list2 not null, and we must put those "large" nodes at the
    // end of our list.
    if (list2 != null)
      endptr.setNext(list2);
    else
      endptr.setNext(list1);
    return list;
  }
  
  /** 
   * Runs the merge sort recursive algorithm on a list of nodes
   * @param list a list of nodes to sort.  The nodes will be rearranged.
   * @return the nodes of the input list, but in sorted order
   */
  private static <T extends Comparable<? super T>> LLNode<T> mergeSort(LLNode<T> list1) {
    if (list1.getNext() == null)       // first check to see if there is anything to sort
      return list1;
    
    LLNode<T> list2 = split(list1);    // otherwise split the list into two halves
    list1 = mergeSort(list1);          // sort each half
    list2 = mergeSort(list2);
    return merge(list1, list2);        // and merge the sorted halves together
  }
  
  /**
   * Sort a linked list using the merge sort algorithm.
   * @param list the linked list to sort
   */
  public static <T extends Comparable<? super T>> void mergeSort(LinkedList<T> list) {
    list.setFront(mergeSort(list.getFront()));
  }
}
