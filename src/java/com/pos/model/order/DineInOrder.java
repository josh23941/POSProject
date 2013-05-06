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
    private int militaryWantTime;
    private String wantDate;
    
    public DineInOrder(){}

    public String getWantDate() {
        return wantDate;
    }

    public void setWantDate(String wantDate) {
        this.wantDate = wantDate;
    }
    
    public int getMilitaryWantTime(){
        return militaryWantTime;
    }
    
    public void setMilitaryWantTime(int militaryWantTime){
        this.militaryWantTime = militaryWantTime;
    }
    
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
                "'table':'" + this.getTableNumber() + "'," +
                "'mWantTime':'" + this.getMilitaryWantTime() + "'," +
                "'wantTime':'" + 
                "'}";             
    }
}
