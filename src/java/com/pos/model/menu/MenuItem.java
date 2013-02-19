/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pos.model.menu;

import java.io.Serializable;

/**
 *
 * @author Josh
 */
public class MenuItem {
    
    private String name;
    private float price;
    private int categoryUID;
    
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

    public int getCategoryUID() {
        return categoryUID;
    }

    public void setCategoryUID(int categoryUID) {
        this.categoryUID = categoryUID;
    }
    
    
}
