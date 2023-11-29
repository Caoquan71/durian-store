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
import model.Storage;

/**
 *
 * @author 84834
 */
public class StorageDAO extends DBContext{
    public Map<Integer, String> getHashMapStorage(){
        Map<Integer, String> hashMap = new HashMap<>();
        String query = "select Id, Storage_Size from Storage";
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
    
    public List<Storage> getAllStorage(){
        List<Storage> list = new ArrayList<>();
          String query = "select * from Storage";
        try{
            PreparedStatement ps = connection.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                list.add(new Storage(rs.getInt(1), rs.getString(2), rs.getFloat(3), rs.getInt(4)));
            }
            ps.close();
            rs.close();
        }catch(SQLException e){
            System.out.println(e);
        }
        return list;
    }
    
    public int createStorage(Storage storage) {
        int success = 0;
        String query = "Insert Into [Storage]([storage_size],[price_bonus],[status])"
                + " values ('" + storage.getStorageSize() + "', " + storage.getPriceBonus() +  ", 1)";
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            success = ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            System.out.println(e);
        }
        return success;
    }
    
    public Storage getStoragebyId(String id){
        String query = "SELECT * FROM storage"
                + " Where id = ?";
        try{
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, id);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                return new Storage(rs.getInt(1), rs.getString(2), rs.getFloat(3), rs.getInt(4));
            }
            ps.close();
            rs.close();
        }catch(SQLException e){
            System.out.println(e);
        }
        return null;
    }
    
    public int updateStorage(String id, String size, float priceBonus){
        int succes = 0;
        String query = "UPDATE [Storage] SET " +
                                       " storage_size= '" + size +
                                        "', price_bonus = " + priceBonus +
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
    public int updateStorage(String id, String status){
        int succes = 0;
        String query = "UPDATE [Storage] SET " +
                                        "[status] = " + status +
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
}