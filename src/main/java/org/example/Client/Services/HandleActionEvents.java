package org.example.Client.Services;

import org.example.Client.Views.MainPanel.Components.LoginPanel;
import org.example.Client.Views.MainPanel.MainPanel;
import org.example.Main;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class HandleActionEvents extends AbstractAction {
    private JTabbedPane jTabbedPane;
    public HandleActionEvents(String name, JTabbedPane jTabbedPane){
        putValue(Action.NAME, name);
        this.jTabbedPane = jTabbedPane;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String buttonName = (String)getValue(Action.NAME);
        switch (buttonName){
            case "register":
                JTextField[] registerfields = MainPanel.registerPanel.getFields();
                System.out.println(registerfields[0].getText());
                System.out.println(registerfields[1].getText());
                break;
            case "login":
                userAutentication();
                break;
        }
    }

    public void userAutentication(){

        // Get the name and password from the DB
        String user = "joshua";
        String pwd = "123";

        // get the values of the loginPanel (USER INPUT)
        JTextField[] loginfields = MainPanel.loginPanel.getFields();
        String loginName = loginfields[0].getText();
        String loginPwd = loginfields[1].getText();

        // we eliminate the login tab IFF the name and password was correct
        if(user.equals(loginName) && pwd.equals(loginPwd)){
            jTabbedPane.remove(MainPanel.loginPanel);
            JOptionPane.showMessageDialog(null, "Wellcome !" + user);
        }
    }
}
