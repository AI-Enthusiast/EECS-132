import java.util.NoSuchElementException;
import java.util.Iterator;

/**
 * A class to represent a linked list of nodes.  The node class is a non-static nested/inner class.
 */
public class List2<T> implements Iterable<T> {
  /** the first node of the list, or null if the list is empty */
  private Node front;
  
  /**
   * Creates an initially empty linked list
   */
  public List2() {
    front = null;
  }
  
  /**
   * Returns the first node.
   */
  protected Node getFront() {
    return front;
  }

  /**
   * Changes the first node.
   * @param node  the first node of the new linked list
   */
  protected void setFront(Node node) {
    this.front = node;
  }

  /**
   * Add an element to the front of the linked list
   */
  public void addToFront(T element) {
    setFront(new Node(element, getFront()));
  }
  
  /**
   * Add an element in order into a sorted list
   */
  public static <E extends Comparable<? super E>> void addInOrder(E element, List2<E> list) {
    // case 1. the list is empty
    if (list.isEmpty())
      list.addToFront(element);
    // case 2. not empty but the first element is larger
    else if (element.compareTo(list.getFront().getElement()) <= 0)
      list.addToFront(element);
    // case 3. not empty and first element is smaller
    else {
      List2<E>.Node nodeptr = list.getFront();
      while (nodeptr.getNext() != null &&
             element.compareTo(nodeptr.getNext().getElement()) > 0) {
        nodeptr = nodeptr.getNext();
      }
      // want element to go immediately after nodeptr
      nodeptr.setNext(list.new Node(element, nodeptr.getNext()));
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
    Node nodeptr = getFront();
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
      Node nodeptr = getFront();
      // the loop will end with nodeptr looking at the last node in list
      while (nodeptr.getNext() != null)
        nodeptr = nodeptr.getNext();
      nodeptr.setNext(new Node(element, null));
    }
  }
  
  /**
   * Required by the Iterable interface. 
   * @return an iterator for the list
   */
  public Iterator<T> iterator() {
    return new Iterator<T>() {
      private Node nodeptr = getFront();      
      /**
       * Returns true if there are more nodes to run through
       * @return true if there are still more values in the iteration
       */
      public boolean hasNext() {
        return nodeptr != null;
      }
      
      /**
       * Returns the next value stored in the linked list
       * @return the next value of the linked list
       */
      public T next() {
        T save = nodeptr.getElement();
        nodeptr = nodeptr.getNext();
        return save;
      }
      
      /** Not implemented */
      public void remove() {
        throw new UnsupportedOperationException();
      }
    };
  }
  
  /** Print the contents of the linked list to the screen */
  public static <E> void printList(List2<E> list) {
    for (E element : list)
      System.out.print(element + " ");
    System.out.println();
  }

  
  /**
   * The node of a linked list.  Because it is non-static, it gets List2's generic.
   */
  public class Node {
    /** the element stored in the node */
    private T element;
    
    /** a reference to the next node of the list */
    private Node next;
    
    /**
     * the constructor
     * @param element  the element to store in the node
     * @param next  a reference to the next node of the list 
     */
    public Node(T element, Node next) {
      this.element = element;
      this.next = next;
    }
    
    /**
     * Returns the element stored in the node
     * @return the element stored in the node
     */
    public T getElement() {
      return element;
    }
    
    /**
     * Returns the next node of the list
     * @return the next node of the list
     */
    public Node getNext() {
      return next;
    }
    
    /**
     * Changes the node that comes after this node in the list
     * @param next  the node that should come after this node in the list.  It can be null.
     */
    public void setNext(Node next) {
      this.next = next;
    }
    
  }
}