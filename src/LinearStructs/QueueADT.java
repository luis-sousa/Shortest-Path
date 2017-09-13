/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package LinearStructs;

import Exceptions.EmptyCollectionException;

/* ESCOLA SUPERIOR DE TECNOLOGIA E GESTÃO DE FELGUEIRAS 
 * LEI - 2012/2013
 *
 * Authors: Luís Sousa [8090228] & Rui Vieira [8100451]
 * 
 */
/**
 * QueueADT defines the interface to a queue collection.
 */
public interface QueueADT<T> {

    /**
     * Adds one element to the rear of the queue
     *
     * @param element  the element to be added to the rear of this queue
     */
    public void enqueue(T element);

    /**
     * Removes and returns the element at the front of the queue
     *
     * @return  the element at the front of this queue
     */
    public T dequeue() throws EmptyCollectionException;

    /**
     * Returns without removing the element at the front of the queue
     *
     * @return  the element at the front of this queue
     */
    public T first() throws EmptyCollectionException;

    /**
     * Returns true if the queue contains no elements
     *
     * @return  true if the queue contains no elements
     */
    public boolean isEmpty();

    /**
     * Returns the number of elements in the queue
     *
     * @return  the integer number of elements in this queue
     */
    public int size();

    /**
     * Returns a string representation of the queue
     *
     * @return  a string representation fo the queue
     */
    @Override
    public String toString();
}
