/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pos.action;

import com.pos.dao.DAOException;
import com.pos.dao.DAOFactory;
import com.pos.dao.MenuItemDAO;
import com.pos.model.menu.MenuCategory;
import com.pos.model.menu.MenuItem;
import java.util.List;

/**
 *
 * @author Josh
 */
public class MenuItemAction {
    
    private MenuItemDAO menuItemDAO;
    
    public MenuItemAction(){
        menuItemDAO = DAOFactory.getMenuItemDAO();
    }
    public void saveMenuItem(MenuItem menuItem){
        try{    
            menuItemDAO.saveMenuItem(menuItem);
        }catch (DAOException e){
            System.out.println(e.getMessage());
        }
    }
    
    public List<MenuItem> getMenuItems(){
        List<MenuItem> menuItems = null;
        try{
            menuItems = menuItemDAO.getMenuItems();
        }catch (DAOException e) {
            System.out.println(e.getMessage());
        }
        return menuItems;
    }
    
    public void saveMenuCategory(MenuCategory menuCategory){
        try{
            menuItemDAO.saveMenuCategory(menuCategory);
        }catch (DAOException e){
            System.out.println(e.getMessage());
        }
    }
    
    public List<MenuCategory> getMenuCategories(){
        List<MenuCategory> menuCategoryList = null;
        try{
            menuCategoryList = menuItemDAO.getMenuCategories();
        }catch (DAOException e){
            System.out.println(e.getMessage());
        }
        return menuCategoryList;
    }
}
