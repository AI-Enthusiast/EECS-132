import java.util.Iterator;

/**
 * Class that is the iterator for Linked List
 *
 * @author Cormac Dacker
 * @since 11/28/2016
 */
public interface LLIterator<T> extends Iterator<T> {

    /**
     * Allows for nodes to be added before and after given location in the LinkedList
     *
     * @param <T> Generic that represents the type of the elements within the nodes
     */
    public class ClassyLLIterator<T> extends LinkedList implements LLIterator<T> {


        /**
         * Points to the current node of the iteration
         */
        private LLNode<T> nodePointer;

        /**
         * A second pointer the points to the front initially then two behind variable nodePointer
         */
        private LLNode<T> secondNodePointer;

        /**
         * Create an iterator for the list starting at the inputted node
         *
         * @param front the first node for the iteration
         */
        public ClassyLLIterator(LLNode<T> front) {
            this.nodePointer = front;
            this.secondNodePointer = front;
        }

        /**
         * Adds the inputted element before the current location of the nodePointer
         *
         * @param element the element of the node that will be added
         */
        public void addBefore(T element) {
            getSecondNodePointer().insertAfter(element);
        }

        /**
         * Adds the inputted element after the current location of the nodePointer
         *
         * @param element the element of the node that will be added
         */
        public void addAfter(T element) {
            getSecondNodePointer().getNext().insertAfter(element);
        }

        /**
         * Getter for the first node pointer
         *
         * @return LLNode representing nodePointer
         */
        public LLNode<T> getNodePointer() {
            return nodePointer;
        }

        /**
         * Getter for the second node pointer
         *
         * @return LLNode representing secondNodePointer
         */
        public LLNode<T> getSecondNodePointer() {
            followNodePointer();
            return secondNodePointer;
        }

        /**
         * Setter for the second node pointer
         *
         * @param secondNodePointer
         */
        private void setSecondNodePointer(LLNode<T> secondNodePointer) {
            this.secondNodePointer = secondNodePointer;
        }

        /**
         * Updates the secondNodePointer[s] location so that is (usually) two behind the nodePointer
         */
        public void followNodePointer() {
            /*
             * Loop runs until the nodePointer is two nodes ahead of the secondNodePointer.
             * The goal of the loop is to follow the nopePointer two places behind or one
             * place if node pointer is one the end of the liked list.
             */
            while (!getSecondNodePointer().getNext().getNext().equals(getNodePointer()) ||
                    !getNodePointer().equals(getFront().getNext()) ||
                    (getNodePointer().getNext() == null && !getSecondNodePointer().getNext().equals(getNodePointer()))) {
                setSecondNodePointer(getSecondNodePointer().getNext());
            }
        }

        /**
         * Determines whether the input is equal to the current linked list
         *
         * @param o Object that is potentially equal to this
         * @return boolean representing if equal or not
         */
        public boolean equals(Object o) {
            /* If the inputted object is an instance of LLNode */
            if (o instanceof LLNode) {
                LLNode<T> node = (LLNode<T>) o;
                return (node.getElement() == this);
            }
            return false;
        }

        /**
         * Returns true if there are more nodes to run through
         *
         * @return true if there are still more values in the iteration
         */
        public boolean hasNext() {
            return nodePointer != null;
        }

        /**
         * Returns the next value stored in the linked list
         *
         * @return the next value of the linked list
         */
        public T next() {
            T save = nodePointer.getElement();
            this.nodePointer = nodePointer.getNext();
            return save;
        }

        /**
         * Not implemented
         */
        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }
}

