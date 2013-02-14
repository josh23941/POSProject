/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pos.action;

import com.pos.dao.DAOException;
import com.pos.dao.LoginDAO;
import com.pos.dao.LoginDAOImpl;


/**
 *
 * @author Josh
 */
public class LoginAction {
    private String username;
    private String password;
    
    public LoginAction(String username, String password){
        this.username = username;
        this.password = password;
    }
    
    public int authenticate(){
        int result = LoginDAO.Result.NO_USER;
        LoginDAO loginDAO = new LoginDAOImpl(username, password);
        try{
            result = loginDAO.authenticate();
        }catch(DAOException e){
            e.getMessage();
        }
        return result;
    }
    
    public String getOrganization(){
        String organization = null;
        LoginDAO loginDAO = new LoginDAOImpl(username, password);
        try{
            organization = loginDAO.getOrganization();
        }
        catch(DAOException e){
            e.getMessage();
        }
        return organization;
    }
    
    public String getRole(){
        String role = null;
        LoginDAO loginDAO = new LoginDAOImpl(username, password);
        try{
            role = loginDAO.getRole();
        }catch(DAOException e){
            e.getMessage();
        }
        return role;
    }
}
