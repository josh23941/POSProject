/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pos.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 * @author Josh
 */
public class OrderDAOImpl extends BaseDAO implements OrderDAO{
    private static final String SEPARATOR = ",";
    private static final String GET_ITEMS_IN_ORDER_SQL_BASE = "SELECT items FROM orders WHERE uid=";
    private static final String UPDATE_ITEMS_IN_ORDER_SQL_BASE = "UPDATE orders SET items=";
    private static final String ADD_ITEM_TO_ORDER_SQL = "INSERT INTO orders VALUES (?,?,?)";
    private static final String CREATE_ORDER_SQL = "INSERT INTO order_ids VALUES (?)";
    private static final String GET_ORDER_IDS_SQL = "SELECT order_id from order_ids";
    private Connection connection = null;
    private PreparedStatement pStatement = null;
    private ResultSet resultSet = null;
    private static boolean firstLoad = true;
    private static ArrayList assignedOrderIds;
    
    public OrderDAOImpl() throws DAOException{
        
        //If this is the first access to order DB make sure existing orders are 
        //kept off of the available order_id array.  After first load this doesn't need to 
        //run, simply get the last int in the list and increment for the next available order_id in the DB.
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
    public void addItem(int uid, String name, double price) throws DAOException{
        try{
            connection = getConnection();
            pStatement = connection.prepareStatement(ADD_ITEM_TO_ORDER_SQL);
            pStatement.setInt(1, uid);
            pStatement.setString(2, name);
            pStatement.setDouble(3, price);
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
    public void removeItem(int uid, String name, boolean all) throws DAOException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void removeOrder(int uid) throws DAOException {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
}
