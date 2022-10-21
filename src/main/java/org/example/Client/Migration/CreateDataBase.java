package org.example.Client.Migration;

import java.sql.*;

public class CreateDataBase implements  Runnable {
    @Override
    public void run() {
        String url = "jdbc:postgresql://localhost:5432/java";
        try {
            Connection conn = DriverManager.getConnection(url, "postgres","root");
            Statement statement = conn.createStatement();
            String sql = "CREATE TABLE users" +
                    "(" +
                    "user_id int NOT NULL UNIQUE," +
                    "name varchar(250) NOT NULL," +
                    "password varchar(250) NOT NULL UNIQUE," +
                    "CONSTRAINT user_id_pk PRIMARY KEY(user_id)" +
                    ")";
            int rows = statement.executeUpdate(sql);

            if(rows == 0){
                System.out.println("data base users created");
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}