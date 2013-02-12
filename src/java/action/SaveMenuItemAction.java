/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package action;

import dao.DAOException;
import dao.DAOFactory;
import dao.MenuItemDAO;
import model.MenuItem;

/**
 *
 * @author Josh
 */
public class SaveMenuItemAction {
    public void save(MenuItem menuItem){
        try{
            MenuItemDAO menuItemDAO = DAOFactory.getMenuItemDAO();
            menuItemDAO.insert(menuItem);
        }catch (DAOException e){
            e.printStackTrace();
        }
    }
}
