/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pos.model.menu;

import com.pos.action.MenuItemAction;
import com.pos.dao.DAOFactory;
import com.pos.dao.MenuItemDAO;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Josh
 */
public class MenuCategoryTreeNode {
    private int uid;
    private int puid;
    private String categoryName;
    private List<MenuCategoryTreeNode> childeren;
    private List<MenuItem> items;
    private static String htmlMenu = null;
    private MenuItemAction menuItemAction;
    
    public MenuCategoryTreeNode(int uid, int puid, String name){
        this.puid = puid;
        this.uid = uid;
        this.categoryName = name;
        childeren = new ArrayList<MenuCategoryTreeNode>();
        if (htmlMenu == null){
            htmlMenu = "";
        }
        menuItemAction = new MenuItemAction();
        items = menuItemAction.getMenuItems(Integer.toString(uid));
        
    }
    
    public void addChild(MenuCategoryTreeNode childNode){
        childeren.add(childNode);
    }
    
    public String outputHTML(){
        htmlMenu = htmlMenu + "<ul>" + this.getCategoryName();
        for(MenuItem item : items){
            htmlMenu += "<li>" + item.getName() + "</li>";
        }
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
    
    public String getCategoryName(){
        return categoryName;
    }
}
