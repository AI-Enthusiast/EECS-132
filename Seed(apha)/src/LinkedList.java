import java.util.*;

/**
 * A class to represent a linked list of nodes.
 * @author Shanti Polara
 */
public class LinkedList<T> implements Iterable{
  
  /** the first node of the list, or null if the list is empty */
  private LLNode<T> first;
  
  /**
   * Creates an initially empty linked list
   */
  public LinkedList() {
    first = null;
  }
  
  /**
   * Returns the first node in the LinkedList
   * @return the first node in the list
   */
  protected LLNode<T> getFirst() {
    return first;
  }
  
  /**
   * Changes the first node.
   * @param node  the node that will be the first node of the new linked list
   */
  protected void setFirst(LLNode<T> node) {
    this.first = node;
  }
  
  /**
   * Add an element to the front of the linked list
   * @param element the element which is being added to the front of the linked list
   */
  public void addToFront(T element) {
    setFirst(new LLNode<T>(element, getFirst()));
  }
  
  /**
   * Return whether the list is empty
   * @return true if the list is empty
   */
  public boolean isEmpty() {
    return (getFirst() == null);
  }
  
  /**
   * Required by the Iterable interface. 
   * @return an iterator for the list
   */
  @Override
  public Iterator<T> iterator() {
    return new LinkedListIterator(getFirst());
  }
  
  /**
   * converts the LinkedList into an ArrayList
   * @return an ArrayList containing the elements of the linked list in the same order
   */
  public ArrayList<T> toArrayList(){
    LinkedList<T>.LinkedListIterator iterator = (LinkedList<T>.LinkedListIterator)iterator();
    ArrayList<T> newList = new ArrayList<T>(length());
    //iterates through the entire list adding elements from the linkedList to the ArrayList
    while(iterator.hasNext()){
      newList.add(iterator.next());
    }
    return newList;
  }
  
  
  /**
   * Returns the length of the linked list
   * @return the number of nodes in the list
   */
  public int length() {
    int lengthSoFar = 0;
    LLNode<T> nodeptr = getFirst();
    //iterates through the entire LinkedList
    while (nodeptr != null) {
      lengthSoFar++;
      nodeptr = nodeptr.getNext();
    }
    return lengthSoFar;
  }
  
  /**
   * Add an element to the very end of the list
   * @param element the element to add to the end of the list
   */
  public void addToEnd(T element) {
    if (isEmpty()) 
      addToFront(element);
    else {
      LLNode<T> nodeptr = getFirst();
      // the loop will end with nodeptr looking at the last node in list
      while (nodeptr.getNext() != null)
        nodeptr = nodeptr.getNext();
      nodeptr.setNext(new LLNode<T>(element, null));
    }
  }
  
  
  /**
   * A class which iterates over a linked list
   * @author Shanti Polara
   */
  public class LinkedListIterator implements LLIterator<T>{
    
    //points to the current node of the iteration 
    private LLNode<T> nodeptr;
    
    //holds onto the node that has been most recently called by next
    private LLNode<T> nodeCalled = null;
    
    //holds onto the node that is before the last call of next
    private LLNode<T> nodeBefore = null;
    
    //holds the number of times next has been called
    private int count = 0;
    
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
    @Override
    public boolean hasNext(){
      return nodeptr != null;
    }
    
    /**
     * Returns the next value stored in the linked list
     * @return the next value of the linked list
     */
    @Override
    public T next() {
      count++;
      nodeBefore = nodeCalled;
      nodeCalled = nodeptr;
      T save = nodeptr.getElement();
      nodeptr = nodeptr.getNext();
      return save;
    }
    
    /**
     * overides the remove method inherited from LLIterator, is not properly implemented
     */
    @Override
    public void remove(){
      throw new UnsupportedOperationException();
    }
    
    
    /**
     * adds an element to the list being iterated before the element that was just returned by the most recent call to next
     * @param element the element that is being added to the list
     */
    public void addBefore(T element) throws NoSuchElementException{
      //if it is the first time next is called, addes the element to the front of the list
      if (count == 1)
        LinkedList.this.addToFront(element);
      //adds the element before the element that was just called
      else if (nodeBefore != null){
        LLNode<T> save = nodeBefore.getNext();
        nodeBefore.setNext( new LLNode<T> (element, save));
      }
      else
        throw new NoSuchElementException();
    }
    
    /**
     * adds an element to the list being iterated after the element that was just returned by the most recent call to next
     * @param element the element that is being added to the list
     */
    @Override
    public void addAfter(T element){
      LLNode<T> save = null;
      //checks if list is empty or next has not been called
      if (LinkedList.this.isEmpty() || nodeCalled == null){
        LinkedList.this.addToFront(element);
      }
      else{
        save = nodeCalled.getNext();
        nodeCalled.setNext( new LLNode<T> (element, save));
      }
      
    }
  }
  
  
  
}

