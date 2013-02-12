/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.MenuItem;

/**
 *
 * @author Josh
 */
public class MenuItemDAOImpl extends BaseDAO implements MenuItemDAO {
    private static final String GET_MENU_ITEMS_SQL = 
            "SELECT name, price FROM menuitems";
    private static final String INSERT_MENU_ITEM_SQL =
            "INSERT INTO menuitems " + 
            "(name, price) " +
            "VALUES (?, ?)";
    public MenuItemDAOImpl() {
    }

    @Override
    public List<MenuItem> getMenuItems() throws DAOException {
        List<MenuItem> menuItems = new ArrayList<MenuItem>();
        Connection connection = null;
        PreparedStatement pStatement = null;
        ResultSet resultSet = null;
        try{
            connection = getConnection();
            pStatement = connection.prepareStatement(GET_MENU_ITEMS_SQL);
            resultSet = pStatement.executeQuery();
            while (resultSet.next()){
                MenuItem menuItem = new MenuItem();
                menuItem.setName(resultSet.getString("name"));
                menuItem.setPrice(resultSet.getFloat("price"));
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
        Connection connection = null;
        PreparedStatement pStatement = null;
        try{
            connection = getConnection();
            pStatement = connection.prepareStatement(INSERT_MENU_ITEM_SQL);
            pStatement.setString(1, menuItem.getName());
            pStatement.setFloat(2, menuItem.getPrice());
            pStatement.execute();
        }catch (SQLException e){
            throw new DAOException("Error adding menu item. " + e.getMessage());
        }finally {
            closeDBObjects(null, pStatement, connection);
        }
    }
}
