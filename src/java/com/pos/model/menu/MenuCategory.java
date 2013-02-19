/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pos.model.menu;

import java.util.List;

/**
 *
 * @author Josh
 */
public class MenuCategory {
    //@todo review if I will use all of these vars.
    private int uid;
    //private MenuCategory parent;
    private String parent;
    private List<MenuCategory> childeren;
    private List<MenuItem> items;
    private String name;
            
    public MenuCategory(){}

    public List<MenuCategory> getChilderen() {
        return childeren;
    }

    public List<MenuItem> getItems() {
        return items;
    }

    public String getName() {
        return name;
    }
    /*
    public MenuCategory getParent() {
        return parent;
    }
    */
    public String getParent(){
        return parent;
    }
    
    public int getUid() {
        return uid;
    }

    public void setChilderen(List<MenuCategory> childeren) {
        this.childeren = childeren;
    }

    public void setItems(List<MenuItem> items) {
        this.items = items;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setParent(String parent) {
        this.parent = parent;
    }
    
    public void setUid(int uid) {
        this.uid = uid;
    }
    
    
}
