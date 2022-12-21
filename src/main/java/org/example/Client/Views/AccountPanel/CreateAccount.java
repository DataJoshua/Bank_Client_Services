package org.example.Client.Views.AccountPanel;

import org.example.Client.Views.ClientPanel.Components.InputComboModel;

import javax.swing.*;

// this extends the basic combo box layout, that give two inputs with the labels and a button with an action
public class CreateAccount extends InputComboModel{

    public CreateAccount(String input1Name, String input2Name, AbstractAction actionToSubscribe) {
        super(input1Name, input2Name, actionToSubscribe);
    }
}
