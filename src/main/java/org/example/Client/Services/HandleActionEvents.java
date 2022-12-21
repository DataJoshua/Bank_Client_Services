package org.example.Client.Services;

import org.example.Client.Models.Account;
import org.example.Client.Views.AccountPanel.AccountPanel;
import org.example.Client.Views.ClientPanel.ClientPanel;
import org.springframework.security.crypto.bcrypt.BCrypt;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.sql.*;
import java.util.ArrayList;

public class HandleActionEvents extends AbstractAction {
    private JTabbedPane jTabbedPane;
    private Connection conn;
    public HandleActionEvents(String name, JTabbedPane jTabbedPane){
        putValue(Action.NAME, name);
        this.jTabbedPane = jTabbedPane;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String buttonName = (String)getValue(Action.NAME);
        switch (buttonName){
            case "register":
                insertUserToDatabase();
                break;
            case "login":
                userAutentication();
                break;
        }
    }

    public void userAutentication() {
        // get the values of the loginPanel (USER INPUT)
        JTextField[] loginfields = ClientPanel.loginPanel.getFields();
        String loginName = loginfields[0].getText();
        String loginPwd = loginfields[1].getText();

        try{
            conn = ConnectToDb.getConn();
            PreparedStatement stm = conn.prepareStatement("SELECT * FROM users WHERE name = ?");
            stm.setString(1, loginName);
            ResultSet resultSet =  stm.executeQuery();

            if(resultSet.next()){
                String passwordHash = resultSet.getString("password_digest");
                if(BCrypt.checkpw(loginPwd, passwordHash)){

                    // get the id from the result set of user and set it as current_user
                    int id = resultSet.getInt("user_id");

                    JOptionPane.showMessageDialog(null, "You logged in!");

                    System.out.println("you logged in!!");

                    // CREATE SESSION

                    Session session = new Session(id);


                    // we eliminate the login tab IFF the name and password was correct
                    jTabbedPane.removeAll();
                    //ArrayList<Account> acountArray =  createAccountArray(session.getCurrent_user_id());

                    // create a new Acount component and add it to the JTabbedPane

                    AccountPanel accountPanel =  new AccountPanel(session.getCurrent_user_id());

                    jTabbedPane.addTab("account", accountPanel);

                }
                else{
                    JOptionPane.showMessageDialog(null, "the password is wrong!");
                    System.out.println("your password is bad");
                }
            }
            else{
                JOptionPane.showMessageDialog(null,"The username is bad");
                System.out.println("your username is bad");
            }

        }catch (SQLException e){
            System.out.println(e);
        }
    }

    private void insertUserToDatabase(){
        JTextField[] registerfields = ClientPanel.registerPanel.getFields();
        try {
            conn = ConnectToDb.getConn();
            String name = registerfields[0].getText();
            String pasword = registerfields[1].getText();

            String password_digest = BCrypt.hashpw(pasword, BCrypt.gensalt(12));

            Statement getId = conn.createStatement();
            ResultSet resultSet = getId.executeQuery("SELECT nextval('users_user_id_seq')");
            resultSet.next();
            int id = resultSet.getInt("nextval");

            PreparedStatement stm = conn.prepareStatement("INSERT INTO users (user_id, name, password_digest) VALUES (?, ?, ?)");
            stm.setInt(1, id);
            stm.setString(2,name);
            stm.setString(3, password_digest);

            int rows = stm.executeUpdate();

            if(rows > 0){
                System.out.println("user registered");
            }
            else{
                System.out.println("user not registered");
            }

        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }
}
