/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pos.dao;

/**
 *
 * @author Josh
 */
public interface OrderDAO {
    public void addItem(int uid, String name, double price) throws DAOException;
    public int createOrder() throws DAOException;
    public void removeItem(int uid, String name, boolean all) throws DAOException;
    public void removeOrder(int uid) throws DAOException;
}
