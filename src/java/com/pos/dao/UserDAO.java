/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pos.dao;

import com.pos.model.User;
import java.util.List;

/**
 *
 * @author Josh
 */
public interface UserDAO {
    
    public class Result{
        public static final int PASS = 0;
        public static final int NO_USER = 1;
        public static final int WRONG_PASSWORD = 2;
    }
    
    public int authenticate(String username, String password) throws DAOException;
    public List<User> getUsers() throws DAOException;
    public void addUser(User user) throws DAOException;
    public User getUser(String username) throws DAOException;
}
