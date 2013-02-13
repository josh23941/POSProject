/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pos.action;

import com.pos.dao.DAOException;
import com.pos.dao.DAOFactory;
import com.pos.dao.MenuItemDAO;
import com.pos.model.MenuItem;
import java.util.List;

/**
 *
 * @author Josh
 */
public class GetMenuItemsAction {
    public List<MenuItem> getMenuItems(){
        MenuItemDAO menuItemDAO = DAOFactory.getMenuItemDAO();
        List<MenuItem> menuItems = null;
        try{
            menuItems = menuItemDAO.getMenuItems();
        }catch (DAOException e) {}
        return menuItems;
    }
}