/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pos.model.menu;

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
    
    
}
