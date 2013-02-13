/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pos.dao;

/**
 *
 * @author Josh
 */
public class DAOException extends Exception {
    private static final long serialVersionUID = 2L;
    private String message;
    
    public DAOException(){}
    
    public DAOException(String message){
        this.message = message;
    }
    
    public String getMessage(){
        return message;
    }
    
    public void setMessage(String message){
        this.message = message;
    }
    
    public String toString(){
        return message;
    }
}
