package com.starlingbank.company.persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Database {
    private static final String DATABASE_URL = "jdbc:postgresql://localhost:5432/training";
    private final Connection conn;

    public Database(){
        Properties props = new Properties();
//        props.setProperty("user", "jess.dam");
        try {
            conn = DriverManager.getConnection(DATABASE_URL, props);
        } catch (SQLException e){
            throw new IllegalStateException("Failed to initiate database conenction ", e);
        }
    }

    public Connection getConnection(){
        return conn;
    }

    public static Connection getNewConnection(){
        try {
            return DriverManager.getConnection(DATABASE_URL);
        } catch (SQLException e){
            throw new IllegalStateException("Failed to initiate database connection ", e);
        }
    }


}
