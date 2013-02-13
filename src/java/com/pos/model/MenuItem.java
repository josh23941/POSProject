/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pos.model;

import java.io.Serializable;

/**
 *
 * @author Josh
 */
public class MenuItem implements Serializable{
    
    private static final long serialVersionUID = 0L;
    private String name;
    private float price;
    
    public MenuItem(){}
    
    public String getName(){
        return name;
    }
    
    public float getPrice(){
        return price;
    }
    
    public void setName(String n){
        name = n;
    }
    
    public void setPrice(float p){
        price = p;
    }
}
