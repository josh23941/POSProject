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
public class DeliveryOrder extends Order{
    
    private String address;
    private String wantTime;
    private String phoneNumber;
    private int militaryWantTime;
    
    public DeliveryOrder(){}

    public int getMilitaryWantTime() {
        return militaryWantTime;
    }

    public void setMilitaryWantTime(int militaryWantTime) {
        this.militaryWantTime = militaryWantTime;
    }
    
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
    
    public String getJSON(){
        return "{'orderId':'"+this.getOrderId()+"'," +
                "'totalPrice':'" + this.getTotalPrice() + "'," +
                "'subTotal':'" + this.getSubTotal() + "'," +
                "'tax':'" + this.getTax() + "'," + 
                "'timeStamp':'" + this.getTimeStamp() + "'," +
                "'humanReadableTime':'" + this.getHumanReadableTime() + "'," +
                "'address':'" + this.getAddress() + "'," +
                "'phone':'" + this.getPhoneNumber() + "'," +
                "'wantTime':'" + this.getWantTime() + "'," +
                "'mWantTime':'" + this.getMilitaryWantTime() + "'}";              
    }
}
