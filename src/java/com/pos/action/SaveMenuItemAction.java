/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pos.action;

import com.pos.dao.DAOException;
import com.pos.dao.DAOFactory;
import com.pos.dao.MenuItemDAO;
import com.pos.model.MenuItem;

/**
 *
 * @author Josh
 */
public class SaveMenuItemAction {
    public void save(MenuItem menuItem){
        MenuItemDAO menuItemDAO = DAOFactory.getMenuItemDAO();
        try{    
            menuItemDAO.insert(menuItem);
        }catch (DAOException e){
            e.printStackTrace();
        }
    }
}
