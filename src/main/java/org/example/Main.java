package org.example;

import org.example.Client.Migration.CreateDataBase;
import org.example.Client.Models.User;
import org.example.Client.Views.MainPanel.MainPanel;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {
        // create table users
        Thread createTable = new Thread(new CreateDataBase());
        //createTable.start();
        new MainPanel();
        User yo = new User("joshua", "123", 2);
        yo.save();
    }
}