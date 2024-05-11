package org.jan.apiservlet.webapp.headers.util;

import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionBaseDatosPool {
    private static String url = "jdbc:mysql://localhost:33008/";
    private static String username = "reales";
    private static String password = "123";
    private static BasicDataSource pool;

    public static BasicDataSource getInstance() throws SQLException {
        if (pool == null) {
            pool = new BasicDataSource();
            pool.setUrl(url);
            pool.setUsername(username);
            pool.setPassword(password);
            pool.setInitialSize(3);
            pool.setMinIdle(3);
            pool.setMaxIdle(8);
            pool.setMaxTotal(8);

        }
        return pool;
    }
    public static Connection getConnection() throws SQLException{
        return getInstance().getConnection();
    }
}