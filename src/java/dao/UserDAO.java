/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import model.User;

/**
 *
 * @author 84834
 */
public class UserDAO extends DBContext{
    public int createUser(int id, String phone, String name){
        int succes = 0;
        String sql = "insert into [DURIAN_SHOP].[dbo].[User](Id,Fullname,Phone) values (?, ?, ?);";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            ps.setString(2, name);            
            ps.setString(3, phone);
            System.out.println(sql);
            succes = ps.executeUpdate();
            ps.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        return succes;
    }
    
    public User getUserById(int id){
        String query = "Select * from [DURIAN_SHOP].[dbo].[User]"
                + " Where [id] = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                return new User(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getBoolean(5));
            }
            ps.close();
            rs.close();
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }
    
    
    public void updateUser(int id,String name, String phone, String address, String gender){
        String query = "UPDATE [DURIAN_SHOP].[dbo].[User] " +
                        " SET fullName = ?, phone = ?, address = ?";
                        
        if (!gender.equals("#")){
            query+= ", gender = ? ";
        }
        query+=" WHERE id = ? ";
        try{
            PreparedStatement ps = connection.prepareStatement(query);
            int index = 1;
            ps.setString(index++, name);
            ps.setString(index++, phone);
            ps.setString(index++, address);
            if (!gender.equals("#")){
                ps.setString(index++, gender);
            }
            ps.setInt(index++, id);
            ps.executeUpdate();
        }catch(SQLException e){
            System.out.println(e);
        } 
    }
    
    public HashMap<Integer, String> getUserName(){
        HashMap<Integer, String> map = new HashMap<>();
        String query = "Select * from [DURIAN_SHOP].[dbo].[User]";
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                map.put(rs.getInt(1), rs.getString(2));
            }
            ps.close();
            rs.close();
        } catch (SQLException e) {
            System.out.println(e);
        }
        return map;
    }
}
