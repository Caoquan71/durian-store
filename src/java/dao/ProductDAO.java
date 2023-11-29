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
import model.Product;

/**
 *
 * @author kienk
 */
public class ProductDAO extends DBContext {
    public List<Product> getProductList(){
        List<Product> productList = new ArrayList<>();
        String query = "SELECT * FROM product";
        try{
            PreparedStatement ps = connection.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                productList.add(new Product(rs.getInt(1), rs.getString(2), rs.getFloat(3),
                        rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7),
                rs.getString(8),rs.getString(9),rs.getString(10), rs.getString(11), rs.getInt(12)));
            }
            ps.close();
            rs.close();
        }catch(SQLException e){
            System.out.println(e);
        }
        return productList;
    }
    
     public Map<Integer, String> getHashMapProduct(){
        Map<Integer, String> hashMap = new HashMap<>();
        String query = "select Id, [Name] from [Product]";
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
    
    public Product getProductbyId(String id){
        String query = "SELECT * FROM product"
                + " Where product.id = ?";
        try{
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, id);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                return new Product(rs.getInt(1), rs.getString(2), rs.getFloat(3),
                        rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7),
                rs.getString(8),rs.getString(9),rs.getString(10), rs.getString(11), rs.getInt(12));
            }
            ps.close();
            rs.close();
        }catch(SQLException e){
            System.out.println(e);
        }
        return null;
    }
    public Product getProductByID(int id){
        Product product = new Product();
        String query = "SELECT * FROM product where id = " + id;
        try{
            PreparedStatement ps = connection.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                product = new Product(rs.getInt(1), rs.getString(2), rs.getFloat(3),
                        rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7),
                rs.getString(8),rs.getString(9),rs.getString(10), rs.getString(11), rs.getInt(12));
            }
            ps.close();
            rs.close();
        }catch(SQLException e){
            System.out.println(e);
        }
        return product;
    }
    
    public int updateProduct(int id, Product pro){
        int succes = 0;
        String query = "UPDATE [Product] SET Name = '" + pro.getName() + 
                                       "', Price = " + pro.getPrice() +
                                       ", Description = '" + pro.getDescription() +
                                       "', Screen = '" + pro.getScreen() +
                                       "', Camera = '" + pro.getCamera() +
                                       "', Ram = '" + pro.getRam() +
                                       "', Pin = '" + pro.getPin() +
                                       "', Chipset = '" + pro.getChipset() +
                                       "', Screen_resolution = '" + pro.getScreenResolution() +
                                       "', img = '" + pro.getImg() +
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
    
    public int updateProduct(String id, String status){
        int succes = 0;
        String query = "UPDATE [Product] SET " +
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
    
    public int deleteProdct (String id){
        int succes = 0;
         String sql = "Delete FROM [Product] WHERE Id LIKE  '" + id + "'";
         try{
            PreparedStatement ps = connection.prepareStatement(sql);
            succes = ps.executeUpdate();
            ps.close();
        }catch(SQLException e){
            System.out.println(e);
        }
         return succes;
    }
    
    public int createProduct(Product product){
        int succes = 0;
         String sql = "INSERT INTO [Product]([Name], [Price], [Description], [Screen], [Camera], [Ram], [Pin], [Chipset], [Screen_resolution], [img])" +
                        "values(' " + product.getName() + "', " + product.getPrice() + ", '" + product.getDescription() + "', '" + product.getScreen() +
                        "', '" + product.getCamera() + "', '" + product.getRam() + "', '" + product.getPin() + "', '" + product.getChipset() + "', '" + 
                        product.getScreenResolution() + "', '" + product.getImg() + "')";
         try{
            PreparedStatement ps = connection.prepareStatement(sql);
            succes =  ps.executeUpdate();
            ps.close();
        }catch(SQLException e){
            System.out.println(e);
        }
         return succes;
    }
    
    public double getPriceByID(String id){
        double price = -1;
        String query = "  select [Price] from [DURIAN_SHOP].[dbo].[Product]"
                + " Where product.id = ?";
        try{
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, id);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                price = rs.getDouble(1);
            }
            ps.close();
            rs.close();
        }catch(SQLException e){
            System.out.println(e);
        }
        return price;
    }
    
    
}
