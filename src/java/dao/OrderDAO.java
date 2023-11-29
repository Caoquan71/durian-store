package dao;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import model.Order;
import model.Status;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author 84834
 */
public class OrderDAO extends DBContext {

    public List<Order> getAllOrder() {
        List<Order> listOrder = new ArrayList<>();
        String sql = "select * from [Order] ";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Order order = new Order(rs.getInt(1), rs.getInt(2), rs.getFloat(3), rs.getDate(4), rs.getInt(5), rs.getNString(6), rs.getNString(7), rs.getString(8));
                listOrder.add(order);
            }
            ps.close();
            rs.close();
        } catch (SQLException e) {
            System.out.println(e);
        }
        return listOrder;
    }

    public List<Order> getOrderList(int userId, String status) {
        List<Order> listOrder = new ArrayList<>();
        String sql = "select * from [Order] where [user_id] = ? ";
        if (!status.equals("1")) {
            sql += " and [status] = " + status;
        } else {
            sql += " and [status] != 1";
        }
        sql += " order by id desc";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, userId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Order order = new Order(rs.getInt(1), rs.getInt(2), rs.getFloat(3), rs.getDate(4), rs.getInt(5), rs.getNString(6), rs.getNString(7), rs.getString(8));
                listOrder.add(order);
            }
            ps.close();
            rs.close();
        } catch (SQLException e) {
            System.out.println(e);
        }
        return listOrder;
    }

    public int getTotalOrder() {
        String query = "select count(*) from [order] where status !=1";
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getInt(1);
            }
            ps.close();
            rs.close();
        } catch (SQLException e) {
            System.out.println(e);
        }
        return 0;
    }

    public List<Order> getRecentOrder() {
        List<Order> listOrder = new ArrayList<>();
        String sql = "SELECT TOP (5) [Id]\n"
                + "      ,[User_Id]\n"
                + "      ,[Total_Price]\n"
                + "      ,[Created_Date]\n"
                + "      ,[status]\n"
                + "      ,[Name]\n"
                + "      ,[Address]\n"
                + "      ,[Phone]\n"
                + "  FROM [DURIAN_SHOP].[dbo].[Order] order by id desc ";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Order order = new Order(rs.getInt(1), rs.getInt(2), rs.getFloat(3), rs.getDate(4), rs.getInt(5), rs.getNString(6), rs.getNString(7), rs.getString(8));
                listOrder.add(order);
            }
            ps.close();
            rs.close();
        } catch (SQLException e) {
            System.out.println(e);
        }
        return listOrder;
    }

    public List<Status> getAllStatus() {
        List<Status> list = new ArrayList<>();
        String sql = "select * from [Status]";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Status(rs.getInt(1), rs.getString(2)));
            }
            ps.close();
            rs.close();
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    }

    public float TotalPriceYear(int year) {
        int total = 0;
        String sql = "select Total_Price from [Order] where YEAR(Created_Date) =" + year;
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                total += rs.getFloat(3);
            }
            ps.close();
            rs.close();
        } catch (SQLException e) {
            System.out.println(e);
        }
        return total;
    }

    public float TotalPriceMonth(int year, Month month) {
        int total = 0;
        String sql = "select * from [Order] where YEAR(Created_Date) =" + year + "and MONTH(Created_Date) = " + month;
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                total += rs.getFloat(3);
            }
            ps.close();
            rs.close();
        } catch (SQLException e) {
            System.out.println(e);
        }
        return total;
    }

    //Check xem id do co order nao con dang ton tai hay khong
    public int checkOrder(int userId) {
        int exist = 0;
        String sql = "select Id from [Order] where [status] = 1 and [User_Id] = " + userId;
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                exist = rs.getInt(1);
            }
            ps.close();
            rs.close();
        } catch (SQLException e) {
            System.out.println(e);
        }
        return exist;
    }

    public int createOrder(int User_id, double Total_Price) {
        int succes = 0;
        String sql = "Insert into [DURIAN_SHOP].[dbo].[Order]([User_Id], [Total_Price], [Created_Date],[Status]) values (?,?,(SELECT GETDATE()),1);";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, User_id);
            ps.setDouble(2, Total_Price);
            succes = ps.executeUpdate();
            ps.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        return succes;
    }

    public Order getOrderById(int User_Id) {
        ArrayList<Order> listOrder = new ArrayList<>();
        Order result = null;
        String sql = "select * from [Order] where  [Order].[User_Id] = " + User_Id;
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Order order = new Order(rs.getInt(1), rs.getInt(2), rs.getFloat(3), rs.getDate(4), rs.getInt(5), rs.getNString(6), rs.getNString(7), rs.getString(8));
                listOrder.add(order);
            }
            ps.close();
            rs.close();
        } catch (SQLException e) {
            System.out.println(e);
        }
        result = listOrder.get(listOrder.size() - 1);
        return result;
    }

    public Order getOrderByOrderId(int id) {
        ArrayList<Order> listOrder = new ArrayList<>();
        Order result = null;
        String sql = "select * from [Order] where  [Order].[Id] = " + id;
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Order order = new Order(rs.getInt(1), rs.getInt(2), rs.getFloat(3), rs.getDate(4), rs.getInt(5), rs.getNString(6), rs.getNString(7), rs.getString(8));
                listOrder.add(order);
            }
            ps.close();
            rs.close();
        } catch (SQLException e) {
            System.out.println(e);
        }
        result = listOrder.get(listOrder.size() - 1);
        return result;
    }

    //check status cua order, neu la 1 thi moi gui len Checkout.
    public int checkStatus(int orderId) {
        int status = 0;
        String sql = "select [status] from [Order] where [Id] = " + orderId;
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                status = rs.getInt(1);
            }
            ps.close();
            rs.close();
        } catch (SQLException e) {
            System.out.println(e);
        }
        return status;
    }

    public int updateTotalPrice(int id, double totalPrice) {
        int succes = 0;
        String sql = "UPDATE [Order] SET [Total_Price] = ? WHERE Id = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setDouble(1, totalPrice);
            ps.setInt(2, id);
            succes = ps.executeUpdate();
            ps.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        return succes;
    }

    public int updateDate(int id) {
        int succes = 0;
        String sql = "update [Order] set [Created_Date] = (SELECT CAST(GETDATE() AS DATE) AS CurrentDate) where Id = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            succes = ps.executeUpdate();
            ps.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        return succes;
    }

    public int updateStatus(int id, int status) {
        int succes = 0;
        String sql = "update [Order] set [status] = ? where Id = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, status);
            ps.setInt(2, id);
            succes = ps.executeUpdate();
            ps.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        return succes;
    }

    public int updateInformation(int id, String address, String name, String phone) {
        int succes = 0;
        String sql = "update [Order] set [Address] = ?, [Name] = ?, [Phone] = ? where Id = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setNString(1, address);
            ps.setNString(2, name);
            ps.setString(3, phone);
            ps.setInt(4, id);
            succes = ps.executeUpdate();
            ps.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        return succes;
    }
}
