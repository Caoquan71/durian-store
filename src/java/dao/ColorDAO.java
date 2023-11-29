/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import model.Color;

/**
 *
 * @author 84834
 */
public class ColorDAO extends DBContext {
    public Map<Integer, String> getHashMapColor(){
        Map<Integer, String> hashMap = new HashMap<>();
        String query = "select Id, Color from [Color]";
        try{
            PreparedStatement ps = connection.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                hashMap.put(rs.getInt(1), rs.getString(2));
            }
            ps.close();
            rs.close();
        }catch(SQLException e){
            System.out.println(e);
        }
        return hashMap;
    }
    
    public List<Color> getAllColor(){
        List<Color> list = new ArrayList<>();
         String query = "select * from [Color]";
        try{
            PreparedStatement ps = connection.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                list.add(new Color(rs.getInt(1), rs.getString(2), rs.getFloat(3), rs.getString(4), rs.getInt(5)));
            }
            ps.close();
            rs.close();
        }catch(SQLException e){
            System.out.println(e);
        }
        return list;
    }
    
      public Color getColorbyId(int id){
        String query = "SELECT * FROM color"
                + " Where id = ?";
        try{
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                return new Color(rs.getInt(1), rs.getString(2), rs.getFloat(3), rs.getString(4), rs.getInt(5));
            }
            ps.close();
            rs.close();
        }catch(SQLException e){
            System.out.println(e);
        }
        return null;
    }
    
     public int updateColor(String id, String status){
        int succes = 0;
        String query = "UPDATE [Color] SET " +
                                       "status = " + status +
                                       " where id = " + id;
          try{
            PreparedStatement ps = connection.prepareStatement(query);
            succes = ps.executeUpdate();
            ps.close();
        }catch(SQLException e){
            System.out.println(e);
        }
          return succes;
    }  
     
     public int updateColor(String id, String color, String hex, float priceBonus){
        int succes = 0;
        String query = "UPDATE [Color] SET " +
                                       " color= '" + color +
                                        "', price_bonus = " + priceBonus +
                                        ", hex_code = '" + hex+
                                       "' where id = " + id;
          try{
            PreparedStatement ps = connection.prepareStatement(query);
            succes = ps.executeUpdate();
            ps.close();
        }catch(SQLException e){
            System.out.println(e);
        }
          return succes;
    } 
     
     public int createColor(Color color) {
        int success = 0;
        String query = "Insert Into [Color]([color],[price_bonus],[hex_code], [status])"
                + " values ('" + color.getName() + "', " + color.getPriceBonus() + ", '" + color.getHexCode() + "', 1)";
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            success = ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            System.out.println(e);
        }
        return success;
    }
     
     public static void main(String[] args) {
        ColorDAO c = new ColorDAO();
        c.updateColor("13", "Yellow", "#ffff00", 100);
    }
}