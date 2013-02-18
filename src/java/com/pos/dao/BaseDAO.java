/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pos.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.sql.DataSource;

/**
 *
 * @author Josh
 */
public class BaseDAO implements DAO{
    
    @Override
    public Connection getConnection() throws DAOException{
        DataSource dataSource = DataSourceCache.getInstance().getDataSource();
        try{
            return dataSource.getConnection();
        } catch(Exception e){
            throw new DAOException("Error getting connection: " + e.getMessage());
        }
        
    }
    
    protected void closeDBObjects(ResultSet resultSet, Statement statement,
            Connection connection){
        if (resultSet != null){
            try{
                resultSet.close();    
            } catch(Exception e){
                System.out.println("Error closing result set: " + e.getMessage());
            }
        }
        if (statement != null) {
            try{
                statement.close();
            }catch (Exception e){
                System.out.println("Error closing prepared statement: " + e.getMessage());
            }
        }
        if (connection != null){
            try{
                connection.close();
            }catch (Exception e){
                System.out.println("Error closing connection: " + e.getMessage());
            }
        }
    }
}
