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
public class SubMenu {
    private int uid;
    private SubMenu parent;
    private List<SubMenu> childeren;
    private List<MenuItem> items;
    private String name;
            
    public SubMenu(){}

    public List<SubMenu> getChilderen() {
        return childeren;
    }

    public List<MenuItem> getItems() {
        return items;
    }

    public String getName() {
        return name;
    }

    public SubMenu getParent() {
        return parent;
    }

    public int getUid() {
        return uid;
    }

    public void setChilderen(List<SubMenu> childeren) {
        this.childeren = childeren;
    }

    public void setItems(List<MenuItem> items) {
        this.items = items;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setParent(SubMenu parent) {
        this.parent = parent;
    }
    
    public void setUid(int uid) {
        this.uid = uid;
    }
    
    
}
