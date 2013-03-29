/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pos.dao;

import com.pos.model.menu.MenuCategory;
import com.pos.model.menu.MenuCategoryTreeNode;
import java.util.List;

/**
 *
 * @author Josh
 */
public interface MenuCategoryDAO {
    public MenuCategoryTreeNode getRootNode() throws DAOException;
    public void saveMenuCategory(MenuCategory menuCategory) throws DAOException;
    public List<MenuCategory> getMenuCategories() throws DAOException;
}
