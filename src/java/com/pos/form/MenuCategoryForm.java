/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pos.form;

import com.pos.model.menu.MenuCategory;

/**
 *
 * @author Josh
 */
public class MenuCategoryForm {
    private String name;
    private String parent;
    
    public MenuCategoryForm(){}

    public String getName() {
        return name;
    }

    public String getParent() {
        return parent;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setParent(String parent) {
        this.parent = parent;
    }

    public MenuCategory getMenuCategoryInstance() {
        MenuCategory menuCategory = new MenuCategory();
        menuCategory.setName(name);
        menuCategory.setParent(parent);
        return menuCategory;
    }
}
