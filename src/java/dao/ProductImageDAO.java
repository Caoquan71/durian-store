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
import model.ProductImage;

/**
 *
 * @author kienk
 */
public class ProductImageDAO extends DBContext {

    public List<ProductImage> getALlListImage() {
        List<ProductImage> pImage = new ArrayList<>();
        String query = "Select * from ProductImage ";
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                pImage.add(new ProductImage(rs.getInt(1), rs.getString(2),
                        rs.getInt(3), rs.getInt(4)));
            }
            ps.close();
            rs.close();
        } catch (SQLException e) {
            System.out.println(e);
        }
        return pImage;
    }

    public ProductImage getProductImage(String id) {
        ProductImage pImage = new ProductImage();
        String query = "Select * from ProductImage where id = " + id;
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                pImage = new ProductImage(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getInt(4));
            }
            ps.close();
            rs.close();
        } catch (SQLException e) {
            System.out.println(e);
        }
        return pImage;
    }

    public int createImage(ProductImage img) {
        int success = 0;
        String query = "Insert Into [ProductImage]([Url],[Product_Id],[Color_Id])"
                + " values ('" + img.getUrl() + "', '" + img.getProductId() + "', '" + img.getColorId() + "')";
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            success = ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            System.out.println(e);
        }
        return success;
    }

    public ProductImage getImagebyProductId(String id) {
        ProductImage pImage = new ProductImage();
        String query = "Select * from ProductImage where Product_Id = " + id;
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                pImage = new ProductImage(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getInt(4));
            }
            ps.close();
            rs.close();
        } catch (SQLException e) {
            System.out.println(e);
        }
        return pImage;
    }

    public int deleteImg(String id) {
        int success = 0;
        String query = "DELETE FROM [ProductImage] WHERE id = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, id);
            success = ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            System.out.println(e);
        }
        return success;
    }

    public static void main(String[] args) {
        ProductImageDAO d = new ProductImageDAO();
        ProductImage p = new ProductImage(0, "hello", 138, 6);
        d.createImage(p);
    }
}
