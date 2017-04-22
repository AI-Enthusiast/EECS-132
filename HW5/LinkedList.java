import java.util.ArrayList;
import java.util.NoSuchElementException;

/**
 * A class to represent a bunch on LLNodes linked together
 *
 * @author Cormac Dacker
 * @since 11/28/16
 */
public class LinkedList<T> implements Iterable<T> {
    /**
     * the first node of the list, or null if the list is empty
     */
    private LLNode<T> front;

    /**
     * Creates an initially empty linked list
     */
    public LinkedList() {
        front = null;
    }

    /**
     * Add an element in order into a sorted list
     *
     * @param element the element to add
     * @param list    the list to add the element to.  The list is assumed to be sorted.
     */
    public static <E extends Comparable<? super E>> void addInOrder(E element, LinkedList<E> list) {
        /* If the list is empty */
        if (list.isEmpty())
            list.addToFront(element);
        /* If not empty but the first element is larger */
        else if (element.compareTo(list.getFront().getElement()) <= 0)
            list.addToFront(element);
        /* If not empty and first element is smaller */
        else {
            LLNode<E> nodeptr = list.getFront();
            /* The goal of the loop is to go through the list re-assigning nodeptr progressively further up the chain */
            while (nodeptr.getNext() != null &&
                    element.compareTo(nodeptr.getNext().getElement()) > 0) {
                nodeptr = nodeptr.getNext();
            }
            /* Want element to go immediately after nodeptr */
            nodeptr.setNext(new LLNode<E>(element, nodeptr.getNext()));
        }
    }

    /**
     * Merges two sorted lists of nodes into a single sorted list.
     * The input lists are destroyed in the process.
     *
     * @param list1 a sorted list of nodes
     * @param list2 a sorted list of nodes
     * @return a list containing all the nodes of the input lists and in sorted order
     */
    private static <T extends Comparable<? super T>> LLNode<T> merge(LLNode<T> list1, LLNode<T> list2) {
        LLNode<T> list;             // the first node of the output list
        if (list1.getElement().compareTo(list2.getElement()) < 0) {
            list = list1;             // the first node is the smaller of list1 and list2
            list1 = list1.getNext();
        } else {
            list = list2;
            list2 = list2.getNext();
        }
        /* Keep track of the last node of the output list */
        /* Keep track of the last node of the output list /* Keep track of the last node of the output list*/
        LLNode<T> endptr = list;
        /*
         * At each iteration, take the smaller of list1 and list2, place it
         * At the end of the output list, and then increment those pointers
         */
        while (list1 != null && list2 != null) {
            if (list1.getElement().compareTo(list2.getElement()) < 0) {
                endptr.setNext(list1);
                list1 = list1.getNext();
            } else {
                endptr.setNext(list2);
                list2 = list2.getNext();
            }
            endptr = endptr.getNext();
        }
        /* One of list1 or list2 not null, and we must put those "large" nodes at the end of our list */
        if (list2 != null)
            endptr.setNext(list2);
        else
            endptr.setNext(list1);
        return list;
    }

    /**
     * Runs the merge sort recursive algorithm on a list of nodes
     *
     * @param list1 a list of nodes to sort.  The nodes will be rearranged.
     * @return the nodes of the input list, but in sorted order
     */
    private static <T extends Comparable<? super T>> LLNode<T> mergeSort(LLNode<T> list1) {
        /* First check to see if there is anything to sort */
        if (list1.getNext() == null) {
            return list1;
        }
        LLNode<T> list2 = split(list1);    // otherwise split the list into two halves
        list1 = mergeSort(list1);          // sort each half
        list2 = mergeSort(list2);
        return merge(list1, list2);        // and merge the sorted halves together
    }

    /**
     * Sort a linked list using the merge sort algorithm.
     *
     * @param list the linked list to sort
     */
    public static <T extends Comparable<? super T>> void mergeSort(LinkedList<T> list) {
        list.setFront(mergeSort(list.getFront()));
    }

    /**
     * Print the contents of the linked list to the screen
     */
    public static <E> void printList(LinkedList<E> list) {
        LLNode<E> nodeptr = list.getFront();
        /* The goal of the loop is to go through the entire list and print each element */
        while (nodeptr != null) {
            System.out.print(nodeptr.getElement() + " ");
            nodeptr = nodeptr.getNext();
        }
        System.out.println();
    }

    /**
     * Splits a list into two equal sized lists of nodes by separating the odd and even nodes
     *
     * @param list1 the input list of nodes to split. At the end, list1 will retain only every other node
     * @return a list of the nodes removed from the input list
     */
    private static <T extends Comparable<? super T>> LLNode<T> split(LLNode<T> list1) {
        LLNode<T> list2 = list1.getNext();  // the returned list will start at the 2nd node
        LLNode<T> nodeptr = list2;          // keeps track of where we are in the list
        LLNode<T> prevptr = list1;          // the node before nextptr
        /* The goal is to set the next pointer of prevnode to skip the next node of the list */
        while (nodeptr != null) {
            prevptr.setNext(nodeptr.getNext());
            prevptr = nodeptr;
            nodeptr = nodeptr.getNext();
        }
        return list2;
    }

    /**
     * Returns the first node.
     */
    protected LLNode<T> getFront() {
        return front;
    }

    /**
     * Changes the first node.
     *
     * @param node the first node of the new linked list
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
     *
     * @return true if the list is empty
     */
    public boolean isEmpty() {
        return (getFront() == null);
    }

    /**
     * Returns the length of the linked list
     *
     * @return the number of nodes in the list
     */
    public int length() {
        int count = 0;                      // counts number of nodes seen
        LLNode<T> nodeptr = getFront();
        /* The goal of the loop is to count how many nodes are in the linked list */
        while (nodeptr != null) {
            count++;
            nodeptr = nodeptr.getNext();
        }
        return count;
    }

    /**
     * Remove and return the element at the front of the list
     *
     * @return the first element of the list
     * @throws NoSuchElementException if there is no such element
     */
    public T removeFromFront() {
        /* If the list if empty */
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
     *
     * @param element the element to add to the end of the list
     */
    public void addToEnd(T element) {
        if (isEmpty())
            addToFront(element);
        else {
            LLNode<T> nodeptr = getFront();
            /* The loop will end with nodeptr looking at the last node in list */
            while (nodeptr.getNext() != null)
                nodeptr = nodeptr.getNext();
            nodeptr.setNext(new LLNode<T>(element, null));
        }
    }

    /**
     * Required by the Iterable interface.
     *
     * @return an iterator for the list
     */
    public LLIterator<T> iterator() {
        return new LLIterator.ClassyLLIterator<T>(getFront());
    }

    /**
     * Returns a String representation of the list
     *
     * @return a String representing the list
     */
    @Override
    public String toString() {
        /* variable builder used to construct the string */
        StringBuilder builder = new StringBuilder();
        builder.append("list:");
        /* Temp node to get the element of each list and add it to the builder */
        LLNode<T> temp = this.getFront();
        /* The goal of the loop is to add the each element of the liked list to the builder */
        for (int index = 0; index < this.length(); index++) {
            builder.append(" " + temp.getElement());
            temp = temp.getNext();
        }
        return builder.toString();
    }

    /**
     * Determines whether an element is stored in the list
     *
     * @param element the element to search for in the list
     * @return true if and only if the parameter element is in the list
     */
    public boolean contains(T element) {
        LLNode<T> temp = this.getFront();
        for (int i = 0; i < this.length(); i++) {
            if (temp.getElement() == element) {
                return true;
            }
            temp = temp.getNext();
        }
        return false;
    }

    /**
     * Deletes the first occurrence of an element in the list.
     * If the element is not in the list, the list is unchanged.
     *
     * @param element the element to remove
     */
    public void remove(T element) {
        /* The original length of the list */
        int ogLength = this.getFront().lengthFromHere();
        /* A node that is used to substitute for the header of the list we are removing from */
        LLNode<T> node = new LLNode<T>(this.getFront().getElement(), this.getFront().getNext());
        /* If the element is the front of the list remove it */
        if (this.getFront().getElement() == element) {
            if (this.getFront().getNext() == null) {
                this.setFront(null);
            } else {
                setFront(this.getFront().getNext());
            }
        }
        /*
         * Loop goes down the list until the length of the list is one less (meaning one was removed) or
         * the index reaches the end of the list
         */
        for (int index = 0; index < ogLength && this.getFront().lengthFromHere() == ogLength; index++) {
            /*
             * If the desired node is found, have the previous node link to the next one,
             * thus skipping the desired node
             */
            if (node.getNext().getElement() == element) {
                /* If the element is the last in the list */
                if (node.getNext().getNext() == null) {
                    node.setNext(null);
                } else {
                    node.deleteNext();
                }
            } else {
                node = node.getNext();
            }
        }
    }

    /**
     * Converts the LinkedList to an array
     *
     * @return ArrayList that represents the LinkList that is now an array
     */
    public ArrayList toArrayList() {
        /* ArrayList that will be returned at the end of the method */
        ArrayList<T> arrayList = new ArrayList<T>();
        /* Node that will be moving up the linked list adding it to the array list */
        LLNode<T> node = this.getFront();
        /* The goal of the loop is to go up the LikedList and add the element of each node to the field arrayList */
        while (node.lengthFromHere() > 0) {
            arrayList.add(node.getElement());
            node = node.getNext();
        }
        return arrayList;
    }
}