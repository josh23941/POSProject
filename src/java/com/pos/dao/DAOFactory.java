/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pos.dao;

/**
 *
 * @author Josh
 */
public class DAOFactory {
    public static MenuItemDAO getMenuItemDAO(){
        return new MenuItemDAOImpl();
    }
    
    public static UserDAO getUserDAO(String username, String password){
        return new UserDAOImpl(username, password);
    }
    
    //@todo get rid of the other version of this soon.
    public static UserDAO getUserDAO(){
        return new UserDAOImpl();
    }
}
