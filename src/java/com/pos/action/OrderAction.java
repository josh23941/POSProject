/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pos.action;

import com.pos.dao.DAOException;
import com.pos.dao.DAOFactory;
import com.pos.dao.OrderDAO;
import com.pos.model.order.CarryoutOrder;
import com.pos.model.order.DeliveryOrder;
import com.pos.model.order.DineInOrder;
import com.pos.model.order.OrderItemInfoEntry;
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
    
    public void addItemToOrder(int uid, String item, double price, int itemIndex){
        try{
            orderDAO.addItem(uid, item, price, itemIndex);
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
    
    public ArrayList<DeliveryOrder> getDeliveryOrders(boolean active){
        ArrayList<DeliveryOrder> list = new ArrayList<DeliveryOrder>();
        try{
            list = orderDAO.getDeliveryOrders(active);
        }catch(DAOException e){
            System.out.println("Error getting delivery orders: " + e.getMessage());
        }
        return list;
    }
    
    public ArrayList<CarryoutOrder> getCarryoutOrders(boolean active){
        ArrayList<CarryoutOrder> list = new ArrayList<CarryoutOrder>();
        try{
            list = orderDAO.getCarryoutOrders(active);
        }catch(DAOException e){
            System.out.println("Error getting carryout orders: " + e.getMessage());
        }
        return list;
    }
    
    public ArrayList<DineInOrder> getDineInOrders(boolean active){
        ArrayList<DineInOrder> list = new ArrayList<DineInOrder>();
        try{
            list = orderDAO.getDineInOrders(active);
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

    public void serveOrder(int orderId) {
        try{
            orderDAO.serveOrder(orderId);
        }catch(DAOException e){
            System.out.println("Error serving order #" + orderId + ": " + e.getMessage());
        }
    }
    
    public DeliveryOrder getDeliveryOrder(int orderId){
        DeliveryOrder order = new DeliveryOrder();
        try{
            order = orderDAO.getDeliveryOrder(orderId);
        }catch(DAOException e){
            System.out.println("Error getting delivery order #" + orderId + ": " + e.getMessage());
        }
        return order;
    }
    
    public CarryoutOrder getCarryoutOrder(int orderId){
        CarryoutOrder order = new CarryoutOrder();
        try{
            order = orderDAO.getCarryoutOrder(orderId);
        }catch(DAOException e){
            System.out.println("Error getting carryout order #" + orderId + ": " + e.getMessage());
        }
        return order;
    }
    
    public DineInOrder getDineInOrder(int orderId){
        DineInOrder order = new DineInOrder();
        try{
            order = orderDAO.getDineInOrder(orderId);
        }catch(DAOException e){
            System.out.println("Error getting dine in order #" + orderId + ": " + e.getMessage());
        }
        return order;
    }
    
    public String getOrderItemInfoJSON(int orderId){
        String json = "{'items':[";
        try{
            ArrayList<OrderItemInfoEntry> list = (ArrayList)orderDAO.getOrderItemInfo(orderId);
            for(OrderItemInfoEntry entry : list){
                json += "{'description':'" + entry.getDescription() 
                        + "','unitPrice':'" + entry.getUnitPrice() 
                        + "','itemIndex':'" + entry.getItemIndex() + "'},";
            }
            if(!list.isEmpty()){
                json = json.substring(0, json.length()-1);
            }
            json += "]}";
        }catch(DAOException e){
            System.out.println("Error getting order item info for order #" + orderId + ": " + e.getMessage());
        }
        return json;
    }
    
    public void removeItemFromOrder(int orderId, int itemIndex){
        try{
            orderDAO.removeItemFromOrder(orderId, itemIndex);
        }catch(DAOException e){
            System.out.println("Error removing item " + itemIndex + " from order #" + orderId + ": " + e.getMessage());
        }
    }
}
