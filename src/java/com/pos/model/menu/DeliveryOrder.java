/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pos.model.menu;

/**
 *
 * @author Josh
 */
public class DeliveryOrder extends Order{
    
    private String address;
    private String wantTime;
    private String phoneNumber;
    
    public DeliveryOrder(){}

    public String getAddress() {
        return address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getWantTime() {
        return wantTime;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setWantTime(String wantTime) {
        this.wantTime = wantTime;
    }
    
    
}
