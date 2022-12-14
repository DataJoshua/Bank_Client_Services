package org.example.Client.Views.ClientPanel.Components;

import javax.swing.*;
import java.awt.*;


// This class creates a pair of labels, inputs and a button to use

abstract public class InputComboModel extends JPanel {
    protected JTextField field1, field2;
    protected JButton button;
    public InputComboModel(String input1Name, String input2Name, AbstractAction actionToSubscribe) {

        field1 = new JTextField();
        field2 = new JTextField();
        // We set a button using a Abstract action to handle the events
        button = new JButton(actionToSubscribe);
        this.setVisible(true);
        setLayout(new GridLayout(3,0));

        this.add(getBox(input1Name, field1));
        this.add(getBox(input2Name, field2));
        this.add(button);

    }

    // This function creates a box with a label and a field
    protected Box getBox(String label, JTextField field){
        Box box = new Box(2);
        box.add(new JLabel(label));
        box.add(field);
        return box;
    }

    // returns the fields, to be used in the HandleActionEvents and get the values that the user insert
    public JTextField[] getFields(){
        JTextField [] inputsArray = {this.field1, this.field2};
        return inputsArray;
    }
}
