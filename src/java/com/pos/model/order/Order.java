/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pos.model.order;

import java.util.Date;
import java.util.List;

/**
 *
 * @author Josh
 */
public class Order{
    private int orderId;
    private double totalPrice;
    private double subTotal;
    private double tax;
    private long timeStamp;
    private String humanReadableTime;
    
    public Order(){}

    public int getOrderId() {
        return orderId;
    }

    public double getSubTotal() {
        return subTotal;
    }

    public double getTax() {
        return tax;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public long getTimeStamp() {
        return timeStamp;
    }
    
    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public void setSubTotal(double subTotal) {
        this.subTotal = subTotal;
    }

    public void setTax(double tax) {
        this.tax = tax;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public void setTimeStamp(long timeStamp) {
        this.timeStamp = timeStamp;
    }

    public String getHumanReadableTime() {
        return humanReadableTime;
    }

    public void setHumanReadableTime(String humanReadableTime) {
        this.humanReadableTime = humanReadableTime;
    }
    
    
    
}
