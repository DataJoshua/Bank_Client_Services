package org.example.Client.Views.AccountPanel;

import org.example.Client.Views.ClientPanel.Components.InputComboModel;

import javax.swing.*;
import java.awt.*;

public class TransactionForm extends InputComboModel {

    private JTextField field3;
    public TransactionForm(String input1Name, String input2Name, String input3Name, AbstractAction actionToSubscribe) {
        super(input1Name, input2Name, actionToSubscribe);
        setLayout(new GridLayout(4, 0));
        field3 = new JTextField();
        remove(button);
        super.add(getBox(input3Name, field3));
        add(button);
    }

    public JTextField[] getFields(){
        JTextField [] inputsArray = {field1, field2, field3};
        return inputsArray;
    }
}
