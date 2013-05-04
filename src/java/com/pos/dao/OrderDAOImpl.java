/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pos.dao;

import com.pos.model.order.CarryoutOrder;
import com.pos.model.order.DeliveryOrder;
import com.pos.model.order.DineInOrder;
import com.pos.model.order.OrderItemInfoEntry;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Josh
 */
public class OrderDAOImpl extends BaseDAO implements OrderDAO{
    private static final String ADD_ITEM_TO_ORDER_SQL = "INSERT INTO orders VALUES (?,?,?,?)";
    private static final String CREATE_ORDER_SQL = "INSERT INTO order_ids VALUES (?,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,1)";
    private static final String GET_ORDER_IDS_SQL = "SELECT order_id from order_ids";
    private static final String COMPLETE_DELIVERY_ORDER_SQL = "UPDATE order_ids SET " + 
            "serveType = ?, address = ?, phone = ?, wantTime = ?, subtotal = ?, tax = ?, total = ?, time = ? WHERE order_id = ?";
    private static final String COMPLETE_CARRYOUT_ORDER_SQL = "UPDATE order_ids SET " +
            "serveType = ?, name = ?, phone = ?, wantTime = ?, subtotal = ?, tax = ?, total = ?, time = ? WHERE order_id = ?";
    private static final String COMPLETE_DINEIN_ORDER_SQL = "UPDATE order_ids SET " +
            "serveType = ?, tableNumber = ?, subtotal = ?, tax = ?, total = ?, time = ? WHERE order_id = ?";
    private static final String GET_DELIVERY_ORDERS_SQL = "SELECT order_id, address, phone, wantTime, tax, subTotal, total, time FROM order_ids WHERE serveType=\"delivery\" AND active=?";
    private static final String GET_CARRYOUT_ORDERS_SQL = "SELECT order_id, name, phone, wantTime, tax, subTotal, total, time FROM order_ids WHERE serveType=\"carryout\" AND active=?";
    private static final String GET_DINEIN_ORDERS_SQL = "SELECT order_id, tableNumber, tax, subTotal, total, time FROM order_ids WHERE serveType=\"dinein\" AND active=?";
    private static final String CANCEL_ORDER_SQL = "DELETE FROM orders WHERE order_id = ?";
    private static final String RESET_ORDER_ID_SQL = "UPDATE order_ids SET " +
            "serveType = NULL, address = NULL, phone = NULL, wantTime = NULL, tableNumber = NULL, subtotal = NULL, tax = NULL, total = NULL, time = NULL WHERE order_id = ?";
    private static final String SERVE_ORDER_SQL = "UPDATE order_ids SET active=0 WHERE order_id=?";
    private static final String GET_ORDER_BY_ID_SQL = "SELECT * FROM order_ids WHERE order_id=?";
    private static final String GET_ORDER_ITEM_INFO_SQL = "SELECT * FROM orders WHERE order_id=?";
    private static final String REMOVE_ITEM_FROM_ORDER_SQL = "DELETE FROM orders WHERE order_id=? AND item_index=?";
    private Connection connection = null;
    private PreparedStatement pStatement = null;
    private ResultSet resultSet = null;
    private static boolean firstLoad = true;
    private static ArrayList assignedOrderIds;
    
    public OrderDAOImpl() throws DAOException{
        
        //If this is the first access to order DB make sure existing orders are 
        //kept off of the available order_id array.  After first load this doesn't need to 
        //run, simply get the last int in the list and increment it for the next available order_id in the DB.
        if(firstLoad){
            assignedOrderIds = new ArrayList();
            try{
                connection = getConnection();
                pStatement = connection.prepareStatement(GET_ORDER_IDS_SQL);
                resultSet = pStatement.executeQuery();
                while (resultSet.next()){
                    int currentId = resultSet.getInt("order_id");
                    assignedOrderIds.add(currentId);
                    
                }
                if(!assignedOrderIds.isEmpty()){
                    Object[] toSort = assignedOrderIds.toArray();
                    Arrays.sort(toSort);
                    assignedOrderIds = new ArrayList();
                    assignedOrderIds.addAll(Arrays.asList(toSort));
                }
            }catch (SQLException e){
                throw new DAOException(e.getMessage());
            }finally{
                closeDBObjects(resultSet, pStatement, connection);
            }
            firstLoad = false;
        }
    }
    
    @Override
    public void addItem(int uid, String name, double price, int itemIndex) throws DAOException{
        try{
            connection = getConnection();
            pStatement = connection.prepareStatement(ADD_ITEM_TO_ORDER_SQL);
            pStatement.setInt(1, uid);
            pStatement.setString(2, name);
            pStatement.setDouble(3, price);
            pStatement.setInt(4, itemIndex);
            pStatement.execute();
        }catch(SQLException e){
            throw new DAOException(e.getMessage());
        }finally{
            closeDBObjects(resultSet, pStatement, connection);
        }
        
    }

    @Override
    public int createOrder() throws DAOException {
        Integer newId = null;
        try{
            connection = getConnection();
            pStatement = connection.prepareStatement(CREATE_ORDER_SQL);
            if(!assignedOrderIds.isEmpty()){
                newId = (Integer)assignedOrderIds.get(assignedOrderIds.size()-1);
                newId++;
                assignedOrderIds.add(newId);      
            }
            else{
                newId = 0;
                assignedOrderIds.add(newId);
            }
            pStatement.setInt(1, newId);
            pStatement.execute();
        }catch(SQLException e){
            throw new DAOException(e.getMessage());
        }finally{
            closeDBObjects(resultSet, pStatement, connection);
        }
        return newId;
    }

    @Override
    public void completeOrder(DeliveryOrder order) throws DAOException {
        try{
            connection = getConnection();
            pStatement = connection.prepareStatement(COMPLETE_DELIVERY_ORDER_SQL);
            pStatement.setString(1, "delivery");
            pStatement.setString(2, order.getAddress());
            pStatement.setString(3, order.getPhoneNumber());
            pStatement.setString(4, order.getWantTime());
            pStatement.setDouble(5, order.getSubTotal());
            pStatement.setDouble(6, order.getTax());
            pStatement.setDouble(7, order.getTotalPrice());
            pStatement.setLong(8, order.getTimeStamp());
            pStatement.setInt(9, order.getOrderId());
            pStatement.execute();
        }catch(SQLException e){
            throw new DAOException(e.getMessage());
        }finally{
            closeDBObjects(resultSet, pStatement, connection);
        }
    }

    @Override
    public void completeOrder(CarryoutOrder order) throws DAOException {
        try{
            connection = getConnection();
            pStatement = connection.prepareStatement(COMPLETE_CARRYOUT_ORDER_SQL);
            pStatement.setString(1, "carryout");
            pStatement.setString(2, order.getName());
            pStatement.setString(3, order.getPhoneNumber());
            pStatement.setString(4, order.getWantTime());
            pStatement.setDouble(5, order.getSubTotal());
            pStatement.setDouble(6, order.getTax());
            pStatement.setDouble(7, order.getTotalPrice());
            pStatement.setLong(8, order.getTimeStamp());
            pStatement.setInt(9, order.getOrderId());
            pStatement.execute();
        }catch(SQLException e){
            throw new DAOException(e.getMessage());
        }finally{
            closeDBObjects(resultSet, pStatement, connection);
        }
    }

    @Override
    public void completeOrder(DineInOrder order) throws DAOException {
        try{
            connection = getConnection();
            pStatement = connection.prepareStatement(COMPLETE_DINEIN_ORDER_SQL);
            pStatement.setString(1, "dinein");
            pStatement.setInt(2, order.getTableNumber());
            pStatement.setDouble(3, order.getSubTotal());
            pStatement.setDouble(4, order.getTax());
            pStatement.setDouble(5, order.getTotalPrice());
            pStatement.setLong(6, order.getTimeStamp());
            pStatement.setInt(7, order.getOrderId());
            pStatement.execute();
        }catch(SQLException e){
            throw new DAOException(e.getMessage());
        }finally{
            closeDBObjects(resultSet, pStatement, connection);
        }
    }
    
  

    @Override
    public ArrayList<DeliveryOrder> getDeliveryOrders(boolean active) throws DAOException {
        ArrayList<DeliveryOrder> list = new ArrayList<DeliveryOrder>();
        try{
            connection = getConnection();
            pStatement = connection.prepareStatement(GET_DELIVERY_ORDERS_SQL);
            if(active){
                pStatement.setInt(1,1);
            }
            else{
                pStatement.setInt(1,0);
            }
            resultSet = pStatement.executeQuery();
            while(resultSet.next()){
                DeliveryOrder order = new DeliveryOrder();
                order.setOrderId(resultSet.getInt("order_id"));
                order.setAddress(resultSet.getString("address"));
                order.setPhoneNumber(resultSet.getString("phone"));
                order.setWantTime(resultSet.getString("wantTime"));
                order.setTax(resultSet.getDouble("tax"));
                order.setSubTotal(resultSet.getDouble("subtotal"));
                order.setTotalPrice(resultSet.getDouble("total"));
                order.setTimeStamp(resultSet.getLong("time"));
                //Date date = new Date(order.getTimeStamp());
                //order.setHumanReadableTime(date.toString());
                list.add(order);
            }
        }catch(SQLException e){
            throw new DAOException(e.getMessage());
        }finally{
            closeDBObjects(resultSet,pStatement,connection);
        }
        return list;
    }

    @Override
    public ArrayList<CarryoutOrder> getCarryoutOrders(boolean active) throws DAOException {
        ArrayList<CarryoutOrder> list = new ArrayList<CarryoutOrder>();
        try{
            connection = getConnection();
            pStatement = connection.prepareStatement(GET_CARRYOUT_ORDERS_SQL);
            if(active){
                pStatement.setInt(1,1);
            }
            else{
                pStatement.setInt(1,0);
            }
            resultSet = pStatement.executeQuery();
            while(resultSet.next()){
                CarryoutOrder order = new CarryoutOrder();
                order.setOrderId(resultSet.getInt("order_id"));
                order.setName(resultSet.getString("name"));
                order.setPhoneNumber(resultSet.getString("phone"));
                order.setWantTime(resultSet.getString("wantTime"));
                order.setTax(resultSet.getDouble("tax"));
                order.setSubTotal(resultSet.getDouble("subtotal"));
                order.setTotalPrice(resultSet.getDouble("total"));
                order.setTimeStamp(resultSet.getLong("time"));
                //Date date = new Date(order.getTimeStamp());
                //order.setHumanReadableTime(date.toString());
                list.add(order);
            }
        }catch(SQLException e){
            throw new DAOException(e.getMessage());
        }finally{
            closeDBObjects(resultSet,pStatement,connection);
        }
        return list;
    }

    @Override
    public ArrayList<DineInOrder> getDineInOrders(boolean active) throws DAOException {
        ArrayList<DineInOrder> list = new ArrayList<DineInOrder>();
        try{
            connection = getConnection();
            pStatement = connection.prepareStatement(GET_DINEIN_ORDERS_SQL);
            if(active){
                pStatement.setInt(1,1);
            }
            else{
                pStatement.setInt(1,0);
            }
            resultSet = pStatement.executeQuery();
            while(resultSet.next()){
                DineInOrder order = new DineInOrder();
                order.setOrderId(resultSet.getInt("order_id"));
                order.setTableNumber(resultSet.getInt("tableNumber"));
                order.setTax(resultSet.getDouble("tax"));
                order.setSubTotal(resultSet.getDouble("subtotal"));
                order.setTotalPrice(resultSet.getDouble("total"));
                order.setTimeStamp(resultSet.getLong("time"));
                //Date date = new Date(order.getTimeStamp());
                //order.setHumanReadableTime(date.toString());
                list.add(order);
            }
        }catch(SQLException e){
            throw new DAOException(e.getMessage());
        }finally{
            closeDBObjects(resultSet,pStatement,connection);
        }
        return list;
    }

    @Override
    public void cancelOrder(int orderId) throws DAOException {
        try{
            connection = getConnection();
            pStatement = connection.prepareStatement(CANCEL_ORDER_SQL);
            pStatement.setInt(1, orderId);
            pStatement.execute();
            pStatement = connection.prepareStatement(RESET_ORDER_ID_SQL);
            pStatement.setInt(1, orderId);
            pStatement.execute();
        }catch(SQLException e){
            throw new DAOException(e.getMessage());
        }finally{
            closeDBObjects(resultSet,pStatement,connection);
        }
    }

    @Override
    public void serveOrder(int orderId) throws DAOException {
        try{
            connection = getConnection();
            pStatement = connection.prepareStatement(SERVE_ORDER_SQL);
            pStatement.setInt(1, orderId);
            pStatement.execute();
        }catch(SQLException e){
            throw new DAOException(e.getMessage());
        }finally{
            closeDBObjects(resultSet,pStatement,connection);
        }
    }

    @Override
    public DeliveryOrder getDeliveryOrder(int orderId) throws DAOException {
        DeliveryOrder order = new DeliveryOrder();
        try{
            connection = getConnection();
            pStatement = connection.prepareStatement(GET_ORDER_BY_ID_SQL);
            pStatement.setInt(1,orderId);
            resultSet = pStatement.executeQuery();
            resultSet.next();
            order.setOrderId(orderId);
            order.setAddress(resultSet.getString("address"));
            order.setPhoneNumber(resultSet.getString("phone"));
            order.setSubTotal(resultSet.getDouble("subTotal"));
            order.setTotalPrice(resultSet.getDouble("total"));
            order.setTax(resultSet.getDouble("tax"));
            order.setWantTime(resultSet.getString("wantTime"));
            order.setTimeStamp(resultSet.getLong("time"));
        }catch(SQLException e){
            throw new DAOException(e.getMessage());
        }finally{
            closeDBObjects(resultSet,pStatement,connection);
        }
        return order;
    }

    @Override
    public CarryoutOrder getCarryoutOrder(int orderId) throws DAOException {
        CarryoutOrder order = new CarryoutOrder();
        try{
            connection = getConnection();
            pStatement = connection.prepareStatement(GET_ORDER_BY_ID_SQL);
            pStatement.setInt(1,orderId);
            resultSet = pStatement.executeQuery();
            resultSet.next();
            order.setOrderId(orderId);
            order.setName(resultSet.getString("name"));
            order.setPhoneNumber(resultSet.getString("phone"));
            order.setSubTotal(resultSet.getDouble("subTotal"));
            order.setTotalPrice(resultSet.getDouble("total"));
            order.setTax(resultSet.getDouble("tax"));
            order.setWantTime(resultSet.getString("wantTime"));
            order.setTimeStamp(resultSet.getLong("time"));
        }catch(SQLException e){
            throw new DAOException(e.getMessage());
        }finally{
            closeDBObjects(resultSet,pStatement,connection);
        }
        return order;
    }

    @Override
    public DineInOrder getDineInOrder(int orderId) throws DAOException {
        DineInOrder order = new DineInOrder();
        try{
            connection = getConnection();
            pStatement = connection.prepareStatement(GET_ORDER_BY_ID_SQL);
            pStatement.setInt(1,orderId);
            resultSet = pStatement.executeQuery();
            resultSet.next();
            order.setOrderId(orderId);
            order.setTableNumber(resultSet.getInt("tableNumber"));
            order.setSubTotal(resultSet.getDouble("subTotal"));
            order.setTotalPrice(resultSet.getDouble("total"));
            order.setTax(resultSet.getDouble("tax"));
            order.setTimeStamp(resultSet.getLong("time"));
        }catch(SQLException e){
            throw new DAOException(e.getMessage());
        }finally{
            closeDBObjects(resultSet,pStatement,connection);
        }
        return order;
    }
    
    @Override
    public List<OrderItemInfoEntry> getOrderItemInfo(int orderId) throws DAOException{
        ArrayList<OrderItemInfoEntry> list = new ArrayList<OrderItemInfoEntry>();
        try{
            connection = getConnection();
            pStatement = connection.prepareStatement(GET_ORDER_ITEM_INFO_SQL);
            pStatement.setInt(1, orderId);
            resultSet = pStatement.executeQuery();
            while(resultSet.next()){
                String description = resultSet.getString("item");
                double unitPrice = resultSet.getDouble("price");
                int itemIndex = resultSet.getInt("itemIndex");
                OrderItemInfoEntry entry = new OrderItemInfoEntry();
                entry.setDescription(description);
                entry.setUnitPrice(unitPrice);
                entry.setItemIndex(itemIndex);
                list.add(entry);
            }
        }catch(SQLException e){
            throw new DAOException(e.getMessage());
        }finally{
            closeDBObjects(resultSet,pStatement,connection);
        }
        return list;
    }

    @Override
    public void removeItemFromOrder(int orderId, int itemIndex) throws DAOException {
        try{
            connection = getConnection();
            pStatement = connection.prepareStatement(REMOVE_ITEM_FROM_ORDER_SQL);
            pStatement.setInt(1,orderId);
            pStatement.setInt(2,itemIndex);
            pStatement.execute();
        }catch(SQLException e){
            throw new DAOException(e.getMessage());
        }finally{
            closeDBObjects(resultSet,pStatement,connection);
        }
    }
}
