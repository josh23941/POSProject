/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pos.model.menu;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Josh
 */
public class MenuCategoryTreeNode {
    private int uid;
    private int puid;
    private List<MenuCategoryTreeNode> childeren;
    private static String htmlMenu = null;
    
    public MenuCategoryTreeNode(int uid, int puid){
        this.puid = puid;
        this.uid = uid;
        childeren = new ArrayList<MenuCategoryTreeNode>();
        if (htmlMenu == null){
            htmlMenu = "";
        }
    }
    
    public void addChild(MenuCategoryTreeNode childNode){
        childeren.add(childNode);
    }
    
    public String outputHTML(){
        htmlMenu = htmlMenu + "<ul>" + this.getUid();
        for(MenuCategoryTreeNode node : childeren){
            node.outputHTML();
        }
        htmlMenu = htmlMenu + "</ul>";
        return htmlMenu;
    }
    
    public int getUid(){
        return uid;
    }
    
    public int getPuid(){
        return puid;
    }
}
