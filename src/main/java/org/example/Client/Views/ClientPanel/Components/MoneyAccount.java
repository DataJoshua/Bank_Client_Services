package org.example.Client.Views.ClientPanel.Components;

import javax.swing.*;
import java.awt.*;

public class MoneyAccount extends JPanel {
    public MoneyAccount (String name){
        this.setLayout(new FlowLayout());
        this.add(new JLabel("welcome to your account " + name));
    }
}
