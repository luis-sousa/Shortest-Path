/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package NonLinearStructs;

import Exceptions.EmptyCollectionException;

/* ESCOLA SUPERIOR DE TECNOLOGIA E GESTÃO DE FELGUEIRAS 
 * LEI - 2012/2013
 *
 * Authors: Luís Sousa [8090228] & Rui Vieira [8100451]
 * 
 */
/**
 * HeapADT defines the interface to a Heap.
 * @param <T>
 */
public interface HeapADT<T> extends BinaryTreeADT<T> {

    /** 
     * Adds the specified object to this heap. 
     *
     * @param obj  the element to added to this head
     */
    public void addElement(T obj);

    /** 
     * Removes element with the lowest value from this heap. 
     *
     * @return  the element with the lowest value from this heap
     * @throws Exceptions.EmptyCollectionException
     */
    public T removeMin() throws EmptyCollectionException;

    /** 
     * Returns a reference to the element with the lowest value in 
     * this heap. 
     *
     * @return  a reference to the element with the lowest value in this heap
     * @throws Exceptions.EmptyCollectionException
     */
    public T findMin() throws EmptyCollectionException;
    
    public void removeAllElements() throws EmptyCollectionException;
}
