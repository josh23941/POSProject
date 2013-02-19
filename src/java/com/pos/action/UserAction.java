/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pos.action;

import com.pos.dao.DAOException;
import com.pos.dao.DAOFactory;
import com.pos.dao.UserDAO;
import com.pos.model.user.User;
import java.util.ArrayList;
import java.util.List;


/**
 *
 * @author Josh
 */

//@todo excpetion should actually do something when caught...this is a todo for the whole project.
public class UserAction {
    private UserDAO userDAO;
    
    public UserAction(){
        userDAO = DAOFactory.getUserDAO();
    }
    
    public int authenticate(String username, String password){
        int result = UserDAO.Result.NO_USER;
        try{
            result = userDAO.authenticate(username, password);
        }catch(DAOException e){
            System.out.println("DB error during authentication " + e.getMessage());
        }
        return result;
    }
    
    public List<User> getUsers(){
        List<User> userList = new ArrayList<User>();
        try{
            userList = userDAO.getUsers();
        }catch(DAOException e){
            System.out.println("DB Error getting users " + e.getMessage());
        }
        return userList;
    }
    
    public void addUser(User user){
        try{
            userDAO.addUser(user);
        }catch(DAOException e){
            System.out.println("DB Error Adding User" + e.getMessage());
        }
    }
    
    public User getUser(String username){
        User user = new User();
        try{
            user = userDAO.getUser(username);
        }catch(DAOException e){
            System.out.println("DB Error getting user " + e.getMessage());
        }
        return user;
    }
}
