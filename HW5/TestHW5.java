import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.testng.AssertJUnit.assertFalse;
import static org.testng.AssertJUnit.assertTrue;

/**
 * Tester for methods in all classes created
 *
 * @author Cormac Dacker
 * @since 11/28/2016.
 */
public class TestHW5 {

    /**
     * Test classes LinkedList and LLNode
     */
    @Test
    public void testLinkedList() {

        /**
         * Test the insertAfter method
         */
        LLNode<Integer> listNode = new LLNode<Integer>(1, null);
        /* test first */
        listNode.insertAfter(3);
        assertEquals("Inserting on end of list. Checking new next node", new Integer(3), listNode.getNext().getElement());
        assertEquals("Checking next's next", null, listNode.getNext().getNext());
        /* test middle */
        listNode.insertAfter(2);
        assertEquals("Inserting in middle of list. Checking new next node", new Integer(2), listNode.getNext().getElement());
        assertEquals("Checking next's next", new Integer(3), listNode.getNext().getNext().getElement());

        /**
         * Test the deleteNext method
         */
        LLNode<Integer> listNode2 = new LLNode<Integer>(1, new LLNode<Integer>(2, new LLNode<Integer>(3, null)));
        /* test 1 */
        listNode2.deleteNext();
        assertEquals("Deleting from middle of list", new Integer(3), listNode2.getNext().getElement());
        /* test many */
        listNode2.deleteNext();
        assertEquals("Deleting last node of list", null, listNode2.getNext());
        /* test 0 */
        listNode2.deleteNext();
        assertEquals("Deleting when next is null", null, listNode2.getNext());

        /**
         * Test the toString method
         */
        LinkedList<Integer> list1 = new LinkedList<Integer>();
        assertEquals("Testing empty list", "list:", list1.toString());
        /* test 0 */
        list1.addToFront(3);
        assertEquals("Testing list of one node", "list: 3", list1.toString());
        /* test 1 */
        list1.addToFront(2);
        assertEquals("Testing list of two nodes", "list: 2 3", list1.toString());
        /* test many */
        list1.addToFront(1);
        assertEquals("Testing list of three nodes", "list: 1 2 3", list1.toString());

        /**
         * Test the contains method
         */
        LinkedList<Integer> list2 = new LinkedList<Integer>();
        list2.addToFront(3);
        list2.addToFront(2);
        list2.addToFront(1);
        /* test 1 */
        assertTrue(list2.contains(3));
        /* test many */
        assertFalse(list2.contains(999999));

        /**
         * Test the remove method
         */
        LinkedList<Integer> list3 = new LinkedList<>();
        list3.addToFront(4);
        list3.addToFront(3);
        list3.addToFront(2);
        list3.addToFront(1);
        /* test many */
        list3.remove(5);
        assertEquals("Removing element not in list", "list: 1 2 3 4", list3.toString());
        /* test middle */
        list3.remove(3);
        assertEquals("Removing middle element", "list: 1 2 4", list3.toString());
        /* test first */
        list3.remove(1);
        assertEquals("Removing first element", "list: 2 4", list3.toString());
        /* test last */
        list3.remove(4);
        assertEquals("Removing last element", "list: 2", list3.toString());
        /* test 1 */
        list3.remove(2);
        assertEquals("Removing only element", "list:", list3.toString());
        /* test 0 */
        list3.remove(0);
        assertEquals("Removing from empty list", "list:", list3.toString());

        /**
         * Test lengthFromHere
         */
        LinkedList<Integer> list4 = new LinkedList<>();
        list4.addToFront(4);
        list4.addToFront(3);
        list4.addToFront(2);
        list4.addToFront(1);
        /* test first */
        assertEquals("Checking length from front", 3, list4.getFront().lengthFromHere());
        /* test middle */
        assertEquals("Checking length from middle", 1, list4.getFront().getNext().getNext().lengthFromHere());
        /* test last */
        assertEquals("Checking length from end", 0, list4.getFront().getNext().getNext().getNext().lengthFromHere());
    }
}
