/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pos.dao;

import junit.framework.TestCase;

/**
 *
 * @author Josh
 */
public class LoginDAOImplTest extends TestCase {
    LoginDAOImpl instance = (LoginDAOImpl)DAOFactory.getLoginDAO("root", "root");
    
    public LoginDAOImplTest(String testName) {
        super(testName);
    }
    
    @Override
    protected void setUp() throws Exception {
        LoginDAOImpl instance = (LoginDAOImpl)DAOFactory.getLoginDAO("root", "root");
    }
    
    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }

    /**
     * Test of authenticate method, of class LoginDAOImpl.
     */
    public void testAuthenticate() throws Exception {
        System.out.println("authenticate");
        int expResult = LoginDAO.Result.PASS;
        int result = instance.authenticate();
        assertEquals(expResult, result);
    }

    /**
     * Test of getOrganization method, of class LoginDAOImpl.
     */
    public void testGetOrganization() throws Exception {
        System.out.println("getOrganization");
        String expResult = "root";
        String result = instance.getOrganization();
        assertEquals(expResult, result);
    }

    /**
     * Test of getRole method, of class LoginDAOImpl.
     */
    public void testGetRole() throws Exception {
        System.out.println("getRole");
        String expResult = "manager";
        String result = instance.getRole();
        assertEquals(expResult, result);
    }
}
