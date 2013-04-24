/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pos.model.menu;

import java.util.List;

/**
 *
 * @author Josh
 */
public class Order{
    private int uid;
    private double totalPrice;
    private String[] items;
    
    public Order(){}

    public String[] getItems() {
        return items;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public int getUid() {
        return uid;
    }

    public void setItems(String[] items) {
        this.items = items;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }
    
    
}
