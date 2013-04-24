/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pos.dao;

import com.pos.model.menu.MenuCategory;
import com.pos.model.menu.MenuCategoryTreeNode;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Josh
 */
public class MenuCategoryDAOImpl extends BaseDAO implements MenuCategoryDAO{
    private static final String GET_MENU_CATEGORIES_SQL = "SELECT * FROM menucategories";
    private static final String INSERT_MENU_CATEGORY_SQL = "INSERT INTO menucategories " +
            "(name, parent_uid) VALUES (?, ?)";
    private static final String GET_UID_BY_NAME_SQL_BASE = "SELECT uid FROM menucategories " +
            "WHERE name="; 
    private static final String INSERT_CATEGORY_CATEGORIES_SQL = "INSERT INTO category_categories " +
            "(parent_cat, child_cat) VALUES (?, ?)";
    private Connection connection = null;
    private PreparedStatement pStatement = null;
    private ResultSet resultSet = null;
    
    public MenuCategoryDAOImpl(){}

    @Override
    public MenuCategoryTreeNode getRootNode() throws DAOException{
        MenuCategoryTreeNode rootNode = new MenuCategoryTreeNode(1,0, "root");
        List<MenuCategoryTreeNode> nodeList = new ArrayList<MenuCategoryTreeNode>();
        nodeList.add(rootNode);
        int largestPuid = 0;
        try{
            connection = getConnection();
            pStatement = connection.prepareStatement(GET_MENU_CATEGORIES_SQL);
            resultSet = pStatement.executeQuery();
            while (resultSet.next()){
                int uid = resultSet.getInt("uid");
                int puid = resultSet.getInt("parent_uid");
                String name = resultSet.getString("name");
                MenuCategoryTreeNode temp = new MenuCategoryTreeNode(uid, puid, name);
                nodeList.add(temp);
                if(puid > largestPuid){
                    largestPuid = puid;
                }
            }
        }catch(SQLException e){
            throw new DAOException("Error getting root node: " + e.getMessage());
        }finally{
            closeDBObjects(resultSet, pStatement, connection);
        }
        
        for (int i = 1; i <= largestPuid; i++){
            
            MenuCategoryTreeNode currentLoadingNode = null;
            for(MenuCategoryTreeNode node : nodeList){
                if (node.getUid() == i){
                    currentLoadingNode = node;
                }
            }
            for(MenuCategoryTreeNode node : nodeList){
                if (node.getPuid() == i){
                    currentLoadingNode.addChild(node);
                }
            }
        }
        return rootNode;
    }
    
      @Override
    public void saveMenuCategory(MenuCategory menuCategory) throws DAOException {
        String parentUIDsql = GET_UID_BY_NAME_SQL_BASE + "\"" + menuCategory.getParent() + "\"";
        int parentUID = 0;
        try{
            connection = getConnection();
            if(!menuCategory.getParent().equals("root")){
                pStatement = connection.prepareStatement(parentUIDsql);
                resultSet = pStatement.executeQuery();
                resultSet.next();
                parentUID = resultSet.getInt("uid");
            }
            else{
                parentUID = 1;
            }
            pStatement = connection.prepareStatement(INSERT_MENU_CATEGORY_SQL);
            pStatement.setString(1, menuCategory.getName());
            pStatement.setInt(2, parentUID);
            pStatement.execute();
            resultSet.close();
            pStatement.close();
            String thisCategoryUIDsql = GET_UID_BY_NAME_SQL_BASE + "\"" + menuCategory.getName() + "\"";
            pStatement = connection.prepareStatement(thisCategoryUIDsql);
            resultSet = pStatement.executeQuery();
            resultSet.next();
            int thisCategoryUID = resultSet.getInt("uid");
            resultSet.close();
            pStatement.close();
            pStatement = connection.prepareStatement(INSERT_CATEGORY_CATEGORIES_SQL);
            pStatement.setInt(1, parentUID);
            pStatement.setInt(2, thisCategoryUID);
            pStatement.execute();
        }catch(SQLException e){
            throw new DAOException("Error inserting menu category: " + e.getMessage());
        }finally{
            closeDBObjects(resultSet, pStatement, connection);
        }
    }

    @Override
    public List<MenuCategory> getMenuCategories() throws DAOException {
        List<MenuCategory> menuCategoryList = new ArrayList<MenuCategory>();
        try{
            connection = getConnection();
            pStatement = connection.prepareStatement(GET_MENU_CATEGORIES_SQL);
            resultSet = pStatement.executeQuery();
            while(resultSet.next()){
                //@todo what happens if resultSet.get.... returns null, do I have to check?
                MenuCategory menuCategory = new MenuCategory();
                menuCategory.setUid(resultSet.getInt("uid"));
                menuCategory.setName(resultSet.getString("name"));
                //@todo might not require this conversion if I don't need String version of parent in MenuCategory.
                menuCategory.setParent(Integer.toString(resultSet.getInt("parent_uid")));
                menuCategoryList.add(menuCategory);
            }
        }catch(SQLException e){
            throw new DAOException("Error getting menu categories: " + e.getMessage());
        }finally{
            closeDBObjects(resultSet, pStatement, connection);
        }
        return menuCategoryList;
    }
}
