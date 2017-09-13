/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Graph;

import Exceptions.EmptyCollectionException;

/* ESCOLA SUPERIOR DE TECNOLOGIA E GESTÃO DE FELGUEIRAS 
 * LEI - 2012/2013
 *
 * Authors: Luís Sousa [8090228] & Rui Vieira [8100451]
 * 
 */
/**
 * NetworkADT defines the interface to a network.
 */
public interface NetworkADT<T> extends GraphADT<T> {

    /** 
     * Inserts an edge between two vertices of this graph. 
     *
     * @param vertex1  the first vertex
     * @param vertex2  the second vertex
     * @param weight   the weight 
     */
    public void addEdge(T vertex1, T vertex2, Weight weight);

    /** 
     * Returns the weight of the shortest path in this network. 
     *
     * @param vertex1  the first vertex
     * @param vertex2  the second vertex
     * @return         the weight of the shortest path in this network
     */
    public double shortestPathWeight(T vertex1, T vertex2) throws EmptyCollectionException;
}
