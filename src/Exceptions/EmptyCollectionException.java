/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Exceptions;

/* ESCOLA SUPERIOR DE TECNOLOGIA E GESTÃO DE FELGUEIRAS 
 * LEI - 2012/2013
 *
 * Authors: Luís Sousa [8090228] & Rui Vieira [8100451]
 * 
 */
/**
 * EmptyCollectionException represents the situation in which a 
 * collection is empty.
 */
public class EmptyCollectionException extends Exception {

    public EmptyCollectionException(String message) {
        super(message);
    }
}
