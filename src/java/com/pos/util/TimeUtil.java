/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pos.util;

/**
 *
 * @author Josh
 */
public class TimeUtil {
    public static String militaryToStringTime(String milTimeString){
        String hourString = milTimeString.substring(0,milTimeString.length()-2);
        String minString = milTimeString.substring(milTimeString.length()-2);
        int hourInt = Integer.parseInt(hourString);
        if(hourInt < 12){
            if(hourInt == 0){
                hourInt = 12;
                return Integer.toString(hourInt) + ":" + minString + "AM";
            }
            else{
                return hourString + ":" + minString + "AM";
            }
        }
        else{
            if(hourInt == 12){
                return hourString + ":" + minString + "PM";
            }
            else{
                hourInt -= 12;
                return Integer.toString(hourInt) + ":" + minString + "PM";
            }
        }
    }
    
    public static String getMySQLDateFormat(String rawDate){
        String[] splits = rawDate.split("/");
        return splits[2] + "-" + splits[0] + "-" + splits[1];
    }
    
    public static String getThisAppDateFormat(String rawDate){
        String[] splits = rawDate.split("-");
        return splits[1] + "/" + splits[2] + "/" + splits[0];
    }
    /*
    public static void main(String[] args){
        System.out.println(TimeUtil.getMySQLDateFormat("05/13/2013"));
        
    }
    */
    
}
