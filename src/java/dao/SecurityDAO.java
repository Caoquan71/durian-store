/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import model.Security;

/**
 *
 * @author 84834
 */
public class SecurityDAO extends DBContext {

    public int insertSecurity(int question_id, int account_id, String answer) {
        int succes = 0;
        String sql = "Insert into [DURIAN_SHOP].[dbo].[Security]([Question_Id], [Account_Id], [Answer]) values (?,?,?);";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, question_id);
            ps.setInt(2, account_id);
            ps.setString(3, answer);
            System.out.println(sql);
            succes = ps.executeUpdate();
            System.out.println(succes);
            ps.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        return succes;
    }
    
    public Security getQuestAnswer (int Account_ID){
        Security infor = new Security();
        String sql = "SELECT * FROM [Security] WHERE Account_Id = " + Account_ID;
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                infor = new Security(rs.getInt(1), rs.getInt(2), rs.getString(3));
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
            System.out.println(e);
        }
        return infor;
    }
}
