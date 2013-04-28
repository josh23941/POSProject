/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pos.model.menu;

/**
 *
 * @author Josh
 */
public class CarryoutOrder extends Order{
    
    private String name;
    private String phoneNumber;
    private String wantTime;
    
    public CarryoutOrder(){
        
    }

    public String getName() {
        return name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getWantTime() {
        return wantTime;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setWantTime(String wantTime) {
        this.wantTime = wantTime;
    }
    
    
}
