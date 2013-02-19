/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pos.action;

import com.pos.dao.DAOException;
import com.pos.dao.DAOFactory;
import com.pos.dao.MenuItemDAO;
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
    public void save(MenuItem menuItem){
        try{    
            menuItemDAO.insert(menuItem);
        }catch (DAOException e){
            
        }
    }
    
    public List<MenuItem> getMenuItems(){
        List<MenuItem> menuItems = null;
        try{
            menuItems = menuItemDAO.getMenuItems();
        }catch (DAOException e) {}
        return menuItems;
    }
}
