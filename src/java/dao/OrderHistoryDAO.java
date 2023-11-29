/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.OrderHistory;

/**
 *
 * @author kienk
 */
public class OrderHistoryDAO extends DBContext {
    public List<OrderHistory> getListOrderHistoryByOrderId(int orderId){
        List<OrderHistory> od = new ArrayList<>();
        String query = "SELECT * FROM orderHistory"
                + " Where order_id = ?";
        try{
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, orderId);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                od.add(new OrderHistory(rs.getInt(1), rs.getInt(2), rs.getDate(3), rs.getString(4)));
            }
            ps.close();
            rs.close();
        }catch(SQLException e){
            System.out.println(e);
        }
        return od;
    }
    
    public int createOrderHistory(int orderId, String description){
        int success = 0;
        String query = "INSERT INTO orderHistory ([order_id], [date], [description]) "
                + " values (?, GETDATE(), ?)";
        
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, orderId);
            ps.setString(2, description);
            success = ps.executeUpdate();
            ps.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        return success;
        
    }
    
    public OrderHistory orderHistoryIsExisted(int orderId, String description){
        String query = "SELECT * FROM orderHistory"
                + " Where order_id = ? and description = ?";
        try{
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, orderId);
            ps.setString(2, description);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                return new OrderHistory(rs.getInt(1), rs.getInt(2), rs.getDate(3), rs.getString(4));
            }
            ps.close();
            rs.close();
        }catch(SQLException e){
            System.out.println(e);
        }
        return null;
        
    }
}
