/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pos.model.order;

/**
 *
 * @author Josh
 */
public class DineInOrder extends Order{
    
    private int tableNumber;
    
    public DineInOrder(){}

    public int getTableNumber() {
        return tableNumber;
    }

    public void setTableNumber(int tableNumber) {
        this.tableNumber = tableNumber;
    }
    
    public String getJSON(){
        return "{'orderId':'"+this.getOrderId()+"'," +
                "'totalPrice':'" + this.getTotalPrice() + "'," +
                "'subTotal':'" + this.getSubTotal() + "'," +
                "'tax':'" + this.getTax() + "'," + 
                "'timeStamp':'" + this.getTimeStamp() + "'," +
                "'humanReadableTime':'" + this.getHumanReadableTime() + "'," +
                "'table':'" + this.getTableNumber() + "'}";              
    }
}
