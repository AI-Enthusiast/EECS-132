import java.util.Iterator;
import java.util.NoSuchElementException; 

/**
 * A interface that extends the interator interface
 * @author Shanti Polara
 * @param T the type of elements returned by the iterator
 */
public interface LLIterator<T> extends Iterator<T>{
  
  
  /**
   * adds an element to the list being iterated before the element that was just returned by the most recent call to next
   * @param element the element that is being added to the list
   */
  void addBefore(T element) throws NoSuchElementException;
  /**
   * adds an element to the list being iterated after the element that was just returned by the most recent call to next
   * @param element the element that is being added to the list
   */
  void addAfter(T element);
  
}