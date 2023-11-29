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
import model.Order;
import model.OrderDetail;
import model.Storage;

/**
 *
 * @author 84834
 */
public class OrderDetailDAO extends DBContext {

    public int getHotProductID() {
        int id = 0;
        String sql = "SELECT Product_Id FROM OrderDetail GROUP BY Product_Id\n"
                + "HAVING SUM(Quantity) = ( SELECT MAX(TotalQuantity) FROM ( SELECT Product_Id, SUM(Quantity) AS TotalQuantity FROM OrderDetail\n"
                + "GROUP BY Product_Id ) AS Subquery);";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                id = rs.getInt(1);
            }
            ps.close();
            rs.close();
        } catch (SQLException e) {
            System.out.println(e);
        }
        return id;
    }

    public int insertOrderDetail(int Order_Id, int Product_Id, int Quantity) {
        int succes = 0;
        String sql = "Insert into [DURIAN_SHOP].[dbo].[OrderDetail]([Order_Id], [Product_Id], [Quantity]) values (?,?,?)";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, Order_Id);
            ps.setInt(2, Product_Id);
            ps.setInt(3, Quantity);
            succes = ps.executeUpdate();
            ps.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        return succes;
    }

    public ArrayList<OrderDetail> getAllOrderDetail(int orderId) {
        ArrayList<OrderDetail> list = new ArrayList<>();
        String query = "select * from [OrderDetail] where [Order_Id] = " + orderId;
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new OrderDetail(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getInt(4)));
            }
            ps.close();
            rs.close();
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    }

    public int updateQuantity(int detailId, int quantity) {
        int succes = 0;
        String sql = "UPDATE [OrderDetail] SET [Quantity] = ? WHERE Id = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, quantity);
            ps.setInt(2, detailId);
            succes = ps.executeUpdate();
            ps.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        return succes;
    }

    public int deleteDetail(int detailId) {
        int succes = 0;
        String sql = "delete from [OrderDetail] where [Id] = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, detailId);
            succes = ps.executeUpdate();
            ps.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        return succes;
    }

    public int checkOrderDetail(int userId, int productId) {
        int succes = 0;
        String sql = "select Order_Id from [OrderDetail] od join [Order] o on od.[Order_Id] = o.Id where Product_Id = ? and status = 1 and o.[User_Id] = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, productId);
            ps.setInt(2, userId);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                succes = rs.getInt(1);
            }
            ps.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        return succes;
    }

    public static void main(String[] args) {
        OrderDetailDAO dao = new OrderDetailDAO();
        ArrayList<OrderDetail> list = dao.getAllOrderDetail(20);
        for (OrderDetail orderDetail : list) {
            System.out.println(orderDetail);
        }
    }
}
