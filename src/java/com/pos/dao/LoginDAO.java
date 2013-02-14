/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pos.dao;

/**
 *
 * @author Josh
 */
public interface LoginDAO {
    
    public class Result{
        public static final int PASS = 0;
        public static final int NO_USER = 1;
        public static final int WRONG_PASSWORD = 2;
    }
    
    public int authenticate() throws DAOException;
    public String getOrganization() throws DAOException;
    public String getRole() throws DAOException;
}
