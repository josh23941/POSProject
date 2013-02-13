/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pos.dao;

import java.util.List;
import com.pos.model.MenuItem;

/**
 *
 * @author Josh
 */
public interface MenuItemDAO extends DAO{
    List<MenuItem> getMenuItems() throws DAOException;
    void insert(MenuItem menuItem) throws DAOException;
}
