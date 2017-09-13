/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package LinearStructs;

import LinearStructs.LinearNode;
import Exceptions.EmptyCollectionException;

/* ESCOLA SUPERIOR DE TECNOLOGIA E GESTÃO DE FELGUEIRAS 
 * LEI - 2012/2013
 *
 * Authors: Luís Sousa [8090228] & Rui Vieira [8100451]
 * 
 */
/**
 * LinkedQueue represents a linked implementation of a queue.
 */
public class LinkedQueue<T> implements QueueADT<T> {

    private int count;
    private LinearNode<T> front, rear;

    /**
     * Creates an empty queue.
     */
    public LinkedQueue() {
        count = 0;
        front = rear = null;
    }

    /**
     * Adds the specified element to the rear of the queue.
     *
     * @param element  the element to be added to the rear of the queue
     */
    @Override
    public void enqueue(T element) {
        LinearNode<T> node = new LinearNode<T>(element);

        if (isEmpty()) {
            front = node;
        } else {
            rear.setNext(node);
        }

        rear = node;
        count++;
    }

    /**
     * Removes the element at the front of the queue and returns a
     * reference to it. Throws an EmptyCollectionException if the
     * queue is empty.
     *
     * @return                           a reference to the element removed 
     *                                   from the front of the queue
     * @throws EmptyCollectionException  if an empty collection exception
     *                                   occurs
     */
    @Override
    public T dequeue() throws EmptyCollectionException {
        if (isEmpty()) {
            throw new EmptyCollectionException("queue");
        }

        T result = front.getElement();
        front = front.getNext();
        count--;

        if (isEmpty()) {
            rear = null;
        }

        return result;
    }

    /**
     * Returns a reference to the element at the front of the queue.
     * The element is not removed from the queue.  Throws an
     * EmptyCollectionException if the queue is empty.  
     *
     * @return                           a reference to the element at the 
     *                                   front of the queue
     * @throws EmptyCollectionException  if an empty collection exception
     *                                   occurs
     */
    @Override
    public T first() throws EmptyCollectionException {
        if (isEmpty()) {
            throw new EmptyCollectionException("queue");
        }

        return front.getElement();
    }

    /**
     * Returns true if this queue is empty and false otherwise. 
     *
     * @return  true if this queue is empty
     */
    @Override
    public boolean isEmpty() {
        return (count == 0);
    }

    /**
     * Returns the number of elements currently in this queue.
     *
     * @return  the number of elements currently in this queue
     */
    @Override
    public int size() {
        return count;
    }

    /**
     * Returns a string representation of this queue. 
     *
     * @return  a string representation of this queue
     */
    @Override
    public String toString() {
        String result = "";
        LinearNode<T> current = front;

        while (current != null) {
            result = result + (current.getElement()).toString() + "\n";
            current = current.getNext();
        }

        return result;
    }
}
