/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pos.form;

import com.pos.model.user.User;

/**
 *
 * @author Josh
 */
public class AddUserForm {
    private String name;
    private String password;
    private String organization;
    private String role;
    
    public AddUserForm(){}
    
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
    
    //This really isn't needed yet but if any request parameters are added that aren't Strings then this will be required.
    public User getUserInstance(){
        User user = new User();
        user.setName(name);
        user.setOrganization(organization);
        user.setPassword(password);
        user.setRole(role);
        return user;
    }
}

