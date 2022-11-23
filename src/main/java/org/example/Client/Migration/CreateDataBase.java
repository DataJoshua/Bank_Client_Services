package org.example.Client.Migration;

import java.sql.*;

public class CreateDataBase implements  Runnable {
    @Override
    public void run() {
        // This thread just create a new table with the especifications for the User model
        String url = "jdbc:postgresql://localhost:5432/semester";
        try {
            Connection conn = DriverManager.getConnection(url, "postgres","root");
            Statement statement = conn.createStatement();
            String sql = "CREATE TABLE IF NOT EXISTS users" +
                    "(" +
                    "user_id serial NOT NULL UNIQUE," +
                    "name varchar(250) NOT NULL," +
                    "password_digest varchar(250) NOT NULL UNIQUE," +
                    "CONSTRAINT user_id_pk PRIMARY KEY(user_id)" +
                    ");" +
                    "CREATE TABLE IF NOT EXISTS accounts(" +
                    "account_id serial NOT NULL," +
                    "name varchar(255) NOT NULL," +
                    "amount int DEFAULT 0," +
                    "user_id bigint references users(user_id)" +
                    ");";
            int rows = statement.executeUpdate(sql);

            if(rows == 0){
                System.out.println("data base users created");
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}