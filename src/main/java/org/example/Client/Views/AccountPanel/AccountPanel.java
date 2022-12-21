package org.example.Client.Views.AccountPanel;

import org.example.Client.Models.Account;
import org.example.Client.Services.ConnectToDb;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.sql.*;
import java.util.ArrayList;
import java.util.Vector;

// This panel will have all the details of the accounts of the current user that has logged in

public class AccountPanel extends JPanel{

    private Connection conn;
    private ArrayList<Account> accountsList;
    private int currentUser;
    private JTable table;
    private CreateAccount createAccountPanel;

    public AccountPanel(int currentUser) throws SQLException {

        this.setVisible(true);
        this.setLayout(new BorderLayout());
        this.conn = ConnectToDb.getConn();
        this.currentUser = currentUser;
        this.accountsList = this.createAccountArray(this.currentUser);
        createTable();
        createJpanelForCreateAccount();

    }

    // this is the panel with the inputs that creates a new account for the current user
    private void createJpanelForCreateAccount(){
        createAccountPanel = new CreateAccount("name: ","amount:", new AccountPanelActions());
        this.add(createAccountPanel, BorderLayout.SOUTH);

    }
    private ArrayList<Account> createAccountArray(int current_user_id) throws SQLException {
        // here we create and add to the MainTabbedPane a new tab called account that just can be seen if the user is logged
        Statement stm = conn.createStatement();
        ResultSet resultSet = stm.executeQuery("SELECT * FROM accounts WHERE user_id = " + current_user_id);
        ArrayList<Account> accountsArray = new ArrayList<>();

        // create a object of Account to each row of the query and then make an array of all of them
        while(resultSet.next()){
            Integer account_id = resultSet.getInt("account_id");
            String name = resultSet.getString("name");
            Integer amount = resultSet.getInt("amount");
            Integer user_id = resultSet.getInt("user_id");

            Account account = new Account(account_id, name, amount, user_id);
            accountsArray.add(account);
        }
        return accountsArray;
    }
    private void createTable(){

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
        table.setEnabled(false);
        JScrollPane scrollPane = new JScrollPane(this.table);
        this.add(scrollPane, BorderLayout.CENTER);
    }

    public void setAccountsList(ArrayList<Account> accountsList){
        this.accountsList = accountsList;
        createTable();
    }

    class AccountPanelActions extends AbstractAction{

        public AccountPanelActions(){
            this.putValue(Action.NAME, "Create Account!");
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            // this method creates a new account and save it in the database
            // then it removes the table from the component and re renders a new one
            // fetching all the accounts including the new one

            try {
               if(createAccount()){

                   remove(table);
                   setAccountsList(createAccountArray(currentUser));
                   revalidate();
                   repaint();
               }

            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        }

        public boolean createAccount() throws SQLException {
            // create new account
            JTextField [] fields =  createAccountPanel.getFields();

            String name = fields[0].getText();
            int amount = Integer.parseInt(fields[1].getText());

            String sql = "insert into accounts (account_id, name, amount, user_id) values (?, ?, ?, ?)";
            String sqlForId = "select nextval('accounts_account_id_seq')";

            // getting the seq for id
            Statement stm = conn.createStatement();
            ResultSet resultSetId = stm.executeQuery(sqlForId);
            resultSetId.next();
            int id = resultSetId.getInt("nextval");

            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setInt(1,id);
            pst.setString(2, name);
            pst.setInt(3, amount);
            pst.setInt(4, currentUser);

            int row = pst.executeUpdate();

            boolean res = false;

            if(row > 0){
                res = true;
                System.out.println("Account created succesfully");
            }

            return res;
        }
    }

}
