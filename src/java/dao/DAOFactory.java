/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

/**
 *
 * @author Josh
 */
public class DAOFactory {
    public static MenuItemDAO getMenuItemDAO(){
        return new MenuItemDAOImpl();
    }
}
