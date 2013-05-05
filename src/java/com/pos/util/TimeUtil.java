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
    /*
    public static void main(String[]  args){
        System.out.println(TimeUtil.militaryToStringTime("000"));
        System.out.println(TimeUtil.militaryToStringTime("1200"));
        System.out.println(TimeUtil.militaryToStringTime("1201"));
        System.out.println(TimeUtil.militaryToStringTime("2300"));
        
    }
    
    */
}
