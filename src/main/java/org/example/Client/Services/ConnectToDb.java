package org.example.Client.Services;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectToDb {
    private static Connection conn;

    public static Connection getConn() throws SQLException {
        String url = "jdbc:postgresql://localhost:5432/semester";
        conn = DriverManager.getConnection(url, "postgres", "root");
        return conn;
    }
}
