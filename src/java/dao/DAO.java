/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.Connection;

/**
 *
 * @author Josh
 */
public interface DAO {
    Connection getConnection() throws DAOException;
}
