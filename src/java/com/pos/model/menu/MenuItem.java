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
    private int submenuUID;
    
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

    public int getSubmenuUID() {
        return submenuUID;
    }

    public void setSubmenuUID(int submenuUID) {
        this.submenuUID = submenuUID;
    }
    
    
}
