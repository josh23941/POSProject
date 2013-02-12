/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

/**
 *
 * @author Josh
 */
public class DataSourceCache {
    private static DataSourceCache instance;
    private DataSource dataSource;
    static{
        instance = new DataSourceCache();
    }
    
    private DataSourceCache(){
        Context context = null;
        try{
            context = new InitialContext();
            dataSource = (DataSource)context.lookup(
                    "java:comp/env/jdbc/POSDataSource");
        }catch (NamingException e){}
    }
    
    public static DataSourceCache getInstance(){
        return instance;
    }
    
    public DataSource getDataSource(){
        return dataSource;
    }
}
