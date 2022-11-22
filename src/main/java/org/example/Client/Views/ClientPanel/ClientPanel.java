package org.example.Client.Views.ClientPanel;

import org.example.Client.Services.HandleActionEvents;
import org.example.Client.Views.ClientPanel.Components.LoginPanel;
import org.example.Client.Views.ClientPanel.Components.RegisterPanel;

import javax.swing.*;
import java.awt.*;

public class ClientPanel extends JFrame {
    public static RegisterPanel registerPanel;
    public static LoginPanel loginPanel;
    JTabbedPane jTabbedPane;
    public ClientPanel(){

        createWindow();

        jTabbedPane = new JTabbedPane();

        // Components
        registerPanel = new RegisterPanel("name", "password", new HandleActionEvents("register", jTabbedPane));
        loginPanel = new LoginPanel("name", "password", new HandleActionEvents("login", jTabbedPane));

        //JtabbedPane puts each panel to other tab
        // add JTabbedPane

        jTabbedPane.add("Login", loginPanel);
        jTabbedPane.add("Register", registerPanel);

        this.add(jTabbedPane, BorderLayout.CENTER);
        // Add COmponents HERE

        // size of the main frame
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        this.setBounds(dimension.width / 4, dimension.height / 4, 1000, 700);
    }


    public void createWindow(){
        this.setVisible(true);
        this.setTitle("User");
        this.setLayout(new BorderLayout());
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);

    }

}
