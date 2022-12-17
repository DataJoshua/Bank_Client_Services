package org.example.Client.Views.AccountPanel;

import org.example.Client.Models.Account;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Vector;

// This panel will have all the details of the accounts of the current user that has logged in

public class AccountPanel extends JPanel{

    private ArrayList<Account> accountsList;
    private JTable table;

    public AccountPanel(ArrayList<Account> accountsList){
        this.accountsList = accountsList;
        this.setVisible(true);
        this.setLayout(new BorderLayout());
        createTable();

        JLabel label = new JLabel("This is the account panel: ");
        this.add(label, BorderLayout.NORTH);
    }

    public void createTable(){

        Vector<Vector<String>> data = new Vector<>();
        Vector<String> headers = new Vector<>();
        headers.add("Account ID");
        headers.add("Name");
        headers.add("Amount");

        for(Account a: this.accountsList){
            Vector<String> row = new Vector<>();
            row.add(a.getId()+"");
            row.add(a.getName());
            row.add(a.getAmount()+"");
            data.add(row);
        }

        this.table = new JTable(data, headers);

        this.add(this.table, BorderLayout.CENTER);
    }
}
