/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pos.form;

/**
 *
 * @author Josh
 */
public class MenuItemForm {
    private String name;
    private String price;
    
    public MenuItemForm(){}
    
    public String getName(){
        return name;
    }
    
    public String getPrice(){
        return price;
    }
    
    public void setName(String n){
        name = n;
    }
    
    public void setPrice(String p){
        price = p;
    }
}
