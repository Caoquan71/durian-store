/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;
/**
 *
 * @author Admin
 */
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Account;

public class AccountDBContext extends DBContext {

    public Account Login (String username, String password) {
        Account account = null;
        
        try {
            String sql = "SELECT * FROM Account WHERE Username = ? AND Password = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, username);
            statement.setString(2, password);
            ResultSet resultSet = statement.executeQuery();
        
            if (resultSet.next()) {
                account = new Account();
                account.setId(resultSet.getInt("Id"));
                account.setUsername(resultSet.getString("Username"));
                account.setPassword(resultSet.getString("Password"));
                account.setRole_id(resultSet.getString("Role_Id"));               
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(AccountDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return account;
    }
}

    



   