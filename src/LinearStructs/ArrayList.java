/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package LinearStructs;

import Exceptions.ElementNotFoundException;
import Exceptions.EmptyCollectionException;
import java.util.Iterator;
import java.util.NoSuchElementException;

/* ESCOLA SUPERIOR DE TECNOLOGIA E GESTÃO DE FELGUEIRAS 
 * LEI - 2012/2013
 *
 * Authors: Luís Sousa [8090228] & Rui Vieira [8100451]
 * 
 */
/** ArrayList represents an array implementation of a list. The front of
 * the list is kept at array index 0. This class will be extended
 * to create a specific kind of list.
 * 
 */
public class ArrayList<T> implements ListADT<T> {

    protected final int DEFAULT_CAPACITY = 100;
    private final int NOT_FOUND = -1;
    protected int rear;
    protected T[] list;

    /**
     * Creates an empty list using the default capacity.
     */
    public ArrayList() {
        rear = 0;
        list = (T[]) (new Object[DEFAULT_CAPACITY]);
    }

    /**
     * Creates an empty list using the specified capacity.
     *
     * @param initialCapacity  the initial capacity of the list
     */
    public ArrayList(int initialCapacity) {
        rear = 0;
        list = (T[]) (new Object[initialCapacity]);
    }

    /**
     * Removes and returns the last element in the list.
     *
     * @return                           the last element in the list
     * @throws EmptyCollectionException  if an empty collection exception occurs
     */
    @Override
    public T removeLast() throws EmptyCollectionException {
        T result;

        if (isEmpty()) {
            throw new EmptyCollectionException("Lista Vazia!!!");
        }

        rear--;
        result = list[rear];
        list[rear] = null;

        return result;
    }

    /**
     * Removes and returns the first element in the list.
     *
     * @return                           the first element in the list
     * @throws EmptyCollectionException  if an empty collection exception occurs
     */
    @Override
    public T removeFirst() throws EmptyCollectionException {
        if (isEmpty()) {
            throw new EmptyCollectionException("Lista Vazia!!!");
        }

        T result = list[0];
        rear--;
        /** shift the elements */
        for (int scan = 0; scan < rear; scan++) {
            list[scan] = list[scan + 1];
        }

        list[rear] = null;

        return result;
    }

    /**
     * Removes and returns the specified element.
     *
     * @return         the specified element
     * @param element  the desired element
     */
    @Override
    public T remove(T element) throws ElementNotFoundException {
        T result;
        int index = find(element);

        if (index == NOT_FOUND) {
            throw new ElementNotFoundException("Elemento nao Encontrado!!!");
        }

        result = list[index];
        rear--;
        /** shift the appropriate elements */
        for (int scan = index; scan < rear; scan++) {
            list[scan] = list[scan + 1];
        }

        list[rear] = null;

        return result;
    }

    /**
     * Returns a reference to the element at the front of the list.
     * The element is not removed from the list.  Throws an
     * EmptyCollectionException if the list is empty.  
     *
     * @return                           a reference to the first element in the 
     *                                   list
     * @throws EmptyCollectionException  if an empty collection exception occurs
     */
    @Override
    public T first() throws EmptyCollectionException {
        if (isEmpty()) {
            throw new EmptyCollectionException("Lista Vazia!!!");
        }

        return list[0];
    }

    /**
     * Returns a reference to the element at the rear of the list.
     * The element is not removed from the list.  Throws an
     * EmptyCollectionException if the list is empty.  
     *
     * @return                           a reference to the last element in this 
     *                                   list
     * @throws EmptyCollectionException  if an empty collection exception occurs
     */
    @Override
    public T last() throws EmptyCollectionException {
        if (isEmpty()) {
            throw new EmptyCollectionException("Lista Vazia!!!");
        }

        return list[rear - 1];
    }

    /**
     * Returns true if this list contains the specified element.
     *
     * @param target  the element being sought in the list
     * @return        true if the list contains the desired element
     */
    @Override
    public boolean contains(T target) {
        return (find(target) != NOT_FOUND);
    }

    /**
     * Returns the array index of the specified element, or the
     * constant NOT_FOUND if it is not found.
     *
     * @param target  the element being sought in this list
     * @return        the integer index of the element, or the constant
     *                NOT_FOUND
     */
    private int find(T target) {
        int scan = 0, result = NOT_FOUND;
        boolean found = false;

        if (!isEmpty()) {
            while (!found && scan < rear) {
                if (target.equals(list[scan])) {
                    found = true;
                } else {
                    scan++;
                }
            }
        }

        if (found) {
            result = scan;
        }

        return result;
    }

    /**
     * Returns true if this list is empty and false otherwise. 
     *
     * @return  true if this list is empty
     */
    @Override
    public boolean isEmpty() {
        return (rear == 0);
    }

    /**
     * Returns the number of elements currently in this list.
     *
     * @return  the integer number of elements in this list
     */
    @Override
    public int size() {
        return rear;
    }

    /**
     * Returns an iterator for the elements currently in this list.
     *
     * @return  an iterator over the elements in this list
     */
    @Override
    public Iterator<T> iterator() {
        return new ArrayIterator<T>(list, rear);
    }

    /**
     * Returns a string representation of this list. 
     *
     * @return  a string representation of this list
     */
    @Override
    public String toString() {
        String result = "";

        for (int scan = 0; scan < rear; scan++) {
            result = result + list[scan].toString() + "\n";
        }

        return result;
    }

    /**
     * Creates a new array to store the contents of the list with
     * twice the capacity of the old one.
     */
    protected void expandCapacity() {
        T[] larger = (T[]) (new Object[list.length * 2]);
        System.arraycopy(list, 0, larger, 0, list.length);

        list = larger;
    }

    protected class ArrayIterator<T> implements Iterator {

        private int count;    // the number of elements in the collection
        private int current;  // the current position in the iteration 

        /**
         * Sets up this iterator using the specified items.
         *
         * @param collection  the collection for which the iterator will be created
         * @param size        the size of the collection
         */
        public ArrayIterator(T[] collection, int size) {
            count = size;
            current = 0;
        }

        /**
         * Returns true if this iterator has at least one more element
         * to deliver in the iteraion.
         *
         * @return  true if this iterator has at least one more element to deliver
         */
        @Override
        public boolean hasNext() {
            return (current < count);
        }

        /**
         * Returns the next element in the iteration. If there are no
         * more elements in this itertion, a NoSuchElementException is
         * thrown.
         *
         * @return                         the next element in the iteration
         * @throws NoSuchElementException  if a no such element exception occurs
         */
        @Override
        public T next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            current++;
            return (T) list[current - 1];
        }

        /**
         * The remove operation is not supported in this collection.
         *
         * @throws UnsupportedOperationException  if an unsupported operation
         *                                        exception occurs
         */
        @Override
        public void remove() throws UnsupportedOperationException {
            throw new UnsupportedOperationException();
        }
    }
}