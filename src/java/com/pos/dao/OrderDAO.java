/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pos.dao;

import com.pos.model.menu.CarryoutOrder;
import com.pos.model.menu.DeliveryOrder;
import com.pos.model.menu.DineInOrder;
import java.util.ArrayList;

/**
 *
 * @author Josh
 */
public interface OrderDAO {
    public void addItem(int uid, String name, double price) throws DAOException;
    public int createOrder() throws DAOException;
    public void cancelOrder(int orderId) throws DAOException;
    public void completeOrder(DeliveryOrder order) throws DAOException;
    public void completeOrder(CarryoutOrder order) throws DAOException;
    public void completeOrder(DineInOrder order) throws DAOException;
    public ArrayList<DeliveryOrder> getDeliveryOrders(boolean active) throws DAOException;
    public ArrayList<CarryoutOrder> getCarryoutOrders(boolean active) throws DAOException;
    public ArrayList<DineInOrder> getDineInOrders(boolean active) throws DAOException;
    public void serveOrder(int orderId) throws DAOException;
}
