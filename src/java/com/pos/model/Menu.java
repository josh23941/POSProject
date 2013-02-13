/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pos.model;

import com.pos.dao.DAOException;
import com.pos.dao.DAOFactory;
import com.pos.dao.MenuItemDAO;
import java.util.List;

/**
 *
 * @author Josh
 */
public class Menu {
    private List<MenuItem> items;
    private MenuItemDAO menuItemDAO;
    public Menu(){
        menuItemDAO = DAOFactory.getMenuItemDAO();
    }
    
    public void refreshMenu(){
        try{
           items = menuItemDAO.getMenuItems();
        }catch(DAOException e){
            e.printStackTrace();
        }
    }
    public List<MenuItem> getItems(){
        return items;
    }
}

