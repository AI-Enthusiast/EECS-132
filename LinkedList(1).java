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
   */
  public void addToFront(T element) {
    setFront(new LLNode<T>(element, getFront()));
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
  
  /** Print the contents of the linked list to the screen */
  public static <E> void printList(LinkedList<E> list) {
    LLNode<E> nodeptr = list.getFront();
    while (nodeptr != null) {
      System.out.print(nodeptr.getElement() + " ");
      nodeptr = nodeptr.getNext();
    }
    System.out.println();
  }
  
  /** Print the contents of the linked list to the screen */
  public static void printList2(LinkedList<?> list) {
    LLNode<?> nodeptr = list.getFront();
    while (nodeptr != null) {
      System.out.print(nodeptr.getElement() + " ");
      nodeptr = nodeptr.getNext();
    }
    System.out.println();
  }
  
  /** Print the contents of the linked list to the screen */
  public static <E> void printList3(LinkedList<E> list) {
    // uses an iterator instead of the nodes
    Iterator<E> it = list.iterator();
    while (it.hasNext()) {
      System.out.print(it.next() + " ");
    }
    System.out.println();
  }
  
  /** Print the contents of the linked list to the screen */
  public static void printList4(LinkedList<?> list) {
    // a for-each loop.  To the right of the colon must be an Iterable value
    for (Object element : list)
      System.out.print(element + " ");
    System.out.println();
  }
}
