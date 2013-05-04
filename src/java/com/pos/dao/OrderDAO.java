/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pos.dao;

import com.pos.model.order.CarryoutOrder;
import com.pos.model.order.DeliveryOrder;
import com.pos.model.order.DineInOrder;
import com.pos.model.order.OrderItemInfoEntry;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Josh
 */
public interface OrderDAO {
    public void addItem(int uid, String name, double price, int itemIndex) throws DAOException;
    public int createOrder() throws DAOException;
    public void cancelOrder(int orderId) throws DAOException;
    public void completeOrder(DeliveryOrder order) throws DAOException;
    public void completeOrder(CarryoutOrder order) throws DAOException;
    public void completeOrder(DineInOrder order) throws DAOException;
    public ArrayList<DeliveryOrder> getDeliveryOrders(boolean active) throws DAOException;
    public ArrayList<CarryoutOrder> getCarryoutOrders(boolean active) throws DAOException;
    public ArrayList<DineInOrder> getDineInOrders(boolean active) throws DAOException;
    public void serveOrder(int orderId) throws DAOException;
    public DeliveryOrder getDeliveryOrder(int orderId) throws DAOException;
    public CarryoutOrder getCarryoutOrder(int orderId) throws DAOException;
    public DineInOrder getDineInOrder(int orderId) throws DAOException;
    public List<OrderItemInfoEntry> getOrderItemInfo(int orderId) throws DAOException;
    public void removeItemFromOrder(int orderId, int itemIndex) throws DAOException;
}
