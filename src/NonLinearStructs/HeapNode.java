/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package NonLinearStructs;

/* ESCOLA SUPERIOR DE TECNOLOGIA E GESTÃO DE FELGUEIRAS 
 * LEI - 2012/2013
 *
 * Authors: Luís Sousa [8090228] & Rui Vieira [8100451]
 * 
 */
/**
 * HeapNode creates a binary tree node with a parent pointer for use 
 * in heaps.
 * @param <T>
 */
public class HeapNode<T> extends BinaryTreeNode<T> {

    protected HeapNode<T> parent;

    /**
     * Creates a new heap node with the specified data.
     * 
     * @param obj  the data to be contained within the new heap nodes
     */
    HeapNode(T obj) {
        super(obj);
        parent = null;
    }
}
