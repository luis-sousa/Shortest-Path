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
 * ElementNotFoundException represents the situation in which a 
 * target element is not present in a collection
 */
public class ElementNotFoundException extends Exception {

    public ElementNotFoundException(String message) {
        super(message);
    }
}
