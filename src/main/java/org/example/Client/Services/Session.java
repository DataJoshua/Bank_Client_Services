package org.example.Client.Services;

import lombok.Getter;
import lombok.Setter;
import org.example.Client.Models.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Getter@Setter
public class Session {
    private int current_user_id;

    public Session(int current_user_id){
        this.current_user_id = current_user_id;
    }

    public User getCurrentUser() throws SQLException {
        String sql = "SELECT * FROM users WHERE id = ?";
        PreparedStatement pst = ConnectToDb.getConn().prepareStatement(sql);
        pst.setInt(1, this.current_user_id);

        ResultSet resultSet =  pst.executeQuery();

        if(resultSet.next()){
            int id = resultSet.getInt("user_id");
            String name = resultSet.getString("name");

            return new User(id, name);
        }
        else{
            return null;
        }
    }
}
