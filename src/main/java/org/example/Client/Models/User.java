package org.example.Client.Models;

import java.sql.*;

public class User {
    static String URL = "jdbc:postgresql://localhost:5432/java";
    static String USER = "postgres";
    static String PASSWORD = "root";
    private int id;
    private Connection conn;
    private String name;
    private String password;
    public User(String name, String password, int id) throws SQLException {
        this.name = name;
        this.password = password;
        this.conn = DriverManager.getConnection(URL, USER, PASSWORD);
        this.id = id;
    }
    public void save() throws SQLException {

        String sql = "INSERT INTO users (user_id, name, password) VALUES (?, ?, ?)";
        PreparedStatement stm = conn.prepareStatement(sql);
        stm.setInt(1, this.id);
        stm.setString(2,this.name);
        stm.setString(3, this.password);
        int rows = stm.executeUpdate();
        if(rows ==  1){
            System.out.println("user created!");
        }
        else{
            System.out.println("something went growng");
        }
    }
}