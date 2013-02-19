/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pos.dao;

import com.pos.model.menu.MenuCategory;
import com.pos.model.menu.MenuItem;
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
public class MenuItemDAOImpl extends BaseDAO implements MenuItemDAO {
    private static final String GET_MENU_ITEMS_SQL = 
            "SELECT name, price, category_uid FROM menuitems";
    private static final String INSERT_MENU_ITEM_SQL = "INSERT INTO menuitems " + 
            "(name, price, category_uid) VALUES (?, ?)";
    private static final String INSERT_MENU_CATEGORY_SQL = "INSERT INTO menucategories " +
            "(name, parent_uid) VALUES (?, ?)";
    private static final String GET_UID_BY_NAME_SQL_BASE = "SELECT uid FROM menucategories " +
            "WHERE name="; 
    private static final String GET_MENU_CATEGORIES_SQL = "SELECT * FROM menucategories";
    private Connection connection = null;
    private PreparedStatement pStatement = null;
    private ResultSet resultSet = null;
    
    public MenuItemDAOImpl() {
    }

    @Override
    public List<MenuItem> getMenuItems() throws DAOException {
        //@todo is arraylist the right structure?  Don't wan't to allow repeats.
        List<MenuItem> menuItems = new ArrayList<MenuItem>();    
        try{
            connection = getConnection();
            pStatement = connection.prepareStatement(GET_MENU_ITEMS_SQL);
            resultSet = pStatement.executeQuery();
            while (resultSet.next()){
                MenuItem menuItem = new MenuItem();
                menuItem.setName(resultSet.getString("name"));
                menuItem.setPrice(resultSet.getFloat("price"));
                menuItem.setSubmenuUID(resultSet.getInt("category_uid"));
                menuItems.add(menuItem);
            }
        }catch (SQLException e){
                throw new DAOException("Error getting menu items. " + e.getMessage());
         }finally{
            closeDBObjects(resultSet, pStatement, connection);
          }
        return menuItems;
        }
    

    @Override
    public void insert(MenuItem menuItem) throws DAOException {
        try{
            connection = getConnection();
            pStatement = connection.prepareStatement(INSERT_MENU_ITEM_SQL);
            pStatement.setString(1, menuItem.getName());
            pStatement.setFloat(2, menuItem.getPrice());
            pStatement.setInt(3, menuItem.getSubmenuUID());
            pStatement.execute();
        }catch (SQLException e){
            throw new DAOException("Error adding menu item. " + e.getMessage());
        }finally {
            closeDBObjects(null, pStatement, connection);
        }
    }

    @Override
    public void saveMenuCategory(MenuCategory menuCategory) throws DAOException {
        String parentUIDsql = GET_UID_BY_NAME_SQL_BASE + "\"" + menuCategory.getParent() + "\"";
        try{
            connection = getConnection();
            pStatement = connection.prepareStatement(parentUIDsql);
            resultSet = pStatement.executeQuery();
            resultSet.next();
            int parentUID = resultSet.getInt("uid");
            pStatement = connection.prepareStatement(INSERT_MENU_CATEGORY_SQL);
            pStatement.setString(1, menuCategory.getName());
            pStatement.setInt(2, parentUID);
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
