/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import model.ProductVariantInfomation;

/**
 *
 * @author 84834
 */
public class ProductVariantInformationDAO extends DBContext{
    public ProductVariantInfomation getDetailInformation(int variantId){
        ProductVariantInfomation list = null;
        String query = "select p.Id, p.[Name], p.[Screen], p.[Camera], p.Ram, p.Pin, p.Chipset, p.Screen_resolution, p.img, v.Color_Id, v.Storage_Id, v.Quantity, v.Variant_Price, v.[Status], v.Sale_id\n" +
                "from Product p join ProductVariant v on p.Id = v.Product_Id where v.Id = " + variantId;
        try{
            PreparedStatement ps = connection.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                list = new ProductVariantInfomation(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), 
                        rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9), rs.getString(10), rs.getString(11), rs.getInt(12), rs.getFloat(13),  rs.getInt(14), rs.getFloat(15));
            }
            ps.close();
            rs.close();
        }catch(SQLException e){
            System.out.println(e);
        }
        return list;
    }
    
    public static void main(String[] args) {
        ProductVariantInformationDAO d = new ProductVariantInformationDAO();
        ProductVariantInfomation test = d.getDetailInformation(2);
        System.out.println(test);
//        List<Storage> s = d.getAllStorageList();
//        for (Storage storage : s) {
//            System.out.println(storage.getStorageSize());
//        }
        
    }  
}