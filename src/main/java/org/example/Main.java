package org.example;

import org.example.Client.Migration.CreateDataBase;
import org.example.Client.Views.ClientPanel.ClientPanel;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {
        // create table users
        Thread createTable = new Thread(new CreateDataBase());
        createTable.start();
        new ClientPanel();

    }
}