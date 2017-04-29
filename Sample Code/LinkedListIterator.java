import java.util.Iterator;

/** An iterator for the linked list. */
public class LinkedListIterator<T> implements Iterator<T> {
  /** points to the current node of the iteration */
  private LLNode<T> nodeptr;
  
  /** Create an iterator for the list starting at the inputted node
    * @param front  the first node for the iteration
    */
  public LinkedListIterator(LLNode<T> front) {
    nodeptr = front;
  }
  
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
}