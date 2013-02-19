/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pos.model.user;

/**
 *
 * @author Josh
 */
public class User {
    private String name;
    private String password;
    private String organization;
    private String role;
    
    public void setName(String name) {
        this.name = name;
    }

    public void setOrganization(String organization) {
        this.organization = organization;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRole(String role) {
        this.role = role;
    }
    
    public User(){}

    public String getName() {
        return name;
    }

    public String getOrganization() {
        return organization;
    }

    public String getPassword() {
        return password;
    }

    public String getRole() {
        return role;
    }
    
    
}
