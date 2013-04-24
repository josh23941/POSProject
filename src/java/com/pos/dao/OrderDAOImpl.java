/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pos.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Josh
 */
public class OrderDAOImpl extends BaseDAO implements OrderDAO{
    private static final String SEPARATOR = ",";
    private static final String GET_ITEMS_IN_ORDER_SQL_BASE = "SELECT items FROM orders WHERE uid=";
    private static final String UPDATE_ITEMS_IN_ORDER_SQL_BASE = "UPDATE orders SET items=";
    private static final String CREATE_ORDER_SQL = "INSERT INTO orders VALUES (null, null, 0.00)";
    private Connection connection = null;
    private PreparedStatement pStatement = null;
    private ResultSet resultSet = null;
    
    @Override
    public void addItem(int uid, String name) throws DAOException{
        String sql = GET_ITEMS_IN_ORDER_SQL_BASE + "\"" + uid + "\"";
        
        try{
            connection = getConnection();
            pStatement = connection.prepareStatement(sql);
            resultSet = pStatement.executeQuery();
            resultSet.next();
            String items = resultSet.getString("items");
            if(items == null){
                items = "";
            }
            items = items.concat(name + SEPARATOR);
            String updateSql = UPDATE_ITEMS_IN_ORDER_SQL_BASE + "\"" + items 
                    + "\"" + "WHERE uid=\"" + uid + "\"";
            pStatement.close();
            pStatement = connection.prepareStatement(updateSql);
            pStatement.execute();
        }catch(SQLException e){
            throw new DAOException(e.getMessage());
        }finally{
            closeDBObjects(resultSet, pStatement, connection);
        }
        
    }

    @Override
    public int createOrder() throws DAOException {
        try{
            connection = getConnection();
            pStatement = connection.prepareStatement(CREATE_ORDER_SQL);
            pStatement.execute();
        }catch(SQLException e){
            throw new DAOException(e.getMessage());
        }finally{
            closeDBObjects(resultSet, pStatement, connection);
        }
        return 0;
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
