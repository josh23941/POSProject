/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pos.dao;

import com.pos.model.menu.MenuCategory;
import java.util.List;
import com.pos.model.menu.MenuItem;

/**
 *
 * @author Josh
 */
public interface MenuItemDAO extends DAO{
    public List<MenuItem> getMenuItems() throws DAOException;
    public void saveMenuItem(MenuItem menuItem) throws DAOException;
    public void saveMenuCategory(MenuCategory menuCategory) throws DAOException;
    public List<MenuCategory> getMenuCategories() throws DAOException;
}
