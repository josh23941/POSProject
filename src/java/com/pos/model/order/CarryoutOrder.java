/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pos.model.order;

import com.pos.util.TimeUtil;

/**
 *
 * @author Josh
 */
public class CarryoutOrder extends Order{
    
    private String name;
    private String phoneNumber;
    private String wantTime;
    private int militaryWantTime;
    
    public CarryoutOrder(){
        
    }

    public int getMilitaryWantTime() {
        return militaryWantTime;
    }

    public void setMilitaryWantTime(int militaryWantTime) {
        this.militaryWantTime = militaryWantTime;
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
    
    public String getJSON(){
        return "{'orderId':'"+this.getOrderId()+"'," +
                "'totalPrice':'" + this.getTotalPrice() + "'," +
                "'subTotal':'" + this.getSubTotal() + "'," +
                "'tax':'" + this.getTax() + "'," + 
                "'timeStamp':'" + this.getTimeStamp() + "'," +
                "'humanReadableTime':'" + this.getHumanReadableTime() + "'," +
                "'name':'" + this.getName() + "'," +
                "'phone':'" + this.getPhoneNumber() + "'," +
                "'wantTime':'" + this.getWantTime() + "'," +
                "'mWantTime':'" + this.getMilitaryWantTime() + "'}";              
    }
}
