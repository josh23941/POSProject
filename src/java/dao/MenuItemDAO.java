/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import model.MenuItem;

/**
 *
 * @author Josh
 */
public interface MenuItemDAO extends DAO{
    List<MenuItem> getMenuItems() throws DAOException;
    void insert(MenuItem menuItem) throws DAOException;
}
