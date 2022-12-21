package org.example.Client.Views.AccountPanel;

import org.example.Client.Services.ConnectToDb;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MakeTransactionPanel extends JPanel {
    private TransactionForm transactionForm;
    private Connection conn;
    public MakeTransactionPanel() throws SQLException {
        // the info of the account we will send
        transactionForm = new TransactionForm("account id: ", "amount: ", "your account: " , new MakeTransactionEvents());
        // the info of the account we will send money to
        this.conn = ConnectToDb.getConn();
        this.setLayout(new BorderLayout());
        this.add(transactionForm, BorderLayout.CENTER);
    }

    // All the events that occurs in this panel goes here
    class MakeTransactionEvents extends AbstractAction{
        private int ammountReceiver;
        private int ammountSender;
        public MakeTransactionEvents(){
            putValue(Action.NAME, "Send money");
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            JTextField [] formValues =  transactionForm.getFields();

            // account we want to send money to
            int accountId = Integer.parseInt(formValues[0].getText());
            int amount = Integer.parseInt(formValues[1].getText());
            int currentUserAccountId = Integer.parseInt(formValues[2].getText());

            try {
                if(verifyData(accountId, amount, currentUserAccountId)){
                    boolean isTransactionDone = makeTransaction(accountId, amount, currentUserAccountId);

                    if(isTransactionDone){
                        JOptionPane.showMessageDialog(null, "the transaction has be done succesfully!");
                    }
                }
                else{
                    JOptionPane.showMessageDialog(null , "The data is invalid");
                }

            } catch (SQLException ex) {throw new RuntimeException(ex);}
        }

        private boolean makeTransaction(int accountId, int amount, int currentUserAccountId) throws SQLException {
            // for the receiver
            PreparedStatement stm = conn.prepareStatement("update accounts set amount = ? where account_id = ?;");
            int newReceiverAmount = ammountReceiver + amount;
            stm.setInt(1, newReceiverAmount);
            stm.setInt(2, accountId);

            // for the sender
            PreparedStatement stm2 = conn.prepareStatement("update accounts set amount = ? where account_id = ?;");
            int newSenderAmount = ammountSender - amount;
            stm2.setInt(1, newSenderAmount);
            stm2.setInt(2, currentUserAccountId);

            int row = stm.executeUpdate();
            int row2 = stm2.executeUpdate();

            if(row > 0 && row2 > 0){
                return true;
            }
            else{
                return false;
            }
        }

        private boolean verifyData(int accountId, int amount, int currentUserAccountId) throws SQLException {


                // verify if the account to make the transaction exists
                PreparedStatement stm = conn.prepareStatement("select * from accounts where account_id = ?");
                stm.setInt(1, accountId);
                ResultSet resultSet1 = stm.executeQuery();

                // verify if the account of the user exists
                PreparedStatement stm2 = conn.prepareStatement("select * from accounts where account_id = ?");
                stm2.setInt(1, currentUserAccountId);
                ResultSet resultSet = stm2.executeQuery();

                // verify if the current user account exists and the destinated account, also see if there is money
                if(resultSet.next() && resultSet1.next()){
                    int currentAmount = resultSet.getInt("amount");
                    if(currentAmount >= amount){
                        ammountSender = currentAmount;
                        ammountReceiver = resultSet1.getInt("amount");
                        return true;
                    }
                    else{
                        return false;
                    }
                }
                else{
                    return false;
                }
        }
    }

}
