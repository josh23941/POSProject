/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pos.action;

import com.pos.dao.DAOException;
import com.pos.dao.DAOFactory;
import com.pos.dao.UserDAO;
import com.pos.model.User;
import java.util.ArrayList;
import java.util.List;


/**
 *
 * @author Josh
 */

//@todo excpetion should actually do something when caught...this is a todo for the whole project.
public class UserAction {
    private String username;
    private String password;
    private UserDAO userDAO;
    
    //@todo you aren't even using the instance vars...plus don't need to store user and pass here.  See other todo on this.
    public UserAction(String username, String password){
        this.username = username;
        this.password = password;
        userDAO = DAOFactory.getUserDAO(username, password);
    }
    
    public UserAction(){
        userDAO = DAOFactory.getUserDAO();
    }
    public int authenticate(){
        int result = UserDAO.Result.NO_USER;
        try{
            result = userDAO.authenticate();
        }catch(DAOException e){
            e.getMessage();
        }
        return result;
    }
    
    public String getOrganization(){
        String organization = null;
        try{
            organization = userDAO.getOrganization();
        }
        catch(DAOException e){
            e.getMessage();
        }
        return organization;
    }
    
    public String getRole(){
        String role = null;
        try{
            role = userDAO.getRole();
        }catch(DAOException e){
            e.getMessage();
        }
        return role;
    }
    
    public List<User> getUsers(){
        List<User> userList = new ArrayList<User>();
        try{
            userList = userDAO.getUsers();
        }catch(DAOException e){
            e.getMessage();
        }
        return userList;
    }
    
    public void addUser(User user){
        try{
            userDAO.addUser(user);
        }catch(DAOException e){
            System.out.println("Error Adding User" + e.getMessage());
        }
    }
}
