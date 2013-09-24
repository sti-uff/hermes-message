/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.uff.sti.hermes.api.base.exception;

/**
 *
 * @author Daniel
 */
public class ApiException extends Exception {

    /**
     * Creates a new instance of
     * <code>ObjectNotFoundException</code> without detail message.
     */
    public ApiException() {
    }

    /**
     * Constructs an instance of
     * <code>ObjectNotFoundException</code> with the specified detail message.
     *
     * @param msg the detail message.
     */
    public ApiException(String msg) {
        super(msg);
    }
    
    public ApiException(Exception ex) {
        super(ex);
    }
    
    public ApiException(String msg, Exception ex) {
        super(msg, ex);
    }
}
