/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pos.dao;

import com.pos.model.user.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Josh
 */
public class UserDAOImpl extends BaseDAO implements UserDAO{
    private Connection connection = null;
    private PreparedStatement pStatement = null;
    private ResultSet resultSet = null;
    private static final String GET_USER_SQL_BASE = "SELECT * FROM users WHERE username=";
    private static final String GET_USERS_SQL = "SELECT * FROM users";
    private static final String INSERT_USER_SQL = "INSERT INTO users " + 
            "(username, password, organization, role) " +
            "VALUES (?, ?, ?, ?)";
    
    public UserDAOImpl(){}
    
    @Override
    public int authenticate(String username, String password) throws DAOException {
        int result = Result.NO_USER;
        String sql = GET_USER_SQL_BASE + "\"" + username + "\"";
        try{
            connection = getConnection();
            pStatement = connection.prepareStatement(sql);
            resultSet = pStatement.executeQuery();
                if (resultSet.next()){
                    if(resultSet.getString("password").equals(password)){
                            result = Result.PASS;
                        }
                    else{
                        result = Result.WRONG_PASSWORD;
                    }
                }  
        }catch (SQLException e){
            throw new DAOException("Error getting user info. " + e.getMessage());
        }finally {
            closeDBObjects(resultSet, pStatement, connection);
        }
        return result;
    }

    @Override
    public List<User> getUsers() throws DAOException {
        List<User> userList = new ArrayList<User>();
        try{
            connection = getConnection();
            pStatement = connection.prepareStatement(GET_USERS_SQL);
            resultSet = pStatement.executeQuery();
            while(resultSet.next()){
                User user = new User();
                user.setName(resultSet.getString("username"));
                //@todo might not need to get pass.
                user.setPassword(resultSet.getString("password"));
                user.setPassword(resultSet.getString("organization"));
                user.setPassword(resultSet.getString("role"));
                userList.add(user);
            }
            
        }catch(SQLException e){
            throw new DAOException("Error accessing all user info" + e.getMessage());
        }finally{
            closeDBObjects(resultSet, pStatement, connection);
        }
        
        return userList;
    }
    
    //@todo for add type of actions a boolean can be passed back...consider using this to redirect to error page. Or back to form with error note.
    @Override
    public void addUser(User user) throws DAOException {
        
        try{
            connection = getConnection();
            pStatement = connection.prepareStatement(INSERT_USER_SQL);
            pStatement.setString(1, user.getName());
            pStatement.setString(2, user.getPassword());
            pStatement.setString(3, user.getOrganization());
            pStatement.setString(4, user.getRole());
            pStatement.execute(); 
        }catch(SQLException e){
            throw new DAOException("Unable to add user: " + e.getMessage());
        }finally{
            closeDBObjects(resultSet, pStatement, connection);
        }
    }

    @Override
    public User getUser(String username) throws DAOException{
        String sql = GET_USER_SQL_BASE + "\"" + username + "\"";
        User user = new User();
        try{
            connection = getConnection();
            pStatement = connection.prepareStatement(sql);
            resultSet = pStatement.executeQuery();
            while(resultSet.next()){
                user.setName(resultSet.getString("username"));
                user.setPassword(resultSet.getString("password"));
                user.setOrganization(resultSet.getString("organization"));
                user.setRole(resultSet.getString("role"));
            }
        }catch(SQLException e){
            throw new DAOException(e.getMessage());
        }finally{
            closeDBObjects(resultSet, pStatement, connection);
        }
        return user;
    }
}
