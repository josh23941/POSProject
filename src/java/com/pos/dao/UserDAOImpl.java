/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pos.dao;

import com.pos.model.User;
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
    private String password;
    private final String GET_USERS_PASS_SQL;
    private final String GET_USERS_ORGANIZATION_SQL; 
    private final String GET_USERS_ROLE_SQL;
    private static final String GET_ALL_USER_INFO_SQL = "SELECT * FROM users";
    private static final String INSERT_USER_SQL = "INSERT INTO users " + 
            "(username, password, organization, role) " +
            "VALUES (?, ?, ?, ?)";
    
    //@todo review this...shouldn't have to pass these values in necessarily just supply them to the mehtods from the actual method calls.
    //@todo go through userDAO related classes and change any naming that uses loginDAO to userDAO...and Login Action can likely go in UserAction
    public UserDAOImpl(String username, String password){
        this.password = password;
        GET_USERS_PASS_SQL = "SELECT password FROM users WHERE username=\"" + username + "\"";
        GET_USERS_ORGANIZATION_SQL = "SELECT organization FROM users WHERE username=\"" + username + "\"";
        GET_USERS_ROLE_SQL = "SELECT role FROM users WHERE username=\"" + username + "\""; 
    }
    
    //@todo make this the main constructor and fill in these vals..possible solution is to concat username within the methods that need them in the SQL.
    public UserDAOImpl(){
        GET_USERS_PASS_SQL = null;
        GET_USERS_ORGANIZATION_SQL = null;
        GET_USERS_ROLE_SQL = null;
    }
    
    @Override
    public int authenticate() throws DAOException {
        int result = Result.NO_USER;
        Connection connection = null;
        PreparedStatement pStatement = null;
        ResultSet resultSet = null;
        try{
            connection = getConnection();
            pStatement = connection.prepareStatement(GET_USERS_PASS_SQL);
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
    public String getOrganization() throws DAOException {
        String organization = null;
        Connection connection = null;
        PreparedStatement pStatement = null;
        ResultSet resultSet = null;
        try{
            connection = getConnection();
            pStatement = connection.prepareStatement(GET_USERS_ORGANIZATION_SQL);
            resultSet = pStatement.executeQuery();
            //Only calling after authentication so no need to test resultSet.next()
            resultSet.next();
            organization = resultSet.getString("organization");
        }catch(SQLException e){
            throw new DAOException("Error accessing user's organization." + e.getMessage());
        }finally{
            closeDBObjects(resultSet, pStatement, connection);
        }
        return organization;
    }
    
    @Override
    public String getRole() throws DAOException {
        String role = null;
        Connection connection = null;
        PreparedStatement pStatement = null;
        ResultSet resultSet = null;
        try{
            connection = getConnection();
            pStatement = connection.prepareStatement(GET_USERS_ROLE_SQL);
            resultSet = pStatement.executeQuery();
            //Only calling after authentication so no need to test resultSet.next()
            resultSet.next();
            role = resultSet.getString("role");
        }catch(SQLException e){
            throw new DAOException("Error accessing user's role." + e.getMessage());
        }finally{
            closeDBObjects(resultSet, pStatement, connection);
        }
        return role;
    }

    @Override
    public List<User> getUsers() throws DAOException {
        List<User> userList = new ArrayList<User>();
        Connection connection = null;
        PreparedStatement pStatement = null;
        ResultSet resultSet = null;
        try{
            connection = getConnection();
            pStatement = connection.prepareStatement(GET_ALL_USER_INFO_SQL);
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
        Connection connection = null;
        PreparedStatement pStatement = null;
        ResultSet resultSet = null;
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
}
