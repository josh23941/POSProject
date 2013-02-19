/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pos.form;

import com.pos.model.menu.MenuItem;

/**
 *
 * @author Josh
 */
public class MenuItemForm {
    private String name;
    private String price;
    private String categoryUID;
    
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

    public String getCategoryUID() {
        return categoryUID;
    }

    public void setCategoryUID(String categoryUID) {
        this.categoryUID = categoryUID;
    }
    
    public MenuItem getMenuItemInstance(){
        MenuItem menuItem = new MenuItem();
        menuItem.setName(name);
        menuItem.setPrice(Float.parseFloat(price));
        menuItem.setCategoryUID(Integer.parseInt(categoryUID));
        return menuItem;
    }
}
