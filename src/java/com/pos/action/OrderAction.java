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
        orderDAO = DAOFactory.getOrderDAO();
    }
    
    public void addItemToOrder(int uid, String item){
        try{
            orderDAO.addItem(uid, item);
        }catch(DAOException e){
            System.out.println("Error adding item to order: " + e.getMessage());
        }
    }
    
    public void startNewOrder(){
        try{
            orderDAO.createOrder();
        }catch(DAOException e){
            System.out.println("Error adding item to order: " + e.getMessage());
        }
    }
}
