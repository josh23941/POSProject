/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pos.action;

import com.pos.dao.DAOException;
import com.pos.dao.DAOFactory;
import com.pos.dao.OrderDAO;

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
            System.out.println("Error adding item to order: " + e.getMessage());
        }
        return orderId;
    }
}
