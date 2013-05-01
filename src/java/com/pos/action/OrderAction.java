/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pos.action;

import com.pos.dao.DAOException;
import com.pos.dao.DAOFactory;
import com.pos.dao.OrderDAO;
import com.pos.model.menu.CarryoutOrder;
import com.pos.model.menu.DeliveryOrder;
import com.pos.model.menu.DineInOrder;
import java.util.ArrayList;

/**
 *
 * @author Josh
 */
public class OrderAction {
    private OrderDAO orderDAO;
    
    public OrderAction(){
        try{
            orderDAO = DAOFactory.getOrderDAO();
        }catch(DAOException e){
            System.out.println("Error Accessing Order Table: " + e.getMessage());
        }
    }
    
    public void addItemToOrder(int uid, String item, double price){
        try{
            orderDAO.addItem(uid, item, price);
        }catch(DAOException e){
            System.out.println("Error adding item to order: " + e.getMessage());
        }
    }
    
    public int startNewOrder(){
        Integer orderId = null;
        try{
            orderId = orderDAO.createOrder();
        }catch(DAOException e){
            System.out.println("Error starting new order: " + e.getMessage());
        }
        return orderId;
    }
    
    public void completeOrder(DeliveryOrder order){
        try{
            orderDAO.completeOrder(order);
        }catch(DAOException e){
            System.out.println("Error completing order: " + e.getMessage());
        }
    }
    
    public void completeOrder(CarryoutOrder order){
        try{
            orderDAO.completeOrder(order);
        }catch(DAOException e){
            System.out.println("Error completing order: " + e.getMessage());
        }
    }
    
    public void completeOrder(DineInOrder order){
        try{
            orderDAO.completeOrder(order);
        }catch(DAOException e){
            System.out.println("Error completing order: " + e.getMessage());
        }
    }
    
    public ArrayList<DeliveryOrder> getDeliveryOrders(){
        ArrayList<DeliveryOrder> list = new ArrayList<DeliveryOrder>();
        try{
            list = orderDAO.getDeliveryOrders();
        }catch(DAOException e){
            System.out.println("Error getting delivery orders: " + e.getMessage());
        }
        return list;
    }
    
    public ArrayList<CarryoutOrder> getCarryoutOrders(){
        ArrayList<CarryoutOrder> list = new ArrayList<CarryoutOrder>();
        try{
            list = orderDAO.getCarryoutOrders();
        }catch(DAOException e){
            System.out.println("Error getting carryout orders: " + e.getMessage());
        }
        return list;
    }
    
    public ArrayList<DineInOrder> getDineInOrders(){
        ArrayList<DineInOrder> list = new ArrayList<DineInOrder>();
        try{
            list = orderDAO.getDineInOrders();
        }catch(DAOException e){
            System.out.println("Error getting dine in orders: " + e.getMessage());
        }
        return list;
    }
    
    public void cancelOrder(int orderId){
        try{
            orderDAO.cancelOrder(orderId);
        }catch(DAOException e){
            System.out.println("Error cancelling order #" + orderId + ": " + e.getMessage());
        }
    }
}
