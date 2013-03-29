/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pos.action;

import com.pos.dao.DAOException;
import com.pos.dao.DAOFactory;
import com.pos.dao.MenuCategoryDAO;
import com.pos.model.menu.MenuCategory;
import com.pos.model.menu.MenuCategoryTreeNode;
import java.util.List;

/**
 *
 * @author Josh
 */
public class MenuCategoryAction {
    public MenuCategoryDAO menuCategoryDAO;
    
    public MenuCategoryAction(){
        menuCategoryDAO = DAOFactory.getMenuCategoryDAO();
    }
    
    public MenuCategoryTreeNode getRootNode(){
        MenuCategoryTreeNode rootNode = null;
        try{
            rootNode = menuCategoryDAO.getRootNode();
        }catch(DAOException e){
            System.out.println("Error getting root node: " + e.getMessage());
        }
        return rootNode;
    }
    
    public void saveMenuCategory(MenuCategory menuCategory){
        try{
            menuCategoryDAO.saveMenuCategory(menuCategory);
        }catch (DAOException e){
            System.out.println(e.getMessage());
        }
    }
    
    public List<MenuCategory> getMenuCategories(){
        List<MenuCategory> menuCategoryList = null;
        try{
            menuCategoryList = menuCategoryDAO.getMenuCategories();
        }catch (DAOException e){
            System.out.println(e.getMessage());
        }
        return menuCategoryList;
    }
}
